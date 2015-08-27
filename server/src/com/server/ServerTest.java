package com.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {
	private static final int PORT = 8888;
	
	public static void main(String[] args) {
		Socket s = null;
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			
			s = serverSocket.accept();
			
			System.out.println("connection accepted:"+s);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())),true);
			
			while(true){
				String str = bufferedReader.readLine();
				if(str.equals("end")) break;
				System.out.println("Echoing:"+str);
				System.out.println(s.getRemoteSocketAddress());
				printWriter.println(str);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(s != null){
				System.out.println("closing.....");
				try {
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
