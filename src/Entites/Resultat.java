package Entites;

import java.util.List;

public abstract class Resultat {		//Il s'agit là encore d'une classe abstraite car les résultats dépendent évidemment du mode de scrutin.
		//On ne précisera que par la suite ce qu'est listeDeResultats et comment fonctionne getGagnant.
    //ATTRIBUT(S)
    private Candidat[] listeDeCandidats;

    private List<?> listeDeResultats;
    
    //CONSTRUCTEUR(S)
    
    
    //SETTER(S) AND GETTER(S)
	public List<?> getListeDeResultats() {
		return listeDeResultats;
	}
	
	public Candidat[] getListeDeCandidats() {
		return this.listeDeCandidats;
	}
    
	protected void setListeDeResultats(List<?> listeDeResultats) {
		this.listeDeResultats = listeDeResultats;
	}
	
	protected void setListeDeCandidats(Candidat[] listeDeCandidats2) {
		this.listeDeCandidats = listeDeCandidats2;
	}
	
	public abstract Candidat getGagnant();
	
	public abstract String imprimeResultat(int Candidati);
    
    //METHODE(S)
	
    
    //MAIN


}
