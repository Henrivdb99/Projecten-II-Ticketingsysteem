package domein;

import java.io.Serializable;

public class ContractType implements Serializable {

    private int contractTypeId;
    private String naam;
    private ContractEnContractTypeStatus status;
    private ManierVanAanmakenTicket manierVanAanmakenTicket;
    private TijdstipTicketAanmaken TijdstipTicketAanmaken;
    private int minimaleDoorlooptijd;
    private int maximaleAfhandeltijd;
    private double contractPrijs;
    
    
	public ContractType() {

	}
	
	
	
	public ContractType(String naam, ContractEnContractTypeStatus status,
			ManierVanAanmakenTicket manierVanAanmakenTicket, domein.TijdstipTicketAanmaken tijdstipTicketAanmaken,
			int minimaleDoorlooptijd, int maximaleAfhandeltijd, double contractPrijs) {
		this.naam = naam;
		this.status = status;
		this.manierVanAanmakenTicket = manierVanAanmakenTicket;
		TijdstipTicketAanmaken = tijdstipTicketAanmaken;
		this.minimaleDoorlooptijd = minimaleDoorlooptijd;
		this.maximaleAfhandeltijd = maximaleAfhandeltijd;
		this.contractPrijs = contractPrijs;
	}



	public int getContractTypeId() {
		return contractTypeId;
	}
	public void setContractTypeId(int contractTypeId) {
		this.contractTypeId = contractTypeId;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public ContractEnContractTypeStatus getStatus() {
		return status;
	}
	public void setStatus(ContractEnContractTypeStatus status) {
		this.status = status;
	}
	public ManierVanAanmakenTicket getManierVanAanmakenTicket() {
		return manierVanAanmakenTicket;
	}
	public void setManierVanAanmakenTicket(ManierVanAanmakenTicket manierVanAanmakenTicket) {
		this.manierVanAanmakenTicket = manierVanAanmakenTicket;
	}
	public TijdstipTicketAanmaken getTijdstipTicketAanmaken() {
		return TijdstipTicketAanmaken;
	}
	public void setTijdstipTicketAanmaken(TijdstipTicketAanmaken tijdstipTicketAanmaken) {
		TijdstipTicketAanmaken = tijdstipTicketAanmaken;
	}
	public int getMinimaleDoorlooptijd() {
		return minimaleDoorlooptijd;
	}
	public void setMinimaleDoorlooptijd(int minimaleDoorlooptijd) {
		this.minimaleDoorlooptijd = minimaleDoorlooptijd;
	}
	public int getMaximaleAfhandeltijd() {
		return maximaleAfhandeltijd;
	}
	public void setMaximaleAfhandeltijd(int maximaleAfhandeltijd) {
		this.maximaleAfhandeltijd = maximaleAfhandeltijd;
	}
	public double getContractPrijs() {
		return contractPrijs;
	}
	public void setContractPrijs(double contractPrijs) {
		this.contractPrijs = contractPrijs;
	}
    
    
	
}
