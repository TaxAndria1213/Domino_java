package bin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import Interface.Interface_global;


public class Serveur {
	final static int port = 9634;
	private Socket socket = null;
	Scanner sc = new Scanner(System.in);
	static ServerSocket socketServeur = null;
	private int nombreJoueur = 1;
	
	
	public Serveur(Socket client) {
		socket = client;
	}
	
	public Serveur() {
		String recu = "";
		try {
			Domino d = new Domino();
			int nbConnecte = 0;
			int nbJoueurMin = this.nombreJoueur;
			socketServeur = new ServerSocket(port);
			System.out.println("Lancement du serveur");
			while(nbConnecte < 2) {
				Socket socketClient = socketServeur.accept();
				Interface_global.socket_liste.add(socketClient);
				nbConnecte++;
				ArrayList<String> part = d.zarainaNyVato();
				Interface_global.part_joueur.add(part);
				//System.out.println(part);
				//System.out.println(Interface_global.liste_domino);
			}
			
			int compteur = 0;
			while(compteur < Interface_global.socket_liste.size()) {
				Serveur s = new Serveur(Interface_global.socket_liste.get(compteur));
				s.traitement(Interface_global.part_joueur.get(compteur));
				compteur++;
				
			}
			
			System.out.println(Interface_global.part_joueur);
			System.out.println(Interface_global.liste_domino);
			System.out.println(Interface_global.adresse_joueur);
			
			
			
		} catch (IOException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	public void traitement(ArrayList<String> array) {
		String envoi = "";
		ArrayList<String> message = array;
		try {
			System.out.println("Connexion avec le client : "+socket.getInetAddress());
			//envoi = sc.nextLine();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream out = new PrintStream(socket.getOutputStream());
			Interface_global.adresse_joueur.add(socket.getInetAddress());
			
			
			
			//System.out.println("Envoi du message");
			//out.println(envoi);
			//out.flush();
			
			//envoi = in.readLine();
			//System.out.println(envoi);
			
			//do {
				//envoi = in.readLine();
				//System.out.println(envoi);
				
				//envoi = sc.nextLine();
				out.println(message);
				out.flush();
			//}while(!envoi.equals("exit"));
			//socketServeur.close();
			//socket.close();
				
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void setNbJoueur(int nb) {
		this.nombreJoueur = nb;
	}
	
	/*
		public void attenteReponse() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
*/
	
}
