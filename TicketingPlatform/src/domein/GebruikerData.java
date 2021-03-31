package domein;

public class GebruikerData {

    private final GebruikerRepository gr;



    public GebruikerData(GebruikerRepository gr) {
		this.gr = gr;
	}



	void populeerData() {
		gr.addGebruiker(new Klant("klant@gmail.com", "wachtwoord1"));
		gr.addGebruiker(new SupportManager("supportmanager@gmail.com", "wachtwoord2"));
		gr.addGebruiker(new Administrator("admin@gmail.com", "wachtwoord3"));
		gr.addGebruiker(new Technieker("techinieker@gmail.com", "wachtwoord4"));
        
    }
}
