package com.delimiter.strategies;

import java.util.stream.Stream;

/***
 * <p>
 * Delimiter is an abstract contract, all parties (implementors) can define
 * their own split logic based on their input type.
 * 
 * @author gaurav.vishal
 *
 * @param <T> Input type.
 */
public interface Delimiter<T> {

	/**
	 * <p>
	 * Method to split input and return Stream of objects.
	 * 
	 * @param t
	 * @return array of type T
	 */
	public Stream<T> split(Stream<T> stream);

}
