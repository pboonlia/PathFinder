package app.pathfinder.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import app.pathfinder.model.Bag;

public class BagDAO {
	public static Map<String, Bag> bagData = new HashMap<String, Bag>();

	public boolean isBagDataEmpty() {
		return bagData.isEmpty();
	}
	
	public List<Bag> getBagList() {
		List<Bag> bags = new ArrayList<Bag>();
		Set<String> bagIdKeys = bagData.keySet();
		for (String i : bagIdKeys) {
			bags.add(bagData.get(i));
		}
		return bags;
	}

	public Bag getBag(String bagId) {
		try {
			if (bagData.containsKey(bagId)) {
				return bagData.get(bagId);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
		return null;
	}
	
	public boolean isBagExists(String bagId) {
			if (bagData.containsKey(bagId)) {
				return true;
			} else{
				return false;
			}
	}

	public boolean addBag(Bag bag) {

		try {
			bagData.put(bag.getBagId(), bag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;

	}

	public boolean deleteBag(String bagId) {
		try {
			bagData.remove(bagId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;

	}

	public boolean updateBag(Bag bag) {
		try {
			bagData.remove(bag.getBagId());
			bagData.put(bag.getBagId(), bag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;

	}

}
