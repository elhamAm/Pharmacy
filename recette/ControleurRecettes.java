package recette;

import utilisateur.ControleurSysteme;

import vente.GestionnaireVentes;
import vente.Vente;

import java.sql.Date;
import java.util.ArrayList;

import personne.Client;
import personne.GestionnairePersonnes;
import personne.Personne;


public class ControleurRecettes {

	private GestionnaireVentes gestVentes;
	private GestionnairePersonnes gestClients;
	private GestionnairePersonnes gestVendeurs;

	private ControleurSysteme sys;

	private FenetreRecettesMenu fMenu;
	private FenetreRecettesResultat fResultat;

	public ControleurRecettes(ControleurSysteme sys) {

		this.sys = sys;

		this.gestClients = sys.getGestionnaireClients();
		this.gestVentes = sys.getGestionnaireVentes();
		this.gestVendeurs = sys.getGestionnaireVendeurs();

		fMenu = new FenetreRecettesMenu(this);
		fResultat = new FenetreRecettesResultat(); 
	}

	protected String[][] construireTable(String type) {

		String[][] donnees = new String[20][20];
		if (fMenu.getPeriode().contentEquals("null")) {
			fMenu.envoyerMessage("Veuillez choisir une période");
		} else {
		
			// les ventes qui correspondent à la période et date voulue
			
			if (type.contentEquals("typeMed")) {
				ArrayList<String> typeMeds = gestVentes.getTypesMeds();
				int i = 0;
				for (String typeMed : typeMeds) {
		
					donnees[i][0] = typeMed;
					double recette = gestVentes.trouverRecette((Object) typeMed, fMenu.getPeriode(), type);
					donnees[i][1] = Double.toString(recette);
					i++;
				}

			} else if (type.contentEquals("vendeur")) {
				int i = 0;
				for (Personne per : gestVendeurs.getListePersonnes()) {
					
					donnees[i][0] = per.getNom();
					donnees[i][1] = per.getPrenom();
					double recette = gestVentes.trouverRecette((Object) per, fMenu.getPeriode(), type);
					donnees[i][2] = Double.toString(recette);
					i++;
				}
			} else if (type.contentEquals("client")) {
				int i = 0;
				for (Personne per : gestClients.getListePersonnes()) {
					donnees[i][0] = per.getNom();
					donnees[i][1] = per.getPrenom();
					donnees[i][2] = Integer.toString(((Client) per).getNumeroSecuriteSociale());
					double recette = gestVentes.trouverRecette((Object) per, fMenu.getPeriode(), type);
					donnees[i][3] = Double.toString(recette);
					i++;
				}
			}
		}
		return donnees;
	}

	protected double trouverRecette() {
		ArrayList<Vente> ventesChoisies;
		if(fMenu.getPeriode().contentEquals("null")) {
			ventesChoisies = gestVentes.getVentes();
		}
			
		else {
			Date date = new Date(System.currentTimeMillis());

			// les ventes qui correspondent à la période et date voulue
			ventesChoisies = gestVentes.trouverVentesDate(date, fMenu.getPeriode());
			
		}
		
		// trouver l'addition des prix des ventes
		double recette = 0;
		for (Vente vente : ventesChoisies) {
			recette += vente.getFacture().getPrixTotal();
		}
		return recette;

	}
	
	
	protected void afficherResultat(String[][] donnees, String[] titreColonnes) {
		fResultat.ouvrir(donnees, titreColonnes);
	}

	
	
	public void ouvrir() {
		fMenu.ouvrir();
	}

	public void fermer() {
		fMenu.fermer();
		sys.ouvrir();
	}
}
