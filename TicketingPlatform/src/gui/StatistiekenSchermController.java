package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import domein.controllers.AangemeldeGebruikerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StatistiekenSchermController extends BorderPane implements Initializable{
	
	@FXML
	private PieChart chartTickets;
	@FXML
	private PieChart chartContracten;
	@FXML
	private PieChart chartGebruiker;
	@FXML
	private Label lblKb;
	@FXML
	private Button btnTerug;
	
	private DashboardSchermController parent;
	private AangemeldeGebruikerController ac;

	public StatistiekenSchermController(DashboardSchermController dashboardSchermController, AangemeldeGebruikerController ac) {
		this.parent = dashboardSchermController;
		this.ac= ac;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("StatistiekenScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
			
			lblKb.setText(Integer.toString(ac.geefKnowledgebaseItems().toArray().length));

		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@FXML
	public void btnTerugOnAction(ActionEvent event) {
		Stage stage = (Stage) (getScene().getWindow());
		stage.setScene(this.parent.getScene());
	}

	@Override
	public void initialize(URL location, ResourceBundle rb) {
		ObservableList<PieChart.Data> chartTicketsData = FXCollections.observableArrayList(
			    new PieChart.Data("Aangemaakt", ac.geefTickets().stream().filter(ticket -> ticket.getStatus().toString().equals("Aangemaakt")).toArray().length),
	            new PieChart.Data("In behandeling", ac.geefTickets().stream().filter(ticket -> ticket.getStatus().toString().equals("InBehandeling")).toArray().length),
	            new PieChart.Data("Afgehandeld", ac.geefTickets().stream().filter(ticket -> ticket.getStatus().toString().equals("Afgehandeld")).toArray().length),
				new PieChart.Data("Geannuleerd", ac.geefTickets().stream().filter(ticket -> ticket.getStatus().toString().equals("Geannuleerd")).toArray().length),
	            new PieChart.Data("Wachten op informatie klant", ac.geefTickets().stream().filter(ticket -> ticket.getStatus().toString().equals("WachtenOpInformatieKlant")).toArray().length),	            
	            new PieChart.Data("Informatie klant ontvangen", ac.geefTickets().stream().filter(ticket -> ticket.getStatus().toString().equals("InformatieKlantOntvangen")).toArray().length),
	            new PieChart.Data("In development", ac.geefTickets().stream().filter(ticket -> ticket.getStatus().toString().equals("InDevelopment")).toArray().length));
		chartTickets.setData(chartTicketsData);
		
		ObservableList<PieChart.Data> chartContractenData = FXCollections.observableArrayList(
				new PieChart.Data("InBehandeling", ac.geefContracten().stream().filter(w -> w.getContractStatus().toString().equals("InBehandeling")).toArray().length),
				new PieChart.Data("Actief", ac.geefContracten().stream().filter(w -> w.getContractStatus().toString().equals("Actief")).toArray().length),
				new PieChart.Data("Afgelopen", ac.geefContracten().stream().filter(w -> w.getContractStatus().toString().equals("Afgelopen")).toArray().length),
				new PieChart.Data("Stopgezet", ac.geefContracten().stream().filter(w -> w.getContractStatus().toString().equals("Stopgezet")).toArray().length),
				new PieChart.Data("NietActief", ac.geefContracten().stream().filter(w -> w.getContractStatus().toString().equals("NietActief")).toArray().length));
		chartContracten.setData(chartContractenData);
		
		ObservableList<PieChart.Data> chartGebruikerData = FXCollections.observableArrayList(
				new PieChart.Data("Administrator", ac.geefWerknemers().stream().filter(w -> w.getRol().toString().equals("Administrator")).toArray().length),
				new PieChart.Data("SupportManager", ac.geefWerknemers().stream().filter(w -> w.getRol().toString().equals("SupportManager")).toArray().length),
				new PieChart.Data("Technieker", ac.geefWerknemers().stream().filter(w -> w.getRol().toString().equals("Technieker")).toArray().length),
				new PieChart.Data("Klanten", ac.geefKlanten().toArray().length));
		chartGebruiker.setData(chartGebruikerData);

	}
}
