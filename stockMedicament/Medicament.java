package stockMedicament;

public class Medicament {
	private String nom; 
	private double prix; 
	private String type; 
	private int quantite;
	
	public Medicament(String nom, double prix, String type, int quantite) {
		this.nom = nom;
		this.prix = prix;
		this.type = type;
		this.quantite= quantite;
	}
	
	protected Medicament(String nom) {
		this.nom = nom; 
		this.prix = 0; 
		this.type = ""; 
		this.quantite = 0; 
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
		
		Medicament other = (Medicament) obj; 
		
		return this.getNom().equals(other.getNom());
	}

	
	public Medicament copy() {
		return new Medicament(this.nom, this.prix, this.type, this.quantite); 
	}


	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	} 
	
	
}
