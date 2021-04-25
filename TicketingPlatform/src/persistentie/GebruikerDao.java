package persistentie;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import domein.*;
import domein.models.Gebruiker;
import domein.models.*;

public interface GebruikerDao extends GenericDao<Gebruiker>  {
        public Gebruiker getGebruikerByEmail(String email) throws EntityNotFoundException;
        public List<Gebruiker> geefWerknemers() throws EntityNotFoundException;
        public List<Gebruiker> geefKlanten() throws EntityNotFoundException;
}