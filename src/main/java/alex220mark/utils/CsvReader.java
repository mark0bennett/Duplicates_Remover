package alex220mark.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class CsvReader {

	// reads in the csv file using import path
	public static Map<String, Integer> readCsvFile(String importPath) {
		// checks that file to be read has the extension *.csv
		if (importPath.substring(importPath.length() - 4).equals(".csv")) {
			// to check for blank line in csv
			String line = "";
			// csv comma delimiter
			String splitBy = ",";
			// new Map for read csv
			Map<String, Integer> readMap = new HashMap<>();
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(importPath));
				// keeps reading and adding Items to readMap until a blank line
				while ((line = bufferedReader.readLine()) != null) {
					// splits the csv by a comma and creates a String array
					String[] readLine = line.split(splitBy);
					// check that the csv only has 2 columns, will return empty list if it has more
					// and will return an empty list is an column is blank
					if (readLine.length >= 3 || readLine[0].isBlank() || readLine[1].isBlank()) {
						return new HashMap<>();
					}
					// the number column in a csv will not allow leading and trailing whitespace
					// if a number is "1 2" - this may call for user to double check which is
					// correct
					// we will not remove white space in between numbers, only return invalid file
					String nameWhiteSpaceRemovedAllCaps = removeWhiteSpaceFromString(readLine[0]).toUpperCase();
					// adding Strings to a HashMap will remove duplicate names
					// if HashMap already contains the String Key, it will sum up the running total
					// for the number
					// else, it will create a new Key and put the current number as the Value
					if (readMap.keySet().contains(nameWhiteSpaceRemovedAllCaps)) {
						Integer newNumberToAdd = (readMap.get(nameWhiteSpaceRemovedAllCaps)
								+ Integer.valueOf(readLine[1]));
						readMap.replace(nameWhiteSpaceRemovedAllCaps, newNumberToAdd);
					} else {
						readMap.put(nameWhiteSpaceRemovedAllCaps, Integer.valueOf(readLine[1]));
					}

				}
				bufferedReader.close();
				// catches ANY exception and will just return a blank Map
			} catch (Exception e) {
				return new HashMap<>();
			}
			// if no exceptions it will return the populated Map with info from the
			// csv file
			return readMap;
		} else {
			// if the file does not end in .csv a blank Map will be returned
			return new HashMap<>();
		}
	}

	public static String removeWhiteSpaceFromString(String originalString) {
		return originalString.trim().replaceAll(" +", " ");
	}

}
