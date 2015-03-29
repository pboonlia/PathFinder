package app.pathfinder.model;

import java.io.Serializable;

public class Flight implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String flightId;
	private String departureGate;
	private String destinationCity;
	private String departureTime;

	public String getDepartureGate() {
		return departureGate;
	}

	public void setDepartureGate(String departureGate) {
		this.departureGate = departureGate;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

}
