package repository;

import javax.persistence.EntityNotFoundException;

import domein.Gebruiker;
import domein.*;

public interface GebruikerDao extends GenericDao<Gebruiker>  {
        public Gebruiker getGebruikerByEmail(String email) throws EntityNotFoundException;   
}