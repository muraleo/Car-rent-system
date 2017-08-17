package car_rent_system.action;

import java.sql.SQLException;
import java.util.List;

import car_rent_system.dao.OrderCDUR;
import car_rent_system.model.Order;
import car_rent_system.model.User;

public class OrderAction {
	OrderCDUR oc = null;
	public OrderAction(){
		oc = new OrderCDUR();
	}
	
	public void add(Order o) throws SQLException{
		oc.addOrder(o);
	}
	
	public void del(int id) throws SQLException{
		oc.removeOrder(id);
	}
	
	public List<Order> get(int id) throws SQLException{
		return oc.getOrder(id);
	}
	
	public List<Order> showOnesOrder(int uid) throws SQLException{
		return oc.queryOrder(uid);
	}
}
