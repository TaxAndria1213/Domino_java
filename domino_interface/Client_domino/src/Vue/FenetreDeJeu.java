package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Pack_principal.ClientPrincipal;


public class FenetreDeJeu {
	
	private JFrame fenetre_principal;
	public JButton bouton_pret = new JButton("Prêt");
	public JLabel label_test_etat = new JLabel("affichage des états");
	public JPanel panel_principal = new JPanel();
	public JPanel panel_droite = new JPanel();
	public JPanel panel_contenant_dominos = new JPanel();
	public JPanel panel_table_et_etat = new JPanel();
	public static JPanel panel_table = new JPanel();
	public JPanel panel_etat = new JPanel();
	public JButton bouton_tsisy = new JButton("Tsy misy");


	
	public FenetreDeJeu() {
		charger();
	}
	
	private void charger() {
		int longueur_bouton = 145;
		int hauteur_bouton = 72;
		
		fenetre_principal = new JFrame();
		
		
		/*
	bouton_pret.setPreferredSize(new Dimension(longueur_bouton, hauteur_bouton));
		bouton_pret.setBackground(new Color(255, 50, 50));
		bouton_pret.setVisible(false);
*/
		panel_principal.setLayout(new BorderLayout());
		
		//paneau de gauche contenant la liste des dominos
		panel_droite.setBackground(new Color(255, 255, 255));
		panel_droite.setLayout(new BorderLayout());
		panel_principal.add(panel_droite, BorderLayout.EAST);
		
		bouton_tsisy.setPreferredSize(new Dimension(longueur_bouton + 10, hauteur_bouton + 10));
		bouton_tsisy.setBackground(new Color(255, 50, 50));
		
		
		panel_contenant_dominos.setLayout(new GridLayout(0,1));
		//panel_contenant_dominos.add(bouton_pret);
		panel_droite.add(panel_contenant_dominos, BorderLayout.CENTER);
		panel_droite.add(bouton_tsisy, BorderLayout.SOUTH);
		
		panel_table_et_etat.setLayout(new BorderLayout());
		panel_table_et_etat.setBackground(new Color(63, 125, 255));
		panel_principal.add(panel_table_et_etat, BorderLayout.CENTER);
		
		panel_table_et_etat.add(panel_etat, BorderLayout.SOUTH);
		panel_etat.setBackground(new Color(151, 255, 63));
		panel_etat.add(label_test_etat);
		
		panel_table.setLayout(new BorderLayout());
		panel_table.setBackground(new Color(79, 124, 253));
		panel_table_et_etat.add(panel_table, BorderLayout.CENTER);
		
		
		/*
		 * actions sur les boutons :
		 * */
		bouton_tsisy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ClientPrincipal.output.println("tsisy");
				ClientPrincipal.output.flush();
			}
		});
		
		
		fenetre_principal.setContentPane(panel_principal);
		fenetre_principal.setExtendedState(JFrame.MAXIMIZED_BOTH);
		fenetre_principal.setUndecorated(true);
		fenetre_principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre_principal.setVisible(true);
	}
}
