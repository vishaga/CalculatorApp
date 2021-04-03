package com.calculator;

import com.calculator.common.InputType;
import com.calculator.common.StrategyPopulatorUtility;
import com.calculator.exceptions.UnsupportedNumberException;
import com.calculator.strategies.AbstractCalculationStrategy;

public class CalculatorApp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		AbstractCalculationStrategy<String> stringCalculator = StrategyPopulatorUtility.get(InputType.STRING);
		String input1 = "1 2 3 4 5 6 7 102";
		String input2 = "1 2 3 4 5 6 7 10";
		String input3 = "1 2 3 4 5 6 7 102,100";
		String input4 = "1 2 3 4 5 6 7 10,200";
		String input5 = "1 2 3 4 5 6 7 10,200,-90";
		String input6 = null;
		String input7 = "";
		System.out.println("Input String is: " + input1);
		System.out.println("Result is: " + stringCalculator.calculate(input1));
		System.out.println("Input String is: " + input2);
		System.out.println("Result is: " + stringCalculator.calculate(input2));
		System.out.println("Input String is: " + input3);
		System.out.println("Result is: " + stringCalculator.calculate(input3));
		System.out.println("Input String is: " + input4);
		System.out.println("Result is: " + stringCalculator.calculate(input4));
		System.out.println("Input String is: " + input6);
		System.out.println("Result is: " + stringCalculator.calculate(input6));
		System.out.println("Input String is: " + input7);
		System.out.println("Result is: " + stringCalculator.calculate(input7));
		System.out.println("Input String is: " + input5);
		try {
			stringCalculator.calculate(input5);
		} catch (UnsupportedNumberException ex) {
			System.out.println("Exception occurred for input: " + input5 + " Exception is: " + ex.getMessage());
		}

	}

	/***
	 * prepopulate strategy map.
	 */
	static {
		StrategyPopulatorUtility.populate();
	}

}
