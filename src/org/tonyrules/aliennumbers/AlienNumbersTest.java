package org.tonyrules.aliennumbers;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlienNumbersTest {

	private static final AlienNumbers DEFAULT_ALIEN_NUMBERS = 
			new AlienNumbers("0123456789", "abcdef");
	
	@Test
	public void testGetDigitValues() {
		AlienNumbers alienNumbers = DEFAULT_ALIEN_NUMBERS;
		
		assertEquals(alienNumbers.getSourceDigitValue('0'), 0);
		assertEquals(alienNumbers.getSourceDigitValue('5'), 5);
		assertEquals(alienNumbers.getSourceDigitValue('9'), 9);

		assertEquals(alienNumbers.getTargetDigitValue('a'), 0);
		assertEquals(alienNumbers.getTargetDigitValue('d'), 3);
		assertEquals(alienNumbers.getTargetDigitValue('f'), 5);
	}
	
	@Test
	public void testConvertSourceToInt() {
		AlienNumbers alienNumbers = DEFAULT_ALIEN_NUMBERS;
		String sourceNumber = "1043";
		int result = alienNumbers.convertSourceNumberToInt(sourceNumber);
		assertEquals(result, 1043);
	}

}
