naward09
========

A collection of scripts. Some really useless, others even more.

Or so it may seem... It might just turn out to work and even be correct!

##TODO's
* Write a Shannon-calculator (oh, this one also needs the "big picture" -> total occurences)
* Think of what to do with non-existing char combo's
* Generate some fancy graphs?
* Think of what size our tuples should be
* Improve old code (especially mine ;) )


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
		[a, b, c]
		emit( [ buffer[i], buffer[(i+1) % 3], buffer[(i+2) % 3] ], ONE);
	}

Combiner/Reducer; sum the sets to a [tuple, occurences]
(single set)

###Run 2 - calc total
Mapper; 