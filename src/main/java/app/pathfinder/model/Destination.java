package app.pathfinder.model;

import java.io.Serializable;

public class Destination implements Serializable {

	private static final long serialVersionUID = -7788619177798333712L;

	private String sourceGate;
	private String destinationGate;
	private short mins;

	
	public String getSourceGate() {
		return sourceGate;
	}

	public void setSourceGate(String sourceGate) {
		this.sourceGate = sourceGate;
	}

	public String getDestinationGate() {
		return destinationGate;
	}

	public void setDestinationGate(String destinationGate) {
		this.destinationGate = destinationGate;
	}

	public short getMins() {
		return mins;
	}

	public void setMins(short mins) {
		this.mins = mins;
	}

}