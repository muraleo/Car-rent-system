package car_rent_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import car_rent_system.dbutil.DBUtil;
import car_rent_system.model.Order;

public class OrderCDUR {
	Connection conn = null;
	public OrderCDUR(){
		conn = DBUtil.getConnection();
	}
	
	public void addOrder(Order o) throws SQLException{
		//remember mysql has its own order table
		String sql = "INSERT INTO car_rent.order"+"(uid, cid, date)" +" value(?,?,?)";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, o.getUid());
		ptmt.setInt(2, o.getCid());
		ptmt.setString(3, o.getD());
		ptmt.execute();
	}
	
	public void removeOrder(int id) throws SQLException{
		String sql = "DELETE FROM car_rent.order WHERE id = ?)";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ptmt.execute();
	}
	
	public List<Order> queryOrder(int uid) throws SQLException{
		List<Order> list = new ArrayList<Order>();
		String sql = "SELECT * FROM car_rent.order WHERE uid = ?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, uid);
		ResultSet rs = ptmt.executeQuery();
		while(rs.next())
			list.add(new Order(rs.getInt("orderid"),rs.getInt("uid"), rs.getInt("cid"), rs.getString("date")));
		return list;
	}
	
	public List<Order> getOrder(int oid) throws SQLException{
		List<Order> list = new ArrayList<Order>();
		String sql = "SELECT * FROM car_rent.order WHERE orderid = ?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, oid);
		ResultSet rs = ptmt.executeQuery();
		while(rs.next())
			list.add(new Order(rs.getInt("orderid"),rs.getInt("uid"), rs.getInt("cid"), rs.getString("date")));
		return list;
	}
	
//	public static void main(String[] args) throws SQLException{
//		OrderCDUR oc = new OrderCDUR();
//		Order o = query;
//		oc.addOrder(o);
//		
//		//System.out.println();
//    }
}
