package Vue;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Pack_principal.ClientPrincipal;

public class FenetreDeJeu {
	private JFrame fenetre_principal;
	private JButton btn_commencer;
	
	public FenetreDeJeu() {
		fenetre_principal = new JFrame();
		
		JPanel panel_principal = new JPanel();
		btn_commencer = new JButton("Commencer");
		panel_principal.add(btn_commencer);
		
		btn_commencer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		fenetre_principal.setSize(new Dimension(500, 350));
		fenetre_principal.setResizable(false);
		fenetre_principal.setLocationRelativeTo(null);
		fenetre_principal.setContentPane(panel_principal);
		fenetre_principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre_principal.setVisible(true);
	}
}
