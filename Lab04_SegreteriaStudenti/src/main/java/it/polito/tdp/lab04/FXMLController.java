package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	private ObservableList <String> listacorsi=FXCollections.observableArrayList();
	Model model;
	
	public void setmodel(Model model) {
		this.model=model;
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> menucorsi;

    @FXML
    private Button btncercaiscritti;

    @FXML
    private TextField txtmatricola;

    @FXML
    private Button btnv;

    @FXML
    private TextField txtnome;

    @FXML
    private TextField txtcognome;

    @FXML
    private Button btniscrivi;

    @FXML
    private Button btncercacorsi;

    @FXML
    private TextArea txtrisultato;

    @FXML
    private Button btnreset;

    @FXML
    void doReset(ActionEvent event) {
    	txtmatricola.clear();
    	txtnome.clear();
    	txtcognome.clear();
    	txtrisultato.clear();

    }

    @FXML
    void docercaCorsi(ActionEvent event) {
    	String ricerca=txtmatricola.getText();
    	Integer matricola;
    	try {
    	matricola=Integer.parseInt(ricerca);
    	} catch(NumberFormatException e) {
    		txtrisultato.setText("Devi inserire un numero\n");
    		return;
    	}
    	if(matricola<0 || matricola>999999) {
    		txtrisultato.setText("Devi inserire un numero tra 0 e 999999\n");
    		return;
    	}
    	if(model.StudentedaMatricola(matricola)==null) {
    		txtrisultato.appendText("inserirsci una matricola giusta,coglione\n");
    		return;
    	}
    	String corso=menucorsi.getValue();
    	if(!corso.equals("")) {
    		boolean iscritto=model.studenteIscritto(matricola,corso);
    		System.out.println(iscritto);
    		if(iscritto==true) {
    		txtrisultato.appendText("lo studente è iscritto al corso\n");
    		}
    		else {
    			txtrisultato.appendText("lo studente non è iscritto al corso\n");
    		}
    		return;
    	}
    	List<Corso> corsistudente=model.getCorsiStudente(matricola);

    	for(Corso c:corsistudente) {
    		txtrisultato.appendText(c+"\n");
    	}

    }

    @FXML
    void docercaIscritti(ActionEvent event) {
    	String nome=menucorsi.getValue();
    	if(nome==null) {
    		txtrisultato.appendText("coglione,metti un corso\n");
    		return;
    	}
    	if(nome.equals("")){
        	List<Studente> studenticorso=model.getStudentiIscrittiAlCorso();
        	for(Studente s:studenticorso) {
        		txtrisultato.appendText(s+"\n");
        	}
    		return;
    	}
    	List<Studente> studenticorso=model.getStudentiIscrittiAlCorso(nome);
    	for(Studente s:studenticorso) {
    		txtrisultato.appendText(s+"\n");
    	}

    }

    @FXML
    void doiscriviti(ActionEvent event) {
    	String ricerca=txtmatricola.getText();
    	Integer matricola;
    	try {
    	matricola=Integer.parseInt(ricerca);
    	} catch(NumberFormatException e) {
    		txtrisultato.setText("Devi inserire un numero\n");
    		return;
    	}
    	if(matricola<0 || matricola>999999) {
    		txtrisultato.setText("Devi inserire un numero tra 0 e 999999\n");
    		return;
    	}
    	if(model.StudentedaMatricola(matricola)==null) {
    		txtrisultato.appendText("inserirsci una matricola giusta,coglione\n");
    		return;
    	}
    	String corso=menucorsi.getValue();
    	if(model.studenteIscritto(matricola,corso)==true) {
    		txtrisultato.appendText("studente già iscritto al corso\n");
    		return;
    	}
    	List <Studente> studentiiscritti=model.getStudentiIscrittiAlCorso(corso);
    	if(studentiiscritti.size()==0) {
    		txtrisultato.appendText("non ci sono iscritti a questo corso\n");
    	return;
    	}
    	List <Corso> corsiiscritto=model.getCorsiStudente(matricola);
    	if(studentiiscritti.size()==0) {
    		txtrisultato.appendText("lo studente non è iscritto a nessun corso\n");
    	return;
    	}
    	this.model.inscriviStudenteACorso(matricola,corso);
    	txtrisultato.appendText("lo studente è stato correttamente assegnato a quel corso\n");
    }

    @FXML
    void doricercamatricola(ActionEvent event) {
    	String ricerca=txtmatricola.getText();
    	Integer matricola;
    	try {
    	matricola=Integer.parseInt(ricerca);
    	} catch(NumberFormatException e) {
    		txtrisultato.setText("Devi inserire un numero\n");
    		return;
    	}
    	if(matricola<0 || matricola>999999) {
    		txtrisultato.setText("Devi inserire un numero tra 0 e 999999\n");
    		return;
    	}
    	Studente st=this.model.StudentedaMatricola(matricola);
    	if(st==null) {
    		return;
    	}
    		txtnome.setText(st.getNome());
    		txtcognome.setText(st.getCognome());
    	}

    
    public void popolabox() {
    	List<Corso> tuttiCorsi=model.getTuttiICorsi();
    	for(Corso c: tuttiCorsi) {
        	listacorsi.addAll(c.getNome());
    	}
    	listacorsi.add("");
    	menucorsi.setItems(listacorsi);
    	menucorsi.setValue("");
    }

    @FXML
    void initialize() {
        assert menucorsi != null : "fx:id=\"menucorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btncercaiscritti != null : "fx:id=\"btncercaiscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtmatricola != null : "fx:id=\"txtmatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnv != null : "fx:id=\"btnv\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtnome != null : "fx:id=\"txtnome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtcognome != null : "fx:id=\"txtcognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btniscrivi != null : "fx:id=\"btniscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btncercacorsi != null : "fx:id=\"btncercacorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtrisultato != null : "fx:id=\"txtrisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnreset != null : "fx:id=\"btnreset\" was not injected: check your FXML file 'Scene.fxml'.";
    }
}
