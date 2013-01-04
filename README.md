naward09
========

A collection of scripts. Some really useless, others even more.

Or so it may seem... It might just turn out to work and even be correct!

##TODO's
* Run on big data set
* Generate some fancy graphs?


---
##The plan...

###Run 1 - tuple gathering
Mapper; send N character sets

    var inputStream,
		i = 0,
		buffer = char[3],
		ONE = new LongWritable(1L);

	while(inputStream.hasNext()) {
		buffer[i] = inputStream.next();
		i = (i + 1) % 3;
		buffer[i] = inputStream.next();
		emit( [ buffer[i], buffer[(i+1) % 3], buffer[(i+2) % 3] ], ONE);
	}

Combiner/Reducer; sum the sets to a \[tuple, occurences\]
(single set)

###Run 2 - calc total
A second run, over the output of the first run. This transforms a large dictionary into three values, two of which are required to calculate the entropy.

About the output;
LOG X	The sum of the o*ln(o) where o is the amount of occurrences of a certain combination.
TOTAL Y	The sum of all the o
DIFF Z	The amount of combinations (#o)

###Run 3 - calculating the entropy
Continues where run 2 stopped.

ln(TOTAL) - (LOG/TOTAL)
LOG and TOTAL being the respective output of shanCalc

---
## NGramCount

The last N input bytes are encoded in an int (or long). After reading position x of the input, the 32-bits int is composed like this:

    31-30 | 29-25 | 24-20 | 19-15 | 14-10 | 9-5 | 4-0 
     00      x-5     x-4     x-3     x-2    x-1    x

Unused bits are zero, e.g. N=4:

    31-30 | 29-25 | 24-20 | 19-15 | 14-10 | 9-5 | 4-0 
     00     00000   00000    x-3     x-2    x-1    x

A space, uppercase and lowercase letters are encoded with their 5 least significant bits in ASCII: SPACE=0, a=A=1, z=Z=26.

    Char  | Hex  | Binary
    ------|------|----------
    Space | 0x20 | 001 00000
        A | 0x41 | 010 00001
        a | 0x61 | 011 00001
        Z | 0x5a | 010 11010
        z | 0x7a | 011 11010
