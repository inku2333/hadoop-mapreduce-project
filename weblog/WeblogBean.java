package demo;
//@�Ƽ�
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;

public class WeblogBean {
	
	private String attribute;//log�����ԣ�������
    private String time;// ��¼����ʱ��
    private String body_bytes_sent;// ��¼���͸��ͻ����ļ���������
	
	private boolean valid = true;// �ж��Ƿ�Ϊ��������

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