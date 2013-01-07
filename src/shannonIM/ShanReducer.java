package shannonIM;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ShanReducer extends Reducer<Text, LongWritable, Text, DoubleWritable> {
	private long total = 0L;
	private double wss = 0.0;

	public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
		long t = 0;
		long[] longs = new long[27];
		int i = -1;
		
		for (LongWritable val : values) {
			t += val.get();
			i++;
			longs[i] = val.get();
		}
		
		double localTotal = (double) t;
		double weightedShan = 0.0;

		for (; i >= 0; i--) {
			weightedShan -= longs[i] * Math.log(longs[i] / localTotal);
		}

		this.total += t;
		this.wss += weightedShan;
	}

	public void cleanup(Context context) {
		try {
			context.write(new Text("TOTAL"), new DoubleWritable((double) total));
			context.write(new Text("WEIGHTED SHAN SUM"), new DoubleWritable(this.wss));
			context.write(new Text("ENTROPY"), new DoubleWritable(wss / total));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
