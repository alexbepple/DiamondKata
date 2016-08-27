import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class DiamondTest {

	private static final String BACKGROUND_CHARACTER = "-";

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
		if (len == 2) {
			String mirrored = mirror(alphabet);
			return join(linebreak,
					Arrays.asList(
							line(mirrored.substring(0 % len, 0 % len + 1), len,
									0 % len),
							line(mirrored.substring(1 % len, 1 % len + 1), len,
									1 % len),
							line(mirrored.substring(2 % len, 2 % len + 1), len,
									2 % len)));
		}
		return alphabet;
	}

	private String line(String ch, int len, int lineIndex) {
		char[] chars = new char[len];
		Arrays.fill(chars, BACKGROUND_CHARACTER.charAt(0));
		chars[len - 1 - lineIndex] = ch.charAt(0);
		return mirror(new String(chars));
	}

	private String mirror(String in) {
		return in + in.substring(0, in.length() - 1);
	}

	private String join(String linebreak, List<String> lines) {
		return lines.stream().collect(joining(linebreak));
	}

}
