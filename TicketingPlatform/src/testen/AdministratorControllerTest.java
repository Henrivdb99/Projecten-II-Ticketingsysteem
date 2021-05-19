package testen;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.controllers.AdministratorController;
import domein.models.Actemium;
import domein.models.Contract;
import domein.models.ContractGegevens;
import domein.models.GebruikerStatus;
import domein.models.Klant;
import javafx.collections.ObservableList;

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
	public void selecteerKlantBestaandId() {
		int klantId = 1;
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

	@Test
	public void geefContractenBestaandeKlant() {
		int klantId = 1;
		klant1.setId(klantId);
		List<Contract> contracts = new ArrayList<Contract>();
		Contract contract1 = new Contract();
		contracts.add(contract1);
		klant1.setContracten(contracts);
		
		//mock trainen
		Mockito.when(actemiumDummy.geefKlant(klantId)).
			thenReturn(klant1);		
		//act
		ac.selecteerKlant(klantId);
		ObservableList<ContractGegevens> gegevenContracts = ac.geefContracten(klantId);
		//assert
		Assertions.assertEquals(1, gegevenContracts.size());
		
		//mock verify
		Mockito.verify(actemiumDummy).geefKlant(klantId);
	}

}
