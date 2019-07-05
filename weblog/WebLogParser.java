package demo;
//@??
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WebLogParser {
    public static WeblogBean parser(String line) {
        WeblogBean webLogBean = new WeblogBean();
        String[] arr = line.split("\t");
		int endth = arr.length;		
		
		if(arr[endth-1].equals("regist"))
		{
			webLogBean.setAttribute("reg");
			webLogBean.setTime(arr[2]);
			webLogBean.setBody_bytes_sent(arr[1]+"\t"+arr[3]);
		}
		
		if(arr[endth-1].equals("login"))
		{
			webLogBean.setAttribute("log");
			webLogBean.setTime(arr[1]);
			webLogBean.setBody_bytes_sent("\t");
		}
		
		if(arr[endth-1].equals("browse"))
		{
			webLogBean.setAttribute("bro");
			webLogBean.setTime(arr[1]);
			webLogBean.setBody_bytes_sent(arr[2]+"\t"+arr[3]);
		}
		
		if(arr[endth-1].equals("order"))
		{
			webLogBean.setAttribute("ord");
			webLogBean.setTime(arr[4]);
			webLogBean.setBody_bytes_sent(arr[1]+"\t"+arr[2]+"\t"+arr[3]);
		}
		if (!arr[endth-1].equals("regist")&&!arr[endth-1].equals("login")&&!arr[endth-1].equals("browse")&&!arr[endth-1].equals("order")) 
		{
			webLogBean.setValid(false);
		}
		
        return webLogBean;
    }
   
    public static String parserTime(String time) {
    	
    	time.replace("/", "-");
    	return time;
    	
    }
}