package domein.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.*;
import java.time.*;
import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name = "Gebruiker.findByEmail", query = "select g from Gebruiker g where g.emailAdres = :email"),
		
		  @NamedQuery(name = "Gebruiker.geefWerknemers", query =
			  "select g from Gebruiker g where TYPE(g) = Administrator or TYPE(g) = Technieker or TYPE(g) = SupportManager"),
		  
		  @NamedQuery(name = "Gebruiker.geefKlanten", query =
		  "select g from Gebruiker g where TYPE(g) = Klant")
		 
})
public abstract class Gebruiker implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String emailAdres;
	private String wachtwoord;
	@Column(name = "registratieDatum")
	private LocalDate registratieDatum;
	@Transient
	private GebruikerStatus status;
	private String naam;
	private String voornaam;
	private String adres;
	private String telefoonnummer;

	public Gebruiker(String emailAdres, String wachtwoord, GebruikerStatus status, String naam, String voornaam, String adres, String telefoonnummer) {
		setEmailAdres(emailAdres);
		setWachtwoord(wachtwoord);
		setStatus(status);
		setNaam(naam);
		setVoornaam(voornaam);
		setAdres(adres);
		setTelefoonnummer(telefoonnummer);
		setRegistratieDatum(LocalDate.now());
	}
	public Gebruiker() {

	}

	public LocalDate getRegistratieDatum() {
		return registratieDatum;
	}
	
	public String getRol()
	{
		return this.getClass().getSimpleName();
	}

	public void setRegistratieDatum(LocalDate registratieDatum) {
		this.registratieDatum = registratieDatum;
	}

	public GebruikerStatus getStatus() {
		return status;
	}

	public void setStatus(GebruikerStatus status) {
		this.status = status;
	}

	public String getNaam() {
		return naam;
	}

	private void setNaam(String naam) {
		if(!naam.isBlank())
		{
			this.naam = naam;
		}
		else throw new IllegalArgumentException("Naam is verplicht");
	}

	public String getVoornaam() {
		return voornaam;
	}

	private void setVoornaam(String voornaam) {
		if(!voornaam.isBlank())
		{
			this.voornaam = voornaam;
		}
		else throw new IllegalArgumentException("Voornaam is verplicht");	}

	public String getAdres() {
		return adres;
	}

	private void setAdres(String adres) {
		if(!adres.isBlank())
		{
			this.adres = adres;
		}
		else throw new IllegalArgumentException("Adres is verplicht");	}

	public String getTelefoonnummer() {
		return telefoonnummer;
	}

	private void setTelefoonnummer(String telefoonnummer) {
		if(!telefoonnummer.isBlank())
		{
			this.telefoonnummer = telefoonnummer;
		}
		else throw new IllegalArgumentException("Telefoonnummer is verplicht");	}

	

	public int getId() {
		return id;
	}

	public String getEmailAdres() {
		return emailAdres;
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEmailAdres(String emailAdres) {
		if(!emailAdres.isBlank())
		{
			this.emailAdres = emailAdres;
		}
		else throw new IllegalArgumentException("E-mailadres is verplicht");	}

	public void setWachtwoord(String wachtwoord) {
		if(!wachtwoord.isBlank())
		{
			this.wachtwoord = wachtwoord;
		}
		else throw new IllegalArgumentException("Wachtwoord is verplicht");	}

	@Override
	public String toString() {
		return String.format("Gebruiker met id %s, email %s, wachtwoord %s, adres %s, naam %s, voornaam %s, telefoonnummer %s, registratiedatum %s",getId(), getEmailAdres(), getWachtwoord(), getAdres(), getNaam(), getVoornaam(), getTelefoonnummer(), getRegistratieDatum());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gebruiker other = (Gebruiker) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
