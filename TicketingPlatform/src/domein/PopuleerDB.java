package controllers;

import java.time.LocalDate;

import domein.Administrator;
import domein.Contract;
import domein.ContractEnContractTypeStatus;
import domein.ContractType;
import domein.GebruikerStatus;
import domein.Klant;
import domein.KnowledgeBase;
import domein.ManierVanAanmakenTicket;
import domein.SupportManager;
import domein.Technieker;
import domein.Ticket;
import domein.TicketStatus;
import domein.TijdstipTicketAanmaken;
import repository.GebruikerDaoJPA;
import repository.GenericDaoJPA;

public class PopuleerDB {
	
    public static int userId = 1; //"210771fc - 21f2 - 47e4 - a902 - 986e2d199105";

	
    public static void run() {
        GebruikerDaoJPA gebruikerdao = new GebruikerDaoJPA();
        GebruikerDaoJPA.startTransaction();

        //GenericDaoJpa<Winkel> winkeldao = new GenericDaoJpa<>(Winkel.class);
       
        Klant klant1 = new Klant("klant@gmail.com", "wachtwoord1", GebruikerStatus.ACTIEF, "Jorissen", "Joris", "Jorisstraat 46, 9000 Gent", "049952754");
        Technieker technieker1 = new Technieker("colinwatkins@gmail.com", "wachtwoord1", GebruikerStatus.ACTIEF, "Pieterssen", "Pieter", "Pieterstraat 46, 9000 Gent", "042252754");
        
        Ticket ticket1 = new Ticket("2020-Error 109271", TicketStatus.Afgehandeld, LocalDate.now(), "loremIpsum", "1");
        Ticket ticket2 = new Ticket("2020-Error 2980", TicketStatus.Afgehandeld, LocalDate.now(), "loremIpsum", "1");
        Ticket ticket3 = new Ticket("2020-Authorisatie Probleem", TicketStatus.Geannuleerd, LocalDate.now(), "loremIpsum", "1");
        
        ContractType contract24_7Email = new ContractType("Email Contract 24/7", ContractEnContractTypeStatus.Actief, ManierVanAanmakenTicket.Email, TijdstipTicketAanmaken.Altijd, 3, 10, 100);
        ContractType contract24_7Email2 = new ContractType("Email Contract 24/7 2 jaar", ContractEnContractTypeStatus.Actief, ManierVanAanmakenTicket.Email, TijdstipTicketAanmaken.Altijd, 2, 10, 100);
        ContractType contract24_7 = new ContractType("Applicatie Contract 24/7", ContractEnContractTypeStatus.Actief, ManierVanAanmakenTicket.EmailEnTelefonischEnApplicatie, TijdstipTicketAanmaken.Altijd, 1, 10, 100);
        ContractType contractWerkuren = new ContractType("Applicatie Contract Werkuren", ContractEnContractTypeStatus.Actief, ManierVanAanmakenTicket.EmailEnTelefonischEnApplicatie, TijdstipTicketAanmaken.TijdensWerkdagen, 1, 10, 100);
       
        Contract contract1 = new Contract(LocalDate.now().plusMonths(-13), contract24_7, 1, klant1, ContractEnContractTypeStatus.Afgelopen); 
        Contract contract2 = new Contract(LocalDate.now().plusMonths(-8), contract24_7, 1, klant1, ContractEnContractTypeStatus.InBehandeling);
        Contract contract3 = new Contract(LocalDate.now().plusMonths(-23).plusDays(-11), contractWerkuren, 2, klant1, ContractEnContractTypeStatus.Actief);
        		
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
        
        GenericDaoJPA<ContractType> contractTypedao = new GenericDaoJPA<>(ContractType.class);
        contractTypedao.insert(contract24_7);
        contractTypedao.insert(contractWerkuren);
        contractTypedao.insert(contract24_7Email);
        contractTypedao.insert(contract24_7Email2);
        
        GenericDaoJPA<Contract> contractdao = new GenericDaoJPA<>(Contract.class);
        contractdao.insert(contract1);
        contractdao.insert(contract2);
        contractdao.insert(contract3);
        
        GenericDaoJPA<KnowledgeBase> knowledgebasedao = new GenericDaoJPA<>(KnowledgeBase.class);
        knowledgebasedao.insert(new KnowledgeBase("Hoe kan ik me aanmelden?", "loremIpsum", LocalDate.now()));
        knowledgebasedao.insert(new KnowledgeBase("Hoe kan ik mijn wachtwoord aanpassen?","loremIpsum", LocalDate.now()));
        knowledgebasedao.insert(new KnowledgeBase("Wat doe ik bij Error 5038","loremIpsum", LocalDate.now()));
        knowledgebasedao.insert(new KnowledgeBase("Hoe annuleer ik een ticket?","Oplossing 4", LocalDate.now()));
        knowledgebasedao.insert(new KnowledgeBase("Mijn nieuwe contract staat niet op actief","Oplossing 5", LocalDate.now()));
        knowledgebasedao.insert(new KnowledgeBase("Ik kan mijn bijlage niet uploaden","Oplossing 6", LocalDate.now()));
        knowledgebasedao.insert(new KnowledgeBase("Waar vind ik  mijn oude contracten terug?","Oplossing 7", LocalDate.now()));
        knowledgebasedao.insert(new KnowledgeBase("Ik kan de naam van mijn ticket niet meer aanpassen","Oplossing 8", LocalDate.now()));
        
		gebruikerdao.insert(klant1);
		gebruikerdao.insert(new SupportManager("supportmanager@gmail.com", "wachtwoord1",GebruikerStatus.ACTIEF, "Tomssen", "Tom", "Tomstraat 46, 9000 Gent", "049912754"));
		gebruikerdao.insert(new Administrator("admin@gmail.com", "wachtwoord1", GebruikerStatus.ACTIEF, "Michaelsen", "Michael", "Michaelstraat 46, 9000 Gent", "049952777"));
		gebruikerdao.insert(technieker1);

	
        GebruikerDaoJPA.commitTransaction();
          
    }

}
