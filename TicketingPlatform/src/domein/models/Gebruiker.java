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

import domein.controllers.BCrypt;

import java.io.*;
import java.time.*;
import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name = "Gebruiker.findByEmail", query = "select g from Gebruiker g where g.emailAdres = :email"),

		@NamedQuery(name = "Gebruiker.geefWerknemers", query = "select g from Gebruiker g where TYPE(g) = Werknemer"),

		@NamedQuery(name = "Gebruiker.geefKlanten", query = "select g from Gebruiker g where TYPE(g) = Klant")

})
public abstract class Gebruiker implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String emailAdres;
	private String wachtwoord;
	@Column(name = "registratieDatum")
	private LocalDate registratieDatum;
	
	private GebruikerStatus status;
	private String naam;
	private String voornaam;
	private String[] adres;
	private String[] telefoonnummers;
	@Transient
	private static final int workload = 12;
	
	private TypeGebruiker rol;

	public Gebruiker(String emailAdres, String wachtwoord, GebruikerStatus status, String naam, String voornaam,
			String[] adres, String[] telefoonnummers) {
		setEmailAdres(emailAdres);
		setWachtwoord(wachtwoord);
		setStatus(status);
		setNaam(naam);
		setVoornaam(voornaam);
		setAdres(adres);
		setTelefoonnummers(telefoonnummers);
		setRegistratieDatum(LocalDate.now());
	}

	public Gebruiker() {

	}

	private String hashPassword(String password_plaintext) {
		String salt = BCrypt.gensalt(workload);
		String hashed_password = BCrypt.hashpw(password_plaintext, salt);

		return (hashed_password);
	}

	public boolean checkPassword(String password_plaintext) {
		boolean password_verified = false;

		if (null == getWachtwoord() || !getWachtwoord().startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

		password_verified = BCrypt.checkpw(password_plaintext, getWachtwoord());

		return (password_verified);
	}

	public LocalDate getRegistratieDatum() {
		return registratieDatum;
	}

	public void setRegistratieDatum(LocalDate registratieDatum) {
		this.registratieDatum = registratieDatum;
	}

	public GebruikerStatus getStatus() {
		return status;
	}

	private void setStatus(GebruikerStatus status) {
		this.status = status;
	}

	public String getNaam() {
		return naam;
	}

	private void setNaam(String naam) {
		if (!naam.isBlank()) {
			this.naam = naam;
		} else
			throw new IllegalArgumentException("Naam is verplicht");
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		if (!voornaam.isBlank()) {
			this.voornaam = voornaam;
		} else
			throw new IllegalArgumentException("Voornaam is verplicht");
	}

	public String[] getAdres() {
		return adres;
	}

	public void setAdres(String[] adres) {
		this.adres = adres;
	}

	public String[] getTelefoonnummers() {
		return telefoonnummers;
	}

	public void setTelefoonnummers(String[] telefoonnummers) {
		if (!telefoonnummers[0].isBlank() || !telefoonnummers[1].isBlank()) {
			this.telefoonnummers = telefoonnummers;
		} else
			throw new IllegalArgumentException("Telefoonnummer is verplicht");
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
	
	public TypeGebruiker getRol() {
		return rol;
	}

	public void setRol(TypeGebruiker rol) {
		this.rol = rol;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEmailAdres(String emailAdres) {
		if (!emailAdres.isBlank()) {
			this.emailAdres = emailAdres;
		} else
			throw new IllegalArgumentException("E-mailadres is verplicht");
	}

	public void setWachtwoord(String wachtwoord) {
		if (!wachtwoord.isBlank()) {
			this.wachtwoord = this.hashPassword(wachtwoord);
		} else
			throw new IllegalArgumentException("Wachtwoord is verplicht");
	}

	@Override
	public String toString() {
		return String.format(
				"Gebruiker met id %s, email %s, wachtwoord %s, adres %s, naam %s, voornaam %s, telefoonnummers %s, registratiedatum %s",
				getId(), getEmailAdres(), getWachtwoord(), getAdres(), getNaam(), getVoornaam(), getTelefoonnummers(),
				getRegistratieDatum());
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
