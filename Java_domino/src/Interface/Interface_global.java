package Interface;

import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public interface Interface_global {
	public static ArrayList<String> liste_domino = new ArrayList<String>();
	public static ArrayList<Socket> socket_liste = new ArrayList<Socket>();
	public static ArrayList<ArrayList<String>> part_joueur = new ArrayList<ArrayList<String>>();
	public static ArrayList<InetAddress> adresse_joueur = new ArrayList<InetAddress>();
}
