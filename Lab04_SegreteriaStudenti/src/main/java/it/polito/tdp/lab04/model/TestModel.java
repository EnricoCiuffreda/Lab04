package it.polito.tdp.lab04.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		List<Corso> tuttiCorsi=model.getTuttiICorsi();
		System.out.println(tuttiCorsi);
		Studente st=model.StudentedaMatricola(146101);
		System.out.println(st);
		List<Studente> studenticorso=model.getStudentiIscrittiAlCorso("Analisi dei sistemi economici");
		System.out.println(studenticorso);
		List<Corso> Corsimatricola=model.getCorsiStudente(199349);
		System.out.println(Corsimatricola);
		boolean iscritto=model.studenteIscritto(146101,"Ingegneria delle qualità");
		System.out.println(iscritto);
		/*
		 * 	Write here your test model
		 */

	}

}
