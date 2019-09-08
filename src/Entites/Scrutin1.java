package Entites;

import java.util.ArrayList;
import java.util.List;

public class Scrutin1 extends Scrutin {
    		//C'est la première application du Scrutin, le Scrutin a 1 tour. Cette classe est très utilisée même dans les autres types de Scrutins car elle organise les candidats selon le nombre de voix.
	//ATTRIBUT(S)
	
	
	//CONSTRUCTEUR(S)
	public Scrutin1() {
		this.setNom("Scrutin majoritaire à 1 tour");
	}
	
	
	//SETTER(S) AND GETTER(S)
	
	
	//METHODE(S)
	public Resultat mettreEnOeuvre(Situation situation) {
		Electeur[] corpsElectoral = situation.getCorpsElectoral();
		Candidat[] listeDeCandidats = situation.getListeDeCandidats();
		List<Integer> listeDeResultatsNonNormalises = new ArrayList<>();
		
		for (int i=0; i<listeDeCandidats.length; i++) {
			listeDeResultatsNonNormalises.add(0);
		}
		
		for (int i=0; i<corpsElectoral.length; i++) {
			Candidat candidatVoulu = null;			
			int indiceCandidat = listeDeResultatsNonNormalises.size();
			int j=0;
			while (indiceCandidat >= listeDeResultatsNonNormalises.size()) {
				candidatVoulu = corpsElectoral[i].getListeDeCandidats()[j];
				indiceCandidat = getIndexOfCandidat(listeDeCandidats, candidatVoulu);
				j+=1;
			}
			
			int nombreVotesActuels = listeDeResultatsNonNormalises.get(indiceCandidat);
			listeDeResultatsNonNormalises.set(indiceCandidat, nombreVotesActuels + 1);
			
		}
		
		return new ResultatScrutin1(listeDeCandidats, listeDeResultatsNonNormalises);
	}
	
	private int getIndexOfCandidat(Candidat[] listeDeCandidats, Candidat candidat) {
		for (int i=0; i<listeDeCandidats.length; i++) {
			if (listeDeCandidats[i] == candidat) {
				return i;
			}
		}
		
		return listeDeCandidats.length+1;
	}
	
	
	//MAIN
}
