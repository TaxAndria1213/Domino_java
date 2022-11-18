package Modele;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Pack_principal.ClientPrincipal;
import Vue.FenetreDeJeu;

public class Domino extends JPanel {
	public String valeurPresser;
	private String chemin;
	private BufferedImage domi;
	
	public Domino(String nom) {
		
		try {
			chemin = "./image_domino_png/"+nom+".png";
			domi = ImageIO.read(new File(chemin));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println(nom);
				valeurPresser = nom;
				Domino.this.setVisible(false);
				FenetreDeJeu.label_test_etat.setText("attend ton tour");
				
				
				ClientPrincipal.output.println(nom);
				ClientPrincipal.output.flush();
				
				
				try {
					String domino_sur_table = "";
					domino_sur_table = ClientPrincipal.input.readLine();
					System.out.println("domy eo ambony latabatra : "+domino_sur_table);
					Domino_sur_table domi = new Domino_sur_table(domino_sur_table);
					
					//FenetreDeJeu.panel_table.repaint();
					
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				try {
					String mess = ClientPrincipal.input.readLine();
					System.out.println(mess);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(domi, 0, 0, null);
	}
}
