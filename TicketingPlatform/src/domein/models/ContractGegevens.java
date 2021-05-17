package domein.models;

import java.time.LocalDate;

public interface ContractGegevens {

	public int getContractNummer();
	public String getContractType();
	public ContractEnContractTypeStatus getContractStatus();
	public LocalDate getContractStart();
	public LocalDate getContractEind();

}
