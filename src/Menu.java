import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Entites.Candidat;
import Entites.Contrainte;
import Entites.Electeur;
import Entites.Election;
import Entites.FonctionSatisfaction;
import Entites.AverageGap;
import Entites.Scrutin;
import Entites.ByMajority1Round;
import Entites.ByMajority2Rounds;
import Entites.ByElimination;
import Entites.Situation;
import IHM.GenerateurSituation;

@SuppressWarnings("serial")
public class Menu extends JFrame implements ActionListener, ListSelectionListener {

	// ATTRIBUTS
	private List<File> situations;
	private FonctionSatisfaction fonctionSatisfaction = new AverageGap();
	private JComboBox<FonctionSatisfaction> fonctionSelect;
	private JList<Scrutin> scrutinsSelect;
	private JButton choixFichier, genererFichier, lancer;
	private JLabel lblSatisfaction, lblScrutins, lblSituations;
	private JPanel panel1, panel2, panel3, panel4;
	private JTextArea texteArea;
	private JScrollPane scrollPane;
	private static Scrutin[] scrutinsDisponibles = new Scrutin[3];

	{
		scrutinsDisponibles[0] = new ByMajority1Round();
		scrutinsDisponibles[1] = new ByMajority2Rounds();
		scrutinsDisponibles[2] = new ByElimination();
	}

	// CONSTRUCTEURS

	public Menu() {
		super();
		// Initialisation des attributs
		choixFichier = new JButton("Choose a file");
		genererFichier = new JButton("Create a new file");
		lancer = new JButton("Launch simulation");
		lblSatisfaction = new JLabel("Satisfaction function:");
		lblSatisfaction.setHorizontalAlignment(JLabel.CENTER);
		lblSatisfaction.setVerticalAlignment(JLabel.CENTER);
		lblScrutins = new JLabel("Studied scrutins");
		lblScrutins.setHorizontalAlignment(JLabel.CENTER);
		lblScrutins.setVerticalAlignment(JLabel.CENTER);
		lblSituations = new JLabel("Situations to simulate:");
		lblSituations.setHorizontalAlignment(JLabel.CENTER);
		lblSituations.setVerticalAlignment(JLabel.CENTER);
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		texteArea = new JTextArea();
		scrollPane = new JScrollPane(texteArea);
		fonctionSelect = new JComboBox<FonctionSatisfaction>();
		fonctionSelect.addItem(new AverageGap());
		scrutinsSelect = new JList<Scrutin>(scrutinsDisponibles);
		scrutinsSelect.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		panel1.setLayout(new GridLayout(1, 2));
		panel1.add(lblSatisfaction);
		panel1.add(fonctionSelect);

		panel2.setLayout(new GridLayout(1, 2));
		panel2.add(lblScrutins);
		panel2.add(scrutinsSelect);

		panel3.setLayout(new GridLayout(1, 3));
		panel3.add(choixFichier);
		panel3.add(scrollPane);
		panel3.add(genererFichier);

		panel4.setLayout(new GridLayout(1, 3));
		panel4.add(new JPanel());
		panel4.add(lancer);


		// Disposition
		setLayout(new GridLayout(5, 1));
		add(panel1);
		add(panel2);
		add(lblSituations);
		add(panel3);
		add(panel4);

		// Ajouter ActionListener
		choixFichier.addActionListener(this);
		genererFichier.addActionListener(this);
		lancer.addActionListener(this);
		fonctionSelect.addActionListener(this);
		scrutinsSelect.addListSelectionListener(this);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

	// GETTERS AND SETTERS

	// METHODES
	private Situation lire_simulation(File f) throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner Sc = new Scanner(f);
		Sc.useDelimiter("#");
		int nbVotants = 0;
		int nbCandidats = 1000;
		String[] nomsCandidats = new String[0];
		Candidat[] listeCandidats = new Candidat[0];
		Electeur[] listeElecteur = new Electeur[0];
		String adresse = f.getAbsolutePath();
		String name = f.getName().replace(".stn", "");
		while (Sc.hasNextLine()) {
			String str = Sc.next();
			if (str.compareTo("Name") == 0) {
				Sc.nextLine();
				name = Sc.nextLine();
			}

			if (str.compareTo("Number of voters") == 0) {
				Sc.nextLine();
				nbVotants = Integer.parseInt(Sc.nextLine());
				listeElecteur = new Electeur[nbVotants];
			}
			if (str.compareTo("Number of candidates") == 0) {
				Sc.nextLine();
				nbCandidats = Integer.parseInt(Sc.nextLine());
				listeCandidats = new Candidat[nbCandidats];
			}
			if (str.compareTo("Candidates' names") == 0) {
				Sc.nextLine();
				Sc.useDelimiter(";");
				nomsCandidats = new String[nbCandidats];
				for (int i = 0; i < nbCandidats; i++) {
					String nom = Sc.next();
					nomsCandidats[i] = nom;
					listeCandidats[i] = new Candidat(nom);
				}
				Sc.useDelimiter("#");
				Sc.nextLine();
			}
			if (str.compareTo("RANKINGS") == 0) {
				Sc.nextLine();
				Sc.useDelimiter(";");
				for (int i = 0; i < nbVotants; i++) {
					Candidat[] choix = new Candidat[nbCandidats];
					for (int j = 0; j < nbCandidats; j++) {
						int k = Integer.parseInt(Sc.next());
						choix[j] = listeCandidats[k - 1];
					}
					listeElecteur[i] = new Electeur(choix);
					Sc.nextLine();
				}
			}
		}
		Contrainte c = new Contrainte(nbVotants, nbCandidats);
		Situation s = new Situation(listeCandidats, listeElecteur, c, name);
		s.setAdresse(adresse);
		return s;
	}

	private File ecrire_resultat(Situation s) {
		String str = findName(s.getName(),"Results",".rslt");
		try {
			FileWriter fw = new FileWriter("Resultats/"+str);
			fw.write("##RESULTS ON##" + "\n" + s.getName() + "\n\n" + "CARACTERISTICS" + "\n\n"
					+ "##Number of candidates##" + "\n" + s.getListeDeCandidats().length + "\n\n"
					+ "##Number of voters##" + "\n" + s.getCorpsElectoral().length + "\n\n"
					+ "##Situation file##" + "\n" + s.getAdresse() + "\n\n" + "##Satisfaction function##"
					+ "\n" + fonctionSatisfaction.toString() + "\n\n" + "##Scrutins##" + "\n");
			@SuppressWarnings("deprecation")
			Object[] scrutins = scrutinsSelect.getSelectedValues();
			Election[] elections = new Election[scrutins.length];
			int n = scrutins.length;
			for (int i = 0; i < n; i++) {
				elections[i] = new Election(s, (Scrutin) scrutins[i]);
				fw.write(scrutins[i].toString() + ";" + fonctionSatisfaction.calculBonheur(elections[i]) + "\n");
			}
			fw.write("\n##Results##\n");
			for (int i = 0; i < n; i++) {
				Scrutin scrutin = (Scrutin) scrutins[i];
				fw.write("**" + scrutin.toString() + "**\n");
				Election e = new Election(s, scrutin);
				for (int j = 0; j < s.getListeDeCandidats().length; j++) {
					fw.write(e.getResultat().imprimeResultat(j) + "\n");
				}
			}
			fw.write("##End results##");
			fw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return new File(str);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == lancer) {
			if (scrutinsSelect.isSelectionEmpty()) {
				JOptionPane.showMessageDialog(this.getParent(), "Please choose at least one scrutin", "Error message",
						JOptionPane.ERROR_MESSAGE);
			} else {
				List<File> listeResultats = new ArrayList<File>();
				for (File f : situations) {
					try {
						listeResultats.add(ecrire_resultat(lire_simulation(f)));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		
		if (e.getSource() == genererFichier) {
			GenerateurSituation gen = new GenerateurSituation();
			gen.setVisible(true);
			situations = gen.getFiles();
			texteArea.setText("");
			for (File f : situations) {
				if (f.getName().endsWith(".stn")) {
					texteArea.append(f.getName() + "\n");
				}
			}
		}
		
		if (e.getSource() == choixFichier) {
			JFileChooser fc1 = new JFileChooser();
			fc1.setMultiSelectionEnabled(true);
			fc1.setCurrentDirectory(new File("File"));
			int returnVal1 = fc1.showOpenDialog(this);
			if (returnVal1 == JFileChooser.APPROVE_OPTION) {
				situations = new ArrayList<File>();
				texteArea.setText("");
				List<File> fichiersSelectionnes = Arrays.asList(fc1.getSelectedFiles());
				for (File f : fichiersSelectionnes) {
					if (f.getName().endsWith(".stn")) {
						situations.add(f);
						texteArea.append(f.getName() + "\n");
					}
				}
			}
			}

			if (e.getSource() == fonctionSelect) {
				fonctionSatisfaction = (FonctionSatisfaction) fonctionSelect.getSelectedItem();
			}
		}
	
	private String findName(String name, String path, String ext) {
		String finalName = name+ext;
		File f = new File(path + "/" + finalName);
		int i = 0;
		if (f.exists()) {
			while (f.exists()) {
				i++;
				finalName = name + " (" + i + ")" + ext;
				f = new File(path + "/" + finalName);
			}
		}
		return finalName;
	}

	// MAIN
	public static void main(String[] args) throws FileNotFoundException {
		Menu appli = new Menu();
		appli.setSize(new Dimension(900, 600));
		appli.setLocation(100, 100);
		appli.setTitle("Menu");
		appli.setVisible(true);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

	}
}
