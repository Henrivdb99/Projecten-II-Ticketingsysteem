package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.models.KnowledgeBase;

public class KnowledgeBaseTest {
	
	private KnowledgeBase knowledgeBase;
	@BeforeEach
	public void before() {
		knowledgeBase= new KnowledgeBase();
	}
	
	@Test
	public void maaktCorrectItemAan()
	{
		knowledgeBase.setOmschrijving("Omschrijving");
		knowledgeBase.setTitel("Titel");
		
		Assertions.assertEquals("Titel", knowledgeBase.getTitel());
		Assertions.assertEquals("Omschrijving", knowledgeBase.getOmschrijving());
	}
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings= {"  "})
	public void incorreteTitel_gooitException(String titel)
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> knowledgeBase.setTitel(titel));
	}
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings= {"  "})
	public void incorreteOmschrijving_gooitException(String omschrijving)
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> knowledgeBase.setOmschrijving(omschrijving));
	}
	

	
}
