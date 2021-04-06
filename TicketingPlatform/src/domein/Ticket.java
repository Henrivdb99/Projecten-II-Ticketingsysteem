package domein;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ticket {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titel;
	private TicketStatus status;
	private LocalDate datumAanmaken;
	private String omschrijving;
	private String typeTicket;
	private String klantId;
	private String techniekerId;
	
	

	public Ticket() {

	}

	public Ticket(String titel, TicketStatus ticketStatus, LocalDate date, String omschrijving, String typeTicket,
			String klantId, String technieker) 
	{
		this.titel = titel;
		this.status = ticketStatus;
		this.datumAanmaken = date;
		this.omschrijving = omschrijving;
		this.typeTicket = typeTicket;
		this.klantId = klantId;
		this.techniekerId = technieker;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public TicketStatus getStatus() {
		return status;
	}
	public void setStatus(TicketStatus status) {
		this.status = status;
	}
	public LocalDate getDatumAanmaken() {
		return datumAanmaken;
	}
	public void setDatumAanmaken(LocalDate datumAanmaken) {
		this.datumAanmaken = datumAanmaken;
	}
	public String getOmschrijving() {
		return omschrijving;
	}
	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}
	public String getTypeTicket() {
		return typeTicket;
	}
	public void setTypeTicket(String typeTicket) {
		this.typeTicket = typeTicket;
	}
	public String getKlantId() {
		return klantId;
	}
	public void setKlantId(String klantId) {
		this.klantId = klantId;
	}
	public String getTechniekerId() {
		return techniekerId;
	}
	public void setTechniekerId(String techniekerId) {
		this.techniekerId = techniekerId;
	}
	
	

}
