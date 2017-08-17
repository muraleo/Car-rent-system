package car_rent_system.server;
import java.net.*;
import java.util.Scanner;

import car_rent_system.dbutil.Command;
import car_rent_system.model.User;

import java.io.*;

public class Client2 {
	private static final String LOGIN = ""+
			"[LOG IN]\t"+
			"[REGISTER]";
	private static final String CONTEXT="" +
			"Function List\n" +
			"*[QUERY/Q]:List all available car\n" +
			"*[GET/G]:List information about one car\n" +
			"*[ADD/A]:Add a new Car\n" +
			"*[UPDATE/U]:Edit a Car\n" +
			"*[DELETE/D]:Remove a Car\n" +
			"*[SEARCH/S]:Search a car\n" +
			"*[EXIT/E]:Exit\n" +
			"*[BREAK/B]:Back to MainMenu";
	
	private static final String OPERATION_LOGIN="LOGIN";
	private static final String OPERATION_REGISTER="REGISTER";
	private static final String OPERATION_MAIN="MAIN MENU";
	private static final String OPERATION_QUERY="QUERY";
	private static final String OPERATION_GET="GET";
	private static final String OPERATION_ADD="ADD";
	private static final String OPERATION_UPDATE="UPDATE";
	private static final String OPERATION_DELETE="DELETE";
	private static final String OPERATION_SEARCH="SEARCH";
	private static final String OPERATION_EXIT="EXIT";
	private static final String OPERATION_BREAK="BREAK";
	private static Socket socket = null;
	
	public Client2() throws IOException{
		socket = new Socket("127.0.0.1",8888);
	}
	
	public void logIn(){
		System.out.println("------Welcome to Leo car rent!------");
		System.out.println(LOGIN);
		System.out.println("------------------------------------");
		Scanner scan = new Scanner(System.in);
		while(true){
			String command = scan.next().toUpperCase().toString();
			if(OPERATION_LOGIN.equals(command)){
				System.out.println("Please input user name:");
				String name = scan.next().toUpperCase().toString();
				System.out.println("Please input password:");
				String password = scan.next().toUpperCase().toString();
				User u = new User(name, password);
				try {
					ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
			        ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());
					Command com = new Command();
					com.setCom("LOGIN");
					com.setData(u);
					outToServer.writeObject(com);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			System.out.println(scan.next());
		}
	}
	
	public static void main(String[] args) {
		while(true){
			try {
				// 1.create socket, indicate ip address, and port number
				// socket = new Socket("127.0.0.1",8888);
				// 2. get output stream to send message to server
				OutputStream os = socket.getOutputStream();
				PrintWriter pw = new PrintWriter(os);
				System.out.println("Login please");
				pw.write("name: xiaom, password: 123");
				pw.flush();
				
				// 3. close outputstream
				socket.shutdownOutput();
				// 3.5 get response from server
				InputStream is = socket.getInputStream();
				InputStreamReader ir = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(ir);
				String info = null;
				while((info = br.readLine())!=null){
					System.out.println(info);
				}
				// 4. close other resources
				pw.close();
				os.close();
				socket.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
