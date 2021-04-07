package domein;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ContractType implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contractTypeId;
    private String naam;
    private ContractEnContractTypeStatus status;
    private ManierVanAanmakenTicket manierVanAanmakenTicket;
    private TijdstipTicketAanmaken tijdstipTicketAanmaken;
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
		this.tijdstipTicketAanmaken = tijdstipTicketAanmaken;
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
		return tijdstipTicketAanmaken;
	}
	public void setTijdstipTicketAanmaken(TijdstipTicketAanmaken tijdstipTicketAanmaken) {
		this.tijdstipTicketAanmaken = tijdstipTicketAanmaken;
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
