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
@NamedQuery(name = "Gebruiker.findByEmail", query = "select g from Gebruiker g where g.emailAdres = :email")
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

	public java.time.LocalDate getRegistratieDatum() {
		return registratieDatum;
	}
	
	public String getRol()
	{
		return this.getClass().getSimpleName();
	}

	public void setRegistratieDatum(java.time.LocalDate registratieDatum) {
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
		this.naam = naam;
	}

	public String getVoornaam() {
		return voornaam;
	}

	private void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getAdres() {
		return adres;
	}

	private void setAdres(String adres) {
		this.adres = adres;
	}

	public String getTelefoonnummer() {
		return telefoonnummer;
	}

	private void setTelefoonnummer(String telefoonnummer) {
		this.telefoonnummer = telefoonnummer;
	}

	

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
		this.emailAdres = emailAdres;
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}

	@Override
	public String toString() {
		return "Gebruiker [id=" + id + ", emailAdres=" + emailAdres + "]";
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
