package car_rent_system.model;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{
	private int orderid;
	private int uid;
	private int cid;
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	private String d;
	
	public Order(){};
	public Order(int u, int c){
		uid = u;
		cid = c;
		d = new Date().toGMTString();
	}
	public Order(int i , int u, int c){
		orderid = i;
		uid = u;
		cid = c;
		d = new Date().toGMTString();
	}
	public Order(int i , int u, int c, String d){
		orderid = i;
		uid = u;
		cid = c;
		this.d = d;
	}
	@Override
	public String toString() {
		return "orderid: " + orderid + ", uid: " + uid + ", cid: " + cid + ", Time: " + d;
	}
}
