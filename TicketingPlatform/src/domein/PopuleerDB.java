package domein;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import domein.models.Contract;
import domein.models.ContractEnContractTypeStatus;
import domein.models.ContractType;
import domein.models.GebruikerStatus;
import domein.models.Klant;
import domein.models.KnowledgeBase;
import domein.models.ManierVanAanmakenTicket;
import domein.models.Ticket;
import domein.models.TicketStatus;
import domein.models.TijdstipTicketAanmaken;
import domein.models.TypeGebruiker;
import domein.models.Werknemer;
import persistentie.GebruikerDaoJPA;
import persistentie.GenericDaoJPA;

public class PopuleerDB {
	
    public static int userId = 1; //"210771fc - 21f2 - 47e4 - a902 - 986e2d199105";
    
    public static void run() {
        GebruikerDaoJPA gebruikerdao = new GebruikerDaoJPA();
        GebruikerDaoJPA.startTransaction();
        
       
        Klant klant1 = new Klant("klant@gmail.com", "wachtwoord1", GebruikerStatus.ACTIEF, "Jorissen", "Joris", new String[] {"Jorisstraat", "46", "9000","Gent"}, new String[] {"049952754", "092214365"});
        Werknemer supportManager1 = new Werknemer("supportmanager@gmail.com", "wachtwoord1",GebruikerStatus.ACTIEF, "Tomssen", "Tom", new String[]{"Tomstraat", "46", "9000", "Gent"}, new String[] {"04991754", "099914365"}, TypeGebruiker.SupportManager);
        Werknemer administrator1 = new Werknemer("administrator@gmail.com", "wachtwoord1", GebruikerStatus.ACTIEF, "Odinson", "Thor", new String[] {"Michaelstraat", "46", "9000", "Gent"}, new String[] {"049952804", "092214185"}, TypeGebruiker.Administrator);
        Werknemer technieker1 = new Werknemer("technieker@gmail.com", "wachtwoord1", GebruikerStatus.ACTIEF, "Pieterssen", "Pieter", new String[] {"Pieterstraat", "46", "9000", "Gent"}, new String[] {"049192754", "092217665"}, TypeGebruiker.Technieker);
        Werknemer gebruiker1 = new Werknemer("techniekerOFF1@gmail.com", "wachtwoord1", GebruikerStatus.NIETACTIEF, "Morgan", "Arthur", new String[] {"Pieterstraat", "46", "9000", "Gent"}, new String[] {"049192754", "092217665"}, TypeGebruiker.Technieker);
        Werknemer gebruiker2 = new Werknemer("supportmanagerOFF1@gmail.com", "wachtwoord1", GebruikerStatus.NIETACTIEF, "Pieterssen", "Pieter", new String[] {"Pieterstraat", "46", "9000", "Gent"}, new String[] {"049192754", "092217665"}, TypeGebruiker.SupportManager);
        Werknemer gebruiker3 = new Werknemer("supportmanagerOFF2@gmail.com", "wachtwoord1", GebruikerStatus.NIETACTIEF, "Pieterssen", "Pieter", new String[] {"Pieterstraat", "46", "9000", "Gent"}, new String[] {"049192754", "092217665"}, TypeGebruiker.SupportManager);
        Werknemer gebruiker4 = new Werknemer("techniekerOFF2@gmail.com", "wachtwoord1", GebruikerStatus.NIETACTIEF, "Pieterssen","Pieter" ,new String[] {"Pieterstraat", "46", "9000", "Gent"}, new String[] {"049192754", "092217665"}, TypeGebruiker.SupportManager);

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
        
        List<Contract> contracts = new ArrayList<>();
        contracts.add(contract1); contracts.add(contract2); contracts.add(contract3); //bidirectioneel in domein, databank blijft gelijk
        klant1.setContracten(contracts);
        
        ticket1.setKlant(klant1);
        ticket2.setKlant(klant1);
        ticket3.setKlant(klant1);
        ticket1.setTechnieker(technieker1);
        ticket2.setTechnieker(technieker1);
        ticket3.setTechnieker(technieker1);
        
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket1); tickets.add(ticket2); tickets.add(ticket3); //bidirectioneel in domein, databank blijft gelijk
        klant1.setTickets(tickets);
        
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
		gebruikerdao.insert(supportManager1);
		gebruikerdao.insert(administrator1);
		gebruikerdao.insert(technieker1);
		gebruikerdao.insert(gebruiker1);
		gebruikerdao.insert(gebruiker2);
		gebruikerdao.insert(gebruiker3);
		gebruikerdao.insert(gebruiker4);

	
        GebruikerDaoJPA.commitTransaction();
          
    }

}
