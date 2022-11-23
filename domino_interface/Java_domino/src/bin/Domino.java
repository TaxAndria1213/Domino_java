package bin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InterfaceAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import Interface.Interface_global;

public class Domino {
	
	public Domino() {
		domi_piece();
	}
	
	private void domi_piece() {
		ArrayList<String> tabDomino = new ArrayList<String>();
		String fichierDomino = "./src/fichier/domino.txt";
		try (BufferedReader br = new BufferedReader(new FileReader(fichierDomino))) {
			String line;
			while((line = br.readLine()) != null) {
				Interface_global.liste_domino.add(line);
			}
			
		} catch (IOException e) {
			System.out.println("Une érreur est survenu");
			e.printStackTrace();
		}
		
	}
	
	public void afficherListeDomino() {
		System.out.println(Interface_global.liste_domino);
	}
	
	public ArrayList<String> zarainaNyVato() {
		Random rand = new Random();
		int pointeur = 0;
		ArrayList<String> anjara_vato = new ArrayList<String>();
		int isan_ny_vato = 0;
		while(isan_ny_vato < 7) {
			pointeur = rand.nextInt(((Interface_global.liste_domino.size())-1)+1);
			anjara_vato.add(Interface_global.liste_domino.get(pointeur));
			isan_ny_vato++;
			Interface_global.liste_domino.remove(pointeur);
		}
		
		
		return anjara_vato;
	}
}
