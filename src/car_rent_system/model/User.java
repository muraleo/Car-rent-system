package car_rent_system.model;

import java.io.Serializable;

public class User implements Serializable{
	private Integer uid;
	private String name;
	private String password;
	
	public User(){};
	public User(String n, String p){
		name = n;
		password = p;
	}
	
	//rememeber id
	public User(Integer id, String n, String p){
		uid = id;
		name = n;
		password = p;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
