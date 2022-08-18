package alex220mark.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import alex220mark.models.Item;

public class CsvReader {

	// reads in the csv file using import path
	public static List<Item> readCsvFile(String importPath) {
		// checks that file to be read has the extension *.csv
		if (importPath.substring(importPath.length() - 4).equals(".csv")) {
			// to check for blank line in csv
			String line = "";
			// csv comma delimiter
			String splitBy = ",";
			// new arraylist for read csv
			List<Item> readList = new ArrayList<>();
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(importPath));
				// keeps reading and adding Items to readList until a blank line
				while ((line = bufferedReader.readLine()) != null) {
					// splits the csv by a comma and creates an array
					String[] readLine = line.split(splitBy);
					// check that the csv only has 2 columns, will return empty list if it has more
					// and will return an empty list is an column is blank
					if (readLine.length >= 3 || readLine[0].isBlank() || readLine[1].isBlank()) {
						return new ArrayList<>();
					}
					// creates new Item using index 0 (name) and index 1 (number) from the readLine
					// array including all duplicates - remove all whitespace before adding
					// the number column in a csv will not allow leading and trailing whitespace
					// if a number is "1 2" - this may call for user to double check which is
					// correct
					// we will not remove white space in between numbers, only return invalid file
					String nameWhiteSpaceRemoved = removeWhiteSpaceFromString(readLine[0]);
					Item itemToAdd = new Item(nameWhiteSpaceRemoved, Integer.valueOf(readLine[1]));
					// adds the newly created Item to the readList ArrayList (which will be the
					// final returned list)
					readList.add(itemToAdd);
				}
				bufferedReader.close();
				// catches ANY exception and will just return a blank ArrayList
			} catch (Exception e) {
				return new ArrayList<>();
			}
			// if no exceptions it will return the populated ArrayList with Items from the
			// csv file
			return readList;
		} else {
			// if the file does not end in .csv a blank ArrayList will be returned
			return new ArrayList<>();
		}
	}

	public static String removeWhiteSpaceFromString(String originalString) {
		return originalString.trim().replaceAll(" +", " ");
	}

}
