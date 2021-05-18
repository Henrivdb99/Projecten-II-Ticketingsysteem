package domein.models;

import java.time.LocalDate;

public interface GebruikerGegevens {

	public int getId();
	public String getEmailAdres();
	public String getWachtwoord();
	public LocalDate getRegistratieDatum();
	public GebruikerStatus getStatus();
	public String getNaam();
	public String getVoornaam();
	public String[] getAdres();
	public String[] getTelefoonnummers();
	public WerknemerRol getRol();
	public String getBedrijfsnaam();
}
