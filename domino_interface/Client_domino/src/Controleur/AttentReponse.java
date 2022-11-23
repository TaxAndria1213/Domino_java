package Controleur;

import java.awt.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import Interface.Interface;
import Modele.Domino_sur_table;
import Pack_principal.ClientPrincipal;
import Vue.FenetreDeJeu;

public class AttentReponse extends Thread {
	private Socket soquette;
	private BufferedReader in;
	
	public AttentReponse(Socket soquette){
		super();
		this.soquette = soquette;
		try {
			in = ClientPrincipal.input;
		} catch (Exception e) {
			try {soquette.close();}
			catch(IOException ee) {}
			System.err.println("Excemtion en ouvrant des flots entrée/sortie");
			return;
		}
		this.start();
	}
	
	public synchronized void run() {
		String ip = soquette.getRemoteSocketAddress().toString();
		try {
			Color verte = new Color(151, 255, 63);
			Color orange = new Color(255, 151, 63);
			System.out.println("Connexion du client numero, IP = "+ip);
			while(true) {
				String msg = in.readLine();
				if(msg != null && !msg.equals("")) {
					System.out.println("Le serveur a reçu du client : "+msg);
					if(msg.equals("tour")) {
						FenetreDeJeu.label_test_etat.setText("C'est ton tour");
						FenetreDeJeu.label_test_etat.repaint();
						FenetreDeJeu.panel_etat.setBackground(verte);
						FenetreDeJeu.panel_etat.repaint();;

					}
					else if(msg.equals("NonTour")) {
						FenetreDeJeu.label_test_etat.setText("Attends ton tour");
						FenetreDeJeu.label_test_etat.repaint();
						FenetreDeJeu.panel_etat.setBackground(orange);
						FenetreDeJeu.panel_etat.repaint();;


					}
					else {
						Interface.liste_domi_sur_table.add(msg);
						System.out.println(Interface.liste_domi_sur_table);
						for(int i = 0; i < Interface.liste_domi_sur_table.size(); i++) {
							Domino_sur_table domi_table = new Domino_sur_table(Interface.liste_domi_sur_table.get(i));
							FenetreDeJeu.panel_table.add(domi_table);
						}
						FenetreDeJeu.panel_table.repaint();
					}
				}else {
					break;
				}
			}
			
			
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
