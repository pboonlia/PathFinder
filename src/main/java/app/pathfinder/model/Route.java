package app.pathfinder.model;

import java.io.Serializable;

public class Route implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String bagId;
	private String conveyorRoute;
	private int travelTime;
	
	public String getBagId() {
		return bagId;
	}
	public void setBagId(String bagId) {
		this.bagId = bagId;
	}
	public String getConveyorRoute() {
		return conveyorRoute;
	}
	public void setConveyorRoute(String conveyorRoute) {
		this.conveyorRoute = conveyorRoute;
	}
	public int getTravelTime() {
		return travelTime;
	}
	public void setTravelTime(int travelTime) {
		this.travelTime = travelTime;
	}
	
	
	

}
