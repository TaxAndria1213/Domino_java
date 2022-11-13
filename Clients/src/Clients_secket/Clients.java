package Clients_secket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Clients {
	public void connection(int port, String ad_ip)
	{
		try 
		{
			InetAddress server = null;
			Socket socket = null;
			String text = "";
			
			server = InetAddress.getByName(ad_ip);
			socket = new Socket(server,port);
			
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream output = new PrintStream(socket.getOutputStream());
			Scanner scan = new Scanner(System.in);
			String message = "";
			System.out.println("envoie mon domino");
			do 
			{
				message = input.readLine();
				System.out.println(message);
				text = scan.nextLine();
				output.println(text);
				output.flush();
			}while(!text.equals("terminer"));
			socket.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
