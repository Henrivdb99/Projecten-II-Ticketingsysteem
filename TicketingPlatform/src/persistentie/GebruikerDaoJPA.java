package persistentie;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import domein.*;
import domein.models.Gebruiker;

import java.util.*;
import domein.models.*;

public class GebruikerDaoJPA extends GenericDaoJPA<Gebruiker> implements GebruikerDao  {
	
    public GebruikerDaoJPA() {
        super(Gebruiker.class);
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
   


	@Override
	public List<Gebruiker> geefWerknemers() throws EntityNotFoundException {
		return super.findAll().stream().filter(g -> !(g instanceof Klant)).collect(Collectors.toList());
		/*try {
            return em.createNamedQuery("Gebruiker.geefWerknemers", Gebruiker.class)
                .getResultList();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } */
	}
    

	@Override
	public List<Gebruiker> geefKlanten() throws EntityNotFoundException {
		return super.findAll().stream().filter(g -> (g instanceof Klant)).collect(Collectors.toList());
		/*try {
            return em.createNamedQuery("Gebruiker.geefKlanten", Gebruiker.class)
                .getResultList();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } */
	}

	
	
	

}
