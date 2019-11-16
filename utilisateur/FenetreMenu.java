package utilisateur;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;

public class FenetreMenu extends JFrame {

	private static final long serialVersionUID = -3831113405039399542L;
	
	private JFrame frame;	
	JButton btnStock;
	JButton btnSaisieVente;
	JButton btnConsultationVente;
	JButton btnRecette;
	
	private ControleurSysteme sys; 

	
	
	public FenetreMenu(ControleurSysteme sys) {
		super("Ma Pharmacie");  
		
		// connaitre le controleur systeme
		this.sys = sys; 
		
		initialiserFrame();
	}

	
	
	private void initialiserFrame() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(4, 1));
		
		btnStock = new JButton("Consulter le stock");
		frame.getContentPane().add(btnStock);
		btnSaisieVente = new JButton("Saisir une vente");
		frame.getContentPane().add(btnSaisieVente);
		btnConsultationVente = new JButton("Consulter les ventes"); 
		frame.getContentPane().add(btnConsultationVente); 
		btnRecette = new JButton("Consulter les recettes");
		
		frame.getContentPane().add(btnRecette);
		
		ecouter(); 
		
	}
	
	private void ecouter() {
		
		// passer le controle au controleur des médicaments si on appuie sur le bouton "stock"
		btnStock.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				sys.callControleurMedicaments();
				}
		});

		
		// passer le controle au controleur de saisie des ventes si on appuie sur le bouton "saisie ventes"
		btnSaisieVente.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				sys.callControleurSaisieVentes(); 
				}
		});

		
		// passer le controle au controleur de consultation des ventes si on appuie sur le bouton "consultation ventes"
		btnConsultationVente.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				sys.callControleurConsultationVentes(); 
				}
		});
		
		
		// passer le controle au controleur des recettes si on appuie sur le bouton "consulter recettes"
		btnRecette.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				sys.callControleurRecette();
				}
		});
	}

	
	// --- ouvrir et fermer la fenetre 
	protected void ouvrir() {
		frame.setVisible(true); 
	}
	
	protected void fermer() {
		frame.setVisible(false);
	}

}
