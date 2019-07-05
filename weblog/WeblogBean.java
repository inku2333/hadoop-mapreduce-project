package demo;
//@云间
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;

public class WeblogBean {
	
	private String attribute;//log条属性，有四种
    private String time;// 记录访问时间
    private String body_bytes_sent;// 记录发送给客户端文件主体内容
	
	private boolean valid = true;// 判断是否为可用数据

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}


	public String getBody_bytes_sent() {
		return body_bytes_sent;
	}

	public void setBody_bytes_sent(String body_bytes_sent) {
		this.body_bytes_sent = body_bytes_sent;
	}
	
	
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	
    
	@Override
	public String toString() {
        StringBuilder sb = new StringBuilder();
		sb.append(this.attribute).append("\t");
        sb.append(this.time).append("\t");
        sb.append(this.body_bytes_sent).append("\t");
		//sb.append(this.valid);

        return sb.toString();
	}
}