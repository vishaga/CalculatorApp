package com.calculator.strategies;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.delimiter.strategies.Delimiter;

/***
 * <p>
 * This is an abstract contract, all parties (implementors) can define their own
 * way of parsing input and mathematical addition.
 * 
 * @author gaurav.vishal
 *
 * @param <T>
 */
public abstract class AbstractCalculationStrategy<T> {

	/**
	 * <p>
	 * Abstract method, parties can define logic to go through stream, perform
	 * transformation, filtration etc and return addition result.
	 * 
	 * @param stream
	 * @return
	 */
	protected abstract int calculateUsingStream(Stream<T> stream);

	/***
	 * <p>
	 * This method takes {@link String} input, converts into {@link Stream} and
	 * passes through all available delimiters to split and calls
	 * {@link CalculationStrategy#calculate(T)} to calculate result and returns.
	 * 
	 * @param t
	 * @return sum
	 */
	public int calculate(T t) {
		Stream<T> stream = t != null ? Stream.of(t) : Stream.empty();
		return calculateUsingStream(passThroughDelimiters(stream));
	}

	/***
	 * <p>
	 * This method takes input stream and passes it through all
	 * {@link Delimiter#split(Stream)}.
	 * 
	 * @param stream
	 * @return Stream<T>
	 */
	private final Stream<T> passThroughDelimiters(Stream<T> stream) {
		List<Delimiter<T>> delimiterList = getAllDelimiters();
		if (delimiterList != null && !delimiterList.isEmpty()) {
			for (Delimiter<T> delimiter : delimiterList) {
				stream = delimiter.split(stream);
			}
			return stream.filter(nullOrEmptyPredicate());
		}
		return Stream.empty();
	}

	/***
	 * Abstract method to get list of delimiter by which input data can pass
	 * through.
	 * 
	 * @return
	 */
	protected abstract List<Delimiter<T>> getAllDelimiters();

	protected abstract Predicate<T> nullOrEmptyPredicate();

}
