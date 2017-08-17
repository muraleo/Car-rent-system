package car_rent_system.model;

import java.io.Serializable;

public class Car implements Serializable{
	private Integer cid;
	private String title;
	private String type;
	private String color;
	private Integer amount;
	
	public Car(){};
	public Car(String t, String ty, String c, int a){
		title = t;
		type = ty;
		color = c;
		amount = a;
	}
	public Car(int id, String t, String ty, String c, int a){
		cid = id;
		title = t;
		type = ty;
		color = c;
		amount = a;
	}
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return " Car id: " + cid + " Title: " + title + " Type: " + type + " Color: " + color + " Amount: " + amount
				+ "";
	}
	
}
