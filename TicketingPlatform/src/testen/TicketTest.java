package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.models.KnowledgeBase;
import domein.models.Ticket;

public class TicketTest {
	
	private Ticket ticket;
	@BeforeEach
	public void before() {
		ticket= new Ticket();
	}
	
	@Test
	public void maaktCorrectTicketAan()
	{
		ticket.setOmschrijving("Omschrijving");
		ticket.setTitel("Titel");
		
		Assertions.assertEquals("Titel", ticket.getTitel());
		Assertions.assertEquals("Omschrijving", ticket.getOmschrijving());
	}
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings= {"  "})
	public void incorreteTitel_gooitException(String titel)
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> ticket.setTitel(titel));
	}
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings= {"  "})
	public void incorreteOmschrijving_gooitException(String omschrijving)
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> ticket.setOmschrijving(omschrijving));
	}
}
