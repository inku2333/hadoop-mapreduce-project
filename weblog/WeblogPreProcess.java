package demo;
//@云间
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.wt.hadoop.mapreduce.Partitioner.PhoneProvicePartition;

public class WeblogPreProcess {

	static class WeblogPreProcessMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
		Text k = new Text();
		NullWritable v = NullWritable.get();

		@Override
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

			String line = value.toString();
			WeblogBean webLogBean = WebLogParser.parser(line);
			if (!webLogBean.isValid())
				return;
			k.set(webLogBean.toString());
			context.write(k, v);

		}

	}

	public static void main(String[] args) throws Exception {
				
		Configuration conf = new Configuration();
		conf.set("mapreduce.framework.name", "local");
	     conf.set("mapreduce.jobtracker.address", "local");
	     conf.set("fs.defaultFS", "local");
		Job job = Job.getInstance(conf);
		
		job.setJarByClass(WeblogPreProcess.class);
		
		job.setMapperClass(WeblogPreProcessMapper.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
	
		job.setPartitionerClass(WeblogPartition.class);
		job.setNumReduceTasks(4);
		
		FileInputFormat.setInputPaths(job, "D:/flow/input2");
		FileOutputFormat.setOutputPath(job,new Path("file:/D:/flow/output2"));
		
		job.waitForCompletion(true);
		
	}
}