Char counter
========

This program counts the occurrences of sets of characters, much like a word count actually...

Once it's done, it sums everything up and dumps the [char set][#occurrences] combinations in a file.

The length of the sets of characters is variable, and only the alphabet and spaces (/[a-zA-Z ]{N}/) are accounted for!

Ideas for re-writes:
- include dots and comma's in the set of "allowed" characters
- N=2 gives an error... fix that, or pretend it didn't happen?
- run multiple lengths at once?
	(can we reverse-engeneer N's smaller than in our calculated data-set?)
- drop buffer when illegal character is seen? (e.g. for sdf$sdf; no "dfs" should be generated) -> is this wise?