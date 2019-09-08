package Entites;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Contrainte {
		//Cette classe spécifie des contraintes que l'utilisateur peut fixer à sa guise. Nous n'avons que le nombre de candidats et le nombre d'électeurs, mais il sera, par la suite, possible d'en ajouter d'autres. 
	
	//ATTRIBUT(S)
	private int nombreElecteurs;
	private int nombreCandidats;
	
	
	//CONSTRUCTEUR(S)
	public Contrainte(int nombreElecteurs, int nombreCandidats) {
		this.nombreCandidats = nombreCandidats;
		this.nombreElecteurs = nombreElecteurs;
	}
	
	//SETTER(S) AND GETTER(S)
	public int getNombreElecteurs() {
		return this.nombreElecteurs;
	}
	
	public int getNombreCandidats() {
		return this.nombreCandidats;
	}
	
	
	//METHODE(S)
	public Candidat[] creerListeDeCandidats() {
		Candidat[] listeDeCandidats = new Candidat[this.getNombreCandidats()];
		for (int i=0; i<this.getNombreCandidats(); i++) {
    		Candidat aAjouter = new Candidat("Candidat" + (i+1));
    		listeDeCandidats[i] = aAjouter;
    	}
		return listeDeCandidats;
	}
	
	public static Candidat[] clone(Candidat[] listeDeCandidatsClonee2) {
		Candidat[] listeDeCandidatsClonee = new Candidat[listeDeCandidatsClonee2.length];
		for (int i=0; i<listeDeCandidatsClonee2.length; i++) {
			listeDeCandidatsClonee[i] = listeDeCandidatsClonee2[i];
		}
		return listeDeCandidatsClonee;
	}
	
	public Electeur[] creerCorpsElectoral(Candidat[] listeDeCandidats) { 	//Lorsqu'une situation est générée depuis une contrainte, on veut que la répartition des votes soit aléatoire. On utilise donc la fonction shuffle sur les listes pour mélanger un clone de la liste de Candidats.
		Electeur[] corpsElectoral = new Electeur[this.getNombreElecteurs()];
		Candidat[] listeDeCandidatsClonee = clone(listeDeCandidats);
	
    	for (int i=0; i<this.getNombreElecteurs(); i++) {
    		List<Candidat> listeDeCandidatsAMelanger = Arrays.asList(listeDeCandidatsClonee);    		
    		Collections.shuffle(listeDeCandidatsAMelanger);
    		
    		listeDeCandidatsClonee = listeDeCandidatsAMelanger.toArray(listeDeCandidatsClonee);
    		corpsElectoral[i] = new Electeur(clone(listeDeCandidatsClonee));
    	}
    	return corpsElectoral;
	}
	
	
	//MAIN
}
