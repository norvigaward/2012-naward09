/*
 * Strongly based on LongSumReducer, as provided by Hadoop.
 * Thus; this is available with the Apache License, Version 2.0.
 * 
 * Thank you Apache!
*/
package shannon;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class DoubleSumReducer<KEY> extends Reducer<KEY, DoubleWritable, KEY, DoubleWritable> {

	private DoubleWritable result = new DoubleWritable();

	public void reduce(KEY key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
		double sum = 0.0;
		for (DoubleWritable val : values) {
			sum += val.get();
		}
		result.set(sum);
		context.write(key, result);
	}
}