package vente.consultation;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import stockMedicament.Medicament;
import vente.Vente;

public class FenetreConsultationVentes extends JFrame {
	
	private static final long serialVersionUID = -2528267157343697393L;
	int i = 1;
	private JPanel contentPane;
	private JScrollPane conteneurVentes; 
	private JPanel conteneurDetails; 
	
	private JButton btnRetour; 
	
	private JLabel lblVendeur; 
	private JLabel lblClient; 
	private JLabel lblSecuSociale; 
	private JLabel lblDate; 
	private JLabel lblMontant; 
	private JLabel lblMeds; 
	
	private ControleurConsultationVentes cVentes; 
	
	private ArrayList<Vente> lVentes; 
	private DefaultListModel<String> lAffichageVentes; 
	private JList<String> jlAffichageVentes; 
	

	public FenetreConsultationVentes(ControleurConsultationVentes cVentes) {
		super("Consultation des ventes"); 
		
		// know the controler this window belongs to 
		this.cVentes = cVentes; 
		
		// initialise variables 
		lAffichageVentes = new DefaultListModel<String>(); 
		
		conteneurDetails = new JPanel();
		conteneurDetails.setBorder(BorderFactory.createTitledBorder("Détails de la vente"));
		
		initialiserFramePrincipal();
		initialiserFrameVentes();
		initialiserFrameDetails();
		
		btnRetour = new JButton("Retour"); 
		
		contentPane.add(conteneurVentes); 
		contentPane.add(Box.createVerticalGlue());
		contentPane.add(conteneurDetails); 
		contentPane.add(Box.createVerticalGlue());
		contentPane.add(btnRetour); 
		
		ecouter(); 
		
		setVisible(true); 
	}
	
	
	
	// --- initialiser l'interface graphique 
	private void initialiserFramePrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		setContentPane(contentPane);	
	}
	
	private void initialiserFrameVentes() {

		// chercher la liste des ventes 
		lVentes = cVentes.getVentes(); 
		
		// creer une liste des ventes en string 
		for(Vente vente: lVentes) {
			lAffichageVentes.addElement(vente.getVendeur().getPrenom()+" "+vente.getVendeur().getNom() 
					+ " -> " + vente.getClient().getPrenom() +" "+ vente.getClient().getNom());			
		}
		
		jlAffichageVentes = new JList<String>(lAffichageVentes); 
		jlAffichageVentes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		jlAffichageVentes.setLayoutOrientation(JList.VERTICAL);
		jlAffichageVentes.setVisibleRowCount(8); 
		
		// create the corresponding panel
		conteneurVentes = new JScrollPane(jlAffichageVentes);
		conteneurVentes.setPreferredSize(new Dimension(250, 80));
		conteneurVentes.setBorder(BorderFactory.createTitledBorder("Ventes"));
	}
	
	
	private void initialiserFrameDetails() {
		conteneurDetails.setLayout(new GridLayout(6, 2));
		// 1
		JLabel lblTagVendeur = new JLabel("Vendeur : "); 
		conteneurDetails.add(lblTagVendeur); 
		lblVendeur = new JLabel("");
		conteneurDetails.add(lblVendeur); 
		
		// 2, 3
		JLabel lblTagClient = new JLabel("Client : "); 
		conteneurDetails.add(lblTagClient); 
		lblClient = new JLabel("");
		conteneurDetails.add(lblClient); 
		
		JLabel lblSecuSocialeTag = new JLabel("Nr securité sociale : "); 
		conteneurDetails.add(lblSecuSocialeTag); 
		lblSecuSociale = new JLabel(""); 
		conteneurDetails.add(lblSecuSociale); 
		
		// 4 
		JLabel lblDateTag = new JLabel("Date : "); 
		conteneurDetails.add(lblDateTag); 
		lblDate = new JLabel(""); 
		conteneurDetails.add(lblDate); 
		
		// 5 
		JLabel lblMontantTag = new JLabel("Montant total : "); 
		conteneurDetails.add(lblMontantTag); 
		lblMontant = new JLabel(""); 
		conteneurDetails.add(lblMontant);	
		
		//6
		JLabel lblMedsTag = new JLabel("Medicaments (quantité) : "); 
		conteneurDetails.add(lblMedsTag); 
		lblMeds = new JLabel(""); 
		conteneurDetails.add(lblMeds); 
		
	}
	
	
	
	// --- permettre à l'interface de réagir aux actions de l'utilisateur 
	private void ecouter() {
		
		
		// --- selection d'une vente 
		jlAffichageVentes.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()==true) {
					return; 
				}
				
				ListSelectionModel lsm = jlAffichageVentes.getSelectionModel();
				int index = lsm.getMinSelectionIndex(); 
				Vente v = lVentes.get(index); 
				majFrameDetails(v); 
			}
		});
		
		
		// --- bouton retour 
		btnRetour.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				cVentes.fermer(); 
				}
		}); 
		
	}
	
	
	
	// --- mise à jour des composantes de l'interface graphique 

	private void majFrameDetails(Vente v) {
		lblVendeur.setText(v.getVendeur().getNom()+ " " + v.getVendeur().getPrenom()); 
		lblClient.setText(v.getClient().getNom()+ " " + v.getClient().getPrenom()); 
		lblSecuSociale.setText(Integer.toString(v.getClient().getNumeroSecuriteSociale())); 
		lblDate.setText(v.getFacture().getDateAsString()); 
		lblMontant.setText(String.format("%.2f CHF", v.getFacture().getPrixTotal())); 
		
		String txtMeds = ""; 
		for(Medicament m:v.getListeMedicaments()) {
			txtMeds = txtMeds + m.getNom()+" ("+Integer.toString(m.getQuantite())+") "; 
		}
		lblMeds.setText(txtMeds);
	}
	
	
	
	// --- ouvrir et fermer la fenetre 
	
	protected void fermer() {
		setVisible(false); 
		dispose();
	}
	

}
