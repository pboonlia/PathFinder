package app.pathfinder.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import app.pathfinder.model.Flight;

public class FlightDAO {
	public static Map<String, Flight> flightData = new HashMap<String, Flight>();

	public boolean isFlightDataEmpty() {
		return flightData.isEmpty();
	}
	
	public List<Flight> getFlightList() {
		List<Flight> flights = new ArrayList<Flight>();
		Set<String> flightIdKeys = flightData.keySet();
		for (String i : flightIdKeys) {
			flights.add(flightData.get(i));
		}
		return flights;
	}

	public Flight getFlight(String flightId) {
		try {
			if (flightData.containsKey(flightId)) {
				return flightData.get(flightId);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
		return null;
	}
	
	public boolean isFlightExists(String flightId) {
			if (flightData.containsKey(flightId)) {
				return true;
			} else{
				return false;
			}
	}

	public boolean addFlight(Flight flight) {

		try {
			flightData.put(flight.getFlightId(), flight);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;

	}

	public boolean deleteFlight(String flightId) {
		try {
			flightData.remove(flightId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;

	}

	public boolean updateFlight(Flight flight) {
		try {
			flightData.remove(flight.getFlightId());
			flightData.put(flight.getFlightId(), flight);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;

	}

}
