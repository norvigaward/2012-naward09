package ngramcount;

import org.apache.hadoop.io.IntWritable;

public class NGramWritable extends IntWritable {

	public NGramWritable() {
	}

	public NGramWritable(int value) {
		super(value);
	}

	@Override
	public String toString() {
		byte[] bytes = new byte[NGramCount.N];
		int k = get();
		for(int j = NGramCount.N-1; j >= 0; j--) {
			bytes[j] = (byte) (k & 0x1f);
			if(bytes[j] > 0) {
				bytes[j] |= 0x60;
			} else {
				bytes[j] |= 0x20;
			}
			k >>= 5;
		}
		return new String(bytes);
	}

	
}
