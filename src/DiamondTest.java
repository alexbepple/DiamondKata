import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class DiamondTest {

	@Test
	public void createsDiamondForOneLetter() {
		assertThat(diamond("A"), is("A"));
	}

	@Test
	public void createsDiamondForTwoLetters() {
		assertThat(diamond("AB"), is("-A-\n" + "B-B\n" + "-A-"));
	}

	private String diamond(String string) {
		if (string.length() > 1) {
			String linebreak = "\n";
			return Arrays.asList("-A-", "B-B", "-A-").stream()
					.collect(joining(linebreak));
		}
		return string;
	}

}
