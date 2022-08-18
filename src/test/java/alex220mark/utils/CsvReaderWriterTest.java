package alex220mark.utils;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

import java.util.List;

import org.junit.jupiter.api.Test;

public class CsvReaderWriterTest {

	private CsvReaderWriter underTest;

	@Test
	void itShouldRemoveAllWhiteSpace() {
		List<String> spaces = List.of("red socks", "red socks", "red socks", "red socks", "red socks");
		List<String> spacesRemoved = List.of(underTest.removeWhiteSpaceFromString(" red socks"),
				underTest.removeWhiteSpaceFromString("red socks  "),
				underTest.removeWhiteSpaceFromString("  red socks "),
				underTest.removeWhiteSpaceFromString(" red    socks "),
				underTest.removeWhiteSpaceFromString("red   socks"));
		assertLinesMatch(spaces, spacesRemoved);
	}

}
