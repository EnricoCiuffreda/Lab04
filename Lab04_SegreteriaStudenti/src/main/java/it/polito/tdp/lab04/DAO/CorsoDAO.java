package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				Corso c=new Corso(codins,numeroCrediti,nome,periodoDidattico);
				corsi.add(c);
				
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String nome) {
		final String sql = "SELECT * FROM corso WHERE nome=?";
		Corso tmp = null;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1,nome);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String codins=rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				int periodoDidattico = rs.getInt("pd");
				tmp=new Corso(codins,numeroCrediti,nome,periodoDidattico);
				}
			conn.close();
			return tmp;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}


	public List<Studente> getStudentiIscrittiAlCorso(String nome) {
		final String sql = "SELECT studente.matricola,studente.cognome,studente.nome,studente.CDS FROM studente,corso,iscrizione WHERE corso.codins=iscrizione.codins && iscrizione.matricola=studente.matricola && corso.nome=?";
		List<Studente> tmp = new ArrayList <>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1,nome);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Integer matricola= rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nomest = rs.getString("nome");
				String cds = rs.getString("CDS");
				Studente studentetmp=new Studente(matricola,cognome,nomest,cds);
				tmp.add(studentetmp);
				}
			conn.close();
			return tmp;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
	}
	
	public List<Studente> getStudentiIscrittiAlCorso() {
		final String sql = "SELECT studente.matricola,studente.cognome,studente.nome,studente.CDS FROM studente,corso,iscrizione WHERE corso.codins=iscrizione.codins && iscrizione.matricola=studente.matricola";
		List<Studente> tmp = new ArrayList <>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);;
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Integer matricola= rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nomest = rs.getString("nome");
				String cds = rs.getString("CDS");
				Studente studentetmp=new Studente(matricola,cognome,nomest,cds);
				tmp.add(studentetmp);
				}
			conn.close();
			return tmp;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
	}

	
	
	public boolean studenteIscritto(Integer matricola, String nome) {
		final String sql = "SELECT studente.matricola,studente.cognome,studente.nome,studente.CDS FROM studente,corso,iscrizione WHERE corso.codins=iscrizione.codins && iscrizione.matricola=studente.matricola && corso.nome=? && studente.matricola=?";
		boolean risultato=false;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);;
			st.setString(1,nome);
			st.setInt(2,matricola);
			ResultSet rs = st.executeQuery();
			if(rs.next()==true) {
				risultato=true;
			}
			conn.close();
			return risultato;
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Integer matricola,String nome) {
		final String sql = "INSERT INTO iscrizione(matricola,codins)\r\n" + 
				"VALUES (?,?)";
		try {
			Corso corsotmp=this.getCorso(nome);
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);;
			st.setInt(1,matricola);
			st.setString(2,corsotmp.getCodins());
			ResultSet rs = st.executeQuery();
			conn.close();
			return true;
		} catch (SQLException e) {
			// e.printStackTrace();
			return false;
		}
}
}
