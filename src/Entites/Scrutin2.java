package Entites;

import java.util.ArrayList;
import java.util.List;

public class Scrutin2 extends Scrutin {
    		//On voit dans la fonction mettreEnOeuvre que l'on a besoin de mettreEnOeuvre un Scrutin1 pour simuler 1 tour.
	//ATTRIBUT(S)
	
	
	//CONSTRUCTEUR(S)
	public Scrutin2() {
		this.setNom("Scrutin majoritaire à 2 tours");
	}
	
	
	//SETTER(S) AND GETTER(S)
	
	
	//METHODE(S)
	public Resultat mettreEnOeuvre(Situation situation) {
		Electeur[] corpsElectoral = situation.getCorpsElectoral();
		Candidat[] listeDeCandidats = situation.getListeDeCandidats();
		List<List<Integer>> listeDeResultatsNonNormalises = new ArrayList<>();
		
		if (listeDeCandidats.length <= 2) {
			Scrutin scrutinEquivalent = new Scrutin1();
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
			Scrutin scrutinEquivalentPremierTour = new Scrutin1();
			ResultatScrutin1 resultatEquivalentPremierTour = (ResultatScrutin1) scrutinEquivalentPremierTour.mettreEnOeuvre(situation);
			
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
			
			Scrutin scrutinEquivalentSecondTour = new Scrutin1();
			ResultatScrutin1 resultatEquivalentSecondTour = (ResultatScrutin1) scrutinEquivalentSecondTour.mettreEnOeuvre(new Situation(listeDeCandidatsToujoursEnLice, corpsElectoral));
			
			
			for (int i=0; i<listeDeCandidats.length; i++) {
				List<Integer> listeDeResultatsCandidat = new ArrayList<>();
				listeDeResultatsCandidat.add((Integer) resultatEquivalentPremierTour.getListeDeResultats().get(i));
				listeDeResultatsCandidat.add((Integer) resultatEquivalentSecondTour.getListeDeResultats().get(i));
				listeDeResultatsNonNormalises.add(listeDeResultatsCandidat);
			}
		}
		
		return new ResultatScrutin2(listeDeCandidats, listeDeResultatsNonNormalises);
	}
	
	
	//MAIN
}
