package main;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import domein.Administrator;
import domein.Gebruiker;
import domein.Klant;
import domein.SupportManager;
import domein.Technieker;
import util.JPAUtil;

public class StartUp {

	public static void main(String[] args) {
		System.out.println("Hello world");
		
		List<Gebruiker> gr = new ArrayList<>();
		gr.add(new Klant("klant@gmail.com", "wachtwoord1"));
		gr.add(new SupportManager("supportmanager@gmail.com", "wachtwoord2"));
		gr.add(new Administrator("admin@gmail.com", "wachtwoord3"));
		gr.add(new Technieker("techinieker@gmail.com", "wachtwoord4"));
		
		EntityManagerFactory enf = JPAUtil.getEntityManagerFactory(); //...
		EntityManager en = enf.createEntityManager();
		
		EntityTransaction transactie = en.getTransaction();
		transactie.begin();
		
		
		for (Gebruiker g: gr)
			en.persist(g);
		
		transactie.commit();
		
		en.close();
		
		enf.close();
	}

}
