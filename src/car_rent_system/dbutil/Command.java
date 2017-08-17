package car_rent_system.dbutil;

import java.io.Serializable;

public class Command implements Serializable {
	private String com = null;
	private Object data = null;
	private boolean flag = false;
	private String result = null;
	
	public String getCom() {
		return com;
	}
	public void setCom(String com) {
		this.com = com;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "Command [com=" + com + ", data=" + data + ", flag=" + flag + ", result=" + result + "]";
	}
}
