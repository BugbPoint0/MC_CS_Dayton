import static org.junit.Assert.*;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author David Dayton
 * Tests the MorseCodeConverter class
 */
public class MorseCodeConverterStudentTest {
	
	File inputFile;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testConvertToEnglishString() {	
		String converter1 = MorseCodeConverter.convertToEnglish("--. --- --- -.. / -- --- .-. -. .. -. --. / -.- .-. ..- ... - -.-- / -.-. .-. . .--");
		assertEquals("good morning krusty crew",converter1);
	}

		
}
