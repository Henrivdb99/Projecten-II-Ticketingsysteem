package domein;

public class Gebruiker {

	private String emailAdres;
	private String wachtwoord;
	
	
	public Gebruiker(String emailAdres, String wachtwoord) {
		this.emailAdres = emailAdres;
		this.wachtwoord = wachtwoord;
	}
	
	
	public Gebruiker() {

	}


	public String getEmailAdres() {
		return emailAdres;
	}
	public String getWachtwoord() {
		return wachtwoord;
	}
	
	
}
