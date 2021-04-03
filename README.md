### SwissRe Assignment.

## Question #1

Using a Test Driven and Object Oriented approach, create a simple String Calculator which covers the following requirements:

1. Adds numbers present in the input, e.g "1,2" = 3
1. Treats empty or null input as zero, e.g "" = 0, null = 0
1. Supports different delimiters, e.g "1,2,3", "1 2 3"
1. Does not support negative numbers, e.g "-100" should not be accepted.
1. Ignore numbers greater than hundred, e.g "101,9,19" = 28.

Implementation uses a couple of concept-like strategy patterns along with Stream to make the code more flexible for further enhancements like support of new Delimiters or extend the source code to support other input type(s).
