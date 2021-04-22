package persistentie;

import javax.persistence.EntityNotFoundException;

import domein.*;
import domein.models.Gebruiker;

public interface GebruikerDao extends GenericDao<Gebruiker>  {
        public Gebruiker getGebruikerByEmail(String email) throws EntityNotFoundException;   
}