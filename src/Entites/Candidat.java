package Entites;


public class Candidat {
    
    //ATTRIBUT(S)
    private String nom;		//Un candidat ne possède qu'un nom. On pourra imaginer dans les évolutions futures lui ajouter un bord politique, etc.

    
    //CONSTRUCTEUR(S)
    public Candidat(String nom) {
    	this.nom = nom;
    }
    
    //SETTER(S) AND GETTER(S)
    public String getNom() {
    	return this.nom;
    }
    
    public String toString() {	//Les fonctions donnent le même résultat, mais il faut imaginer que, dans les évolutions futures, il y aura d'autres paramètres, d'où la nécessité de distinguer le getter du toString.
    	return this.nom;
    }
        
    //METHODE(S)
    
    
    //MAIN

}
