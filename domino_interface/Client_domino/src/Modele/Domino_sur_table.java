package Modele;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Domino_sur_table extends JPanel {
	public int droite;
	public int gauche;
	public String valeurPresser;
	private String chemin;
	private BufferedImage domi;
	
	public Domino_sur_table(String nom) {
		try {
				this.valeurPresser = nom;
				chemin = "./image_domino_png/"+nom+".png";
				domi = ImageIO.read(new File(chemin));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		SetDroite();
		setGauche();
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(domi, 0, 0, null);
	}
	
	public void SetDroite() {
		this.droite = valeurPresser.charAt(2);
	}
	
	public void setGauche() {
		this.gauche = valeurPresser.charAt(0);
	}
}

