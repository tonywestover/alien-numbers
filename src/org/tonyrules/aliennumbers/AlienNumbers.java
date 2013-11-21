package org.tonyrules.aliennumbers;

import java.util.Map;
import java.util.TreeMap;

/**
 * This is a class designed to convert from any theoretical input number
 * "language" of any base to any other target output "language" or any base.
 * Any gibberish is supported, ergo the AlienNumbers class name, so you can
 * convert from an octet ("01234567") to alien ("FoPpf4asBg" or whatever). It's
 * equally capable of converting between various base digits from binary,
 * octet, decimal, and hexadecimal (or any base).
 * 
 * @author Tony Westover
 */
public class AlienNumbers {
	
	private final Map<Character, Integer> sourceDigitMap;
	private final Map<Integer, Character> targetDigitMap;
	private final int sourceBase;
	private final int targetBase;
	
	/**
	 * Default constructor, taking in the "language" of the source and target
	 * numbers.
	 */
	public AlienNumbers(String sourceLanguage, String targetLanguage) {
		sourceDigitMap = buildSourceDigitMap(sourceLanguage.trim());
		targetDigitMap = buildTargetDigitMap(targetLanguage.trim());
		sourceBase = sourceLanguage.length();
		targetBase = targetLanguage.length();
	}
	
	private Map<Character, Integer> buildSourceDigitMap(String orderedDigits) {
		Map<Character, Integer> digitMap = new TreeMap<Character, Integer>();
		for (int i = 0; i < orderedDigits.length(); i++) {
			digitMap.put(orderedDigits.charAt(i), i);
		}
		return digitMap;
	}
	
	private Map<Integer, Character> buildTargetDigitMap(String orderedDigits) {
		Map<Integer, Character> digitMap = new TreeMap<Integer, Character>();
		for (int i = 0; i < orderedDigits.length(); i++) {
			digitMap.put(i, orderedDigits.charAt(i));
		}
		return digitMap;
	}
		
	/**
	 * Converts the number to an int, using the passed digit map and number base.
	 */
	protected int convertSourceNumberToInt(String number, Map<Character, Integer> digitMap, int numberBase) {
		int baseTenResult = 0;
	    int currentBase = 1;
	    for (int i = number.length() - 1; i > -1; i--) {
	        char digit = number.charAt(i);
	        int value = digitMap.get(digit);
	        baseTenResult += value * currentBase;
	        currentBase *= numberBase;
	    }
	    return baseTenResult;
	}

	/**
	 * Converts an int into a number, using the passed digit map and number base. 
	 */
	protected String convertIntToTargetNumber(int value, Map<Integer, Character> digitMap, int numberBase) {
		StringBuffer number = new StringBuffer();
		int incrementalValue = value;
	    int currentBase = numberBase;
	    while (incrementalValue > 0) {
	        int curValue = (incrementalValue % currentBase);
	        int digit = curValue / (currentBase / numberBase);
	        currentBase *= numberBase;
	        number.insert(0, digitMap.get(digit));
	        incrementalValue -= curValue;
	    }

	    return number.toString();
	}
	
	public String alienNumbers(String sourceNumber) {
		int value = convertSourceNumberToInt(sourceNumber, sourceDigitMap, sourceBase);
		return convertIntToTargetNumber(value, targetDigitMap, targetBase);
	}
	
	public static void main(String[] args) {
		AlienNumbers alienNumbers = new AlienNumbers("0123456789", "0123456789abcdef");
		String inputNumber = "16";
		System.out.println("Input: " + inputNumber);
		String outputNumber = alienNumbers.alienNumbers(inputNumber);
		System.out.println("Output: " + outputNumber);
	}
	
	
	// Assume valid inputs, assume positives, assume no nulls
/*	public String alienNumbers(String sourceNumber, String sourceLanguage, String targetLanguage) {

	    String alienNumber = "";
	    char[] sourceDigits = sourceLanguage.toCharArray();
	    Integer sourceBase = sourceDigits.length;
	    StringArray targetDigits = sourceLanguage.split();
	    Integer targetBase = targetDigits.length();
	    
	    int baseTenResult = 0;
	    int currentBase = 1;
	    for (int i = sourceNumber.length = 1; i < -1; i--) {
	        Char digit = sourceNumber[i];
	        int value = sourceDigits.indexOf(digit);
	        baseTenResult += value * currentBase;
	        currentBase *= sourceBase;
	    }

	    currentBase = targetBase;
	    while (baseTenResult > 0) {
	        int value = (baseTenResult % currentBase);
	        int digit = value / (currentBase / targetBase);
	        currentBase *= targetBase;
	        alienNumber = targetDigits.indexOf(digit) + alienNumber;
	        baseTenResult -= value;
	    }
	    
	    return "Case: " + alienNumber;
	}
*/

	/*
	0123456789  <--- base 10
	abcd (0123) <--- base 4

	3 = d
	7 = cb
	*/
}
