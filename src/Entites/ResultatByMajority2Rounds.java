package Entites;

import java.util.List;

public class ResultatByMajority2Rounds extends Resultat {
			//Cette classe s'appuie en partie sur ResultatsScrutin1 pour donner le vainqueur du Scrutin2.
    //ATTRIBUT(S)

	
    //CONSTRUCTEUR(S)
    public ResultatByMajority2Rounds(Candidat[] listeDeCandidats, List<List<Integer>> listeDeResultats) {
    	this.setListeDeCandidats(listeDeCandidats);
    	this.setListeDeResultats(listeDeResultats);
    }
    
    
    //SETTER(S) AND GETTER(S)
    
    
    //METHODE(S)
    public Candidat getGagnant() {
		@SuppressWarnings("unchecked")
		List<Integer> resultatsAuxDeuxToursPremier = (List<Integer>) this.getListeDeResultats().get(0);
		int max = resultatsAuxDeuxToursPremier.get(1);
		int indiceMax = 0;
		int nombreDeCandidats = this.getListeDeCandidats().length;
		
		for (int i=1; i<nombreDeCandidats; i++) {
			@SuppressWarnings("unchecked")
			List<Integer> resultatsAuxDeuxTours = (List<Integer>) this.getListeDeResultats().get(i);
			if (max < resultatsAuxDeuxTours.get(1)) {
				max = resultatsAuxDeuxTours.get(1);
				indiceMax = i;
			}
		}
		
		return this.getListeDeCandidats()[indiceMax];
	}

	@SuppressWarnings("unchecked")
	public String imprimeResultat(int Candidati) {
		return ((List<List<Integer>>) this.getListeDeResultats().get(Candidati)).get(0) + ";" + ((List<List<Integer>>) this.getListeDeResultats().get(Candidati)).get(1) + ";";
	}
    
    //MAIN


}
