package domein.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Contract implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contractId;
	private int doorlooptijd;
	@ManyToOne
	@JoinColumn(name="CONTRACTTYPE_ID")
	private ContractType contractType;
	private LocalDate startDatum;
	private LocalDate eindDatum;
	private ContractEnContractTypeStatus contractStatus;
	private Klant klant;

	public Contract() {

	}

	public Contract(int doorlooptijd, ContractType contractType, LocalDate startDatum, Klant klant) 
	{
		this(startDatum, contractType, doorlooptijd, klant, ContractEnContractTypeStatus.InBehandeling);
	}

	public Contract(LocalDate startDatum, ContractType contractType, int doorlooptijd, Klant klant,
			ContractEnContractTypeStatus contractStatus)
	{
		setDoorlooptijd(doorlooptijd);
		setContractType(contractType);
		setStartDatum(startDatum);
		setEindDatum(startDatum.plusYears(doorlooptijd));
		setContractStatus(contractStatus);
		setKlant(klant);
	}

	public int getContractId() {
		return contractId;
	}

	private void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public int getDoorlooptijd() {
		return doorlooptijd;
	}

	public void setDoorlooptijd(int doorlooptijd) {
		if (doorlooptijd > 3 || doorlooptijd < 1) {
			throw new IllegalArgumentException("Doorlooptijd moet binnen het domein [1, 3] liggen");
		}

		this.doorlooptijd = doorlooptijd;
	}

	public ContractType getContractType() {
		return contractType;
	}

	public void setContractType(ContractType contractType) {
		if (contractType == null) {
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

	public String getContractTypeNaam() {
		return contractType.getNaam();
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
