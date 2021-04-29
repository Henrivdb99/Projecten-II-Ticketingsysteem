package testen;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.controllers.AdministratorController;
import domein.controllers.SupportManagerController;
import domein.controllers.TechniekerController;
import persistentie.GebruikerDaoJPA;

@ExtendWith(MockitoExtension.class)
class AangemeldeGebruikerControllerTest {

	@Mock
	private GebruikerDaoJPA gebruikerRepositoryDummy;
	@InjectMocks
	private AdministratorController ac;
	@InjectMocks
	private SupportManagerController sc;
	@InjectMocks
	private TechniekerController tc;
	


}
