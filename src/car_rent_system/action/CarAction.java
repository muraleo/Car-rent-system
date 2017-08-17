package car_rent_system.action;

import java.sql.SQLException;
import java.util.*;

import car_rent_system.dao.CarCDUR;
import car_rent_system.model.Car;

public class CarAction {
	private CarCDUR cc = null;
	public CarAction(){
		cc = new CarCDUR();
	}
	
	public void add(Car c) throws SQLException{
		cc.addCar(c);
	}
	
	public void del(int id) throws SQLException{
		cc.removeCar(id);
	}
	
	public void edit(Car c) throws SQLException{
		cc.updateCar(c);
	}
	
	public Car get(int id) throws SQLException{
		return cc.getCar(id);
	}
	
	public List<Car> queryByTitle(String t){
		return cc.queryByTitle(t);
	}
	
	public List<Car> queryByType(String t){
		return cc.queryByType(t);
	}
	
	public List<Car> queryByColor(String t){
		return cc.queryByColor(t);
	}
	
	public List<Car> query(List<Map<String, Object>> params){
		return cc.fuzzyQuery(params);
	}
	
//	public static void main(String[] args){
//		CarAction ca = new CarAction();
//		try {
//			Car c = ca.get(7);
//			c.setType("Q19");
//			ca.edit(c);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}