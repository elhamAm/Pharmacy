package utilisateur;

import vente.GestionnaireVentes;
import vente.Vente;
import vente.consultation.ControleurConsultationVentes;
import vente.saisie.ControleurSaisieVentes;

import stockMedicament.ControleurMedicaments;
import stockMedicament.GestionnaireMedicaments;
import stockMedicament.Medicament;

import personne.Client;
import personne.GestionnairePersonnes;
import personne.Vendeur;

import recette.ControleurRecettes;

import java.util.ArrayList;
import java.util.Date;



public class ControleurSysteme {
	
	// Gestionnaires 
	private GestionnaireVentes gestVentes; 
	private GestionnaireMedicaments gestMeds; 
	private GestionnairePersonnes gestClients;
	private GestionnairePersonnes gestVendeurs;
	
	// Controleurs 
	private ControleurMedicaments controleurMeds;
	private ControleurSaisieVentes controleurSaisieVentes;
	private ControleurConsultationVentes controleurConsultationVentes; 
	private ControleurRecettes controleurRecettes;
	
	// Fenetre de menu (premiere fenetre qui s'affiche) 
	private FenetreMenu fUtilisateur; 

	
	
	ControleurSysteme() {
		
		// instancier les composantes du système 
		gestVentes = new GestionnaireVentes();
		gestMeds = new GestionnaireMedicaments(); 
		gestClients = new GestionnairePersonnes();
		gestVendeurs = new GestionnairePersonnes();

		controleurMeds = new ControleurMedicaments(this);
		controleurSaisieVentes = new ControleurSaisieVentes(this);
		controleurConsultationVentes = new ControleurConsultationVentes(this); 
		controleurRecettes = new ControleurRecettes(this);
		
		/*
		// charger les données supposées dans laa base de donnée 
		gestClients.add(new Client("Dupont", "Marie", 324));
		gestClients.add(new Client("Blanc", "Jean", 324));
		
 
		gestVendeurs.add(new Vendeur("Gardner","Paul"));
		
		gestMeds.add(new Medicament("Paracetamol", 5.43, "Anti-douleur", 39)); 
		gestMeds.add(new Medicament("Algifor", 12.32, "Anti-fièvre", 24)); 
		gestMeds.add(new Medicament("Xanax", 10.50, "Mental", 2)); 
		
		
		ArrayList<Medicament> lMeds1 = new ArrayList<Medicament>(); 
		lMeds1.add(new Medicament("Paracetamol", 5.43, "Anti-douleur", 2)); 
		lMeds1.add(new Medicament("Algifor", 12.32, "Anti-fièvre", 1)); 
		gestVentes.add(new Client("Blanc", "Jean", 324), new Vendeur("Gardner","Paul"), lMeds1);
		
		//lMeds.clear();
		ArrayList<Medicament> lMeds2 = new ArrayList<Medicament>();
		lMeds2.add(new Medicament("Xanax", 10.50, "Mental", 1)); 
		gestVentes.add(new Client("Dupont", "Marie", 324), new Vendeur("Gardner","Paul"), lMeds2);
		ArrayList<Vente> lVentes = gestVentes.getVentes(); 
		Vente v = lVentes.get(0); 
		Date d = v.getFacture().testing__getDate(); 
		d.setTime(0); // mettre la date au 01/01/1970
		*/ 
		
		// mettre la liste des ventes à jour
		gestVentes.supprimerVentesPerimees();
			


		// appeller l'interface grpahique
		fUtilisateur = new FenetreMenu(this); 
		fUtilisateur.ouvrir(); 
		
	}
	
	
	
	
	// -- GUI
	// display the main window of our program 
	public void ouvrir() {
		fUtilisateur.ouvrir(); 
	}
	
	public void fermer() {
		fUtilisateur.fermer();
	}
	
	
	
	
	// --- transmissions of power 
	protected void callControleurMedicaments() {
		controleurMeds.ouvrir(); 
	};
	
	protected void callControleurSaisieVentes() {
		controleurSaisieVentes.ouvrir(); 
	}; 
	
	protected void callControleurConsultationVentes() {
		controleurConsultationVentes.ouvrir(); 
	}; 
	
	protected void callControleurRecette() {
		controleurRecettes.ouvrir(); 
	}; 
	
	
	
	
	// -- getters of the structurators
	public GestionnaireVentes getGestionnaireVentes() {
		return gestVentes; 
	}
	
	public GestionnaireMedicaments getGestionnaireMedicaments() {
		return gestMeds; 
	}
	
	public GestionnairePersonnes  getGestionnaireClients() {
		return gestClients;
	}
	
	public GestionnairePersonnes getGestionnaireVendeurs() {
		return gestVendeurs;
	}
	

	
	
	// -- main 
	public static void main(String[] args) {
		
		new ControleurSysteme();
		
	}

}
