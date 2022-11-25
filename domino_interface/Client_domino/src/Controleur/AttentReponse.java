package Controleur;

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Interface.Interface;
import Modele.Domino_sur_table;
import Pack_principal.ClientPrincipal;
import Vue.FenetreDeJeu;

public class AttentReponse extends Thread {
	public FenetreDeJeu fen;
	private Socket soquette;
	private BufferedReader in;
	
	public AttentReponse(Socket soquette, FenetreDeJeu fen){
		super();
		this.soquette = soquette;
		this.fen = fen;
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
		int longueur_bouton = 145;
		int hauteur_bouton = 72;
		try {
			Color verte = new Color(151, 255, 63);
			Color orange = new Color(255, 151, 63);
			Color bleu_table = new Color(79, 124, 253);
			System.out.println("Connexion du client numero, IP = "+ip);
			while(true) {
				String msg = in.readLine();
				if(!msg.equals("fin")) {
					if(msg != null && !msg.equals("")) {
						System.out.println("Le serveur a reçu du client : "+msg);
						if(msg.equals("tour")) {
							FenetreDeJeu.label_test_etat.setText("C'est ton tour");
							FenetreDeJeu.label_test_etat.repaint();
							FenetreDeJeu.conteneur_etat.setBackground(verte);
							FenetreDeJeu.conteneur_etat.repaint();
							

						}
						else if(msg.equals("NonTour")) {
							FenetreDeJeu.label_test_etat.setText("Attends ton tour");
							FenetreDeJeu.label_test_etat.repaint();
							FenetreDeJeu.conteneur_etat.setBackground(orange);
							FenetreDeJeu.conteneur_etat.repaint();


						}
						else if(msg.length() == 5) {
							FenetreDeJeu.lab_domy_libre.setText(msg);
							FenetreDeJeu.lab_domy_libre.repaint();
						}
						
						else if(!msg.equals("tsisy") && msg.length() == 3) {
							Interface.liste_domi_sur_table.add(msg);
							System.out.println(Interface.liste_domi_sur_table);
							Domino_sur_table domi_table = new Domino_sur_table(msg);
							JPanel panel_domi = new JPanel();
							domi_table.setPreferredSize(new Dimension(longueur_bouton, hauteur_bouton));
							System.out.println("Ito le message "+msg);
							panel_domi.add(domi_table);
							panel_domi.setBackground(bleu_table);
							panel_domi.setBorder(null);
							FenetreDeJeu.conteneur_domino_table.add(panel_domi);
							FenetreDeJeu.conteneur_domino_table.setBackground(bleu_table);
							FenetreDeJeu.panel_table.setBackground(bleu_table);
							FenetreDeJeu.panel_table.repaint();
						}
					}else {
						break;
					}
				}
				else {
					String message = in.readLine();
					System.out.println("mifarana amin'ny : "+message);
					JOptionPane.showMessageDialog(null, "La partie est terminer \n Vous avez gagné : "+message+" point(s)", "FIN", JOptionPane.INFORMATION_MESSAGE);
					fen.fenetre_principal.dispose();
					break;
				}
			}
			
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
