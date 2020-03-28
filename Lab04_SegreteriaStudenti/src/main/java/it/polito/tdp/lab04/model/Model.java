package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	Model model;
	
	public List<Corso> getTuttiICorsi(){
		CorsoDAO dao=new CorsoDAO();
		return dao.getTuttiICorsi();
	}
	
	public Studente StudentedaMatricola(Integer ricerca) {
	StudenteDAO dao=new StudenteDAO();
	return dao.StudentedaMatricola(ricerca);
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(String nome){
		CorsoDAO dao=new CorsoDAO();
		return dao.getStudentiIscrittiAlCorso(nome);
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(){
		CorsoDAO dao=new CorsoDAO();
		return dao.getStudentiIscrittiAlCorso();
	}
	
	public List<Corso> getCorsiStudente(Integer matricola) {
		StudenteDAO dao=new StudenteDAO();
		return dao.getCorsiStudente(matricola);
	}
	
	
	public boolean studenteIscritto(Integer matricola, String nome) {
		CorsoDAO dao=new CorsoDAO();
		return dao.studenteIscritto(matricola, nome);
	}
	
	
	public Corso getCorso(String nome) {
		CorsoDAO dao=new CorsoDAO();
		return dao.getCorso(nome);
	}
    
   public boolean inscriviStudenteACorso(Integer matricola,String nome) {
		CorsoDAO dao=new CorsoDAO();
		return dao.inscriviStudenteACorso(matricola, nome);
	}
	
	

}
