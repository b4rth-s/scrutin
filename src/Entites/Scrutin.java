package Entites;


public abstract class Scrutin {
    		//Cette classe abstraite spécifie des pré-requis pour les Scrutins que l'on voudrait encoder. L'essentiel est d'avoir un nom et de pouvoir être mis en place (i.e. renvoyer les résultats d'une situation).
	//ATTRIBUT(S)
	private String nom;
	
	//CONSTRUCTEUR(S)
	
	
	//SETTER(S) AND GETTER(S)
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	//METHODE(S)
	public abstract Resultat mettreEnOeuvre(Situation situation);
	
	public String toString() {
		return this.nom;
	};
	
	//MAIN

}
