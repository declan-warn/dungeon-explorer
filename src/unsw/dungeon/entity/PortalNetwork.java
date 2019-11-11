package unsw.dungeon.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import unsw.dungeon.event.EntityReachedPortalEvent;
import unsw.dungeon.event.EventListener;

import java.util.List;

public class PortalNetwork implements EventListener {

	private Map<Integer, List<Portal>> portals;
	
	public PortalNetwork() {
		this.portals = new HashMap<>();
	}
	
	public void register(Portal portal) {
		this.portals.putIfAbsent(portal.getId(), new ArrayList<>());
		this.portals.get(portal.getId()).add(portal);
	}
	
	private Portal getLink(Portal portal) {
		List<Portal> linked = this.portals.get(portal.getId());
		int pos = linked.indexOf(portal);
		int size = linked.size();
		
		return linked.get((pos + 1) % size);
	}
	
	@Override
	public void handle(EntityReachedPortalEvent event) {
		Portal link = getLink(event.getPortal());
		event.getMovement().cancel();
		event.getEntity().setPos(link.getX(), link.getY());
	}
	
}
