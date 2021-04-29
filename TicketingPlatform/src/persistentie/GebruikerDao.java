package persistentie;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import domein.*;
import domein.models.*;

public interface GebruikerDao extends GenericDao<Gebruiker>  {
        Gebruiker getGebruikerByEmail(String email) throws EntityNotFoundException;
        List<Gebruiker> geefWerknemers() throws EntityNotFoundException;
        List<Gebruiker> geefKlanten() throws EntityNotFoundException;
}