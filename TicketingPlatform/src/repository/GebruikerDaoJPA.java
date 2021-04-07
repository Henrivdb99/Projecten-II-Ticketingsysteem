package repository;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import domein.Gebruiker;

public class GebruikerDaoJPA extends GenericDaoJPA<Gebruiker> implements GebruikerDao  {
	
    public GebruikerDaoJPA() {
        super(Gebruiker.class);
    }

	public List<Gebruiker> geefGebruikers() {
		throw new UnsupportedOperationException();
	}

    @Override
    public Gebruiker getGebruikerByEmail(String email) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("Gebruiker.findByEmail", Gebruiker.class)
                 .setParameter("email", email)
                .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
    }
	
	
	

}
