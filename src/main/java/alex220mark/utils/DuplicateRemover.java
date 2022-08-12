package alex220mark.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alex220mark.models.Item;

public class DuplicateRemover {

	public static Map<String, Integer> convertListToMapAndRemoveDuplicates(List<Item> initialList) {
		// create new Map to return
		Map<String, Integer> finalMap = new HashMap<>();
		// adding the names from the List Parameter to the new map (in all caps), this
		// removes duplicate names
		// TODO: need to account for names with more than 1 whitespace between words, here? or when we read in csv?
		for (int i = 0; i < initialList.size(); i++) {
			finalMap.put(initialList.get(i).getName().toUpperCase(), 0);
		}
		// checking if the name exists in the finalMap and if it does adds the
		// intialList number to finalMap number to give us a total number
		for (int i = 0; i < initialList.size(); i++) {
			String itemUpperCase = initialList.get(i).getName().toUpperCase();
			if (finalMap.keySet().contains(itemUpperCase)) {
				Integer newNumberToAdd = (finalMap.get(itemUpperCase) + initialList.get(i).getNumber());
				finalMap.replace(itemUpperCase, newNumberToAdd);
			}
		}
		return finalMap;
	}
	
}
