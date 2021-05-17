package domein.models;

import java.time.LocalDate;

public interface KnowledgeBaseGegevens {
	
	public int getKnowledgeBaseId();
	public String getTitel();
	public LocalDate getDatumToevoegen();
	public String getOmschrijving();
}
