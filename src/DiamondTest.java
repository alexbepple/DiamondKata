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
		List<String> upperHalfAndCenterLine = createUpperHalfAndCenterLine(alphabet);
		return join(LINEBREAK, completeFrom(upperHalfAndCenterLine));
	}

	private List<String> createUpperHalfAndCenterLine(String alphabet) {
		String mirrored = mirror(alphabet);
		List<String> upperHalfAndCenterLine = new ArrayList<String>();
		for (int i = 0; i < alphabet.length(); i++) {
			upperHalfAndCenterLine.add(line(i, alphabet.length(),
					mirrored.charAt(i)));
		}
		return upperHalfAndCenterLine;
	}

	private List<String> completeFrom(List<String> upperHalfAndCenterLine) {
		List<String> result = new ArrayList<String>(upperHalfAndCenterLine);
		List<String> lowerHalf = upperHalfAndCenterLine.subList(0,
				upperHalfAndCenterLine.size() - 1);
		Collections.reverse(lowerHalf);
		result.addAll(lowerHalf);
		return result;
	}

	private String line(int lineIndex, int alphabetSize, char ch) {
		char[] leftHalf = new char[alphabetSize];
		Arrays.fill(leftHalf, BACKGROUND_CHARACTER);
		leftHalf[Math.abs(alphabetSize - 1 - lineIndex)] = ch;
		return mirror(new String(leftHalf));
	}

	private String mirror(String in) {
		return in
				+ new StringBuilder(in.substring(0, in.length() - 1)).reverse();
	}

	private String join(String linebreak, List<String> lines) {
		return lines.stream().collect(joining(linebreak));
	}

}
