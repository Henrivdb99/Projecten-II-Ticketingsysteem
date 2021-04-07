package domein;

import java.io.Serializable;
import java.time.LocalDate;

public class Contract implements Serializable {
	
    private int contractId;
	private int doorlooptijd;
    private ContractType contractType;
    private LocalDate startDatum;
    private LocalDate eindDatum;
    private ContractEnContractTypeStatus contractStatus;
    private Klant klant;

 
    public Contract() 
    {
    	
    }

	public Contract(int doorlooptijd, ContractType contractType, LocalDate startDatum, LocalDate eindDatum,
			Klant klant) {
		this(doorlooptijd, contractType, eindDatum, eindDatum, klant, ContractEnContractTypeStatus.InBehandeling);
	}

	public Contract(int doorlooptijd, ContractType contractType, LocalDate startDatum, LocalDate eindDatum, 
			Klant klant, ContractEnContractTypeStatus contractStatus) {
		this.doorlooptijd = doorlooptijd;
		this.contractType = contractType;
		this.startDatum = startDatum;
		this.eindDatum = eindDatum;
		this.contractStatus = contractStatus;
		this.klant = klant;
	}

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public int getDoorlooptijd() {
		return doorlooptijd;
	}

	public void setDoorlooptijd(int doorlooptijd) {
        if (doorlooptijd > 3 || doorlooptijd < 1)
        {
            throw new IllegalArgumentException("Doorlooptijd moet binnen het domein [1, 3] liggen");
        }

		this.doorlooptijd = doorlooptijd;
	}

	public ContractType getContractType() {
		return contractType;
	}

	public void setContractType(ContractType contractType) {
		if (contractType == null)
        {
            throw new IllegalArgumentException("ContractType mag niet leeg zijn");
        }
		this.contractType = contractType;
	}

	public LocalDate getStartDatum() {
		return startDatum;
	}

	public void setStartDatum(LocalDate startDatum) {
		this.startDatum = startDatum;
	}

	public LocalDate getEindDatum() {
		return eindDatum;
	}

	public void setEindDatum(LocalDate eindDatum) {
		this.eindDatum = eindDatum;
	}


	public ContractEnContractTypeStatus getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(ContractEnContractTypeStatus contractStatus) {
		this.contractStatus = contractStatus;
	}

	public Klant getKlant() {
		return klant;
	}

	public void setKlant(Klant klant) {
		this.klant = klant;
	}
}
