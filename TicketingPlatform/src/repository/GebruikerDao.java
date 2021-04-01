package repository;

import javax.persistence.EntityNotFoundException;

import domein.Gebruiker;

public interface GebruikerDao extends GenericDao<Gebruiker>  {
        public Gebruiker getGebruikerByEmail(String email) throws EntityNotFoundException;   
}