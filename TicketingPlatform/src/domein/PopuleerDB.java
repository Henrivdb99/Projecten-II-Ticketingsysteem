package domein;

import java.time.LocalDate;
import java.time.LocalDateTime;

import repository.GebruikerDaoJPA;
import repository.GenericDaoJPA;

public class PopuleerDB {
	
    public static String userId = "210771fc - 21f2 - 47e4 - a902 - 986e2d199105";

	
    public static void run() {
        GebruikerDaoJPA gebruikerdao = new GebruikerDaoJPA();
        GebruikerDaoJPA.startTransaction();

        //GenericDaoJpa<Winkel> winkeldao = new GenericDaoJpa<>(Winkel.class);
        
		gebruikerdao.insert(new Klant("klant@gmail.com", "wachtwoord1"));
		gebruikerdao.insert(new SupportManager("supportmanager@gmail.com", "wachtwoord2"));
		gebruikerdao.insert(new Administrator("admin@gmail.com", "wachtwoord3"));
		gebruikerdao.insert(new Technieker("techinieker@gmail.com", "wachtwoord4"));


        GenericDaoJPA<Ticket> ticketdao = new GenericDaoJPA<>(Ticket.class);
        ticketdao.insert(new Ticket("2020-Error 109271", TicketStatus.Afgehandeld, LocalDate.now(), "loremIpsum", "1", userId, "Colin C. Watkins"));
        ticketdao.insert(new Ticket("2020-Error 2980", TicketStatus.Afgehandeld, LocalDate.now(), "loremIpsum", "1", userId, "Colin C. Watkins"));
        ticketdao.insert(new Ticket("2020-Authorisatie Probleem", TicketStatus.Geannuleerd, LocalDate.now(), "loremIpsum", "1", userId, "Colin C. Watkins"));
	
        GebruikerDaoJPA.commitTransaction();
          
    }

}
