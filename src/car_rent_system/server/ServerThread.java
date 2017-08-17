package car_rent_system.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import car_rent_system.action.CarAction;
import car_rent_system.action.OrderAction;
import car_rent_system.action.UserAction;
import car_rent_system.dbutil.Command;
import car_rent_system.model.Car;
import car_rent_system.model.Order;
import car_rent_system.model.User;

public class ServerThread extends Thread{
	// 1. socket corresponding thread
	private Socket socket = null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	private CarAction ca = new CarAction();
	private OrderAction oa = new OrderAction();
	private UserAction ua = new UserAction();
	private User u = null;
	
	public ServerThread(Socket socket){
		this.socket = socket;
	}
	
	// thread's run method
	public void run(){
		System.out.println("Welcome");
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			Command com = (Command) ois.readObject();
			com = execute(com);
			oos.writeObject(com);
			oos.flush();
			//System.out.println("in run " + com);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} finally{
			try {
				if(oos!=null)
					oos.close();
				if(ois!=null)
					ois.close();
				if(socket!=null)
					socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// execute command and get result
	public Command execute(Command com){
		System.out.println("in execute " + com);
		String cmd = com.getCom().toUpperCase();
		if(cmd.equals("LOGIN")){
			try {
				User u = (User) com.getData();
				boolean flag = ua.Login(u);
				u = ua.get(u.getName(), u.getPassword());
				com.setData(u);
				com.setFlag(flag);
				if(flag){
					com.setResult("Log in successfully!");
				}else{
					com.setResult("Log in faild");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(cmd.equals("REGISTER")){
			try {
				User u = (User) com.getData();
				ua.add(u);
				com.setFlag(true);
				com.setResult("Register done.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(cmd.equals("LISTALLCARS")){
			List<Car> clist = (List<Car>) ca.queryByTitle("");
			com.setData(clist);
			com.setFlag(true);
			com.setResult("All available cars.");
		}else if(cmd.equals("ADDNEWCAR")){
			try {
				Car c = (Car) com.getData();
				ca.add(c);
				com.setFlag(true);
				com.setResult("New car added successful!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(cmd.equals("EDITACAR")){
			try {
				Car c = (Car) com.getData();
				ca.edit(c);
				com.setFlag(true);
				com.setResult("Edit car successful!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(cmd.equals("REMOVEACAR")){
			try {
				int i = (int) com.getData();
				ca.del(i);
				com.setFlag(true);
				com.setResult("Remove car successful!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(cmd.equals("SEARCHACAR")){
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			list.add((Map<String, Object>) com.getData());
			ca.query(list);
			com.setFlag(true);
			com.setResult("Search car successful!");
		}else if(cmd.equals("RENTACAR")){
			try {
				int[] usercar = (int[]) com.getData();
				Car c = ca.get(usercar[0]);
				if(c.getAmount()>1){
					Order o = new Order(usercar[1], usercar[0]);
					oa.add(o);
					System.out.println(usercar[0]);
					c.setAmount(c.getAmount()-1);
					ca.edit(c);
					com.setFlag(true);
					com.setResult("New car order completed");
				}else{
					com.setFlag(false);
					com.setResult("Fail to rent a car");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(cmd.equals("LISTALLORDERS")){
			int uuid = (int) com.getData();
			List<Order> olist;
			try {
				olist = (List<Order>) oa.showOnesOrder(uuid);
				com.setData(olist);
				com.setFlag(true);
				com.setResult("All Your Orders.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return com;
	}
}
