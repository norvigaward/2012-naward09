package charCount;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.reduce.LongSumReducer;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.io.compress.GzipCodec;

public class CharCount extends Configured implements Tool {
	@Override
	public int run(String[] args) throws Exception {
		Job job = new Job();

		job.setJobName("Character list building");

		// ~ Now where can we find them classes?
		job.setJarByClass(CharMapper.class);

		// ~ Set map/combine/reduce classes
		job.setMapperClass(CharMapper.class);
		job.setCombinerClass(LongSumReducer.class);
		job.setReducerClass(LongSumReducer.class);

		// ~ Define input/output types
		job.setInputFormatClass(SequenceFileInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);

		// ~ Specify input/output
		FileInputFormat.setInputPathFilter(job, SimpleInputFilter.class);
		SimpleInputFilter.setFilter("textData");
		Path inputPath = new Path("/home/participant/data/multiple/*/*");
		Path outputPath = new Path("/tmp/outData_MULTIRUN_multiFolderLevel/");
		FileInputFormat.addInputPath(job, inputPath);
		FileOutputFormat.setOutputPath(job, outputPath);
		
		// ~ Compress that output!
		FileOutputFormat.setCompressOutput(job, true);
		FileOutputFormat.setOutputCompressorClass(job, GzipCodec.class);

		System.out.println("Output path is " + outputPath.toString());

		boolean verbose = false;
		return (job.waitForCompletion(verbose) ? 1 : 0);
	}
}
