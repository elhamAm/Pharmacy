package stockMedicament;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FenetreDonneeMed extends JFrame {

	private static final long serialVersionUID = -1842722242523602119L;
	private JPanel contentPane;
	private JTextField textNom;
	private JTextField textPrix;
	private JTextField textType;
	private JTextField textQuantite;



	public FenetreDonneeMed() {
		initaliserFrame();
	}
	
	private void initaliserFrame(){
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		setContentPane(contentPane);
		
		JLabel lblNom = new JLabel("Nom");
		contentPane.add(lblNom);
		
		JLabel lblPrix = new JLabel("Prix");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblPrix, 60, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNom, 0, SpringLayout.WEST, lblPrix);
		contentPane.add(lblPrix);
		
		JLabel lblType = new JLabel("Type");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblType, 60, SpringLayout.WEST, contentPane);
		contentPane.add(lblType);
		
		JLabel lblQuantite = new JLabel("Quantite");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblQuantite, 60, SpringLayout.WEST, contentPane);
		contentPane.add(lblQuantite);
		
		textNom = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, textNom, 63, SpringLayout.EAST, lblNom);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNom, 5, SpringLayout.NORTH, textNom);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textNom, -212, SpringLayout.SOUTH, contentPane);
		contentPane.add(textNom);
		textNom.setColumns(10);
		
		textPrix = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblPrix, 5, SpringLayout.NORTH, textPrix);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textPrix, 90, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textPrix, 0, SpringLayout.EAST, textNom);
		contentPane.add(textPrix);
		textPrix.setColumns(10);
		
		textType = new JTextField();
		
		sl_contentPane.putConstraint(SpringLayout.NORTH, textType, 27, SpringLayout.SOUTH, textPrix);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblType, 5, SpringLayout.NORTH, textType);
		sl_contentPane.putConstraint(SpringLayout.WEST, textType, 0, SpringLayout.WEST, textNom);
		contentPane.add(textType);
		textType.setColumns(10);
		
		textQuantite = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textQuantite, 41, SpringLayout.SOUTH, textType);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblQuantite, 5, SpringLayout.NORTH, textQuantite);
		sl_contentPane.putConstraint(SpringLayout.WEST, textQuantite, 0, SpringLayout.WEST, textNom);
		contentPane.add(textQuantite);
		textQuantite.setColumns(10);
	}
	
	
	protected void ouvrir(Medicament med) {
		setVisible(true);
		
		textNom.setText(med.getNom());
		textPrix.setText(Double.toString(med.getPrix()));
		textType.setText(med.getType());
		textQuantite.setText(Integer.toString(med.getQuantite()));
		
		textNom.setEditable(false);
		textPrix.setEditable(false);
		textType.setEditable(false);
		textQuantite.setEditable(false);
		
		
		
	}
}
