##An improved version of shannon

It uses a smarter algorithm to achieve a lower approximation of the Shannon limit, and it does not require multiple runs.

###The inner workings
The expected input looks like this  return
[seven character string][some 8'th char]	[#occurrences]  return
[seven character string][other 8'th char]	[#occurrences]  return
etc.

This mapper ignores the 8'th character, uses the seven character string as KEY, and the #occurrences as VALUE.  enter
Every combination of the [seven character string][char] is unique, thus there are no more than 27 outputs with the same KEY.  enter

The reducer calculates the weighted sum of the entropy of chosing the 8'th char, for every [seven character string] weighted by the amount of occurrences of those strings. It also keeps track of the sum of occurrences.

The cleanup() function of the reducer uses the information gathered during the reduce phase to calculate the entropy (and give some other interesting information).
