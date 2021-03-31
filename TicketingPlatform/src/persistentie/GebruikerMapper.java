package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domein.Gebruiker;

public class GebruikerMapper {
	private static final String SELECT_GEBRUIKER = "SELECT * FROM G23Ticketing WHERE Email = ?";

	public Gebruiker geefGebruiker(String email) {
		Gebruiker gebruiker = null;
		String wachtwoord = "";
		try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);PreparedStatement stat = conn.prepareStatement(SELECT_GEBRUIKER)) {
			stat.setString(1, email);
			try(ResultSet res = stat.executeQuery()) {
				while(res.next()) {
					wachtwoord = res.getString("wachtwoord");
				}
				if (! wachtwoord.isEmpty() ) {
					 gebruiker = new Gebruiker(email, wachtwoord);
					}

			}
		} catch (SQLException e) {
			throw new  RuntimeException(e);
		} 
		return gebruiker;
	}

}
