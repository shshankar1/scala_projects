#What is an nGram?

N-grams are simply all combinations of adjacent words or letters of length n
that you can find in your source text.

For example, given the word fox, all 2-grams (or “bigrams”) are "fo" and "ox".

You may also count the word boundary – that would expand the list of 2-grams to "#f", "fo", "ox", and "x#",
where # denotes a word boundary.

You can do the same on the word level.
As an example, the hello, world! text contains the following word-level bigrams: "# hello", "hello world", "world #".

The basic point of n-grams is that they capture the language structure from the statistical point of view,
like what letter or word is likely to follow the given one.

The longer the n-gram (the higher the n), the more context you have to work with.

Optimum length really depends on the application – if your n-grams are too short,
you may fail to capture important differences.

On the other hand, if they are too long, you may fail to capture the “general knowledge”
and only stick to particular cases.
