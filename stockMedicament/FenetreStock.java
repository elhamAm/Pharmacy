package stockMedicament;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class FenetreStock extends JFrame {

	private static final long serialVersionUID = 2642685701690699119L;

	private int quantite;
	
	private JPanel contentPane;	
	private JList<String> liste;
	
	private final JButton btnVoirDonnees = new JButton("Voir les donnees");
	private JButton btnSupprimer = new JButton("Supprimer");
	private JButton btnModifier = new JButton("Modifier");
	private JButton btnAjouter = new JButton("Ajouter");
	private JButton btnRetour = new JButton("Retour");
	private JButton btnValiderModif = new JButton("Valider Modification");
	private JTextField textQuantite = new JTextField();
	private JFrame quantiteFrame; 
	
	private DefaultListModel<String> model = new DefaultListModel<String>();

	
	private ControleurMedicaments controleMeds;
	private FenetreSaisieMedicaments fSaisirMed;
	private FenetreDonneeMed fDonneeMed;


	public FenetreStock(ControleurMedicaments controleMeds, GestionnaireMedicaments gestMeds) {
		super("Stock"); 
		
		this.controleMeds =controleMeds;
		fSaisirMed = new FenetreSaisieMedicaments(controleMeds);
		fDonneeMed = new FenetreDonneeMed();
		
		maj(gestMeds);
		liste = new JList<String>(model);
		liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	
		initialiserFrame();
		

		ecouter(); 
		
		
	}
	
	private void initialiserFrame() {

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(liste, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(16)
							.addComponent(btnVoirDonnees)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnSupprimer, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
								.addComponent(btnModifier, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
								.addComponent(btnAjouter, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
								.addComponent(btnRetour, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(liste, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnRetour)
							.addGap(12)
							.addComponent(btnAjouter)
							.addGap(14)
							.addComponent(btnSupprimer)
							.addGap(11)
							.addComponent(btnModifier)
							.addGap(8)
							.addComponent(btnVoirDonnees, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addGap(43))))
		);
		
		contentPane.setLayout(gl_contentPane);
		
		setContentPane(contentPane);
	}
	
	// met à jour la liste des médicaments
	protected void maj(GestionnaireMedicaments gestMeds){
		model.clear();
		ArrayList<Medicament> meds = gestMeds.getMedicaments(); 
		for (int i = 0; i < meds.size(); i++) {
			model.addElement(meds.get(i).getNom());
		}
		
		
	}
	
	
	protected void envoyerMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	
	private void ecouter() {
		
		/* **********************   bouton du stock (ajouter) ***************** */
		btnAjouter.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e){
			
				//l'ecran de la demande de medicament d'affiche
				// TODO : passer par le controleur ??? 
				fSaisirMed.ouvrir();		

			}
		});
		
		
		/* **********************  bouton de la suppression de medicaments ***************** */
		btnSupprimer.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e){
			
				
				int i = liste.getSelectedIndex();
				if(i==-1) {
					return; 
				}
				else {
					controleMeds.supprimer(i);
				}
			}
		});
		
		
		/* **********************  bouton de la modification de medicaments ***************** */
		btnModifier.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int i = liste.getSelectedIndex();
				if(i==-1) {
					return; 
				}
				else {
					ecranSaisirModification(controleMeds.get(i));
				}
			}
		});
		
		/* **********************  bouton qui saisit la quantité modifiée de medicaments ***************** */
		btnValiderModif.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e){
				String quantiteStr = textQuantite.getText();
				quantite = Integer.parseInt(quantiteStr);
				int selectionne = liste.getSelectedIndex();
				//changer la quantite du medicament choisi
				controleMeds.modifier(selectionne, quantite);
				quantiteFrame.setVisible(false);
			}
		});
		
		/* **********************  bouton qui nous fait voir les donnees ***************** */
		btnVoirDonnees.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e){
				int i = liste.getSelectedIndex();
				if(i==-1) {
					return; 
				}
				else {
					fDonneeMed.ouvrir(controleMeds.get(i));
				}				
			}
		});
	
		/* **********************  bouton qui nous retourne au menu ***************** */
		btnRetour.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e){
				//setVisible(false);
				controleMeds.exit();
				
			}
		});
	}	
	
	
	private void ecranSaisirModification(Medicament m){
		// TODO : factor ceci en une classe
		JFrame modifierFrame = new JFrame();
		modifierFrame.getContentPane().setLayout(new GridLayout(3,2));
		modifierFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		modifierFrame.setSize(400,200);
		modifierFrame.setTitle("Modifier médicament");
	
		
		JLabel lblMedAModif = new JLabel("Medicament à modifier :"); 
		modifierFrame.getContentPane().add(lblMedAModif); 
		
		JLabel lblMed = new JLabel(m.getNom()); 
		modifierFrame.getContentPane().add(lblMed);
		
		/*
		JLabel lblPrix = new JLabel("Prix :"); 
		modifierFrame.getContentPane().add(lblPrix); 
		
		JTextField txtPrix = new JTextField(Double.toString(m.getPrix()));
		modifierFrame.getContentPane().add(txtPrix); 
		
		
		JLabel lblType = new JLabel("Type :"); 
		modifierFrame.getContentPane().add(lblType); 
		
		JTextField txtType = new JTextField(m.getType());
		modifierFrame.getContentPane().add(txtType);
		*/ 
		
		
		JLabel lblQte = new JLabel("Quantité :"); 
		modifierFrame.getContentPane().add(lblQte); 
		
		JTextField txtQte = new JTextField(Integer.toString(m.getQuantite()));
		modifierFrame.getContentPane().add(txtQte); 
	 
		
		JButton btnValiderModif =  new JButton("Valider Modification");
		modifierFrame.getContentPane().add(btnValiderModif);
		
		modifierFrame.setVisible(true);
		
		/* **********************  bouton qui saisit la quantité modifiée de medicaments ***************** */
		btnValiderModif.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e){
				
				// get field values 
				//String type = txtType.getText(); 
				String strQte = txtQte.getText(); 
				//String strPrix = txtPrix.getText(); 
				int index = liste.getSelectedIndex();
				
				
				if(strQte.isEmpty()) {
					JOptionPane.showMessageDialog(modifierFrame, "Veuillez entre une quantité.", 
							JOptionPane.MESSAGE_TYPE_PROPERTY, JOptionPane.ERROR_MESSAGE);
					return; 
				}
				/*
				if(type.isEmpty() || strQte.isEmpty() || strPrix.isEmpty()) {
					// TODO : fire an error 
					return; 
				}
				*/ 
				
				int quantite; 
				// double prix; 
				
				try {
					 quantite = Integer.parseInt(strQte); 
				} catch (NumberFormatException nbfe) {
					JOptionPane.showMessageDialog(modifierFrame, "La quantité devrait être un entier.", 
							JOptionPane.MESSAGE_TYPE_PROPERTY, JOptionPane.ERROR_MESSAGE);
					return; 
				}
				/*
				try {
					prix = Double.parseDouble(strPrix); 
				} catch (NumberFormatException nbfe) {
					JOptionPane.showMessageDialog(modifierFrame, "Le prix devrait avoir une valeur numérique.", 
							JOptionPane.MESSAGE_TYPE_PROPERTY, JOptionPane.ERROR_MESSAGE); 
					return; 
				}
				*/ 
				
				//changer la quantite du medicament choisi
				controleMeds.modifier(index, quantite); // , type, prix); 
				
				modifierFrame.dispose();
			}
		});
	}
	
	
	
	// ouvrir et fermer
	protected void ouvrir() {
		setVisible(true);	
	}
	
	protected void fermer() {
		setVisible(false);
	};
}
