package stockMedicament;
import java.util.ArrayList;

public class GestionnaireMedicaments {
	
	private ArrayList<Medicament> medicaments = new ArrayList<Medicament>();
	
	public ArrayList<Medicament> getMedicaments(){
		return medicaments;
	}
	
	public boolean contains(Medicament med) {
		
		return medicaments.contains(med);
	}
	
	public Medicament get(String med) {
		Medicament m = new Medicament(med); 
		int index = medicaments.indexOf(m); 
		return medicaments.get(index); 
	}
	
	public void add(Medicament med) {
		
		medicaments.add(med);
	}
	
	public void delete(int index) {
		medicaments.remove(index);
	}	
	
	public int indexOf(Medicament med) {
		return medicaments.indexOf(med); 
	}

}
