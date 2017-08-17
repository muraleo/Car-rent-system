package car_rent_system.dbutil;

import java.sql.*;

public class DBUtil { //in order ot get mysql connection
	//step 4 url, name, password
	private static final String url = "jdbc:mysql://127.0.0.1:3306/car_rent"+
	"?autoReconnect=true&useSSL=false"; //turn off SSL
	private static final String name = "root";
	private static final String password = "FOREVER080923";
	private static Connection conn = null;
	
	static{
		try {
			// step 1 load JDBC drive
			Class.forName("com.mysql.jdbc.Driver");
			// step 2 establish database connection
			conn = DriverManager.getConnection(url, name, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		return conn;
	}
	
//	public static void main(String[] args){
//		
//		try {
//			// step 3 manipulate database by conn
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery("select * from car");
//			while(rs.next()){
//				int id = rs.getInt("cid");
//				String title = rs.getString("title");
//				String type = rs.getString("type");
//				String color = rs.getString("color");
//				int amount = rs.getInt("amount");
//				
//				System.out.println("cid :"+id);
//				System.out.println("title :"+title);
//				System.out.println("type :"+type);
//				System.out.println("color :"+color);
//				System.out.println("amount :"+amount);
//				System.out.println();
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
