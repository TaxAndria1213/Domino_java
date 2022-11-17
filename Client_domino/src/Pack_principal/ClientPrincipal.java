package Pack_principal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import Interface.Interface;
import Vue.FenetreDeJeu;

public class ClientPrincipal {
	final static int port = 9635;
	
	public ClientPrincipal() {
		String envoi = "";

		try {
			InetAddress serveur = null;
			Socket socket = null;
			
			serveur = InetAddress.getByName("127.0.0.1");
			socket = new Socket(serveur, port);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream out = new PrintStream(socket.getOutputStream());
			Scanner entree = new Scanner(System.in);
			String message = "";
			System.out.println("Reception de message...");
			
			message = in.readLine();
			if(message.equals("Démarrer")) {
				FenetreDeJeu fenetre_jeux = new FenetreDeJeu();
				
				message = in.readLine();
				System.out.println(message);
				
				
				/*
				 * antsoina le méthode mi-créer anle domino ato amin'ny client
				 * 
				 * */
				creerListeDomino(message);
				
				
				System.out.println("Ma part de Domino : "+Interface.partJoueur);
				
				/*
				 * boucler les réponses
				 * 
				 * */
				
				while(true) {
					message = in.readLine();
					
					System.out.println(message);
					
					if(message.equals("fin")) {
						break;
					}
					
					envoi = entree.nextLine();
					out.println(envoi);
					out.flush();
					/*
					 * fafana ao amin'ny ArrayList partJoueur ilay pièce avy nariany.
					 * */
					partActuelleJoueur(envoi);
					
					System.out.println("le reste de ma part : "+Interface.partJoueur);
					
				}
				socket.close();
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
}
