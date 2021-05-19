package testen;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.controllers.LoginController;
import domein.models.Werknemer;
import persistentie.GebruikerDaoJPA;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

	@Mock
	private GebruikerDaoJPA gebruikerRepositoryDummy;
	@InjectMocks
	private LoginController gc;
	
	
	
	@ParameterizedTest
	@CsvSource({"supportmanager@gmail.com, wachtwoord2",
		"admin@gmail.com, wachtwoord3", "techinieker@gmail.com, wachtwoord4"})
	public void meldAanWerkenemrJuisteGegevens(String email, String wachtwoord) {
		//mock trainen
		Werknemer werknemer = new Werknemer();
		werknemer.setEmailAdres(email);
		werknemer.setWachtwoord(wachtwoord);
		Mockito.when(gebruikerRepositoryDummy.getGebruikerByEmail(email)).
		thenReturn(werknemer);
		
		//act
		gc.meldAan(email, wachtwoord);
		//assert
		Assertions.assertEquals(email, gc.getAangemeldeGebruiker().getEmailAdres());
		Assertions.assertTrue(gc.getAangemeldeGebruiker().checkPassword(wachtwoord));
		
		//mock verify
		Mockito.verify(gebruikerRepositoryDummy).getGebruikerByEmail(email);

	}
	
	@ParameterizedTest
	@CsvSource({"onbestaand@gmail.com, wachtwoord1", "onbestaand@icloud.com, wachtwoord2",
		"onbestaand@gmail.com, wachtwoord3", "onbestaand@hotmail.com, wachtwoord4"})
	public void meldAanGebruikerFouteGegevens(String email, String wachtwoord) {
		//mock trainen
		Mockito.when(gebruikerRepositoryDummy.getGebruikerByEmail(email))
		.thenThrow(new EntityNotFoundException());
		
		//act
		
		//assert
		Assertions.assertThrows(IllegalArgumentException.class, () -> gc.meldAan(email, wachtwoord));
		Assertions.assertEquals(null, gc.getAangemeldeGebruiker());
		
		//mock verify
		Mockito.verify(gebruikerRepositoryDummy).getGebruikerByEmail(email);
	}
	
	@Test
	public void meldAanGebruikerFouteGegevensTimeout() {
		String email = "werknemer@gmail.com";
		String juisteWachtwoord = "123456";
		String fouteWachtwoord = "fout";
				
		//mock trainen
		Werknemer werknemer = new Werknemer();
		werknemer.setEmailAdres(email);
		werknemer.setWachtwoord(juisteWachtwoord);
		Mockito.when(gebruikerRepositoryDummy.getGebruikerByEmail(email)).
		thenReturn(werknemer);
		
		//act
		for (int x = 0; x < 5; x++) { //5 keer
			Assertions.assertThrows(IllegalArgumentException.class, () -> gc.meldAan(email, fouteWachtwoord)); //mislukt uiteraard
		}
		
		//assert
		Assertions.assertThrows(IllegalArgumentException.class, () -> gc.meldAan(email, juisteWachtwoord)); //mislukt wegens te veel pogingen
		Assertions.assertEquals(null, gc.getAangemeldeGebruiker());
		
		//mock verify
		Mockito.verify(gebruikerRepositoryDummy, Mockito.times(6)).getGebruikerByEmail(email);
	}

}
