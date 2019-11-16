package vente.saisie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;

import personne.Client;
import personne.Vendeur;
import stockMedicament.GestionnaireMedicaments;
import stockMedicament.Medicament;
import utilisateur.ControleurSysteme;
import vente.GestionnaireVentes;

public class ControleurSaisieVentes {
	
	private ControleurSysteme sys; 
	
	private GestionnaireMedicaments gestMeds; 
	private GestionnaireVentes gestVentes; 
	
	private FenetreSaisieVentes fVentes;
	

	
	public ControleurSaisieVentes(ControleurSysteme sys) { 
		
		// connaitre le controleur système
		this.sys = sys; 
		
		// connaitre les gestionnaires nécessaires 
		gestMeds = sys.getGestionnaireMedicaments(); 
		gestVentes = sys.getGestionnaireVentes(); 
		
	}
	
	
	
	// --- methodes nécessaires au bon fonctionnement de fVentes
	protected DefaultListModel<String> getListeMedicaments() {
		// get the list of drugs 
		ArrayList<Medicament> listeMedicaments = gestMeds.getMedicaments();
		Iterator<Medicament> i = listeMedicaments.iterator(); 
		
		// make a list of the corresponding drugs
		DefaultListModel<String> lNomMeds = new DefaultListModel<String>();
		while(i.hasNext()) {
			lNomMeds.addElement(i.next().getNom()); 
		}
		
		return lNomMeds; 
	}

	protected int getQuantite(String medicament) {
		Medicament med = gestMeds.get(medicament); 
		return med.getQuantite();
	}
	

	
	// --- methodes en rapport avec l'etablissement d'une vente 
	protected void etablirVente(Client c, Vendeur v, ArrayList<Medicament> lMedicaments) {
		gestVentes.add(c, v, lMedicaments); 
		if(!sys.getGestionnaireVendeurs().contains(v)) {
			sys.getGestionnaireVendeurs().add(v);
		}
		if(!sys.getGestionnaireClients().contains(v)) {
			sys.getGestionnaireClients().add(c);
		}
	}
	
	protected ArrayList<Medicament> majStock(List<String> lMeds, ArrayList<Integer> quantiteMedicaments) {
		
		ArrayList<Medicament> listeMedicamentsAAcheter = new ArrayList<Medicament>(); 
		
		for(int i=0; i<lMeds.size(); i++) {
			// mettre à jour 
			String strMed = lMeds.get(i); 
			Medicament med = gestMeds.get(strMed); 
			int qte = med.getQuantite(); 
			if(qte - quantiteMedicaments.get(i) == 0 ) {
				gestMeds.delete(gestMeds.indexOf(med));
			}
			else {
				med.setQuantite(qte - quantiteMedicaments.get(i));
			}
			
			// creer la liste des médicaments à acheter
			Medicament newMed = med.copy(); 
			newMed.setQuantite(quantiteMedicaments.get(i));
			listeMedicamentsAAcheter.add(newMed); 
		}
		return listeMedicamentsAAcheter;
	}
	
	
	
	// --- interactions with the sales controller
	// give power to the sales controller
	public void ouvrir() {
		fVentes = new FenetreSaisieVentes(this); 
	}
	
	// give power back to the system controller
	public void fermer() {
		fVentes.fermer(); 
		sys.ouvrir();
	}

}
