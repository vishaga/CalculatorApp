package com.calculator.strategies;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.calculator.exceptions.InvalidDelimiterException;
import com.calculator.exceptions.UnsupportedNumberException;
import com.delimiter.strategies.Delimiter;
import com.delimiter.strategies.StringCommaSplitter;
import com.delimiter.strategies.StringSpaceSplitter;

/***
 * <p>
 * {@link StringCalculationStrategy} Test class to test various cases with
 * respect to input and available {@link Delimiter} list.
 * 
 * @author gaurav.vishal
 *
 */
public class StringCalculationStrategyTest {

	@Rule
	private ExpectedException expectedEx = ExpectedException.none();

	/**
	 * Interactive test cases that creates {@link StringCalculationStrategy} with
	 * null parameter.
	 * 
	 * @precondition none
	 * 
	 * @exception RuntimeException
	 * 
	 */
	@Test
	public void constructorInit_exception_nullCase() {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("delimiterList can't be null or empty");
		new StringCalculationStrategy(null);
	}

	/**
	 * Interactive test cases that creates {@link StringCalculationStrategy} with
	 * empty list parameter.
	 * 
	 * @precondition none
	 * 
	 * @exception RuntimeException
	 * 
	 */
	@Test
	public void constructorInit_exception_emptyCollectionCase() {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("delimiterList can't be null or empty");
		new StringCalculationStrategy(Arrays.asList());
	}

	/**
	 * Interactive test that creates {@link StringCalculationStrategy} by passing
	 * {@link Delimiter} list containing only one {@link StringCommaSplitter} object
	 * in it.
	 * 
	 * @testcase Parses comma separated string and find sum of all numbers.
	 *           <p>
	 *           sum of "1,2,3,4,5,6,7,8,9,10" = 55
	 *           <p>
	 * 
	 *           sum of "1,2,3,4,5,6,7,8,9,10,101" = 55 (All numbers greater than
	 *           100 should be ignored in calculation)
	 * 
	 * @precondition {@link StringCommaSplitter} object should be passed in
	 *               {@link StringCalculationStrategy} constructor by wrapping it
	 *               into {@link List}
	 * 
	 * @postcondition 55 should be returned as result.
	 * @exception None
	 * 
	 * @passCriteria There should not be any delimiter other than comma(",")
	 * 
	 */
	@Test
	public void calculate_case1() {
		Delimiter<String> commaSplitter = new StringCommaSplitter();
		AbstractCalculationStrategy<String> strategy = new StringCalculationStrategy(Arrays.asList(commaSplitter));
		int total = strategy.calculate("1,2,3,4,5,6,7,8,9,10");
		Assert.assertEquals(total, 55);
		total = strategy.calculate("1,2,3,4,5,6,7,8,9,10,101");
		Assert.assertEquals(total, 55);
	}

	/**
	 * Interactive test that creates {@link StringCalculationStrategy} by passing
	 * {@link Delimiter} list containing only one {@link StringCommaSplitter} object
	 * in it.
	 * 
	 * @precondition {@link StringCommaSplitter} object should be passed in
	 *               {@link StringCalculationStrategy} constructor by wrapping it
	 *               into {@link List}
	 * 
	 * @exception UnsupportedNumberException should be thrown as input contains
	 *                                       negative number (-1)
	 * 
	 * @passCriteria There should not be any delimiter other than comma(",")
	 * 
	 */
	@Test
	public void calculate_commaSplitter_exceptionCase1() {
		Delimiter<String> commaSplitter = new StringCommaSplitter();
		AbstractCalculationStrategy<String> strategy = new StringCalculationStrategy(Arrays.asList(commaSplitter));
		expectedEx.expect(UnsupportedNumberException.class);
		expectedEx.expectMessage("Negative numbers are not supported");
		strategy.calculate("1,2,3,4,5,6,7,8,9,10,-1");
	}

	/**
	 * Interactive test that creates {@link StringCalculationStrategy} by passing
	 * {@link Delimiter} list containing only one {@link StringCommaSplitter} object
	 * in it.
	 * 
	 * @precondition {@link StringCommaSplitter} object should be passed in
	 *               {@link StringCalculationStrategy} constructor by wrapping it
	 *               into {@link List}
	 * 
	 * @exception InvalidDelimiterException should be thrown as input contains
	 *                                      invalid/unknown delimiter (white space)
	 * 
	 */
	@Test
	public void calculate_commaSplitter_exceptionCase2() {
		Delimiter<String> commaSplitter = new StringCommaSplitter();
		AbstractCalculationStrategy<String> strategy = new StringCalculationStrategy(Arrays.asList(commaSplitter));
		expectedEx.expect(InvalidDelimiterException.class);
		expectedEx.expectMessage("Invalid Delimiter found between valid numbers");
		strategy.calculate("1 2");
	}

	/**
	 * Interactive test that creates {@link StringCalculationStrategy} by passing
	 * {@link Delimiter} list containing only one {@link StringSpaceSplitter} object
	 * in it.
	 * 
	 * @testcase Parses white space separated string and find sum of all numbers.
	 *           <p>
	 *           sum of "1 2 3 4 5 6 7 8 9 10" = 55
	 *           <p>
	 * 
	 *           sum of "1 2 3 4 5 6 7 8 9 10 199" = 55 (All numbers greater than
	 *           100 should be ignored in calculation)
	 * 
	 * @precondition {@link StringSpaceSplitter} object should be passed in
	 *               {@link StringCalculationStrategy} constructor by wrapping it
	 *               into {@link List}
	 * 
	 * @postcondition 55 should be returned as result.
	 * 
	 * @exception None
	 * 
	 * @passCriteria There should not be any delimiter other than white space(" ")
	 * 
	 */
	@Test
	public void calculate_case2() {
		Delimiter<String> spaceSplitter = new StringSpaceSplitter();
		AbstractCalculationStrategy<String> strategy = new StringCalculationStrategy(Arrays.asList(spaceSplitter));
		int total = strategy.calculate("1 2 3 4 5 6 7 8 9 10");
		Assert.assertEquals(total, 55);
		total = strategy.calculate("1 2 3 4 5 6 7 8 9 10 199");
		Assert.assertEquals(total, 55);
	}

	/**
	 * Interactive test that creates {@link StringCalculationStrategy} by passing
	 * {@link Delimiter} list containing only one {@link StringSpaceSplitter} object
	 * in it.
	 * 
	 * @precondition {@link StringSpaceSplitter} object should be passed in
	 *               {@link StringCalculationStrategy} constructor by wrapping it
	 *               into {@link List}
	 * 
	 * @exception UnsupportedNumberException should be thrown as input contains
	 *                                       negative number (-1)
	 * 
	 * @passCriteria There should not be any delimiter other than white space(" ")
	 * 
	 */
	@Test
	public void calculate_spaceSplitter_exceptionCase1() {
		Delimiter<String> spaceSplitter = new StringSpaceSplitter();
		AbstractCalculationStrategy<String> strategy = new StringCalculationStrategy(Arrays.asList(spaceSplitter));
		expectedEx.expect(UnsupportedNumberException.class);
		expectedEx.expectMessage("Negative numbers are not supported");
		strategy.calculate("1 2 3 4 5 6 7 8 9 10 -1");
	}

	/**
	 * Interactive test that creates {@link StringCalculationStrategy} by passing
	 * {@link Delimiter} list containing only one {@link StringSpaceSplitter} object
	 * in it.
	 * 
	 * @precondition {@link StringSpaceSplitter} object should be passed in
	 *               {@link StringCalculationStrategy} constructor by wrapping it
	 *               into {@link List}
	 * 
	 * @exception InvalidDelimiterException should be thrown as input contains
	 *                                      invalid/unknown delimiter (comma)
	 * 
	 */
	@Test
	public void calculate_spaceSplitter_exceptionCase2() {
		Delimiter<String> spaceSplitter = new StringSpaceSplitter();
		AbstractCalculationStrategy<String> strategy = new StringCalculationStrategy(Arrays.asList(spaceSplitter));
		expectedEx.expect(InvalidDelimiterException.class);
		expectedEx.expectMessage("Invalid Delimiter found between valid numbers");
		strategy.calculate("1,2");
	}

	/**
	 * Interactive test that creates {@link StringCalculationStrategy} by passing
	 * {@link Delimiter} list containing {@link StringSpaceSplitter} and
	 * {@link StringCommaSplitter} objects in it.
	 * 
	 * @testcase Parses white space separated string as well as comma separated
	 *           string and find sum of all numbers.
	 *           <p>
	 * 
	 *           sum of "1 2 3 4 5 6 7 8 9 10,11,101" = 66 (All numbers greater than
	 *           100 should be ignored in calculation)
	 * 
	 * @precondition {@link StringSpaceSplitter} and {@link StringCommaSplitter}
	 *               object should be passed in {@link StringCalculationStrategy}
	 *               constructor by wrapping it into {@link List}
	 * 
	 * @postcondition 66 should be returned as result.
	 * 
	 * @exception None
	 * 
	 * @passCriteria Input contains mixed delimiters i.e white space(" ") as well as
	 *               comma(",")
	 * 
	 */
	@Test
	public void calculate_case3_hybridDelimiter() {
		Delimiter<String> spaceSplitter = new StringCommaSplitter();
		Delimiter<String> commaSplitter = new StringSpaceSplitter();
		AbstractCalculationStrategy<String> strategy = new StringCalculationStrategy(
				Arrays.asList(spaceSplitter, commaSplitter));
		int total = strategy.calculate("1 2 3 4 5 6 7 8 9 10,11,101");
		Assert.assertEquals(total, 66);
	}

	/**
	 * Interactive test that creates {@link StringCalculationStrategy} by passing
	 * {@link Delimiter} list containing {@link StringSpaceSplitter} and
	 * {@link StringCommaSplitter} objects in it.
	 * 
	 * @testcase Treats null input as No input and returns 0.
	 *           <p>
	 * 
	 *           sum of null = 0
	 * 
	 * @precondition {@link StringSpaceSplitter} and {@link StringCommaSplitter}
	 *               object should be passed in {@link StringCalculationStrategy}
	 *               constructor by wrapping it into {@link List}
	 * 
	 * @postcondition 0 should be returned as result.
	 * 
	 * @exception None
	 * 
	 * @passCriteria Input should be null.
	 * 
	 */
	@Test
	public void calculate_nullCase() {
		Delimiter<String> spaceSplitter = new StringCommaSplitter();
		Delimiter<String> commaSplitter = new StringSpaceSplitter();
		AbstractCalculationStrategy<String> strategy = new StringCalculationStrategy(
				Arrays.asList(spaceSplitter, commaSplitter));
		int total = strategy.calculate(null);
		Assert.assertEquals(total, 0);
	}

	/**
	 * Interactive test that creates {@link StringCalculationStrategy} by passing
	 * {@link Delimiter} list containing {@link StringSpaceSplitter} and
	 * {@link StringCommaSplitter} objects in it.
	 * 
	 * @testcase Treats empty string input as No input and returns 0.
	 *           <p>
	 * 
	 *           sum of "" = 0
	 * 
	 * @precondition {@link StringSpaceSplitter} and {@link StringCommaSplitter}
	 *               object should be passed in {@link StringCalculationStrategy}
	 *               constructor by wrapping it into {@link List}
	 * 
	 * @postcondition 0 should be returned as result.
	 * 
	 * @exception None
	 * 
	 * @passCriteria Input should be "" or " ".
	 * 
	 */
	@Test
	public void calculate_emptyStringCase() {
		Delimiter<String> spaceSplitter = new StringCommaSplitter();
		Delimiter<String> commaSplitter = new StringSpaceSplitter();
		AbstractCalculationStrategy<String> strategy = new StringCalculationStrategy(
				Arrays.asList(spaceSplitter, commaSplitter));
		int total = strategy.calculate("");
		Assert.assertEquals(total, 0);
		total = strategy.calculate(" ");
		Assert.assertEquals(total, 0);
	}

}
