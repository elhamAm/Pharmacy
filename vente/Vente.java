package vente;

import java.util.ArrayList;

import personne.Client;
import personne.Vendeur;

import stockMedicament.Medicament;


public class Vente {
	private Client client; 
	private Vendeur vendeur; 
	private ArrayList<Medicament> listeMedicaments = new ArrayList<Medicament>(); 
	private Facture facture; 
	
	
	public Vente(Client c, Vendeur v, ArrayList<Medicament> lMedicaments, Facture facture){
		this.client = c; 
		this.vendeur = v; 
		this.listeMedicaments = lMedicaments;
		
		this.facture = facture;
	}
	
	// TODO : override equals and hashcodes
	@Override
	public boolean equals(Object obj) {
		// check if we were provided with null pointer 
		if(obj == null) {
			return false; 
		}
		// class cast 
		if ( getClass() != obj.getClass()) {
            return false;
        }
		
		Vente other = (Vente) obj; 
		
		if( !this.facture.equals(other.getFacture()) ) {
			return false; 
		}
		
		return true; 
	}
	
	// no setters because we cannot modify a sale
	public Client getClient() {
		return client;
	}

	public Vendeur getVendeur() {
		return vendeur;
	}

	public ArrayList<Medicament> getListeMedicaments() {
		return listeMedicaments;
	}
	
	public Facture getFacture() {
		return facture;
	}	
	
	public double getPrixDuType(Object cle) {
		double prix=0;
		for (Medicament med: listeMedicaments) {
			if(med.getType().contentEquals((String)cle))
					prix += med.getPrix()* med.getQuantite();
		}
		return prix;
	}
	
}