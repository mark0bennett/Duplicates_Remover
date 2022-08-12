package alex220mark.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alex220mark.models.Item;

public class DuplicateRemover {

	public static Map<String, Integer> convertListToMapAndRemoveDuplicates(List<Item> initialMap) {
		// create new Map to return
		Map<String, Integer> finalMap = new HashMap<>();
		// adding the names from the List Parameter to the new map (in all caps), this
		// removes duplicate names
		// TODO: need to account for names with more than 1 whitespace between words, here? or when we read in csv?
		for (int i = 0; i < initialMap.size(); i++) {
			finalMap.put(initialMap.get(i).getName().toUpperCase(), 0);
		}
		// checking if the name exists in the finalMap and if it does adds the
		// intialList number to finalMap number to give us a total number
		for (int i = 0; i < initialMap.size(); i++) {
			String itemUpperCase = initialMap.get(i).getName().toUpperCase();
			if (finalMap.keySet().contains(itemUpperCase)) {
				Integer newNumberToAdd = (finalMap.get(itemUpperCase) + initialMap.get(i).getNumber());
				finalMap.replace(itemUpperCase, newNumberToAdd);
			}
		}
		return finalMap;
	}
	
}
