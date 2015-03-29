package app.pathfinder.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.pathfinder.constants.PathFinderRestURIConstants;
import app.pathfinder.dao.FlightDAO;
import app.pathfinder.model.Flight;

@Controller
public class FlightService 
{
	
	  private static final Logger logger = LoggerFactory.getLogger(FlightService.class);
	  private FlightDAO flightDAO = new FlightDAO();
	  
	  @RequestMapping(value = PathFinderRestURIConstants.GET_FLIGHTS, method = RequestMethod.GET)
	    public @ResponseBody ResponseEntity<List<Flight>> getFlights() {
	        logger.info("Start getFlights");
	        if(flightDAO.isFlightDataEmpty()){
	            return new ResponseEntity<List<Flight>>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<List<Flight>>(flightDAO.getFlightList() , HttpStatus.OK);
	    }
	  
	  @RequestMapping(value = PathFinderRestURIConstants.GET_FLIGHT, method = RequestMethod.GET)
	    public @ResponseBody ResponseEntity<Flight> getFlight(@PathVariable("id") String flightId) {
	        logger.info("Start getFlight");
	        if(flightDAO.isFlightExists(flightId)) {
	        	return new ResponseEntity<Flight>(flightDAO.getFlight(flightId),HttpStatus.OK);
	        }
        	return new ResponseEntity<Flight>(HttpStatus.NOT_FOUND);
	    }
	  
	  @RequestMapping(value = PathFinderRestURIConstants.CREATE_FLIGHT, method = RequestMethod.POST)
	    public @ResponseBody ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
	        logger.info("Start createFlight");
	        if(flightDAO.isFlightExists(flight.getFlightId())) {
	        	return new ResponseEntity<Flight>(HttpStatus.BAD_REQUEST);
	        }
	        if(!validateFlightData(flight)){
	          	return new ResponseEntity<Flight>(HttpStatus.BAD_REQUEST);
	  	    }
	        flightDAO.addFlight(flight);
	        return new ResponseEntity<Flight>(HttpStatus.ACCEPTED);
	    }
	  
	  @RequestMapping(value = PathFinderRestURIConstants.UPDATE_FLIGHT, method = RequestMethod.PUT)
	    public @ResponseBody ResponseEntity<Flight> updateFlight(@PathVariable("id") String flightId,@RequestBody Flight flight) {
	        logger.info("Start updateFlight");
	        if(!flightDAO.isFlightExists(flightId)) {
	        	return new ResponseEntity<Flight>(HttpStatus.BAD_REQUEST);
	        }
	        if(!validateFlightData(flight)){
	          	return new ResponseEntity<Flight>(HttpStatus.BAD_REQUEST);
	  	    }
	        flightDAO.updateFlight(flight);
	     	return new ResponseEntity<Flight>(HttpStatus.OK);
		       
	    }
	  
	  @RequestMapping(value = PathFinderRestURIConstants.DELETE_FLIGHT, method = RequestMethod.DELETE)
	    public @ResponseBody ResponseEntity<Flight> deleteFlight(@PathVariable("id") String flightId) {
	        logger.info("Start deleteFlight");
	        if(!flightDAO.isFlightExists(flightId)) {
	        	return new ResponseEntity<Flight>(HttpStatus.BAD_REQUEST);
	        }
	        flightDAO.deleteFlight(flightId);
	     	return new ResponseEntity<Flight>(HttpStatus.NO_CONTENT);
	    }
	  
	  private boolean validateFlightData(Flight flight) {
		  if(flight.getFlightId().isEmpty()){
			  return false;
		  }
		  if(flight.getDepartureGate().isEmpty()){
			  return false; 
		  }
		  if(flight.getDepartureTime().isEmpty()){
			  return false;
		  }
		return true;
		  
	  }
	  
	  
	  

}
