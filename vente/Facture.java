package vente;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Facture {	
	
	private double prixTotal; 
	private Date date;
	
	public Facture(double pt){
		this.prixTotal = pt; 
		this.date = new Date(System.currentTimeMillis()); 
	}
	
	public double getPrixTotal() {
		return prixTotal;
	}
	
	public String getDateAsString() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm"); 
		return dateFormat.format(date);
	}
	
	public Date getDate() {
		Date d = new Date(date.getTime()); 
		return d;
	}
	
	@Deprecated
	public Date testing__getDate() {
		// TODO : this is a security flaw : it is kept here for making testing easier. 
		return date; 
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
		
		Facture other = (Facture) obj; 
		
		if( !this.getDate().equals(other.getDate()) ) {
			return false; 
		}
		if( this.getPrixTotal() != other.getPrixTotal() ) {
			return false; 
		}
		
		return true;
	} 
}
