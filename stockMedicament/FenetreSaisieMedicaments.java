package stockMedicament;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Font;

public class FenetreSaisieMedicaments extends JFrame {

	private static final long serialVersionUID = -7837125459870456854L;

	private JPanel contentPane;
	
	private JTextField textNom;
	private JTextField textPrix;
	private JTextField textType;
	private JTextField textQuantite;
	
	private JButton btnSaisirMed;
	
	private Medicament medicament;
	
	private ControleurMedicaments controleMeds;
	private JLabel label;




	public FenetreSaisieMedicaments(ControleurMedicaments controleMeds) {
		
		this.controleMeds = controleMeds;
		initialiserFrame(); 
		ecouter();
	}
		
	
	// --- methodes pour saisie du formulaire 
	private Medicament getMedicament() {
		String nom = "";
		String type = "";
		float prix = 0;
		int quantite = 0;
		
		nom = textNom.getText();
		String prixStr = textPrix.getText();
		prix = Float.valueOf(prixStr.trim()).floatValue();
		type = textType.getText();
		String quantiteStr = textQuantite.getText();
		quantite = Integer.parseInt(quantiteStr);
		
		medicament = new Medicament(nom, prix, type, quantite);
		return medicament;
	}
	
	private Boolean formulaireIsValide() {
	
		
		if(textNom.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "pas de nom");
			return false;
		}

		if(textPrix.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "pas de Prix");
			return false;
		}

		if(textType.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "pas de type");
			return false;
		}
		
		if(textQuantite.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "pas de type");
			return false;
		}

		return true;
	}
	
	
	// --- methodes pour l'affichage de la fenetre
	private void initialiserFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel nomLabel = new JLabel("Nom");
		nomLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		contentPane.add(nomLabel);
		
		
		textNom = new JTextField();
		contentPane.add(textNom);
		textNom.setColumns(10);
		
		JLabel prixLabel = new JLabel("Prix");
		prixLabel.setFont(new Font("InaiMathi", Font.PLAIN, 15));
		contentPane.add(prixLabel);
		
		textPrix = new JTextField();
		contentPane.add(textPrix);
		textPrix.setColumns(10);
		
		JLabel typeLabel = new JLabel("Type");
		contentPane.add(typeLabel);
		
		textType = new JTextField();
		contentPane.add(textType);
		textType.setColumns(10);
		
		JLabel quantiteLabel = new JLabel("Quantite");
		contentPane.add(quantiteLabel);
		
		textQuantite = new JTextField();
		contentPane.add(textQuantite);
		textQuantite.setColumns(10);
		
		label = new JLabel("");
		contentPane.add(label);
		
		btnSaisirMed = new JButton("Valider");
		
		contentPane.add(btnSaisirMed);
	}
	

	private void ecouter() {
		/* **********************  bouton de la prise de medicaments ***************** */
		btnSaisirMed.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e){
		
				//récupérer le médicament
				if(formulaireIsValide()){
					Medicament med = getMedicament();
					controleMeds.ajouter(med);
							
				}
				setVisible(false);

			}
			
		});
	}
	
	protected void ouvrir() {
		setVisible(true);

	}	

}
