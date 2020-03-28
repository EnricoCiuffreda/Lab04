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

public class StudenteDAO {	
	
	public Studente StudentedaMatricola(Integer ricerca) {
	final String sql = "SELECT matricola,cognome,nome,CDS FROM studente WHERE matricola=?";
	Studente s=null;
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1,ricerca);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			Integer matricola= rs.getInt("matricola");
			String cognome = rs.getString("cognome");
			String nome = rs.getString("nome");
			String cds = rs.getString("CDS");
			s=new Studente(matricola,cognome,nome,cds);			
		}
		conn.close();
		return s;
		

	} catch (SQLException e) {
		// e.printStackTrace();
		throw new RuntimeException("Errore Db", e);
	}
}
	
	
	public List<Corso> getCorsiStudente(Integer matricola) {
		final String sql = "SELECT corso.codins,corso.nome,corso.crediti,corso.pd FROM corso,iscrizione WHERE corso.codins=iscrizione.codins && iscrizione.matricola=?";
		List <Corso> tmp=new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,matricola);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String codins=rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				Corso c=new Corso(codins,numeroCrediti,nome,periodoDidattico);
				tmp.add(c);
				}
			conn.close();
			return tmp;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
	}

}
