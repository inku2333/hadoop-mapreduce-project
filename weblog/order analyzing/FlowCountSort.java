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
 * ����ͳ�� 
 * 
 * �ڲ���
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
				count += iw.get();//��ȡ<k,1>
			}
			context.write(text, new LongWritable(count));
		}

		
		
		
	}
	 
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

		//������һ��job���� - ��ҵ���߼���ص���Ϣ���ĸ���mapper���ĸ���reducer��Ҫ������������������Ľ�����������
		//����������õ�job�ύ����Ⱥȥ����
		Configuration conf = new Configuration();
		conf.set("mapreduce.framework.name", "local");
		conf.set("mapreduce.jobtracker.address", "local");
		conf.set("fs.defaultFS", "local");
		Job job = Job.getInstance(conf);
		
		//ָ�����job���ڵ�jar��  java -jar
//				job.setJar("/home/hadoop/wordcount.jar");	 hadoop -jar xxxx.jar ��.��
		job.setJarByClass(FlowCountSort.class); 
		
		//����map��reduce����
		job.setMapperClass(FlowCountSortMapper.class);
		job.setReducerClass(FlowCountSortReducer.class);
		
		//�������ǵ��߼�Mapper������key��value����������
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		//���õ�ҵ���߼�Reducer������key��value����������
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		
		//ָ��Ҫ������������ڵ�λ��
		FileInputFormat.setInputPaths(job, "D:/flow/newinput/");
		//ָ���������֮��Ľ���������λ��
		FileOutputFormat.setOutputPath(job, new Path("file:/D:/flow/output4"));
		//��yarn��Ⱥ�ύ���job
		boolean complete = job.waitForCompletion(true);
		System.exit(complete?0:1); 
	}

}
