package alex220mark.utils;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

import java.util.List;

import org.junit.jupiter.api.Test;

public class CsvExporterTest {

	private CsvExporter underTest;

	@Test
	void itShouldCapitalise() {
		List<String> spaces = List.of("Red Socks", "Red Socks", "Red Socks", "Red Socks", "Red Socks", "Red Socks");
		List<String> spacesRemoved = List.of(underTest.capitalizeString("red socks"),
				underTest.capitalizeString("Red socks"),
				underTest.capitalizeString("red Socks"),
				underTest.capitalizeString("Red Socks"),
				underTest.capitalizeString("reD soCKs"),
				underTest.capitalizeString("rEd SOckS"));
		assertLinesMatch(spaces, spacesRemoved);
	}

}
