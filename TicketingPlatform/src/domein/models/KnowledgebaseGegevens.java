package domein.models;

import java.time.LocalDate;

public interface KnowledgebaseGegevens {
	
	public int getKnowledgeBaseId();
	public String getTitel();
	public LocalDate getDatumToevoegen();
	public String getOmschrijving();
}
