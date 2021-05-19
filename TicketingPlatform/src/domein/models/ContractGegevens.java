package domein.models;

import java.time.LocalDate;

public interface ContractGegevens {

	public int getContractId();
	public ContractType getContractType();
	public ContractEnContractTypeStatus getContractStatus();
	public LocalDate getStartDatum();
	public LocalDate getEindDatum();

}
