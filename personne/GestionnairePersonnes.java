package personne;
import java.util.ArrayList;

public class GestionnairePersonnes {


	private ArrayList<Personne> listePersonnes = new ArrayList<Personne>(); 
	
	
	public ArrayList<Personne> getListePersonnes() {
		return listePersonnes;
	}
	
	

	public boolean contains(Vendeur vendeur) {
		return listePersonnes.contains(vendeur);
	}
	
	public boolean contains(Client client) {
		return listePersonnes.contains(client); 
	}

	public void add(Vendeur vendeur) {
		listePersonnes.add(vendeur);
		//System.out.println("Un nouveau vendeur a été ajouté.");
		return; 
	}

	
	public void add(Client client) {
		listePersonnes.add(client); 
		//System.out.println("Un nouveau client a été ajouté.");
		return; 
	}

}
