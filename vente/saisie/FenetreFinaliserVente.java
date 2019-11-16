package vente.saisie;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerNumberModel;

import personne.Client;
import personne.Vendeur;
import stockMedicament.Medicament;

public class FenetreFinaliserVente extends JFrame {

	//super("Saisie quantité de médicaments"); 

	private static final long serialVersionUID = 5861085093977100693L;
	private JPanel contentPane;
	
	ArrayList<JSpinner> spinnerRecall; 
	
	private ControleurSaisieVentes cVentes; 
	
	private Client client; 
	private Vendeur vendeur; 


	public FenetreFinaliserVente(ControleurSaisieVentes cVentes, Client c, Vendeur v, List<String> lMeds) {
		
		// set attributes 
		this.cVentes = cVentes; 
		this.client = c; 
		this.vendeur = v; 
		
		// the main panel 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 300);
		getRootPane().setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// choose the layout 
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS)); 		
		
		
		// --- create pane where we display quantities for the selected drugs 
		JPanel drugPane = new JPanel(); 
		drugPane.setLayout(new GridLayout(lMeds.size(),2));
		spinnerRecall = new ArrayList<JSpinner>(); 
		
		// for every drug display a name with a spinner for the quantity
		Iterator<String> i = lMeds.iterator(); 
		while(i.hasNext()) {
			String medName = i.next(); 
			int qte = cVentes.getQuantite(medName); 
		
			JLabel lblMed = new JLabel(medName); 
			JSpinner spinner = new JSpinner( new SpinnerNumberModel(1, 1, qte, 1) ); 
			((DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
			spinnerRecall.add(spinner); 
					
			drugPane.add(lblMed); 
			drugPane.add(spinner); 
			
		}
		
		// make it scrollable
		JScrollPane scrollPane = new JScrollPane(drugPane);
		
		
		// --- instructions to the user
		JLabel lblIntro = new JLabel("Veuillez selectioner les quantités de médicaments désirés"); 
		
		// --- validation button 
		JButton btnValider = new JButton("Valider"); 
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// transform spinner list to a list of quantities 
				ArrayList<Integer> quantiteMedicaments = new ArrayList<Integer>(); 
				for (JSpinner spin: spinnerRecall) {
					Integer value = (Integer)spin.getValue(); 
					quantiteMedicaments.add( value ); 
				}
				
				
				ArrayList<Medicament> lMedicaments = cVentes.majStock(lMeds, quantiteMedicaments); 
				
				cVentes.etablirVente(client, vendeur, lMedicaments); 
				
				// retourner vers le menu de vente 
				fermer(); 
				return; 
			}
		}); 
		
		
		// --- populate the main window 
		contentPane.add(lblIntro); 
		contentPane.add(Box.createVerticalGlue());
		
		contentPane.add(scrollPane); 
		
		contentPane.add(Box.createVerticalGlue());
		contentPane.add(btnValider);  
		
		setVisible(true); 
	}
	
	
	private void fermer() {
		cVentes.ouvrir();
		setVisible(false); 
		dispose(); 
	}
	

}
