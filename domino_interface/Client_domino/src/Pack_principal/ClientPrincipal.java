package Pack_principal;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import Controleur.AttentReponse;
import Interface.Interface;
import Modele.Domino;
import Vue.FenetreDeJeu;

public  class ClientPrincipal {
	public static int nombre_joueur = 0;
	public static String reponse = "";
	final static int port = 9635;
	public static Socket socket;
	public static BufferedReader input;
	public static PrintStream output;
	
	public ClientPrincipal(FenetreDeJeu fen) {
		
		try {
			
			if(Interface.partJoueur.size() == 0) {
				InetAddress serveur = null;
				Socket socket = null;
				
				serveur = InetAddress.getByName("192.168.151.246");
				socket = new Socket(serveur, port);
				ClientPrincipal.socket = socket;

				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				ClientPrincipal.input = in;
				PrintStream out = new PrintStream(socket.getOutputStream());
				ClientPrincipal.output = out;

			}
			
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
				afficherCestTonTour(message);
				
				new AttentReponse(ClientPrincipal.socket, fen);
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
	
	public static void afficherCestTonTour(String message) {
		Color verte = new Color(151, 255, 63);
		Color orange = new Color(255, 151, 63);
		if(message.equals("tour")) {
			FenetreDeJeu.label_test_etat.setText("C'est ton tour");
			FenetreDeJeu.label_test_etat.repaint();
			FenetreDeJeu.panel_etat.setBackground(verte);
			FenetreDeJeu.panel_etat.repaint();
			

		}
		else if(message.equals("NonTour")) {
			FenetreDeJeu.label_test_etat.setText("Attends ton tour");
			FenetreDeJeu.label_test_etat.repaint();
			FenetreDeJeu.panel_etat.setBackground(orange);
			FenetreDeJeu.panel_etat.repaint();


		}
	}
}
