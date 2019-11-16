package recette;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

public class FenetreRecettesResultat extends JFrame {


	private static final long serialVersionUID = 1686656257943502331L;
	private JTable j;
	DefaultTableModel jModel;
	
	JFrame f;
	private String [][] donnees= { { "donnee1", "donnee2" }}; 

	private String[] titreColonnes= { "nom1","nom2" }; 
	

	public FenetreRecettesResultat() {
		
		initialiserFrame();

	}
	
	
	private void initialiserFrame() {

		  	f = new JFrame(); 
		  
	        // Frame Title 
	        f.setTitle("Recettes"); 
	        jModel= new DefaultTableModel(donnees, titreColonnes);
	        j= new JTable(jModel);
	        j.setBounds(30, 40, 200, 300); 
	  
	        // adding it to JScrollPane 
	        JScrollPane sp = new JScrollPane(j); 
	        f.add(sp); 
	        // Frame Size 
	        f.setSize(500, 200); 
	  
	        
		
	}
	
	protected void ouvrir(String [][] d, String[] t) {
				
		  this.donnees= d;
		  this.titreColonnes=t;
		  
		  jModel = new DefaultTableModel(this.donnees, this.titreColonnes);
		  j.setModel(jModel);
		
		  f.setVisible(true); 
	}
	
	

	
}
	
	




