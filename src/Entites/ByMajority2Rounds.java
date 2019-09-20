package Entites;

import java.util.ArrayList;
import java.util.List;

public class ByMajority2Rounds extends Scrutin {
    		//On voit dans la fonction mettreEnOeuvre que l'on a besoin de mettreEnOeuvre un Scrutin1 pour simuler 1 tour.
	//ATTRIBUT(S)
	
	
	//CONSTRUCTEUR(S)
	public ByMajority2Rounds() {
		this.setNom("Election by elimination with 2 rounds");
	}
	
	
	//SETTER(S) AND GETTER(S)
	
	
	//METHODE(S)
	public Resultat mettreEnOeuvre(Situation situation) {
		Electeur[] corpsElectoral = situation.getCorpsElectoral();
		Candidat[] listeDeCandidats = situation.getListeDeCandidats();
		List<List<Integer>> listeDeResultatsNonNormalises = new ArrayList<>();
		
		if (listeDeCandidats.length <= 2) {
			Scrutin scrutinEquivalent = new ByMajority1Round();
			Resultat resultatEquivalent = scrutinEquivalent.mettreEnOeuvre(situation);
			
			for (int i=0; i<listeDeCandidats.length; i++) {
				List<Integer> listeDeResultatsCandidat = new ArrayList<>();
				listeDeResultatsCandidat.add((Integer) resultatEquivalent.getListeDeResultats().get(i));
				if (resultatEquivalent.getGagnant() == listeDeCandidats[i]) {
					listeDeResultatsCandidat.add(corpsElectoral.length);
				} else {
					listeDeResultatsCandidat.add(0);
				}
				
				listeDeResultatsNonNormalises.add(listeDeResultatsCandidat);
			}
		} else {
			Scrutin scrutinEquivalentPremierTour = new ByMajority1Round();
			ResultatByMajority1Round resultatEquivalentPremierTour = (ResultatByMajority1Round) scrutinEquivalentPremierTour.mettreEnOeuvre(situation);
			
			Candidat[] listeDeCandidatsToujoursEnLice = new Candidat[listeDeCandidats.length];
			Candidat premier = resultatEquivalentPremierTour.getGagnant();
			Candidat second = resultatEquivalentPremierTour.getSecond();
			for (int i=0; i<listeDeCandidats.length; i++) {
				if (listeDeCandidats[i].equals(premier) || listeDeCandidats[i].equals(second)) {
					listeDeCandidatsToujoursEnLice[i] = listeDeCandidats[i];
				} else {
					listeDeCandidatsToujoursEnLice[i] = null;
				}
			}
			
			Scrutin scrutinEquivalentSecondTour = new ByMajority1Round();
			ResultatByMajority1Round resultatEquivalentSecondTour = (ResultatByMajority1Round) scrutinEquivalentSecondTour.mettreEnOeuvre(new Situation(listeDeCandidatsToujoursEnLice, corpsElectoral));
			
			
			for (int i=0; i<listeDeCandidats.length; i++) {
				List<Integer> listeDeResultatsCandidat = new ArrayList<>();
				listeDeResultatsCandidat.add((Integer) resultatEquivalentPremierTour.getListeDeResultats().get(i));
				listeDeResultatsCandidat.add((Integer) resultatEquivalentSecondTour.getListeDeResultats().get(i));
				listeDeResultatsNonNormalises.add(listeDeResultatsCandidat);
			}
		}
		
		return new ResultatByMajority2Rounds(listeDeCandidats, listeDeResultatsNonNormalises);
	}
	
	
	//MAIN
}
