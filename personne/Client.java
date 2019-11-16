package personne;

public class Client extends Personne {

	private Integer numeroSecuriteSociale;

	public Client(String nom, String prenom, Integer nrSecuSociale) {
		super(nom, prenom); 
		this.numeroSecuriteSociale = nrSecuSociale; 
	}
	
    @Override
    public int hashCode() {
        return this.getNom().hashCode() + this.getPrenom().hashCode() + this.getNumeroSecuriteSociale();
    }
    
    
	@Override 
	public boolean equals(Object o) {
		// check if we were provided with null pointer 
		if(o == null) {
			return false; 
		}
		// class cast 
		if ( getClass() != o.getClass()) {
            return false;
        }
		
		Client other = (Client) o; 
		
		if( this.getNumeroSecuriteSociale() != other.getNumeroSecuriteSociale() ) {
			return false; 
		}
		return true; 
	}
	
	
	
	public int getNumeroSecuriteSociale() {
		return numeroSecuriteSociale;
	}

	
	public void setNumeroSecuriteSociale(Integer numeroSecuriteSociale) {
		this.numeroSecuriteSociale = numeroSecuriteSociale;
	}
	
	
}
