package bin;

import java.sql.*;

public class Connexion {
	public Connexion(String nom, int scoreAjout) {
		int score = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/domino", "root", "");
			PreparedStatement ps = con.prepareStatement("SELECT * FROM joueur");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				if(rs.getString(2).equals(nom)) {
					score = rs.getInt(3);
					score += scoreAjout;
					Statement state = con.createStatement();
					String update = "UPDATE `joueur` SET `SCORE`='"+score+"' WHERE `NOM_JOUEUR` = '"+nom+"'";
					state.executeUpdate(update);
				}
			}
			
			
			
			
			
			PreparedStatement afficher = con.prepareStatement("SELECT * FROM joueur");
			ResultSet resultatAfficher = afficher.executeQuery();
			while(resultatAfficher.next()) {
				System.out.println(resultatAfficher.getInt(1)+"\t"+resultatAfficher.getString(2)+"\t"+resultatAfficher.getInt(3));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
