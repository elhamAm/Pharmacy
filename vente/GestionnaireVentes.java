package vente;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.lang.Math;


import personne.Client;
import personne.Vendeur;
import stockMedicament.Medicament;


public class GestionnaireVentes {

	private ArrayList<Vente> lVentes;

	public GestionnaireVentes() {
		lVentes = new ArrayList<Vente>();
	}

	
	// --- modification de la base de donnés 
	public void add(Client c, Vendeur v, ArrayList<Medicament> lMedicaments) {

		// etablir la facture
		double prixTotal = 0;

		for (Medicament med : lMedicaments) {
			prixTotal += med.getQuantite() * med.getPrix();
		}

		Facture facture = new Facture(prixTotal);

		// établir la vente
		Vente vente = new Vente(c, v, lMedicaments, facture);
		lVentes.add(vente);
	}

	
	// mise-à-jour des ventes
	public void supprimerVentesPerimees() {
		// aujourd'hui
		Date derniereMaj = new Date(); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(derniereMaj);
		// il y a une année
		cal.set(Calendar.YEAR,cal.get(Calendar.YEAR)-1);
		derniereMaj = cal.getTime(); 
	
		for(Vente v: lVentes) {
			if( derniereMaj.after(v.getFacture().getDate()) ) {
				lVentes.remove(v); 
			}
		}
	}
	
	
	
	
	// trouver les ventes qui correspondent à la periode voulue
	public ArrayList<Vente> trouverVentesDate(Date dateActuel, String periode) {

		ArrayList<Vente> ventesChoisies = new ArrayList<Vente>();
	
		switch (periode) {

		case "journalier":
	
			for (Vente vente : lVentes) {
				if (Math.abs(vente.getFacture().getDate().getTime() - dateActuel.getTime()) / (1000 * 60 * 60) < 24) {
					ventesChoisies.add(vente);
				}
			}
			break;

		case "hebdomadaire":
			
			for (Vente vente : lVentes) {
				if (Math.abs(vente.getFacture().getDate().getTime() - dateActuel.getTime()) / (1000 * 60 * 60 * 24) < 7) {
					ventesChoisies.add(vente);
				}
			}
			break;

		case "mensuel":
		
			for (Vente vente : lVentes) {
				if (Math.abs(vente.getFacture().getDate().getTime() - dateActuel.getTime()) / (1000 * 60 * 60 * 24) < 30) {
					ventesChoisies.add(vente);
				}
			}
			break;
		}
			

		return ventesChoisies;
	}


	// trouver la recette totale des ventes qui correspondent à la periode et clé voulue(clé peut etre type
	// de med ou vendeur ou client)

	public double trouverRecette(Object cle, String periode, String type){
		ArrayList <Vente> ventesChoisies = new ArrayList<Vente>();
		Date dateActuel = new Date(System.currentTimeMillis()); 
		//les ventes qui correspondent à la période et date voulue
		ventesChoisies = trouverVentesDate(dateActuel, periode);
	
	
		//trouver l'addition des prix des ventes
		double recette = 0;
		if(type.contentEquals("typeMed")) {
			for (Vente vente : ventesChoisies) {
				recette += vente.getPrixDuType(cle);
			}
		}
		else if(type.contentEquals("vendeur"))  {
			for (Vente vente : ventesChoisies) {
				if(vente.getVendeur().equals(cle))
				recette += vente.getFacture().getPrixTotal();
			}
		}
		else if(type.contentEquals("client")){
			
			for (Vente vente : ventesChoisies) {
				
				
				if(vente.getClient().equals(cle))
				recette += vente.getFacture().getPrixTotal();
			}
		}
		return recette;
		
	}


	

	// --- getters
	public ArrayList<Vente> getVentes() {
		return lVentes;
	}
	
	//r envoyer tous les types de medicaments dans cette vente
	public ArrayList<String> getTypesMeds(){
		ArrayList<String> typeMeds = new ArrayList<String>();
		for (Vente vente:lVentes) {
			for(Medicament med: vente.getListeMedicaments()) {
				
				if(!typeMeds.contains(med.getType())) {
					typeMeds.add(med.getType());
				}
			}
		}
		return typeMeds;
	}
	
}
