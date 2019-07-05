package demo;
//@云间
import java.util.HashMap;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class WeblogPartition extends Partitioner<Text, NullWritable>{
	private static HashMap<String, Integer> partitionmap = new HashMap<String, Integer>();
	static {
		partitionmap.put("reg", 0);
		partitionmap.put("log", 1);
		partitionmap.put("bro", 2);
		partitionmap.put("ord", 3);
	}
//	@Override
//	public int getPartition(Text text, WeblogBean weblogBean, int numpartition) {
//		Integer code = partitionmap.get(text.toString().substring(0, 3));
//		return code == null ? 4 : code;
//	}
	@Override
	public int getPartition(Text text, NullWritable arg1, int arg2) {
		Integer code = partitionmap.get(text.toString().substring(0, 3));
		return code == null ? 4 : code;
	}
	
}
