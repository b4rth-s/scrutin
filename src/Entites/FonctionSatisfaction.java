package Entites;


public abstract class FonctionSatisfaction {
    
    //ATTRIBUT(S)
	private String nom;
    
    
    //CONSTRUCTEUR(S)
    
    
    //SETTER(S) AND GETTER(S)
	protected void setNom(String nom) {		//La classe abstraite FonctionSatisfaction n'a qu'un nom. On demandera uniquement de calculer le bonheur généré par une élection.
		this.nom = nom;
	}
    
    
    //METHODE(S)
    public abstract float calculBonheur(final Election election);
    
    public String toString() {
    	return this.nom;
    };
    
    
    //MAIN


}
