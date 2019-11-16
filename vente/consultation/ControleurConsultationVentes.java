package vente.consultation;

import java.util.ArrayList;

import utilisateur.ControleurSysteme;
import vente.GestionnaireVentes;
import vente.Vente;

public class ControleurConsultationVentes {
	
	ControleurSysteme sys; 
	GestionnaireVentes gVentes; 
	FenetreConsultationVentes fVentes; 

	public ControleurConsultationVentes(ControleurSysteme sys) {
		
		this.sys = sys; 
		gVentes = sys.getGestionnaireVentes(); 
	
	}

	
	protected ArrayList<Vente> getVentes() {
		return gVentes.getVentes(); 
	}
	
	// --- ouverture et fermeture de l'interface graphique / passassion du pouvoir 
	public void ouvrir() { 
		fVentes = new FenetreConsultationVentes(this); 
	}

	public void fermer() {
		fVentes.fermer();
		sys.ouvrir();		
	}

}
