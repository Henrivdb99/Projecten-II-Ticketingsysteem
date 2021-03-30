package domein;

public class DomeinController {

	private Gebruiker gebruiker;
	private TicketRepository ticketRepo;
	private ContractRepository contractRepo;
	private GebruikerRepository gebruikerRepo;
	

	public DomeinController() {
	}



	public Gebruiker getGebruiker() {
		return gebruiker;
	}



	public TicketRepository getTicketRepo() {
		return ticketRepo;
	}



	public ContractRepository getContractRepo() {
		return contractRepo;
	}



	public GebruikerRepository getGebruikerRepo() {
		return gebruikerRepo;
	}



	public void meldAan(String email, String wachtwoord) {
		throw new UnsupportedOperationException();
	}
}
