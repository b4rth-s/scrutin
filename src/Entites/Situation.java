package Entites;


import java.util.Arrays;


public class Situation {
    	//La situation correspond au corps électoral et aux candidats. On ajoute aussi un attribut contrainte pour faciliter le travail du groupe sur l'IHM.
    //ATTRIBUT(S)
    private Candidat[] listeDeCandidats;

    private Electeur[] corpsElectoral;
    
    @SuppressWarnings("unused")
	private Contrainte contrainte; 
    
    private String adresse;
    private String name;
    
    
    //CONSTRUCTEUR(S)
    public Situation(Candidat[] listeDeCandidats, Electeur[] corpsElectoral, Contrainte contrainte, String name) {
    	this.listeDeCandidats = listeDeCandidats;
    	this.corpsElectoral = corpsElectoral;
    	this.contrainte = contrainte;
    	this.name = name;
    }
    
    public Situation(Candidat[] listeDeCandidats, Electeur[] corpsElectoral, Contrainte c) {
    	this.listeDeCandidats = listeDeCandidats;
    	this.corpsElectoral = corpsElectoral;
    	this.contrainte = c;
    	this.name = "Situation" + this.hashCode();
    }
    
    public Situation(Candidat[] listeDeCandidats, Electeur[] corpsElectoral) {
    	this.listeDeCandidats = listeDeCandidats;
    	this.corpsElectoral = corpsElectoral;
    	this.name = "Situation" + this.hashCode();
    	this.contrainte = new Contrainte(corpsElectoral.length, listeDeCandidats.length);
    }
    
    public Situation(Contrainte contrainte) {
    	this.contrainte = contrainte;
    	this.listeDeCandidats = contrainte.creerListeDeCandidats();
    	this.corpsElectoral = contrainte.creerCorpsElectoral(listeDeCandidats);
    	this.name = "Situation" + this.hashCode();
    }
    
    
    //SETTER(S) AND GETTER(S)
	public Electeur[] getCorpsElectoral() {
		return corpsElectoral;
	}
    
	public Candidat[] getListeDeCandidats() {
		return listeDeCandidats;
	}
    
	public String toString() {
		return this.name + "\nCandidats :\n" + Arrays.toString(this.getListeDeCandidats()) + "\n\nCorps électoral :\n" + Arrays.toString(this.getCorpsElectoral());
	}
	
	public void setAdresse(String a) {
		adresse = a;
	}
	
	public String getAdresse() {
		return adresse;
	}
	
	public String getName() {
		return name;
	}
	
    //METHODE(S)
    
    
    //MAIN
	public static void main(String[] args) {	//Ce test permet de tester ce que donne la création d'une situation à partir d'une contrainte.
		Situation situationTest = new Situation(new Contrainte(10, 2));
		System.out.println("Candidats :\n" + Arrays.toString(situationTest.getListeDeCandidats()));
		System.out.println("Corps électoral :\n" + Arrays.toString(situationTest.getCorpsElectoral()[0].getListeDeCandidats()));
		System.out.println(Arrays.toString(situationTest.getCorpsElectoral()[1].getListeDeCandidats()));
		System.out.println(Arrays.toString(situationTest.getCorpsElectoral()[2].getListeDeCandidats()));
		System.out.println(Arrays.toString(situationTest.getCorpsElectoral()[3].getListeDeCandidats()));
		System.out.println(Arrays.toString(situationTest.getCorpsElectoral()[4].getListeDeCandidats()));
		System.out.println(Arrays.toString(situationTest.getCorpsElectoral()[5].getListeDeCandidats()));
		System.out.println(Arrays.toString(situationTest.getCorpsElectoral()[6].getListeDeCandidats()));
		System.out.println(Arrays.toString(situationTest.getCorpsElectoral()[7].getListeDeCandidats()));
		System.out.println(Arrays.toString(situationTest.getCorpsElectoral()[8].getListeDeCandidats()));
		System.out.println(Arrays.toString(situationTest.getCorpsElectoral()[9].getListeDeCandidats()));
	}


}
