package com.calculator.strategies;

import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import com.calculator.exceptions.InvalidDelimiterException;
import com.calculator.exceptions.UnsupportedNumberException;
import com.delimiter.strategies.Delimiter;

/***
 * <p>
 * This is a calculation strategy class, which accepts String, splits it based
 * on various known {@link Delimiter} list to get numbers and return sum of all.
 * 
 * <p>
 * This class also filters numbers based on defined conditions and doesn't
 * support negative numbers.
 * 
 * @author gaurav.vishal
 *
 */
public class StringCalculationStrategy extends AbstractCalculationStrategy<String> {

	/***
	 * List of delimiters, input data will be passed through each one of it to split
	 * the incoming string.
	 */
	private List<Delimiter<String>> delimiterList;

	public StringCalculationStrategy(List<Delimiter<String>> delimiterList) {
		isNullOrEmpty(delimiterList);
		this.delimiterList = delimiterList;
	}

	private void isNullOrEmpty(List<Delimiter<String>> delimiterList) {
		if (delimiterList == null || delimiterList.isEmpty()) {
			throw new RuntimeException("delimiterList can't be null or empty");
		}
	}

	/***
	 * <p>
	 * Accepts {@link Stream} of {@link String} and converts into Integer and return
	 * sum of all integers.
	 * 
	 * <p>
	 * Each number passes through filter and ignores all numbers greater than 100.
	 * 
	 */
	@Override
	protected int calculateUsingStream(Stream<String> stream) {
		int result = stream.mapToInt(toInteger()).filter(ignoreGreaterThan100()).filter(ignoreNegative()).sum();
		return result;
	}

	@Override
	protected List<Delimiter<String>> getAllDelimiters() {
		return delimiterList;
	}

	@Override
	protected Predicate<String> nullOrEmptyPredicate() {
		return string -> string != null && !string.trim().isEmpty();
	}

	/***
	 * converts string into integers and return. Any string with unknown delimiter
	 * will not be parsed into number and will throw
	 * {@link InvalidDelimiterException}.
	 * 
	 * @return
	 */
	private static ToIntFunction<String> toInteger() {
		return (string) -> {
			try {
				return Integer.valueOf(string);
			} catch (Exception ex) {
				throw new InvalidDelimiterException("Invalid Delimiter found between valid numbers", ex);
			}
		};
	}

	private static IntPredicate ignoreGreaterThan100() {
		return number -> number <= 100;
	}

	private static IntPredicate ignoreNegative() {
		return number -> {
			if (number < 0) {
				throw new UnsupportedNumberException("Negative numbers are not supported");
			}
			return true;
		};
	}

}
