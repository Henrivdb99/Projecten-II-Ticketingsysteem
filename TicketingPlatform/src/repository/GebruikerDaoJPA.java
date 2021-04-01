package repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import domain.Bier;
import domein.Gebruiker;
import domein.GebruikerData;
import persistentie.GebruikerMapper;

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
            return em.createNamedQuery("Bier.findByName", Gebruiker.class)
                 .setParameter("bierNaam", email)
                .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
    }

	@Override
	public List<Gebruiker> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> Gebruiker get(U id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gebruiker update(Gebruiker object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Gebruiker object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Gebruiker object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <U> boolean exists(U id) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	

}
