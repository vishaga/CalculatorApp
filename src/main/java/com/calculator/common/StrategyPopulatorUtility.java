package com.calculator.common;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.calculator.strategies.AbstractCalculationStrategy;
import com.calculator.strategies.StringCalculationStrategy;
import com.delimiter.strategies.Delimiter;
import com.delimiter.strategies.StringCommaSplitter;
import com.delimiter.strategies.StringSpaceSplitter;


public class StrategyPopulatorUtility {

	@SuppressWarnings("rawtypes")
	private static Map<InputType, AbstractCalculationStrategy> strategy = new EnumMap<>(InputType.class);

	private static List<Delimiter<String>> getStringDelimiters() {
		return Arrays.asList(new StringCommaSplitter(), new StringSpaceSplitter());
	}

	public static void populate() {
		strategy.put(InputType.STRING, new StringCalculationStrategy(getStringDelimiters()));
	}

	@SuppressWarnings("rawtypes")
	public static AbstractCalculationStrategy get(InputType type) {
		return strategy.get(type);
	}

}
