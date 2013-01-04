package shannon;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ShanMap extends Mapper<Text, Text, Text, DoubleWritable> {
	private static final Text LOG = new Text("LOG");
	private static final Text TOTAL = new Text("TOTAL");
	private static final Text DIFF = new Text("DIFF");
	private static final DoubleWritable ONE = new DoubleWritable(1);

	public void map(Text key, Text val, Context context) {
		double count = Double.parseDouble(val.toString());
		
		double output = count * Math.log(count);

		try {
			context.write(LOG, new DoubleWritable(output));
			context.write(TOTAL, new DoubleWritable(count));
			context.write(DIFF, ONE);
		} catch (Exception e) {
			System.out.println("Error writing to context");
		}
	}
}
