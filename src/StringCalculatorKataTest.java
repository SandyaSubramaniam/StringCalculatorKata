import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StringCalculatorKataTest {

	@Test
	void testPassingEmptyString() {

		assertEquals("0", StringCalculatorKata.add(""));
	}

	@Test
	void testPassingTwoNumbers() {

		assertEquals("3", StringCalculatorKata.add("1,2"));
	}

	@Test
	void testPassingManyNumbers() {

		assertEquals("32", StringCalculatorKata.add("5,3,8,7,9"));
	}

	@Test
	void testPassingNewlineCharacter() {

		assertEquals("6", StringCalculatorKata.add("1\n2,3"));
	}

	@Test
	void testMissingNumberInLastPosition() {

		assertEquals("Number expected but EOF found.", StringCalculatorKata.add("1,3,"));
	}

	@Test
	void testNegativeNumbers() {

		assertEquals("Negative not allowed : -1", StringCalculatorKata.add("-1,2"));
	}

	@Test
	void testMultipleNegativeNumbers() {

		assertEquals("Negative not allowed : -1, -3", StringCalculatorKata.add("-1,2,-3"));
	}

	@Test
	void testDelimiterConditionOne() {

		assertEquals("3", StringCalculatorKata.add("//;\n1;2"));
	}

	@Test
	void testDelimiterConditionTwo() {

		assertEquals("6", StringCalculatorKata.add("//|\n1|2|3"));
	}

	@Test
	void testDelimiterConditionThree() {

		assertEquals("5", StringCalculatorKata.add("//sep\n2sep3"));
	}

	@Test
	void testDelimiterConditionFour() {

		assertEquals("';' is expected, other unexpected characters found: ,", StringCalculatorKata.add("//;\n1;2,4"));
		assertEquals("'|' is expected, other unexpected characters found: ,", StringCalculatorKata.add("//|\n1|2,4"));
		assertEquals("'|' is expected, other unexpected characters found: ,+)",
				StringCalculatorKata.add("//|\n1|2,+3)4"));
	}

	@Test
	void testGetDelimiter() {

		assertEquals(",", StringCalculatorKata.getDelimiter("1;2;3"));
		assertEquals(";", StringCalculatorKata.getDelimiter("//;\n1;2"));
		assertEquals("|", StringCalculatorKata.getDelimiter("//|\n1|2|3"));
		assertEquals("sep", StringCalculatorKata.getDelimiter("//sep\n2sep3"));
		assertEquals("|", StringCalculatorKata.getDelimiter("//|\n1|2,3"));
	}
}
