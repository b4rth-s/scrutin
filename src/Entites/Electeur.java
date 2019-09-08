package Entites;


public class Electeur {
    
	//ATTRIBUT(S)
    private Candidat[] listeDeCandidats;		//Dans notre représentation, l'électeur n'a qu'une liste de candidats, ordonnée selon sa préférence.

    
    //CONSTRUCTEUR(S)
    public Electeur(Candidat[] listeOrdonnee) {
    	this.listeDeCandidats = listeOrdonnee;
    }
    
    
    //SETTER(S) AND GETTER(S)
    public Candidat[] getListeDeCandidats() {
    	return this.listeDeCandidats;
    }
    
    public String toString() {
    	return this.listeDeCandidats.toString();
    }
    
    
    //METHODE(S)
    public int ecart(Candidat candidat) {			//Cette fonction, utile par la suite, permet de donner le rang d'un candidat dans sa liste de voeux. S'il n'est pas présent, l'écart est nul.
    	int nombreCandidats = listeDeCandidats.length;
    	
    	for (int i=0; i<nombreCandidats; i++) {
    		if (listeDeCandidats[i] == candidat) {
    			return i + 1;
    		}
    	}
    	
    	return 0;			//Elle renvoie 0 si le candidat n'est pas dans la liste.
    }
    
    
    //MAIN

}
