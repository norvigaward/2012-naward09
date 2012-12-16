package shannon;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.reduce.LongSumReducer;

public class ShanRed extends LongSumReducer<Text> { //extends Reducer<Text, LongWritable, Text, LongWritable> {
}
