import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class DiamondTest {

	private static final char BACKGROUND_CHARACTER = '-';

	@Test
	public void createsDiamondForOneLetter() {
		assertThat(diamond("A"), is("A"));
	}

	@Test
	public void createsDiamondForTwoLetters() {
		assertThat(diamond("AB"), is("-A-\n" + "B-B\n" + "-A-"));
		assertThat(diamond("BA"), is("-B-\n" + "A-A\n" + "-B-"));
	}

	private String diamond(String alphabet) {
		String linebreak = "\n";
		int len = alphabet.length();
		String mirrored = mirror(alphabet);

		List<String> lines = new ArrayList<String>();
		for (int i = 0; i < mirrored.length(); i++) {
			lines.add(line(i % len, len, mirrored.charAt(i % len)));
		}
		return join(linebreak, lines);
	}

	private String line(int lineIndex, int len, char ch) {
		char[] chars = new char[len];
		Arrays.fill(chars, BACKGROUND_CHARACTER);
		chars[len - 1 - lineIndex] = ch;
		return mirror(new String(chars));
	}

	private String mirror(String in) {
		return in + in.substring(0, in.length() - 1);
	}

	private String join(String linebreak, List<String> lines) {
		return lines.stream().collect(joining(linebreak));
	}

}
