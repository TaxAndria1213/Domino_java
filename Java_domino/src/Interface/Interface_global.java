package Interface;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public interface Interface_global {
	public static ArrayList<String> liste_domino = new ArrayList<String>();
	public static ArrayList<Socket> socket_liste = new ArrayList<Socket>();
	public static ArrayList<BufferedReader> liste_ins = new ArrayList<BufferedReader>();
	public static ArrayList<PrintStream> liste_outs = new ArrayList<PrintStream>();
	public static ArrayList<ArrayList<String>> part_joueur = new ArrayList<ArrayList<String>>();
	public static ArrayList<InetAddress> adresse_joueur = new ArrayList<InetAddress>();
	public static ArrayList<String> reponse_trois_joueurs = new ArrayList<String>();
	public static ArrayList<String> Res = new ArrayList<String>();
	public static ArrayList<String> reste = new ArrayList<String>();
}
