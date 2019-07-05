package demo2;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 订单统计 
 * 
 * 内部类
 */
public class FlowCountSort {

	private static class FlowCountSortMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
		@Override
		protected void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			String line = value.toString();
			String[] fields = line.split("\t");
			context.write(new Text(fields[2]), new IntWritable(1));
			context.write(new Text(fields[4]), new IntWritable(1));
			context.write(new Text(fields[3]), new IntWritable(1));
			context.write(new Text(fields[1].substring(11,13)), new IntWritable(1));

		}
	}
	
	private static class FlowCountSortReducer extends Reducer<Text, IntWritable, Text, LongWritable> {

		@Override
		protected void reduce(Text text, Iterable<IntWritable> iterable,
				Reducer<Text, IntWritable, Text, LongWritable>.Context context) throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			int count = 0;
			for (IntWritable iw : iterable) {
				count += iw.get();//获取<k,1>
			}
			context.write(text, new LongWritable(count));
		}

		
		
		
	}
	 
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

		//描述成一个job对象 - 把业务逻辑相关的信息（哪个是mapper，哪个是reducer，要处理的数据在哪里，输出的结果放哪里……）
		//把这个描述好的job提交给集群去运行
		Configuration conf = new Configuration();
		conf.set("mapreduce.framework.name", "local");
		conf.set("mapreduce.jobtracker.address", "local");
		conf.set("fs.defaultFS", "local");
		Job job = Job.getInstance(conf);
		
		//指定这个job所在的jar包  java -jar
//				job.setJar("/home/hadoop/wordcount.jar");	 hadoop -jar xxxx.jar 包.类
		job.setJarByClass(FlowCountSort.class); 
		
		//运行map、reduce对象
		job.setMapperClass(FlowCountSortMapper.class);
		job.setReducerClass(FlowCountSortReducer.class);
		
		//设置我们的逻辑Mapper类的输出key和value的数据类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		//设置的业务逻辑Reducer类的输出key和value的数据类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		
		//指定要处理的数据所在的位置
		FileInputFormat.setInputPaths(job, "D:/flow/newinput/");
		//指定处理完成之后的结果所保存的位置
		FileOutputFormat.setOutputPath(job, new Path("file:/D:/flow/output4"));
		//向yarn集群提交这个job
		boolean complete = job.waitForCompletion(true);
		System.exit(complete?0:1); 
	}

}
