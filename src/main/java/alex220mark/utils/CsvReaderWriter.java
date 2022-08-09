package alex220mark.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import alex220mark.models.Item;

public class CsvReaderWriter {

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
					// creates new Item using index 0 (name) and index 1 (number) from the readLine
					// array including all duplicates
					Item itemToAdd = new Item(readLine[0], Integer.valueOf(readLine[1]));
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

	// exports a new csv file from a Map after the duplicates have been removed
	public static void exportCsvFile(Map<String, Integer> mapToExport) {
		try {
			// TODO: can we add a custom way to export the csv location, different dir paths
			// for different computers
			PrintWriter writer = new PrintWriter("C:\\Users\\markb\\Downloads\\newDuplicatesRemovedFile.csv");
			// for each entry - export to a line in the csv (name, number,)
			for (Map.Entry<String, Integer> entry : mapToExport.entrySet()) {
				writer.println(capitalizeString(entry.getKey()) + "," + entry.getValue().toString() + ",");
			}
			writer.close();
		} catch (Exception e) {
			// TODO: do something if we have an exception
		}
	}

	// Capitalises the first letter of each word in String for export into new csv
	private static String capitalizeString(String string) {
		char[] chars = string.toLowerCase().toCharArray();
		boolean found = false;
		for (int i = 0; i < chars.length; i++) {
			if (!found && Character.isLetter(chars[i])) {
				chars[i] = Character.toUpperCase(chars[i]);
				found = true;
			} else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') {
				found = false;
			}
		}
		return String.valueOf(chars);
	}

}
