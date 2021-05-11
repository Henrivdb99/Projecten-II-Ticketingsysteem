package domein.models;

import java.time.LocalDate;

public interface TicketGegevens {
	public int getId();

	public String getTitel();

	public TicketStatus getStatus();

	public LocalDate getDatumAanmaken();

	public String getOmschrijving();

	public String getTypeTicket();

	public Klant getKlant();

	public Werknemer getTechnieker();

	public String getNaamVoornaam();

	public String getOpmerkingen();
}
