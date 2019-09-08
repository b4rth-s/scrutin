package Entites;

import java.util.ArrayList;
import java.util.List;

public class Scrutin3 extends Scrutin {
			//On voit dans la fonction mettreEnOeuvre que l'on a besoin de mettreEnOeuvre un Scrutin1 pour simuler 1 tour.
	//ATTRIBUT(S)
	
	
	//CONSTRUCTEUR(S)
	public Scrutin3() {
		this.setNom("Scrutin par élimination");
	}
	
	
	//SETTER(S) AND GETTER(S)
	
	
	//METHODE(S)
	public Resultat mettreEnOeuvre(Situation situation) {
		Candidat[] listeDeCandidats = situation.getListeDeCandidats();
		Candidat[] listeDeCandidatsClonee = Contrainte.clone(listeDeCandidats);
		List<List<Integer>> listeDeResultats = new ArrayList<>();
		for (int i=0; i<listeDeCandidats.length; i++) {
			listeDeResultats.add(new ArrayList<>());
		}
		
		for (int i=0; i<listeDeCandidats.length-1; i++) {
			ResultatScrutin1 resultatDuTour = (ResultatScrutin1) new Scrutin1().mettreEnOeuvre(new Situation(listeDeCandidatsClonee, situation.getCorpsElectoral()));
			
			for (int j=0; j<listeDeCandidats.length; j++) {
				listeDeResultats.get(j).add((Integer) resultatDuTour.getListeDeResultats().get(j));
			}
			
			Candidat perdant = resultatDuTour.getDernier();
			for (int j=0; j<listeDeCandidatsClonee.length; j++) {
				if (perdant.equals(listeDeCandidatsClonee[j])) {
					listeDeCandidatsClonee[j] = null;
					
				}
			}
		}
		
		return new ResultatScrutin3(listeDeCandidats, listeDeResultats);
		
	}
	
	
	//MAIN
}
