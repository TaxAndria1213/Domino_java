package Modele;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Domino_sur_table extends JPanel {
	public String valeurPresser;
	private String chemin;
	private BufferedImage domi;
	
	public Domino_sur_table(String nom) {
		
		/*
	try {
			chemin = "./image_domino_png/"+nom+".png";
			domi = ImageIO.read(new File(chemin));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(domi, 140, 140, null);
	}
}

