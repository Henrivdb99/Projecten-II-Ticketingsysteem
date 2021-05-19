package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.models.Contract;

public class ContractTest {
	
	private Contract contract;
	@BeforeEach
	public void before() {
		contract= new Contract();
	}
	
	@Test
	public void maaktCorrectContractAan()
	{
		contract.setDoorlooptijd(2);
		contract.setContractId(5);
		
		Assertions.assertEquals(2, contract.getDoorlooptijd());
		Assertions.assertEquals(5, contract.getContractId());
	}

}
