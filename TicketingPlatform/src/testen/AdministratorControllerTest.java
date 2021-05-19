package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.controllers.AdministratorController;
import domein.models.Actemium;
import domein.models.GebruikerStatus;
import domein.models.Klant;

@ExtendWith(MockitoExtension.class)
class AdministratorControllerTest {

	@Mock
	private Actemium actemiumDummy;
	@InjectMocks
	private AdministratorController ac;


	@Test
	public void selecteerKlantBestaandId() {
		int klantId = 1;
		Klant klant1 = new Klant("klant@student.hogent.be", "wachtwoord1", GebruikerStatus.ACTIEF, "Jorissen", "Joris", new String[] {"Jorisstraat", "46", "9000","Gent"}, new String[] {"049952754", "092214365"}, "HoGent");
		klant1.setId(klantId);
		//mock trainen
		Mockito.when(actemiumDummy.geefKlant(klantId)).
			thenReturn(klant1);		
		//act
		ac.selecteerKlant(klantId);
		//assert
		Assertions.assertEquals(klant1, ac.getSelectedKlant());
		
		//mock verify
		Mockito.verify(actemiumDummy).geefKlant(klantId);
	}


}
