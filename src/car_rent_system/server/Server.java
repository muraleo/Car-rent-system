package car_rent_system.server;

import java.io.*;
import java.net.*;

public class Server {

	public static void main(String[] args) {
		try {
			// 1.create a server socket, indicate port
			ServerSocket ss = new ServerSocket(8888);
			// 2.call accept() to listen
			System.out.println("Server is start, waiting for client");
			//Socket socket = null;
			while(true){
				Socket socket = ss.accept();
				ServerThread st = new ServerThread(socket);
				st.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
