package IHM;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Entites.Candidat;
import Entites.Contrainte;
import Entites.Electeur;

@SuppressWarnings("serial")
public class GenerateurSituation extends JDialog implements ActionListener {
	// ATTRIBUTS :
	private JLabel lblNomFichier, lblNbSituation, lblContraintes, lblVotants, lblCandidats;
	private JTextField txtNomFichier, txtNbSituation, txtNbVotant, txtNbCandidats;
	private JButton ok, annuler;
	private List<File> situations;
	

	// CONSTRUCTEURS :
	public GenerateurSituation() {
		super();
		// Paramètrage de la fenetre
		setModal(true);
		setSize(670, 470);
		setTitle("Générateur de situations éléctorale");
		setResizable(false);
		setLocationRelativeTo(this.getParent());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		// Initialisation des attributs
		situations = new ArrayList<File>();
		
		lblNomFichier = new JLabel("Nom du Fichier : ");
		lblNbSituation= new JLabel("Nombre de situations :");
		lblContraintes = new JLabel("Contraintes :");
		lblVotants= new JLabel("Nombre d'électeurs");
		lblCandidats= new JLabel("Nombre de candidats");
		
		txtNomFichier = new JTextField();
		txtNbSituation =new JTextField();
		txtNbVotant= new JTextField(); 
		txtNbCandidats=new JTextField();

		ok = new JButton("OK");
		annuler = new JButton("Annuler");
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2, 2));
		panel1.add(lblNomFichier);
		panel1.add(txtNomFichier);
		panel1.add(lblNbSituation);		
		panel1.add(txtNbSituation);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2, 2));
		panel2.add(lblCandidats);
		panel2.add(txtNbCandidats);
		panel2.add(lblVotants);		
		panel2.add(txtNbVotant);
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(1, 2));
		panel3.add(ok);
		panel3.add(annuler);

		// Disposition
		setLayout(new GridLayout(4,1));
		add(panel1);
		add(lblContraintes);
		add(panel2);
		add(panel3);
		

		// On ajoute la fenetre comme ActionListener
		ok.addActionListener(this);
		annuler.addActionListener(this);
	}

	// METHODES
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok && verifieEntres()) {
			String nom = txtNomFichier.getText();
			int nbVotants = Integer.parseInt(txtNbVotant.getText());;
			int nbCandidats = Integer.parseInt(txtNbCandidats.getText());;
			int nbSituations = Integer.parseInt(txtNbSituation.getText());;
			Contrainte contrainte = new Contrainte(nbVotants, nbCandidats);
			for(int i =0;i<nbSituations;i++) {
				try {
					situations.add(creerSituation(nom+" "+i+" ",contrainte));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			this.dispose();
		}
		if (e.getSource() == annuler) {
			this.dispose();
		}
	}

	public File creerSituation(String nom, Contrainte contrainte) throws IOException {
		FileWriter fw = new FileWriter("Fichiers/"+nom+".stn");
		Candidat[] candidats = contrainte.creerListeDeCandidats();
		Electeur[] corpsElectoral = contrainte.creerCorpsElectoral(candidats);
		Date maintenant = new Date();
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat heure = new SimpleDateFormat("H:mm:ss");
		fw.write("##Nom##" + "\n" + nom + "\n\n" 
				+ "##Date##" + "\n" + date.format(maintenant) + "\n\n"
				+ "##Heure##" + "\n" + heure.format(maintenant) + "\n\n"
				+ "CARACTERISTIQUE" + "\n\n"
				+ "##Nombre de votants##" + "\n" + contrainte.getNombreElecteurs() + "\n\n"
				+ "##Nombre de candidats##" + "\n" + contrainte.getNombreCandidats() + "\n\n"
				+ "##Noms des candidats##" + "\n");
		for(Candidat c:candidats) {
			fw.write(c.getNom()+";");
		}
		fw.write("\n\n"+ "##CHOIX##"+"\n");
		for(Electeur e:corpsElectoral) {
			Candidat[] listeCandidats = e.getListeDeCandidats();
			for(Candidat c:listeCandidats) {
				fw.write(c.getNom().replaceAll("Candidat", "") + ";");
			}
			fw.write("1\n");
		}
		fw.close();
		return new File("Fichiers/"+nom+".stn");
	}
	
	public boolean verifieEntres() {
		if (!estUnEntier(txtNbCandidats.getText()) || !estUnEntier(txtNbSituation.getText()) || !estUnEntier(txtNbVotant.getText())) {
			JOptionPane.showMessageDialog(this.getParent(), "Vérifiez les données saisies",
					"Message d'erreur", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (txtNomFichier.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this.getParent(), "Vérifiez entrer un nom",
					"Message d'erreur", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public boolean estUnEntier(String chaine) {
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	
	public List<File> getFiles() {
		return situations;
	}
	
	
}