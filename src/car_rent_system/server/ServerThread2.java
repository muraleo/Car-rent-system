package car_rent_system.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread2 extends Thread{
	// 1. socket corresponding thread
	Socket socket = null;
	
	public ServerThread2(Socket socket){
		this.socket = socket;
	}
	
	// thread's run method
	public void run(){
		InputStream is = null;
		InputStreamReader ir = null;
		BufferedReader br = null;
		OutputStream os = null;
		PrintWriter pw = null;
		try {
			// 3.get client input stream
			is = socket.getInputStream();
			ir = new InputStreamReader(is);
			br = new BufferedReader(ir);
			// 4.read client input
			String info = null;
			while((info = br.readLine())!= null){
						System.out.println("Client "+info);
					}
			// 5.shutdown socket
			socket.shutdownInput();
			// 5.5 response client
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			pw.write("connected to server successfully");
			pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			// 6.close resourse
			try {
				if(pw!=null)
					pw.close();
				if(os!=null)
					os.close();
				if(br!=null)
					br.close();
				if(ir!=null)
					ir.close();
				if(is!=null)
					is.close();
				if(socket!=null)
					socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
