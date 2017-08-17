package car_rent_system.action;

import java.sql.SQLException;

import car_rent_system.dao.UserCDUR;
import car_rent_system.model.Car;
import car_rent_system.model.User;

public class UserAction {
	private UserCDUR uc = null;
	
	public UserAction(){
		uc = new UserCDUR();
	}
	
	public void add(User u) throws SQLException{
		uc.addUser(u);
	}
	
	public void del(int id) throws SQLException{
		uc.removeUser(id);
	}
	
	public void edit(User u) throws SQLException{
		uc.updateUser(u);
	}
	
	public User get(int id) throws SQLException{
		return uc.getUser(id);
	}
	
	public User get(String n, String p) throws SQLException{
		return uc.getUser(n, p);
	}
	
	public boolean Login(User u) throws SQLException{
		return uc.queryUser(u);
	}
}
