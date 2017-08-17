package car_rent_system.dao;

import java.sql.*;
import java.util.*;
import car_rent_system.dbutil.DBUtil;
import car_rent_system.model.Car;

public class CarCDUR {
	public void addCar(Car c) throws SQLException{
		Connection conn = DBUtil.getConnection();
		//use question mark as place holder, so same statement can be supplied with different values
		String sql = ""+"INSERT INTO car "+"(title, type, color, amount)"+" value(?,?,?,?)";
		/*If you want to execute a Statement object many times, 
		*it usually reduces execution time to use a PreparedStatement object instead.
		*The main feature of a PreparedStatement object is that, unlike a Statement object, 
		*it is given a SQL statement when it is created. 
		*The advantage to this is that in most cases, 
		*this SQL statement is sent to the DBMS right away, 
		*where it is compiled. As a result, 
		*the PreparedStatement object contains not just a SQL statement, 
		*but a SQL statement that has been precompiled. 
		*This means that when the PreparedStatement is executed, 
		*the DBMS can just run the PreparedStatement SQL statement without having to compile it first.
		*Although PreparedStatement objects can be used for SQL statements with no parameters, 
		*you probably use them most often for SQL statements that take parameters. 
		*The advantage of using SQL statements that take parameters is that you can use the 
		*same statement and supply it with different values each time you execute it. 
		**/
		PreparedStatement ptmt = conn.prepareStatement(sql);
		//pass in parameter, 1 indicate the first ?
		ptmt.setString(1, c.getTitle());
		ptmt.setString(2, c.getType());
		ptmt.setString(3, c.getColor());
		ptmt.setInt(4, c.getAmount());
		
		//execute just return boolean, executeQuery return ResultSet
		ptmt.execute();
	}
	
	public void removeCar(int id) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = "DELETE FROM car WHERE cid = ?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ptmt.execute();
	}
	
	//remember the space in sql string
	public void updateCar(Car c) throws SQLException{
		Connection conn = DBUtil.getConnection();
		String sql = ""+"UPDATE car "+"SET title = ?, type = ?, color = ?, amount = ?"+
					" WHERE cid = ?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		//pass in parameter, 1 indicate the first ?
		ptmt.setString(1, c.getTitle());
		ptmt.setString(2, c.getType());
		ptmt.setString(3, c.getColor());
		ptmt.setInt(4, c.getAmount());
		ptmt.setInt(5, c.getCid());
		
		//execute just return boolean, executeQuery return ResultSet
		ptmt.execute();
	}
	
	public Car getCar(int id) throws SQLException{
		Car temp = null;
		Connection conn = DBUtil.getConnection();
		String sql = "SELECT * FROM car WHERE cid = ?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ResultSet rs = ptmt.executeQuery();
		while(rs.next()){
			temp = new Car(rs.getInt("cid"),rs.getString("title"), rs.getString("type"), rs.getString("color"), rs.getInt("amount"));	
		}
		return temp;
	}
	
	public List<Car> queryByTitle(String t){
		Connection conn = DBUtil.getConnection();
		List<Car> list = new ArrayList<Car>();
		try {
			//use like to do fuzzy query in sql, string match use %
			String sql = "SELECT * FROM car WHERE title LIKE ?";
			PreparedStatement ptmt = conn.prepareStatement(sql);
			//use % to do partial match
			ptmt.setString(1,"%"+t+"%");
			ResultSet rs = ptmt.executeQuery();
			Car car = null;
			while(rs.next()){
				car = new Car();
				car.setCid(rs.getInt("cid"));
				car.setTitle(rs.getString("title"));
				car.setType(rs.getString("type"));
				car.setColor(rs.getString("color"));
				car.setAmount(rs.getInt("amount"));
				list.add(car);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Car> queryByType(String t){
		Connection conn = DBUtil.getConnection();
		List<Car> list = new ArrayList<Car>();
		try {
			//use like to do fuzzy query in sql, string match use %
			String sql = "SELECT * FROM car WHERE type LIKE ?";
			PreparedStatement ptmt = conn.prepareStatement(sql);
			//use % to do partial match
			ptmt.setString(1,"%"+t+"%");
			ResultSet rs = ptmt.executeQuery();
			Car car = null;
			while(rs.next()){
				car = new Car();
				car.setCid(rs.getInt("cid"));
				car.setTitle(rs.getString("title"));
				car.setType(rs.getString("type"));
				car.setColor(rs.getString("color"));
				car.setAmount(rs.getInt("amount"));
				list.add(car);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Car> queryByColor(String t){
		Connection conn = DBUtil.getConnection();
		List<Car> list = new ArrayList<Car>();
		try {
			//use like to do fuzzy query in sql, string match use %
			String sql = "SELECT * FROM car WHERE color LIKE ?";
			PreparedStatement ptmt = conn.prepareStatement(sql);
			//use % to do partial match
			ptmt.setString(1,"%"+t+"%");
			ResultSet rs = ptmt.executeQuery();
			Car car = null;
			while(rs.next()){
				car = new Car();
				car.setCid(rs.getInt("cid"));
				car.setTitle(rs.getString("title"));
				car.setType(rs.getString("type"));
				car.setColor(rs.getString("color"));
				car.setAmount(rs.getInt("amount"));
				list.add(car);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Car> fuzzyQuery(List<Map<String, Object>> params){
		Connection conn = DBUtil.getConnection();
		List<Car> list = new ArrayList<Car>();
		try {
			//use like to do fuzzy query in sql, string match use %
			StringBuilder sql = new StringBuilder();
			//USE WHERE 1=1 CAN SOLVE WHERE PROBLEM
			sql.append("SELECT * FROM car WHERE 1=1 ");
			if(params!=null && params.size() > 0){
				for(int i = 0; i<params.size(); i++){
					sql.append("AND "+params.get(i).get("name")+" LIKE "+params.get(i).get("value"));
				}
			}
			PreparedStatement ptmt = conn.prepareStatement(sql.toString());
			//use % to do partial mat
			ResultSet rs = ptmt.executeQuery();
			Car car = null;
			while(rs.next()){
				car = new Car();
				car.setCid(rs.getInt("cid"));
				car.setTitle(rs.getString("title"));
				car.setType(rs.getString("type"));
				car.setColor(rs.getString("color"));
				car.setAmount(rs.getInt("amount"));
				list.add(car);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
