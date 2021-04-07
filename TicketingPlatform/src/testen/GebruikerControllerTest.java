package testen;

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
import repository.*;
import controllers.*;

@ExtendWith(MockitoExtension.class)
class GebruikerControllerTest {

	@Mock
	private GebruikerDaoJPA gebruikerRepositoryDummy;
	@InjectMocks
	private GebruikerController dc;
	
	
	@BeforeEach
	public void before() {
	dc = new GebruikerController();
	}

	
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

}
