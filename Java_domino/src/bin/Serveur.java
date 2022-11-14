package bin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Interface.Interface_global;


public class Serveur {
	public static int port = 9634; //varavarana ho an'ny joueurs
	private int nbJoueur = 3; //définition statique an'ny nombre de joueur
	private BufferedReader in = null;
	private PrintStream out = null;
	private String tour = "tour";
	private int tour_de = 0;
	public static ArrayList<String> Res = new ArrayList<String>(); //array liste misy ny nombre disponible eny amn table

	private ArrayList<String> res1 = new ArrayList<String>();
	private ArrayList<String> res2 = new ArrayList<String>();
	
	
	public Serveur() {
		try {
			System.out.println("Démarrage du serveur...");
			Domino d = new Domino();
			ServerSocket socketServer = new ServerSocket(port);
			int compteur = 0;
			/*
			 * raha mbola latsaky ny 3 ny joueurs de miandry fona le serveur
			 * 
			 * */
			while(compteur < nbJoueur) {
				Socket joueur = socketServer.accept();
				Interface_global.socket_liste.add(joueur);
				interractionAvecSocket(joueur);
				/*
				 * ito no mizara anle domy fito
				 * */
				ArrayList<String> part = d.zarainaNyVato();
				
				/*
				 * ao anaty Interface_global no misy anle vaton'ny joueur
				 * 
				 * */
				Interface_global.part_joueur.add(part);
				compteur++;
			}
			
			/*
			 * eto no zarainy makany amin'ny joueur tsirairay ny anjara vatony
			 * 
			 * */
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
			 * manomboka milalao amzay ny joueurs
			 * 
			 * */
			while(true){
				/*
				 * mandefa anle message "c'est ton tour" any amin'ny joueur[compteur]
				 * 
				 * */
				out = Interface_global.liste_outs.get(tour_de);
				out.println("C'est ton "+tour+" tes pieces disponibles : "+Interface_global.part_joueur.get(tour_de));
				out.flush();
				
				/*
				 * eto ndray no mandray ny domy nalefan'ny joueur ilay serveur
				 * 
				 * */
				in = Interface_global.liste_ins.get(tour_de);
				
				/*
				 * Refa avy mamaly daoly le joueur 3 de ataony amzay ny instruction eto
				 * ito hamantarana we tapitra ny lalao rah ohatra ka "tsisy" daholo ny valin'ny an'ny joueur.
				 * de io reponse_trois_joueurs io ny tableau misy ny valiny nalefan'izy telo niaraka
				 * alaina any anatin'io tableau io zany ilay valiny na samy tsia daholo na mbola misy manana vato
				 * 
				 * */
				if(Interface_global.reponse_trois_joueurs.size() == 3) {
					rep1 = Interface_global.reponse_trois_joueurs.get(0);
					rep2 = Interface_global.reponse_trois_joueurs.get(1);
					rep3 = Interface_global.reponse_trois_joueurs.get(2);
					if(rep1.equals("tsisy") && rep2.equals("tsisy") && rep3.equals("tsisy")) {
						break;
					}
					/*
					 * de ito ra ohatra ka tsy mitovy daoly le reponse de fafany ndray le ao anaty tableau
					 * */
					Interface_global.reponse_trois_joueurs.clear();
				}
				
				/*
				 * maka valiny any amin'ny joueur
				 * */
				reponse_joueur = in.readLine();
				verifierReponse(reponse_joueur);
				System.out.println("Réponse avec tsisy : "+Interface_global.liste_reponse);
				
				/*
				 * condition raha telo no nanao tsisy
				 * 
				 * */
				if(Interface_global.liste_reponse.size() == 3) {
					break;
				}
				
				
				ajouterDansRes(reponse_joueur);
				partActuelleJoueur(tour_de, reponse_joueur);
				Interface_global.reponse_trois_joueurs.add(reponse_joueur);
				
				
				System.out.println("Réponse joueur "+Interface_global.socket_liste.get(tour_de).getRemoteSocketAddress()+" : "+reponse_joueur);
				System.out.println(Interface_global.reponse_trois_joueurs);
				//System.out.println("Dans Res : "+Res);
				
				/*
				 * eto indray ny manao ny mampiseho anle domy afaka alefa
				 * rah ohatra ka 3-5 5-6 de "3-6" no avoakany
				 * */
				if(Res.size() > 1 && !reponse_joueur.equals("tsisy")) {
					String[] resultat1;
					String[] resultat2;
					//System.out.println("Dans res : plus de 2");
					resultat1 = Res.get(0).split("-");
					resultat2 = Res.get(1).split("-");
					
					for(int i=0 ; i < 2; i++) {
						res1.add(resultat1[i]);
						res2.add(resultat2[i]);
					}
					
					
					//System.out.println(res1 +"  /  "+res2);
					for(int x = 0; x < res1.size(); x++) {
						for(int y = 0; y < res2.size(); y++) {
							if(res1.get(x).equals(res2.get(y))) {
								res1.remove(x);
								res2.remove(y);
							}
						}
					}
					System.out.println("Résultat sur table : "+res1 +"  /  "+res2);
					Res.clear();
					Res.add(res1.get(0)+"-"+res2.get(0));
					res1.clear();
					res2.clear();
				}
				System.out.println("Reste sur table est : "+Res);

				/*
				 * ito no manova ny tour.
				 * */
				tour_de++;
				
				/*
				 * rah ohatra ka tonga amin'ny tour'ny fahatelo de miverina zero ny tour.
				 * 
				 * */
				if(tour_de == 3) {
					tour_de = 0;
				}
			}
			
			
			
			
			System.out.println(Interface_global.part_joueur);
			System.out.println(Interface_global.liste_domino);
			System.out.println(Interface_global.adresse_joueur);
			
			
			calculSommePartFinale();
			
			System.out.println(Interface_global.somme_part);
			
			int gagnant = chercherGangant();
			for(int i = 0; i < nbJoueur; i++) {
				if(i == gagnant) {
					Interface_global.liste_outs.get(i).println("Vous êtes le gagnant ! bravo");
					Interface_global.liste_outs.get(i).flush();
				}
				else {
					Interface_global.liste_outs.get(i).println("le joueur "+(gagnant+1)+" a gangé la partie");
				}
			}
			
			int gainFinal =0;
			for(int i = 0; i < nbJoueur; i++) {
				if(i != gagnant) {
					gainFinal += Interface_global.somme_part.get(i);
				}
				
			}
			
			
			/* refa vita ny lalao de idina eto ny socket client sy socketServeur */
			for(int i = 0; i < Interface_global.socket_liste.size(); i++) {
				Interface_global.liste_outs.get(i).println("Gain du gagnant : "+gainFinal);
				Interface_global.socket_liste.get(i).close();
			}
			socketServer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * ito no manampy joueur any anaty tableau sy ilay BufferReader ary ilay PrintStream
	 * 
	 * */
	
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
	
	
	public void ajouterDansRes(String reponse) {
	if(!reponse.equals("tsisy"))
		Res.add(reponse);
	}
	
	
	/*
	 * part anle joueur après ny tour-ny.
	 * 
	 * */
	public void partActuelleJoueur(int index, String reponse) {
		ArrayList<String> part = Interface_global.part_joueur.get(index);
		if(!reponse.equals("tsisy")) {
			for(int i = 0; i < part.size(); i++) {
				String reponse_inverse = "";
				for(int compte_inverse = reponse.length()-1; compte_inverse >= 0; compte_inverse--) {
					reponse_inverse += reponse.charAt(compte_inverse);
				}
				if(part.get(i).equals(reponse) || part.get(i).equals(reponse_inverse)) {
					part.remove(i);
				}
			}
		}
	}
	
	public void verifierReponse(String reponse) {
		if(reponse.equals("tsisy")) {
			Interface_global.liste_reponse.add(reponse);
		}
		else {
			Interface_global.liste_reponse.clear();
		}
	}
	
	public int chercherGangant() {
		int compteur = 0;
		int min, compare;
		
		compare = Interface_global.somme_part.get(compteur);
		min = compteur;
		
		for(int i = 1; i < Interface_global.somme_part.size(); i++) {
			if(compare > Interface_global.somme_part.get(i)) {
				compare = Interface_global.somme_part.get(i);
				min = i;
			}
		}
		
		return min;
	}
	
	public void calculGain() {
		
	}
	
	public void calculSommePartFinale() {
		for(int i = 0; i < nbJoueur; i++) {
			ArrayList<String> stockResteJoueur = Interface_global.part_joueur.get(i);
			ArrayList<Integer> listeEntierDesRestes = new ArrayList<Integer>();
			
			for(int j = 0; j < stockResteJoueur.size(); j++) {
				String[] rest = stockResteJoueur.get(j).split("-");
				for(int x = 0; x < rest.length; x++) {
					listeEntierDesRestes.add(Integer.parseInt(rest[x]));
				}
			}
			
			Interface_global.somme_part.add(calculSommeDansArrayList(listeEntierDesRestes));
		}
	}
	
	public int calculSommeDansArrayList(ArrayList<Integer> list) {
		int somme = 0;
		for(int i = 0; i < list.size(); i++) {
			somme += list.get(i);
		}
		return somme;
	}
}
