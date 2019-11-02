package unsw.dungeon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class PortalNetwork {

	private Map<Integer, List<Portal>> portals;
	
	public PortalNetwork() {
		this.portals = new HashMap<>();
	}
	
	public void register(Portal portal) {
		this.portals.putIfAbsent(portal.getLinkId(), new ArrayList<>());
		this.portals.get(portal.getLinkId()).add(portal);
		
		portal.addListener(new EventHandler<EntityReachedPortalEvent>() {
			@Override
			public void handle(EntityReachedPortalEvent event) {
				Portal link = getLink(event.getPortal());
				event.getMovement().cancel();
				event.getEntity().setPos(link.getX(), link.getY());
			}
		});
	}
	
	private Portal getLink(Portal portal) {
		List<Portal> linked = this.portals.get(portal.getLinkId());
		int pos = linked.indexOf(portal);
		int size = linked.size();
		
		return linked.get((pos + 1) % size);
	}
	
}
