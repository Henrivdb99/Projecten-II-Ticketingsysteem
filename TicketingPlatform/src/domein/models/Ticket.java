package domein.models;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import javafx.beans.property.SimpleStringProperty;

@Entity
public class Ticket implements Serializable, TicketGegevens {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titel;
	private TicketStatus status;
	private LocalDate datumAanmaken;
	private String omschrijving;
	private String opmerkingen;

	private String typeTicket;

	@ManyToOne
	private Klant klant;
	@ManyToOne
	private Werknemer technieker;

	public Ticket() {

	}

	public Ticket(String titel, TicketStatus ticketStatus, LocalDate date, String omschrijving,String opmerkingen, String typeTicket) {
		this.titel = titel;
		this.status = ticketStatus;
		this.datumAanmaken = date;
		this.omschrijving = omschrijving;
		this.typeTicket = typeTicket;
		this.opmerkingen= opmerkingen;
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

	public Klant getKlant() {
		return klant;
	}

	public void setKlant(Klant klant) {
		this.klant = klant;
	}

	public Werknemer getTechnieker() {
		return technieker;
	}

	public void setTechnieker(Werknemer technieker) {
		this.technieker = technieker;
	}

	public String getNaamVoornaam() {
		return klant.getVoornaam() + " " + klant.getNaam();
	}

	public String getOpmerkingen() {
		return opmerkingen;
	}

}
