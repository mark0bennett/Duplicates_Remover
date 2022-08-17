package alex220mark.utils;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CsvReaderWriterTest {

	// TODO: test capitalisation of Strings

	private CsvReaderWriter underTest;

	@BeforeEach
	void setUp() {
		underTest = new CsvReaderWriter();
	}

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
