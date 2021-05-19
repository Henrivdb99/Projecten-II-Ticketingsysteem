package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.controllers.TechniekerController;
import domein.models.Actemium;
import domein.models.WerknemerRol;

@ExtendWith(MockitoExtension.class)
class TechniekerControllerTest {

	@Mock
	private Actemium actemiumDummy;
	@InjectMocks
	private TechniekerController tc;
	

	@Test
	public void geefTypeGebruikerGeeftTechnieker() {
		Assertions.assertEquals(WerknemerRol.Technieker, tc.geefAangemeldeGebruikerType());
	}
}
