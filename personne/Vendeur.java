package personne;

public class Vendeur extends Personne{

	public Vendeur(String nom, String prenom) {
		super(nom, prenom); 
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	
	
	@Override
    public int hashCode() {
        return this.getNom().hashCode() + this.getPrenom().hashCode();
    }
	
	
}
