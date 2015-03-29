package app.pathfinder.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.pathfinder.constants.PathFinderRestURIConstants;
import app.pathfinder.dao.BagDAO;
import app.pathfinder.dao.DestinationDAO;
import app.pathfinder.dao.FlightDAO;
import app.pathfinder.model.Bag;
import app.pathfinder.model.Destination;
import app.pathfinder.model.Flight;
import app.pathfinder.model.Route;

@Controller
public class RouteService {

	@RequestMapping(value = PathFinderRestURIConstants.GET_ROUTES, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Route>> getAllRoutes() {
		List<Route> routeList = new ArrayList<Route>();
		List<Bag> bagList = new ArrayList<Bag>();
		BagDAO bagDAO = new BagDAO();

		bagList = bagDAO.getBagList();
		for (Bag bag : bagList) {
			Route route = new Route();
			route = identifyRoute(bag.getBagId());
			routeList.add(route);
		}
		return new ResponseEntity<List<Route>>(routeList, HttpStatus.OK);

	}

	@RequestMapping(value = PathFinderRestURIConstants.GET_ROUTE, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Route> getRoute(
			@PathVariable("id") String bagId) {
		Route route = new Route();
		route = identifyRoute(bagId);
		return new ResponseEntity<Route>(route, HttpStatus.OK);

	}

	private Route identifyRoute(String bagId) {
		Route route = new Route();
		Bag bag = BagDAO.bagData.get(bagId);
		Destination destination = DestinationDAO.destinationData.get(bag
				.getSourceGate());
		Flight flight = FlightDAO.flightData.get(bag.getFlightId());

		route.setBagId(bagId);
		route.setConveyorRoute(getConveyorRoute(destination,
				bag.getSourceGate(), flight.getDepartureGate()));
		route.setTravelTime(getTravelTime(destination,
				bag.getSourceGate(), flight.getDepartureGate()));
		return route;
	}

	private int getTravelTime(Destination destination, String sourceGate,
			String departureGate) {
		// TODO Auto-generated method stub
		return 0;
	}

	private String getConveyorRoute(Destination destination,
			String sourceGate, String departureGate) {
		return departureGate;
		// TODO Auto-generated method stub

	}
}
