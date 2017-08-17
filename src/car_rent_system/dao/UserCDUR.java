package car_rent_system.dao;

import java.sql.*;
import java.util.*;

import car_rent_system.dbutil.DBUtil;
import car_rent_system.model.User;

public class UserCDUR {
	public Connection conn = null;
	public UserCDUR(){
		conn = DBUtil.getConnection();
	}
	public void addUser(User u) throws SQLException{
		String sql = "INSERT INTO user (name, password) "+"value(?,?)";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, u.getName());
		ptmt.setString(2, u.getPassword());
		ptmt.execute();
	}
	
	public void removeUser(int id) throws SQLException{
		String sql = "DELETE FROM user WHERE uid = ?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ptmt.execute();
	}
	
	public void updateUser(User u) throws SQLException{
		String sql = "UPDATE user SET name = ?, password = ?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, u.getName());
		ptmt.setString(2, u.getPassword());
		ptmt.execute();
	}
	
	public User getUser(Integer id) throws SQLException{
		String sql = "SELECT * FROM user WHERE uid = ?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ResultSet rs = ptmt.executeQuery();
		User temp = null;
		while(rs.next()){
			temp = new User(rs.getInt("uid"), rs.getString("name"), rs.getString("password"));
		}
		return temp;
	}
	
	public User getUser(String n, String p) throws SQLException{
		String sql = "SELECT * FROM user WHERE name = ? AND password = ?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, n);
		ptmt.setString(2, p);
		ResultSet rs = ptmt.executeQuery();
		User temp = null;
		while(rs.next()){
			temp = new User(rs.getInt("uid"), rs.getString("name"), rs.getString("password"));
		}
		return temp;
	}
	
	public boolean queryUser(User u) throws SQLException{
		String sql = "SELECT * FROM user WHERE name = ? and password = ?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, u.getName());
		ptmt.setString(2, u.getPassword());
		ResultSet rs = ptmt.executeQuery();
		//return new User(rs.getInt("uid"), rs.getString("name"), rs.getString("password"));
		boolean flag = false;
		if(rs.next()) flag = true;
		return flag;
	}
	
//	public static void main(String[] args) throws SQLException{
//		UserCDUR uc = new UserCDUR();
//		//User u = new User("xiaoli","131313");
//		//User u = uc.getUser(2);
//		System.out.println(uc.queryUser(u));
//	}
}
