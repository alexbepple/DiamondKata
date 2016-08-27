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
		assertThat(diamond("BA"), is("-B-\n" + "A-A\n" + outmost("B")));
	}

	private String diamond(String alphabet) {
		String linebreak = "\n";
		if (alphabet.length() == 2) {
			String mirrored = alphabet
					+ alphabet.substring(0, alphabet.length() - 1);
			return join(linebreak, Arrays.asList(
					outmost(mirrored.substring(0, 1)),
					centerline(mirrored.substring(1, 2)),
					outmost(mirrored.substring(2, 3))));
		}
		return alphabet;
	}

	private String centerline(String ch) {
		return ch + BACKGROUND_CHARACTER + ch;
	}

	private String outmost(String ch) {
		return BACKGROUND_CHARACTER + ch + BACKGROUND_CHARACTER;
	}

	private String join(String linebreak, List<String> lines) {
		return lines.stream().collect(joining(linebreak));
	}

}
