package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.controllers.SupportManagerController;
import domein.models.Actemium;
import domein.models.WerknemerRol;

@ExtendWith(MockitoExtension.class)
class SupportManagerControllerTest {

	@Mock
	private Actemium actemiumDummy;
	@InjectMocks
	private SupportManagerController sc;
	

	@Test
	public void geefTypeGebruikerGeeftSupportManager() {
		Assertions.assertEquals(WerknemerRol.SupportManager, sc.geefAangemeldeGebruikerType());
	}
}
