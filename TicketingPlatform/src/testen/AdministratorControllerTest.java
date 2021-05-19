package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.controllers.AdministratorController;
import domein.models.Actemium;
import domein.models.GebruikerStatus;
import domein.models.Klant;
import domein.models.WerknemerRol;

@ExtendWith(MockitoExtension.class)
class AdministratorControllerTest {

	@Mock
	private Actemium actemiumDummy;
	@InjectMocks
	private AdministratorController ac;
	
	private Klant klant1;

	@BeforeEach
	public void before() {
		klant1 = new Klant("klant@student.hogent.be", "wachtwoord1", GebruikerStatus.ACTIEF, "Jorissen", "Joris", new String[] {"Jorisstraat", "46", "9000","Gent"}, new String[] {"049952754", "092214365"}, "HoGent");
	}
	

	@Test
	public void geefTypeGebruikerGeeftAdministrator() {

		Assertions.assertEquals(WerknemerRol.Administrator, ac.geefAangemeldeGebruikerType());
		

	}

}
