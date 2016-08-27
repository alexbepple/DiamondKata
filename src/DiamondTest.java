import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class DiamondTest {

	private static final char BACKGROUND_CHARACTER = '-';
	private static final String LINEBREAK = "\n";

	@Test
	public void createsDiamondForOneLetter() {
		assertThat(diamond("A"), is("A"));
	}

	@Test
	public void createsDiamondForTwoLetters() {
		assertThat(diamond("AB"), is("-A-\n" + "B-B\n" + "-A-"));
		assertThat(diamond("BA"), is("-B-\n" + "A-A\n" + "-B-"));
	}

	@Test
	public void createsDiamondForThreeLetters() {
		assertEquals("--A--\n" + "-B-B-\n" + "C---C\n" + "-B-B-\n" + "--A--",
				diamond("ABC"));
	}

	private String diamond(String alphabet) {
		List<String> upperPartAndCenterLine = createUpperPartAndCenterLine(alphabet);
		List<String> allLines = completeFrom(upperPartAndCenterLine);
		return join(LINEBREAK, allLines);
	}

	private List<String> createUpperPartAndCenterLine(String alphabet) {
		List<String> upperPartAndCenterLine = new ArrayList<String>();
		for (int i = 0; i < alphabet.length(); i++) {
			upperPartAndCenterLine.add(line(i, alphabet.length(),
					alphabet.charAt(i)));
		}
		return upperPartAndCenterLine;
	}

	private String line(int pos, int alphabetSize, char ch) {
		char[] centerAndRightPart = createLineOfLength(alphabetSize);
		centerAndRightPart[pos] = ch;
		return completeLineFrom(new String(centerAndRightPart));
	}

	private char[] createLineOfLength(int alphabetSize) {
		char[] centerAndRightPart = new char[alphabetSize];
		Arrays.fill(centerAndRightPart, BACKGROUND_CHARACTER);
		return centerAndRightPart;
	}

	private String completeLineFrom(String centerAndRightPart) {
		StringBuilder leftPart = new StringBuilder(
				centerAndRightPart.substring(1)).reverse();
		return leftPart + centerAndRightPart;
	}

	private String join(String linebreak, List<String> lines) {
		return lines.stream().collect(joining(linebreak));
	}

	private List<String> completeFrom(List<String> upperPartAndCenterLine) {
		List<String> result = new ArrayList<String>(upperPartAndCenterLine);
		List<String> lowerPart = createLowerPartFrom(upperPartAndCenterLine);
		result.addAll(lowerPart);
		return result;
	}

	private List<String> createLowerPartFrom(List<String> upperPartAndCenterLine) {
		List<String> upperPart = upperPartAndCenterLine.subList(0,
				upperPartAndCenterLine.size() - 1);
		return reverse(upperPart);
	}

	private List<String> reverse(List<String> lowerPart) {
		Collections.reverse(lowerPart);
		return lowerPart;
	}
}
