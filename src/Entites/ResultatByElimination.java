package Entites;

import java.util.List;

public class ResultatByElimination extends Resultat {
			//Cette classe s'appuie en partie sur ResultatsScrutin1 pour donner le vainqueur du Scrutin3.
    //ATTRIBUT(S)

	
    //CONSTRUCTEUR(S)
    public ResultatByElimination(Candidat[] listeDeCandidats, List<List<Integer>> listeDeResultats) {
    	this.setListeDeCandidats(listeDeCandidats);
    	this.setListeDeResultats(listeDeResultats);
    }
    
    
    //SETTER(S) AND GETTER(S)
    
    
    //METHODE(S)
    public Candidat getGagnant() {
		@SuppressWarnings("unchecked")
		List<Integer> resultatsATousLesToursPremier = (List<Integer>) this.getListeDeResultats().get(0);
		int max = resultatsATousLesToursPremier.get(this.getListeDeCandidats().length-2);
		int indiceMax = 0;
		int nombreDeCandidats = this.getListeDeCandidats().length;
		
		for (int i=1; i<nombreDeCandidats; i++) {
			@SuppressWarnings("unchecked")
			List<Integer> resultatsATousLesTours = (List<Integer>) this.getListeDeResultats().get(i);
			if (max < resultatsATousLesTours.get(this.getListeDeCandidats().length-2)) {
				max = resultatsATousLesTours.get(this.getListeDeCandidats().length-2);
				indiceMax = i;
			}
		}
		
		return this.getListeDeCandidats()[indiceMax];
	}

	@SuppressWarnings("unchecked")
	public String imprimeResultat(int Candidati) {
		String res = "";
		for (int i=0; i<this.getListeDeCandidats().length-1;i++) {
			res += ((List<List<Integer>>) this.getListeDeResultats().get(Candidati)).get(i) + ";";
		}
		return res;
	}
    
    //MAIN


}
