package domein.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
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
	@Column(name = "Ticketid")
	private int id;
	private String titel;
	private TicketStatus status;
	private LocalDate datumAanmaken;
	private String omschrijving;
	private String opmerkingen;
	private String bijlage;
	private int typeTicket;

	private String kwaliteit;
	private boolean supportNodig;
	private boolean oplossing;

	@ManyToOne
	private Klant klant;
	@ManyToOne
	private Werknemer technieker;

	public Ticket() {

	}

	public Ticket(String titel, TicketStatus ticketStatus, LocalDate date, String omschrijving, String opmerkingen,
			int typeTicket, Klant klant, Werknemer technieker, String bijlage) {
		setTitel(titel);
		setStatus(ticketStatus);
		setDatumAanmaken(date);
		setOmschrijving(omschrijving);
		setOpmerkingen(opmerkingen);
		setTypeTicket(typeTicket);
		setKlant(klant);
		setTechnieker(technieker);
		setBijlage(bijlage);
	}

	public int getId() {
		return id;
	}

	public String getBijlage() {
		return bijlage;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitel() {
		return titel;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public LocalDate getDatumAanmaken() {
		return datumAanmaken;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public int getTypeTicket() {
		return typeTicket;
	}

	public Klant getKlant() {
		return klant;
	}

	public Werknemer getTechnieker() {
		return technieker;
	}

	public String getOpmerkingen() {
		return opmerkingen;
	}

	public String getNaamVoornaam() {
		return klant.getVoornaam() + " " + klant.getNaam();
	}

	
	public void setBijlage(String bijlage) {
		this.bijlage = bijlage;
	}

	public void setTitel(String titel) {
		if(!titel.isBlank())
		{
			this.titel = titel;
		}else throw new IllegalArgumentException("Titel is verplicht");
	}

	public void setStatus(TicketStatus status) {
		if(status != null)
		{
			this.status = status;
		}else throw new IllegalArgumentException("Status is verplicht");	}

	public void setDatumAanmaken(LocalDate datumAanmaken) {
		if(datumAanmaken != null)
		{
			this.datumAanmaken = datumAanmaken;
		}else throw new IllegalArgumentException("DatumAanmaken is verplicht");		
		}

	public void setOmschrijving(String omschrijving) {
		if(!omschrijving.isBlank())
		{
			this.omschrijving = omschrijving;
		}else throw new IllegalArgumentException("Omschrijving is verplicht");	}

	public void setTypeTicket(int typeTicket) {
		if(typeTicket != 0)
		{
			this.typeTicket = typeTicket;
		}else throw new IllegalArgumentException("TypeTicket is verplicht");	}

	public void setKlant(Klant klant) {
		if(klant != null)
		{
			this.klant = klant;
		}else throw new IllegalArgumentException("Klant is verplicht");	
	}

	public void setTechnieker(Werknemer technieker) {
		if(technieker != null)
		{
			this.technieker = technieker;
		}else throw new IllegalArgumentException("Technieker is verplicht");	
		}

	public void setOpmerkingen(String opmerkingen) {
		this.opmerkingen = opmerkingen;
	}

	public String getKwaliteit() {
		return kwaliteit;
	}

	public void setKwaliteit(String kwaliteit) {
		this.kwaliteit = kwaliteit;
	}

	public boolean isSupportNodig() {
		return supportNodig;
	}

	public void setSupportNodig(boolean supportNodig) {
		this.supportNodig = supportNodig;
	}

	public boolean isOplossing() {
		return oplossing;
	}

	public void setOplossing(boolean oplossing) {
		this.oplossing = oplossing;
	}

}
