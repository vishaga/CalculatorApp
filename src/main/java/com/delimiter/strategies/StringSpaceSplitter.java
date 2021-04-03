package com.delimiter.strategies;

import java.util.stream.Stream;

/**
 * <p>
 * This class operates on String input and splits it based on defined logic.
 * 
 * @author gaurav.vishal
 *
 */
public class StringSpaceSplitter implements Delimiter<String> {

	/***
	 * <p>
	 * This method goes through input stream and splits each stream data based on
	 * comma (,) and returns Stream of objects.
	 * 
	 * @param {@link Stream<String>}
	 * @return {@link Stream<String>}
	 */
	@Override
	public Stream<String> split(Stream<String> stream) {
		if (stream != null) {
			return stream.flatMap(string -> Stream.of(string.split(" ")));
		}
		return Stream.empty();
	}

}
