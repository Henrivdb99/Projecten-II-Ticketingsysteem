package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.models.Klant;

public class KlantTest {
	
	private Klant klant;
	@BeforeEach
	public void before() {
		klant= new Klant();
	}
	
	@Test
	public void maaktCorrecteKlantAan()
	{
		klant.setVoornaam("Voornaam");
		klant.setNaam("Achternaam");
		
		Assertions.assertEquals("Voornaam", klant.getVoornaam());
		Assertions.assertEquals("Achternaam", klant.getNaam());
	}

}
