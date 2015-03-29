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
import app.pathfinder.dao.BagDAO;
import app.pathfinder.model.Bag;

@Controller
public class BagService {

	private static final Logger logger = LoggerFactory
			.getLogger(BagService.class);
	private BagDAO bagDAO = new BagDAO();

	@RequestMapping(value = PathFinderRestURIConstants.GET_BAGS, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Bag>> getBags() {
		logger.info("Start getBags");
		if (bagDAO.isBagDataEmpty()) {
			return new ResponseEntity<List<Bag>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Bag>>(bagDAO.getBagList(), HttpStatus.OK);
	}

	@RequestMapping(value = PathFinderRestURIConstants.GET_BAG, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Bag> getBag(
			@PathVariable("id") String bagId) {
		logger.info("Start getBag");
		if (bagDAO.isBagExists(bagId)) {
			return new ResponseEntity<Bag>(bagDAO.getBag(bagId), HttpStatus.OK);
		}
		return new ResponseEntity<Bag>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = PathFinderRestURIConstants.CREATE_BAG, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Bag> createBag(@RequestBody Bag bag) {
		logger.info("Start createBag");
		if (bagDAO.isBagExists(bag.getBagId())) {
			return new ResponseEntity<Bag>(HttpStatus.BAD_REQUEST);
		}
		if (!validateBagData(bag)) {
			return new ResponseEntity<Bag>(HttpStatus.BAD_REQUEST);
		}
		bagDAO.addBag(bag);
		return new ResponseEntity<Bag>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = PathFinderRestURIConstants.UPDATE_BAG, method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Bag> updateBag(
			@PathVariable("id") String bagId, @RequestBody Bag bag) {
		logger.info("Start updateBag");
		if (!bagDAO.isBagExists(bagId)) {
			return new ResponseEntity<Bag>(HttpStatus.BAD_REQUEST);
		}
		if (!validateBagData(bag)) {
			return new ResponseEntity<Bag>(HttpStatus.BAD_REQUEST);
		}
		bagDAO.updateBag(bag);
		return new ResponseEntity<Bag>(HttpStatus.OK);

	}

	@RequestMapping(value = PathFinderRestURIConstants.DELETE_BAG, method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Bag> deleteBag(
			@PathVariable("id") String bagId) {
		logger.info("Start deleteBag");
		if (!bagDAO.isBagExists(bagId)) {
			return new ResponseEntity<Bag>(HttpStatus.BAD_REQUEST);
		}
		bagDAO.deleteBag(bagId);
		return new ResponseEntity<Bag>(HttpStatus.NO_CONTENT);
	}

	private boolean validateBagData(Bag bag) {
		if (bag.getBagId().isEmpty()) {
			return false;
		}
		if (bag.getSourceGate().isEmpty()) {
			return false;
		}
		if (bag.getFlightId().isEmpty()) {
			return false;
		}
		return true;

	}

}
