package app.pathfinder.model;

import java.io.Serializable;

public class Bag implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7832200695670887270L;

	private String bagId;
	private String sourceGate;
	private String flightId;

	public String getBagId() {
		return bagId;
	}

	public void setBagId(String bagId) {
		this.bagId = bagId;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getSourceGate() {
		return sourceGate;
	}

	public void setSourceGate(String sourceGate) {
		this.sourceGate = sourceGate;
	}

	

}
