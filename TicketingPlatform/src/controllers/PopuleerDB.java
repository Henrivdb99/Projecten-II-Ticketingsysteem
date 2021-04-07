package controllers;

import java.time.LocalDate;

import domein.Administrator;
import domein.Klant;
import domein.SupportManager;
import domein.Technieker;
import domein.Ticket;
import domein.TicketStatus;
import repository.GebruikerDaoJPA;
import repository.GenericDaoJPA;

public class PopuleerDB {
	
    public static int userId = 1; //"210771fc - 21f2 - 47e4 - a902 - 986e2d199105";

	
    public static void run() {
        GebruikerDaoJPA gebruikerdao = new GebruikerDaoJPA();
        GebruikerDaoJPA.startTransaction();

        //GenericDaoJpa<Winkel> winkeldao = new GenericDaoJpa<>(Winkel.class);
       
        Klant klant1 = new Klant("klant@gmail.com", "wachtwoord1");
        Technieker technieker1 = new Technieker("colinwatkins@gmail.com", "wachtwoord4");
        
        Ticket ticket1 = new Ticket("2020-Error 109271", TicketStatus.Afgehandeld, LocalDate.now(), "loremIpsum", "1");
        Ticket ticket2 = new Ticket("2020-Error 2980", TicketStatus.Afgehandeld, LocalDate.now(), "loremIpsum", "1");
        Ticket ticket3 = new Ticket("2020-Authorisatie Probleem", TicketStatus.Geannuleerd, LocalDate.now(), "loremIpsum", "1");
        

        ticket1.setKlant(klant1);
        ticket2.setKlant(klant1);
        ticket3.setKlant(klant1);
        ticket1.setTechnieker(technieker1);
        ticket2.setTechnieker(technieker1);
        ticket3.setTechnieker(technieker1);

        
        GenericDaoJPA<Ticket> ticketdao = new GenericDaoJPA<>(Ticket.class);
        
        
        ticketdao.insert(ticket1);
        ticketdao.insert(ticket2);
        ticketdao.insert(ticket3);
        
        
		gebruikerdao.insert(klant1);
		gebruikerdao.insert(new SupportManager("supportmanager@gmail.com", "wachtwoord2"));
		gebruikerdao.insert(new Administrator("admin@gmail.com", "wachtwoord3"));
		gebruikerdao.insert(technieker1);


	
        GebruikerDaoJPA.commitTransaction();
          
    }

}
