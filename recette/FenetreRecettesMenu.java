package recette;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class FenetreRecettesMenu extends JFrame {
	
	private static final long serialVersionUID = 4881717372975375060L;
	private JPanel contentPane;
	private JButton btnClient = new JButton("client");
	private JButton btnVendeur = new JButton("vendeur");
	private JButton btnTotal = new JButton("total");
	private JButton btnType = new JButton("type de Medicament");
	private JButton btnRetour = new JButton("Retour");
	
	
	private JCheckBox chckbxJournalier = new JCheckBox("journalier");
	private JCheckBox chckbxMensuel = new JCheckBox("mensuel");
	private JCheckBox chckbxHebd = new JCheckBox("hebdomadaire");
	
	private ControleurRecettes cRecettes;
	
	
	String[][] donnees;


	public FenetreRecettesMenu(ControleurRecettes cRecettes) {
	
		this.cRecettes = cRecettes;
	
		initialiserFrame();
	
		ecouter();
	}
	
	private void initialiserFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		chckbxJournalier.setBounds(288, 47, 128, 23);
		contentPane.add(chckbxJournalier);
		
		
		chckbxMensuel.setBounds(288, 118, 128, 23);
		contentPane.add(chckbxMensuel);
		
		
		chckbxHebd.setBounds(288, 191, 128, 23);
		contentPane.add(chckbxHebd);
		
		/* not more than one box can be checked*/
		ButtonGroup groupe = new ButtonGroup();
		groupe.add(chckbxJournalier);
		groupe.add(chckbxMensuel);
		groupe.add(chckbxHebd);
		
		

		btnTotal.setBounds(61, 24, 117, 29);
		contentPane.add(btnTotal);
		
		
	
		btnClient.setBounds(61, 117, 117, 29);
		contentPane.add(btnClient);
		
		

		btnVendeur.setBounds(61, 164, 117, 29);
		contentPane.add(btnVendeur);
		


		btnType.setBounds(34, 210, 182, 29);
		contentPane.add(btnType);
		
		JLabel lblNewLabel = new JLabel("trié par:");
		lblNewLabel.setBounds(21, 84, 61, 16);
		contentPane.add(lblNewLabel);
		

		btnRetour.setBounds(299, 228, 117, 29);
		contentPane.add(btnRetour);
	}
	
	private void ecouter() {
		
		btnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] titreColonnes = { "Nom","Prenom","Social Securité","Recette"};
				donnees = cRecettes.construireTable("client");
				cRecettes.afficherResultat(donnees, titreColonnes);	
			}
		});
		
		btnType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] titreColonnes = { "Nom","Recette"};
				donnees = cRecettes.construireTable("typeMed");
				cRecettes.afficherResultat(donnees, titreColonnes);
			}
		});
		
		btnVendeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] titreColonnes = { "Nom","Prenom","Recette"};
				donnees = cRecettes.construireTable("vendeur");
				cRecettes.afficherResultat(donnees, titreColonnes);
			}
		});
		
		btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double rec = cRecettes.trouverRecette();
				envoyerMessage("recette: " + rec);
			}
		});
		
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cRecettes.fermer();
			}
		});
	}
		
	protected String getPeriode() {
		if (chckbxJournalier.isSelected()) {
			return "journalier";
		} else if (chckbxHebd.isSelected()) {
			return "hebdomadaire";
		} else if (chckbxMensuel.isSelected()) {
			return "mensuel";
		} else {
			return "null";
		}

	}
		
	
	protected void envoyerMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	
	
	protected void ouvrir() {
		setVisible(true);
	}

	protected void fermer() {
		setVisible(false);
	}
}
