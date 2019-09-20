package Entites;

import java.util.List;

public class ResultatByMajority1Round extends Resultat {
			//Scrutin1 et ResultatScrutin1 sont des classes très importantes qui implémentent de nombreuses fonctions qui seront par la suite utilisées dans les autres Scrutins et ResultatsScrutins.
    //ATTRIBUT(S)

	
    //CONSTRUCTEUR(S)
    public ResultatByMajority1Round(Candidat[] listeDeCandidats, List<Integer> listeDeResultats) {	//Ainsi, on choisi ici de renvoyer les résultats avec une liste ordonnée de voix.
    	this.setListeDeCandidats(listeDeCandidats);
    	this.setListeDeResultats(listeDeResultats);
    }
    
    
    //SETTER(S) AND GETTER(S)
    
    
    //METHODE(S)
    public Candidat getGagnant() {		//La fonction getGagnant() analyse les listeDeResultats pour donner le gagnant.
		int max = (int) this.getListeDeResultats().get(0);
		int indiceMax = 0;
		int nombreDeCandidats = this.getListeDeCandidats().length;
		
		for (int i=1; i<nombreDeCandidats; i++) {
			if (max < (int) this.getListeDeResultats().get(i)) {
				max = (int) this.getListeDeResultats().get(i);
				indiceMax = i;
			}
		}
		
		return this.getListeDeCandidats()[indiceMax];
	}
    
    public Candidat getSecond() {		//Cette fonction donne le deuxième. C'est notamment utile dans le Scrutin2 (majoritaire à 2 tours) pour choisir ceux qui seront au deuxième tour.
    	if (this.getListeDeResultats().size() < 2) {
    		return null;
    	} else {
    		int max = Math.max((int) this.getListeDeResultats().get(0), (int) this.getListeDeResultats().get(1));
    		int avantMax = Math.min((int) this.getListeDeResultats().get(0), (int) this.getListeDeResultats().get(1));
    		
    		int indiceMax, indiceAvantMax;
    		if (max == (int) this.getListeDeResultats().get(0)) {
    			indiceMax = 0;
    			indiceAvantMax = 1;
    		} else {
    			indiceMax = 1;
    			indiceAvantMax = 0;
    		}
    		int nombreDeCandidats = this.getListeDeCandidats().length;
    		
    		for (int i=2; i<nombreDeCandidats; i++) {
    			if (max < (int) this.getListeDeResultats().get(i)) {
    				avantMax = max;
    				indiceAvantMax = indiceMax;
    				max = (int) this.getListeDeResultats().get(i);
    				indiceMax = i;
    			} else if (avantMax < (int) this.getListeDeResultats().get(i)) {
    				avantMax = (int) this.getListeDeResultats().get(i);
    				indiceAvantMax = i;
    			}
    		}
    		
    		return this.getListeDeCandidats()[indiceAvantMax];
    	}

	}
    
    public Candidat getDernier() {		//Cette fonction donne le dernier. C'est notamment utile dans le Scrutin3 (par élimination), pour savoir qui éliminer.
    	int i=0;
    	while (this.getListeDeCandidats()[i] == null) {
    		i+=1;
    	}
    	int min = (int) this.getListeDeResultats().get(i);
		int indiceMin = i;
		int nombreDeCandidats = this.getListeDeCandidats().length;
		
		for (int j=i; j<nombreDeCandidats; j++) {
			if ( ( min > (int) this.getListeDeResultats().get(j) ) && ( this.getListeDeCandidats()[j] != null )) {
				min = (int) this.getListeDeResultats().get(j);
				indiceMin = j;
			}
		}
		
		return this.getListeDeCandidats()[indiceMin];
    }


	public String imprimeResultat(int Candidati) {
		return this.getListeDeResultats().get(Candidati) + ";";
	}
    
    //MAIN


}
