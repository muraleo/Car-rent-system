package car_rent_system.server;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import car_rent_system.dbutil.Command;
import car_rent_system.model.Car;
import car_rent_system.model.Order;
import car_rent_system.model.User;

import java.io.*;

public class Client {
	Scanner input = new Scanner(System.in);
	boolean flag = false;
	private Socket socket = null;
	private static final String LOGIN = ""+
			"[LOG IN]\t"+
			"[REGISTER]";
	private static final String CONTEXT="" +
			"Function List\n" +
			"*1.[QUERY/Q]:  List all available car\n" +
			"*2.[ADD/A]:    Add a new Car\n" +
			"*3.[UPDATE/U]: Edit a Car\n" +
			"*4.[DELETE/D]: Remove a Car\n" +
			"*5.[SEARCH/S]: Search a car\n" +
			"*6.[RENT/R]:   Rent a car\n" +
			"*7.[ORDERS/O]: Show all orders of you\n" +
			"*8.[EXIT/E]:   Exit\n" +
			"*0.[BREAK/B]:  Back to MainMenu";
	private ObjectOutputStream outToServer= null;
	private ObjectInputStream inFromServer = null;
	private User currentUser = null;
	
	public void showMainMenu(){
		System.out.println("Welcom to Leo car rent");
		System.out.println(LOGIN);
		System.out.println("----------------------");
		System.out.println("Please choose:");
		String func = input.next().toUpperCase().toString();
		if(func.equals("LOGIN")){
			flag = clientLogin();
		}
		if(func.equals("REGISTER")){
			flag = clientRegister();
		}
		if(flag == true){
			System.out.println(CONTEXT);
			while(true){
				int funci = input.nextInt();
				switch(funci){
					case 0:
						System.out.println(CONTEXT);
						break;
					case 1:
						clientListCar();
						break;
					case 2:
						clientAddCar();
						break;
					case 3:
						clientEditCar();
						break;
					case 4:
						clientRemoveCar();
						break;
					case 5:
						clientSearchACar();
						break;
					case 6:
						clientRentACar();
						break;
					case 7:
						clientShowOrders();
						break;
					case 8:
						System.out.println("Bye");
						System.exit(0);
					default:
						System.out.println("Roung function");
				}
			}
		}
	}
	
	public boolean clientLogin(){
		User u = new User();
		Command com = new Command();
		int count = 0;
		while(true){
			count++;
			if(count > 3){
				System.out.println("Fail more than 3 times, exit.");
				System.exit(0);
			}
			System.out.println("Please input name: ");
			u.setName(input.next());
			System.out.println("Please input password");
			u.setPassword(input.next());
			com.setCom("LOGIN");
			com.setData(u);
			try {
				socket = new Socket("127.0.0.1",8888);
				sendData(com);
				com = getData();
				System.out.println(com.getResult());
				System.out.println("--------------");
				if(com.isFlag()){
					currentUser = (User) com.getData();
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return com.isFlag();
	}
	
	public boolean clientRegister(){
		User u = new User();
		Command com = new Command();
		System.out.println("Please input name: ");
		u.setName(input.next());
		System.out.println("Please input password");
		u.setPassword(input.next());
		com.setCom("REGISTER");
		com.setData(u);
		try {
			socket = new Socket("127.0.0.1",8888);
			sendData(com);
			com = getData();
			currentUser = (User) com.getData();
			System.out.println(com.getResult());
			System.out.println("--------------");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return com.isFlag();
	}
	
	public void clientListCar(){
		List<Car> clist = new ArrayList<Car>();
		Command com = new Command();
		com.setCom("LISTALLCARS");
		try {
			socket = new Socket("127.0.0.1",8888);
			sendData(com);
			com = getData();
			System.out.println(com.getResult());
			clist = (List<Car>) com.getData();
			for(Car c : clist)
				System.out.println(c);
			System.out.println("--------------");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clientAddCar(){
		Car c = new Car();
		Command com = new Command();
		System.out.println("Please input Title: ");
		c.setTitle(input.next());
		System.out.println("Please input Type");
		c.setType(input.next());
		System.out.println("Please input Color");
		c.setColor(input.next());
		System.out.println("Please input Amount");
		c.setAmount(input.nextInt());
		com.setCom("ADDNEWCAR");
		com.setData(c);
		try {
			socket = new Socket("127.0.0.1",8888);
			sendData(com);
			com = getData();
			System.out.println(com.getResult());
			System.out.println("--------------");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clientEditCar(){
		Car c = new Car();
		Command com = new Command();
		System.out.println("Which car do you want to edit?");
		clientListCar();
		System.out.println("Please input Cid: ");
		c.setCid(input.nextInt());
		System.out.println("Please input Title: ");
		c.setTitle(input.next());
		System.out.println("Please input Type");
		c.setType(input.next());
		System.out.println("Please input Color");
		c.setColor(input.next());
		System.out.println("Please input Amount");
		c.setAmount(input.nextInt());
		com.setCom("EDITACAR");
		com.setData(c);
		try {
			socket = new Socket("127.0.0.1",8888);
			sendData(com);
			com = getData();
			System.out.println(com.getResult());
			System.out.println("--------------");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clientRemoveCar(){
		Command com = new Command();
		System.out.println("Which car do you want to remove?");
		clientListCar();
		System.out.println("Please input Cid: ");
		com.setData(input.nextInt());
		com.setCom("REMOVEACAR");
		try {
			socket = new Socket("127.0.0.1",8888);
			sendData(com);
			com = getData();
			System.out.println(com.getResult());
			System.out.println("--------------");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clientSearchACar(){
		Command com = new Command();
		System.out.println("You can search by 1.Title 2.Type 3.Color");
		int func = input.nextInt();
		String name = null;
		switch(func){
			case 1:
				name = "Title";
				break;
			case 2:
				name = "Type";
				break;
			case 3:
				name = "Color";
				break;
			default:
				name = "";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("Input the value:");
		String value = input.next();
		map.put(name, value);
		com.setData(map);
		com.setCom("SEARCHACAR");
		try {
			socket = new Socket("127.0.0.1",8888);
			sendData(com);
			com = getData();
			System.out.println(com.getResult());
			System.out.println("--------------");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clientRentACar(){
		Command com = new Command();
		System.out.println("Which car do you want to rent?");
		clientListCar();
		System.out.println("Please input Cid: ");
		int[] usercar = {input.nextInt(), currentUser.getUid()};
		com.setData(usercar);
		com.setCom("RENTACAR");
		try {
			socket = new Socket("127.0.0.1",8888);
			sendData(com);
			com = getData();
			System.out.println(com.getResult());
			System.out.println("--------------");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clientShowOrders(){
		List<Order> olist = new ArrayList<Order>();
		Command com = new Command();
		int uid = currentUser.getUid();
		com.setData(uid);
		com.setCom("LISTALLORDERS");
		try {
			socket = new Socket("127.0.0.1",8888);
			sendData(com);
			com = getData();
			System.out.println(com.getResult());
			olist = (List<Order>) com.getData();
			for(Order o : olist)
				System.out.println(o);
			System.out.println("--------------");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendData(Command com){
		try {
			outToServer = new ObjectOutputStream(socket.getOutputStream());
			outToServer.writeObject(com);
			outToServer.flush();
			socket.shutdownOutput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Command getData(){
		Command com = null;
		try {
			inFromServer = new ObjectInputStream(socket.getInputStream());
			com = (Command) inFromServer.readObject();
			socket.shutdownInput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return com;
	}
}
