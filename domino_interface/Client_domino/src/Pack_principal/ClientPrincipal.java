package Pack_principal;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Interface.Interface;
import Modele.Domino;
import Vue.FenetreDeJeu;

public  class ClientPrincipal {
	public static int nombre_joueur = 0;
	final static int port = 9635;
	public static Socket socket;
	public static BufferedReader input;
	public static PrintStream output;
	
	public ClientPrincipal(FenetreDeJeu fen) {
		String envoi = "";
		
		try {
			
			if(Interface.partJoueur.size() == 0) {
				InetAddress serveur = null;
				Socket socket = null;
				
				serveur = InetAddress.getByName("127.0.0.1");
				socket = new Socket(serveur, port);
				ClientPrincipal.socket = socket;
				
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				ClientPrincipal.input = in;
				PrintStream out = new PrintStream(socket.getOutputStream());
				ClientPrincipal.output = out;
			}
			
			Scanner entree = new Scanner(System.in);
			String message = "";
			System.out.println("Reception de message...");
			
			message = ClientPrincipal.input.readLine();
			
			if(message.equals("Démarrer")) {
				message = ClientPrincipal.input.readLine();
				try {
					ClientPrincipal.nombre_joueur = Integer.parseInt(message);
					System.out.println("le nombre de joueur est : "+ClientPrincipal.nombre_joueur);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				message = ClientPrincipal.input.readLine();
				creerListeDomino(message);
				poserLesImagesDomino(fen);
				System.out.println(Interface.partJoueur);
				
				message = ClientPrincipal.input.readLine();
				System.out.println(message);
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * ajouter sa part dans le tableau part
	 * 
	 * */

	private static void creerListeDomino(String message) {
		String mess_part = "";
		for(int i = 0; i < message.length(); i++) {
			if(message.charAt(i) != '[' && message.charAt(i) != ']'&& message.charAt(i) != ' ') {
				mess_part += message.charAt(i);
			}
		}
		
		String[] part_tableau = mess_part.split(",");
		for(int i = 0; i < part_tableau.length; i++) {
			Interface.partJoueur.add(part_tableau[i]);
		}
	}
	
	/*
	 * part anle joueur après ny tour-ny.
	 * 
	 * */
	public static void partActuelleJoueur(String reponse) {
		ArrayList<String> part = Interface.partJoueur;
		if(!reponse.equals("tsisy")) {
			for(int i = 0; i < part.size(); i++) {
				String reponse_inverse = "";
				for(int compte_inverse = reponse.length()-1; compte_inverse >= 0; compte_inverse--) {
					reponse_inverse += reponse.charAt(compte_inverse);
				}
				if(part.get(i).equals(reponse) || part.get(i).equals(reponse_inverse)) {
					Interface.partJoueur.remove(i);
				}
			}
		}
	}
	
	public void poserLesImagesDomino(FenetreDeJeu fen) {
		for(int i = 0; i < Interface.partJoueur.size(); i++) {
			//System.out.println(Interface.partJoueur.get(i));
			Domino domi = new Domino(Interface.partJoueur.get(i));
			fen.panel_contenant_dominos.add(domi);
		}
	}
}
