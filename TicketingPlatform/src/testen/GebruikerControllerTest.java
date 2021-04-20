package testen;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import controllers.GebruikerController;
import domein.Administrator;
import repository.GebruikerDaoJPA;

@ExtendWith(MockitoExtension.class)
class GebruikerControllerTest {

	@Mock
	private GebruikerDaoJPA gebruikerRepositoryDummy;
	@InjectMocks
	private GebruikerController dc;
	
	
	
	@ParameterizedTest
	@CsvSource({"klant@gmail.com, wachtwoord1", "supportmanager@gmail.com, wachtwoord2",
		"admin@gmail.com, wachtwoord3", "techinieker@gmail.com, wachtwoord4"})
	public void meldAanGebruikerJuisteGegevens(String email, String wachtwoord) {
		//mock trainen
		Mockito.when(gebruikerRepositoryDummy.getGebruikerByEmail(email)).
		thenReturn(new Administrator(email, wachtwoord));
		
		//act
		dc.meldAan(email, wachtwoord);
		//assert
		Assertions.assertEquals(email, dc.getGebruiker().getEmailAdres());
		Assertions.assertEquals(wachtwoord, dc.getGebruiker().getWachtwoord());
		
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
		Assertions.assertThrows(IllegalArgumentException.class, () -> dc.meldAan(email, wachtwoord));
		Assertions.assertEquals(null, dc.getGebruiker());
		
		//mock verify
		Mockito.verify(gebruikerRepositoryDummy).getGebruikerByEmail(email);

	}

}
