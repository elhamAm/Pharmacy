package personne;

public abstract class Personne {
	private String nom; 
	private String prenom;
	
	Personne(String nom, String prenom) {
		this.nom = nom; 
		this.prenom = prenom; 
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

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
		
		Personne other = (Personne) obj; 
		
		if( this.getNom() != other.getNom() ) {
			return false; 
		}
		if( this.getPrenom() != other.getPrenom() ) {
			return false; 
		}
		
		return true;
	} 
	
	
	
}
