package app.pathfinder.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import app.pathfinder.model.Destination;

public class DestinationDAO {
	public static Map<String, Destination> destinationData = new HashMap<String, Destination>();

	public boolean isDestinationDataEmpty() {
		return destinationData.isEmpty();
	}
	
	public List<Destination> getDestinationList() {
		List<Destination> destinations = new ArrayList<Destination>();
		Set<String> destinationIdKeys = destinationData.keySet();
		for (String i : destinationIdKeys) {
			destinations.add(destinationData.get(i));
		}
		return destinations;
	}

	public Destination getDestination(String destinationId) {
		try {
			if (destinationData.containsKey(destinationId)) {
				return destinationData.get(destinationId);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
		return null;
	}
	
	public boolean isDestinationExists(String destinationId) {
			if (destinationData.containsKey(destinationId)) {
				return true;
			} else{
				return false;
			}
	}

	public boolean addDestination(Destination destination) {

		try {
			destinationData.put(destination.getDestinationGate(), destination);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;

	}

	public boolean deleteDestination(String destinationId) {
		try {
			destinationData.remove(destinationId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;

	}

	public boolean updateDestination(Destination destination) {
		try {
			destinationData.remove(destination.getDestinationGate());
			destinationData.put(destination.getDestinationGate(), destination);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;

	}

}
