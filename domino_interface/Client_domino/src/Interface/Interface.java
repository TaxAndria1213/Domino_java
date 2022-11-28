package Interface;

import java.util.ArrayList;

import Modele.Domino;
import Modele.Domino_sur_table;

public interface Interface {
	public ArrayList<String> partJoueur = new ArrayList<String>();
	public ArrayList<Domino> liste_domino = new ArrayList<Domino>();
	public static ArrayList<Domino_sur_table> domi_sur_table = new ArrayList<Domino_sur_table>();
}
