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
import app.pathfinder.dao.DestinationDAO;
import app.pathfinder.model.Destination;

@Controller
public class DestinationService 
{
	
	  private static final Logger logger = LoggerFactory.getLogger(DestinationService.class);
	  private DestinationDAO destinationDAO = new DestinationDAO();
	  
	  @RequestMapping(value = PathFinderRestURIConstants.GET_DESTINATIONS, method = RequestMethod.GET)
	    public @ResponseBody ResponseEntity<List<Destination>> getDestinations() {
	        logger.info("Start getDestinations");
	        if(destinationDAO.isDestinationDataEmpty()){
	            return new ResponseEntity<List<Destination>>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<List<Destination>>(destinationDAO.getDestinationList() , HttpStatus.OK);
	    }
	  
	  @RequestMapping(value = PathFinderRestURIConstants.GET_DESTINATION, method = RequestMethod.GET)
	    public @ResponseBody ResponseEntity<Destination> getDestination(@PathVariable("id") String destinationId) {
	        logger.info("Start getDestination");
	        if(destinationDAO.isDestinationExists(destinationId)) {
	        	return new ResponseEntity<Destination>(destinationDAO.getDestination(destinationId),HttpStatus.OK);
	        }
        	return new ResponseEntity<Destination>(HttpStatus.NOT_FOUND);
	    }
	  
	  @RequestMapping(value = PathFinderRestURIConstants.CREATE_DESTINATION, method = RequestMethod.POST)
	    public @ResponseBody ResponseEntity<Destination> createDestination(@RequestBody Destination destination) {
	        logger.info("Start createDestination");
	        if(destinationDAO.isDestinationExists(destination.getDestinationGate())) {
	        	return new ResponseEntity<Destination>(HttpStatus.BAD_REQUEST);
	        }
	        if(!validateDestinationData(destination)){
	          	return new ResponseEntity<Destination>(HttpStatus.BAD_REQUEST);
	  	    }
	        destinationDAO.addDestination(destination);
	        return new ResponseEntity<Destination>(HttpStatus.ACCEPTED);
	    }
	  
	  @RequestMapping(value = PathFinderRestURIConstants.UPDATE_DESTINATION, method = RequestMethod.PUT)
	    public @ResponseBody ResponseEntity<Destination> updateDestination(@PathVariable("id") String destinationId,@RequestBody Destination destination) {
	        logger.info("Start updateDestination");
	        if(!destinationDAO.isDestinationExists(destinationId)) {
	        	return new ResponseEntity<Destination>(HttpStatus.BAD_REQUEST);
	        }
	        if(!validateDestinationData(destination)){
	          	return new ResponseEntity<Destination>(HttpStatus.BAD_REQUEST);
	  	    }
	        destinationDAO.updateDestination(destination);
	     	return new ResponseEntity<Destination>(HttpStatus.OK);
		       
	    }
	  
	  @RequestMapping(value = PathFinderRestURIConstants.DELETE_DESTINATION, method = RequestMethod.DELETE)
	    public @ResponseBody ResponseEntity<Destination> deleteDestination(@PathVariable("id") String destinationId) {
	        logger.info("Start deleteDestination");
	        if(!destinationDAO.isDestinationExists(destinationId)) {
	        	return new ResponseEntity<Destination>(HttpStatus.BAD_REQUEST);
	        }
	        destinationDAO.deleteDestination(destinationId);
	     	return new ResponseEntity<Destination>(HttpStatus.NO_CONTENT);
	    }
	  
	  private boolean validateDestinationData(Destination destination) {
		  if(destination.getDestinationGate().isEmpty()){
			  return false;
		  }
		  if(destination.getSourceGate().isEmpty()){
			  return false; 
		  }
		  if(destination.getMins() <= (short) 0){
			  return false;
		  }
		return true;
		  
	  }
	  
	  
	  

}
