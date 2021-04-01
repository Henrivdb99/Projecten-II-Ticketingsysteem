package domein;

import repository.GebruikerDaoJPA;

public class GebruikerData {
	
    public static void run() {
        GebruikerDaoJPA gebruikerdao = new GebruikerDaoJPA();
        GebruikerDaoJPA.startTransaction();

        //GenericDaoJpa<Winkel> winkeldao = new GenericDaoJpa<>(Winkel.class);
        
		gebruikerdao.insert(new Klant("klant@gmail.com", "wachtwoord1"));
		gebruikerdao.insert(new SupportManager("supportmanager@gmail.com", "wachtwoord2"));
		gebruikerdao.insert(new Administrator("admin@gmail.com", "wachtwoord3"));
		gebruikerdao.insert(new Technieker("techinieker@gmail.com", "wachtwoord4"));

        GebruikerDaoJPA.commitTransaction();
    }

	void populeerData() {

        
    }
}
