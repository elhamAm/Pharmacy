package stockMedicament;

import javax.swing.JFrame;


import utilisateur.ControleurSysteme;



public class ControleurMedicaments extends JFrame{

	private static final long serialVersionUID = -8460385155723415868L;
	private GestionnaireMedicaments gestMeds;
	private FenetreStock fStock;
	
	private ControleurSysteme sys;
	
	 
	public ControleurMedicaments(ControleurSysteme sys) {
		
		//ecran avec les options du stock est affiché dés que le constructeur est appelé
		this.sys = sys;

		this.gestMeds = sys.getGestionnaireMedicaments();

		fStock = new FenetreStock(this, gestMeds);
		
		
	}
	
	protected void ajouter(Medicament med) {
		
		//ajouter le mediments à notre gestionnaire de medicaments
		if(gestMeds.contains(med)) {
			fStock.envoyerMessage("Ce médicament est déjà existant");
		}
		else {
			gestMeds.add(med);
			fStock.maj(gestMeds);
		}
	}
	
	
	protected void supprimer(int selectionne) {
	
		gestMeds.delete(selectionne);
		fStock.maj(gestMeds);
	}
	
	protected void modifier(int index, int quantite) {//, String type, double prix) {
		
		Medicament m = gestMeds.getMedicaments().get(index); 
		//m.setPrix(prix);
		m.setQuantite(quantite);
		//m.setType(type);
		
		fStock.maj(gestMeds);
	}
	
	protected Medicament get(int i) {
		return gestMeds.getMedicaments().get(i);
	}
	
	
	// rendre le pouvoir au controleurSysteme
	public void exit() {
		fStock.fermer(); 
		sys.ouvrir();
	}
	
	// ouvrir la fenetre de 
	public void ouvrir() {
		fStock.maj(gestMeds);
		fStock.ouvrir();
	}

}
