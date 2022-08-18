package alex220mark.utils;

import java.io.PrintWriter;
import java.util.Map;

public class CsvExporter {

	// exports a new csv file from a Map after the duplicates have been removed
	public static void exportCsvFile(Map<String, Integer> mapToExport, String saveFilePath) {
		try {
			PrintWriter writer = new PrintWriter(saveFilePath + ".csv");
			// for each entry - export to a line in the csv (name, number,)
			for (Map.Entry<String, Integer> entry : mapToExport.entrySet()) {
				writer.println(capitalizeString(entry.getKey()) + "," + entry.getValue().toString() + ",");
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Capitalises the first letter of each word in String for export into new csv
	public static String capitalizeString(String string) {
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
