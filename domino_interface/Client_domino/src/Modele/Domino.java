package Modele;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import Pack_principal.ClientPrincipal;

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
				
				
				ClientPrincipal.output.println(nom);
				ClientPrincipal.output.flush();
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
	
	public void attendreReponse() {
		try {
			ClientPrincipal.reponse = ClientPrincipal.input.readLine();
			System.out.println(ClientPrincipal.reponse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
