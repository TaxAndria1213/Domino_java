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
	public static int port = 9634;
	private int nbJoueur = 3;
	private BufferedReader in = null;
	private PrintStream out = null;
	private String messageRecu = "";
	private String tour = "tour";
	private int tour_de = 0;
	
	public Serveur() {
		try {
			System.out.println("Démarrage du serveur...");
			Domino d = new Domino();
			ServerSocket socketServer = new ServerSocket(port);
			int compteur = 0;
			while(compteur < nbJoueur) {
				Socket joueur = socketServer.accept();
				Interface_global.socket_liste.add(joueur);
				interractionAvecSocket(joueur);
				ArrayList<String> part = d.zarainaNyVato();
				Interface_global.part_joueur.add(part);
				compteur++;
			}
			
			compteur = 0;
			while(compteur < nbJoueur) {
				PrintStream out = Interface_global.liste_outs.get(compteur);
				ArrayList<String> part = Interface_global.part_joueur.get(compteur);
				out.println(part);
				compteur++;
			}
			
			String reponse_joueur = "";
			String rep1;
			String rep2;
			String rep3;
			/*
	rep1 = Interface_global.reponse_trois_joueurs.get(0);
			rep2 = Interface_global.reponse_trois_joueurs.get(1);
			rep3 = Interface_global.reponse_trois_joueurs.get(2);
*/
			while(true){
				out = Interface_global.liste_outs.get(tour_de);
				out.println("C'est ton "+tour);
				out.flush();
				in = Interface_global.liste_ins.get(tour_de);
				if(Interface_global.reponse_trois_joueurs.size() == 3) {
					rep1 = Interface_global.reponse_trois_joueurs.get(0);
					rep2 = Interface_global.reponse_trois_joueurs.get(1);
					rep3 = Interface_global.reponse_trois_joueurs.get(2);
					if(rep1.equals("tsisy") && rep2.equals("tsisy") && rep3.equals("tsisy")) {
						break;
					}
					Interface_global.reponse_trois_joueurs.clear();
				}
				reponse_joueur = in.readLine();
				Interface_global.reponse_trois_joueurs.add(reponse_joueur);
				System.out.println("Réponse joueur "+Interface_global.socket_liste.get(tour_de).getRemoteSocketAddress()+" : "+reponse_joueur);
				System.out.println(Interface_global.reponse_trois_joueurs);
				
				
				PrintStream affichage_reste = null;
				for(int x = 0; x < nbJoueur; x++) {
					affichage_reste = Interface_global.liste_outs.get(x);
					affichage_reste.println(nombreLibre(reponse_joueur));
					affichage_reste.flush();
				}
				
				tour_de++;
				if(tour_de == 3) {
					tour_de = 0;
				}
				
			}
			
			
			System.out.println(Interface_global.part_joueur);
			System.out.println(Interface_global.liste_domino);
			System.out.println(Interface_global.adresse_joueur);
			
			for(int i = 0; i < Interface_global.socket_liste.size(); i++) {
				Interface_global.socket_liste.get(i).close();
			}
			socketServer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void interractionAvecSocket(Socket joueur) {
		try {
			System.out.println("Connexion avec : "+joueur.getRemoteSocketAddress());
			in = new BufferedReader(new InputStreamReader(joueur.getInputStream()));
			out = new PrintStream(joueur.getOutputStream());
			Interface_global.liste_ins.add(in);
			Interface_global.liste_outs.add(out);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String nombreLibre(String reponse) {
		String resultat = "pas de resultat";
		if( Interface_global.Res.size() <= 1) {
			Interface_global.Res.add(reponse);
		}else {
			for(int i = 0; i < Interface_global.Res.size(); i++) {
				String rep1 = Interface_global.Res.get(i);
				String rep[] = rep1.split("-");
				for(int j = 0; j < rep.length; j++) {
					Interface_global.reste.add(rep[j]);
				}
			}
			
			String nb = null, compare = null, resultat_final = "";
			
			for(int x = 0; x < Interface_global.reste.size(); x++) {
				nb = Interface_global.reste.get(x);
				for(int y = x+1; y < Interface_global.reste.size(); y++) {
					if(nb.equals(Interface_global.reste.get(y))) {
						Interface_global.reste.remove(y);
					}
				}
			}
			
			Interface_global.Res.clear();
			Interface_global.Res.add(Interface_global.reste.get(0)+"-"+Interface_global.reste.get(1));
			resultat = Interface_global.reste.get(0)+"-"+Interface_global.reste.get(1);
		}
		
		return resultat;

	}
}
