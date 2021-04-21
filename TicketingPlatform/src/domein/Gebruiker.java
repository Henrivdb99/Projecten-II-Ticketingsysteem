package domein;

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

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@NamedQueries({
		@NamedQuery(name = "Gebruiker.findByEmail", query = "select g from Gebruiker g where g.emailAdres = :email") })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Gebruiker implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private StringProperty emailAdres, naam, voornaam, adres, telefoonnummer;
	private String wachtwoord;
	private LocalDate registratieDatum;
	private GebruikerStatus status;
	

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

	private void setRegistratieDatum(LocalDate registratieDatum) {
		this.registratieDatum = registratieDatum;
	}

	public String getStatus() {
		return status.toString();
	}

	private void setStatus(GebruikerStatus status) {
		this.status = status;
	}

	public String getNaam() {
		return naam.get();
	}

	private void setNaam(String naam) {
		this.naam = new SimpleStringProperty(naam);
	}

	public String getVoornaam() {
		return voornaam.get();
	}

	private void setVoornaam(String voornaam) {
		this.voornaam = new SimpleStringProperty(voornaam);
	}

	public String getAdres() {
		return adres.get();
	}

	private void setAdres(String adres) {
		this.adres = new SimpleStringProperty(adres);
	}

	public String getTelefoonnummer() {
		return telefoonnummer.get();
	}

	private void setTelefoonnummer(String telefoonnummer) {
		this.adres = new SimpleStringProperty(telefoonnummer);
	}

	public int getId() {
		return id;
	}

	public String getEmailAdres() {
		return emailAdres.get();
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEmailAdres(String emailAdres) {
		this.emailAdres = new SimpleStringProperty(emailAdres);
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}

	@Override
	public String toString() {
		return "Gebruiker [id=" + getId() + ", emailAdres=" + getEmailAdres() + "]";
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
