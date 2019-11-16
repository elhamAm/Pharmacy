package vente.saisie;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import personne.Client;
import personne.Vendeur;



public class FenetreSaisieVentes extends JFrame {

	private static final long serialVersionUID = -4018730784242891607L;
	
	// conteneurs 
	private Container conteneurForm; // conteneur pricipal
	private JPanel conteneurVendeur;
 	private JPanel conteneurClient; 
 	private JScrollPane conteneurMedicaments; 
 	private JPanel conteneurBouttons; 
 	
 	// bouttons  
	JButton btnValider;
	JButton btnRetour; 
	
 	private DefaultListModel<String> lMedicaments; 
 	private JList<String> jlMedicaments =new JList<String>(); 
 	
	private JTextField tfNomVendeur;
	private JTextField tfPrenomVendeur;
	private JTextField tfNomClient;
	private JTextField tfPrenomClient;
	private JTextField tfSecuSociale;
	
	private ControleurSaisieVentes cVentes; 
	
	private String nomVendeur; 
	private String prenomVendeur; 
	private String nomClient; 
	private String prenomClient; 
	private Integer nrSecuSociale; 
	
	
	public FenetreSaisieVentes(ControleurSaisieVentes cv) {
		// set the title of the frame
		super("Ventes");
		
		// initialize classes attributes  
		cVentes = cv; // sales controler -> who this class reports results to.
		
		
		// field default values
		nomVendeur = "Anne"; 
		prenomVendeur = "Dupré"; 
		nomClient = "Juste"; 
		prenomClient = "Blanc"; 
		nrSecuSociale = 10; 	
		
		// create the window
		initialiserFramePrincipal();
		
		
		// initialise the sub-components of the main frame 
		initialiserFrameVendeur();
		initialiserFrameClient();
		initialiserFrameMedicaments();
		initialiserFrameBoutons();
		
		// populate main panel 
		conteneurForm.add(conteneurVendeur);
		conteneurForm.add(Box.createVerticalGlue());
		conteneurForm.add(conteneurClient); 
		conteneurForm.add(Box.createVerticalGlue());
		conteneurForm.add(conteneurMedicaments); 
		conteneurForm.add(Box.createVerticalGlue());
		conteneurForm.add(conteneurBouttons);
		
		setVisible(true);
		ecouter(); 
		
	}
	
	
	// --- initialiser l'interface graphique 
	private void initialiserFramePrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		getRootPane().setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
		
	    // main panel 
		conteneurForm = getContentPane(); 
		
		// choose the layout 
		BoxLayout layout = new BoxLayout(conteneurForm, BoxLayout.Y_AXIS);
		conteneurForm.setLayout(layout);
	}
	
	private void initialiserFrameVendeur() {
		
	    // create a panel for salesman information 
		conteneurVendeur = new JPanel(new BorderLayout());
		conteneurVendeur.setLayout(new GridLayout(2,2));
		conteneurVendeur.setBorder(BorderFactory.createTitledBorder("Données Vendeur"));
		
		
		// populate the salesman panel		
		JLabel lblNom = new JLabel("Nom");
		conteneurVendeur.add(lblNom);
		
		tfNomVendeur = new JTextField(nomVendeur);
		tfNomVendeur.setColumns(10);
		conteneurVendeur.add(tfNomVendeur);
		
		JLabel lblPrenom = new JLabel("Prenom");
		conteneurVendeur.add(lblPrenom);
		
		tfPrenomVendeur = new JTextField(prenomVendeur);
		conteneurVendeur.add(tfPrenomVendeur);
		tfPrenomVendeur.setColumns(10);
		
	}
	
	private void initialiserFrameClient() {
		// create a panel for client information 
		conteneurClient = new JPanel();
		conteneurClient.setBorder(new EmptyBorder(5, 5, 5, 5));
		conteneurClient.setLayout(new GridLayout(3,2));
		conteneurClient.setBorder(BorderFactory.createTitledBorder("Données Client"));
		
		// populate the client panel 		
		JLabel lblNom_2 = new JLabel("Nom");
		conteneurClient.add(lblNom_2);
		
		tfNomClient = new JTextField(nomClient);
		conteneurClient.add(tfNomClient);
		tfNomClient.setColumns(10);
		
		JLabel lblPrenom_1 = new JLabel("Prenom");
		conteneurClient.add(lblPrenom_1);
		
		tfPrenomClient = new JTextField(prenomClient);
		conteneurClient.add(tfPrenomClient);
		tfPrenomClient.setColumns(10);
		
		JLabel lblNrSecuriteSociale = new JLabel("Nr Securite Sociale");
		conteneurClient.add(lblNrSecuriteSociale);
		
		tfSecuSociale = new JTextField(nrSecuSociale.toString());
		conteneurClient.add(tfSecuSociale);
		tfSecuSociale.setColumns(10);
	}
	
	private void initialiserFrameMedicaments() {
		// create the list of drugs 
		lMedicaments = cVentes.getListeMedicaments(); 
		jlMedicaments = new JList<String>(lMedicaments); 
		jlMedicaments.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		jlMedicaments.setLayoutOrientation(JList.VERTICAL);
		jlMedicaments.setVisibleRowCount(8); 
		
		// create the corresponding pane
		conteneurMedicaments = new JScrollPane(jlMedicaments);
		conteneurMedicaments.setPreferredSize(new Dimension(250, 80));
		conteneurMedicaments.setBorder(BorderFactory.createTitledBorder("Médicaments (maintenir <ctrl> appuyé pour en séléctioner plusieurs)"));
	}
	
	private void initialiserFrameBoutons() {
		// definir le conteneur de boutons
		conteneurBouttons = new JPanel(); 
		conteneurBouttons.setLayout( new BoxLayout(conteneurBouttons, BoxLayout.X_AXIS) );
		
		
		// definir les boutons
		btnValider = new JButton("Valider");
		btnRetour = new JButton("Retour"); 
		
		
		// populer le conteneur de boutons		
		conteneurBouttons.add(btnValider);
		conteneurBouttons.add(Box.createHorizontalGlue());
		
		conteneurBouttons.add(btnRetour);
	}

	
	// --- listeners de bouttons 
	private void ecouter() {
	
		// validation 
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nbOfErrors = 0; 
				
				// --- salesman data
				nomVendeur = tfNomVendeur.getText(); 		
				if(nomVendeur.isEmpty()) {
					nbOfErrors++; 
					JOptionPane.showMessageDialog(conteneurForm, "Veuillez spécifier un nom de vendeur.", "Inane error",
						    JOptionPane.ERROR_MESSAGE); 
				}
				
				prenomVendeur = tfPrenomVendeur.getText();
				if(prenomVendeur.isEmpty()) {
					nbOfErrors++; 
					JOptionPane.showMessageDialog(conteneurForm, "Veuillez spécifier un prénom de vendeur.", "Inane error",
						    JOptionPane.ERROR_MESSAGE); 
				}
				
				// --- client data
				nomClient = tfNomClient.getText();
				if(nomClient.isEmpty()) {
					nbOfErrors++; 
					JOptionPane.showMessageDialog(conteneurForm, "Veuillez spécifier un nom de client.", "Inane error",
						    JOptionPane.ERROR_MESSAGE); 
				}
				
				prenomClient = tfPrenomClient.getText();
				if(prenomClient.isEmpty()) {
					nbOfErrors++; 
					JOptionPane.showMessageDialog(conteneurForm, "Veuillez spécifier un prénom de client.", 
								"Inane error",JOptionPane.ERROR_MESSAGE);
				}		

				String secuString = tfSecuSociale.getText(); 
				if(secuString.isEmpty()) {
					nbOfErrors++; 
					
					JOptionPane.showMessageDialog(conteneurForm, "Veuillez spécifier un numéro de sécurité sociale pour le client.", 
								"Inane error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					try {
						nrSecuSociale = Integer.valueOf(secuString);
					} catch(NumberFormatException nfex) {
						nbOfErrors++; 
						JOptionPane.showMessageDialog(conteneurForm, "Le numéro de sécurité sociale devrait êttre un entier", 
								"Inane error", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				
				// --- drug data 
				List<String> lMeds =  jlMedicaments.getSelectedValuesList(); 
				if(lMeds.isEmpty()) {
					nbOfErrors++; 
					JOptionPane.showMessageDialog(conteneurForm, "Veuillez séléctioner un ou plusieurs médicaments. "
							+ "\n\nNote: plusieurs médicaments peuvent être selectionnés en maintenant la touche <Ctrl> appuyée.", 
							"Inane error", JOptionPane.ERROR_MESSAGE);
				}
				
				
				// --- s'il n'y a pas d'erreurs saisir la quantité des médicaments choisis
				if(nbOfErrors==0) {		
					Vendeur v = new Vendeur(nomVendeur, prenomVendeur); 
					Client c = new Client(nomClient, prenomClient, nrSecuSociale); 
					
					new FenetreFinaliserVente(cVentes, c, v, lMeds); 
					fermer();
				}
				
				return; 
			}
		});
	
		// retour 
		btnRetour.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				cVentes.fermer(); 
				}
		}); 
	}
	

	
	// --- ouvrir et fermer la fenetre
	
	protected void fermer() {
		setVisible(false);
		dispose();
	}
	

}
