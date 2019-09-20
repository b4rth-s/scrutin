package Entites;

import java.util.List;


public class Election {
    
    //ATTRIBUT(S)
    private Situation situation;

    private Resultat resultat;

    private Scrutin scrutin;
    
    
    //CONSTRUCTEUR(S)
    public Election(Situation situation, Scrutin scrutin) {
    	this.situation = situation;
    	this.scrutin = scrutin;
    	this.resultat = this.scrutin.mettreEnOeuvre(this.situation);	//On applique directement dans le constructeur la méthode mettre en œuvre de scrutin. 
    																	//Cela signifie que dès la création d'une élection, celle-ci possède un résultat.
    }
    
    
    //SETTER(S) AND GETTER(S)
    public Situation getSituation() {
    	return this.situation;
    }
    
    public Scrutin getScrutin() {
    	return this.scrutin;
    }
    
    public Resultat getResultat() {
    	return this.resultat;
    }
    
    
    //METHODE(S)
    
    
    //MAIN
    public static void main(String[] args) {		//Dans cette fonction main, nous faisons un test de tout le package Entites. 
 /*   	List<Candidat> listeDeCandidats = new ArrayList<>();
    	listeDeCandidats.add(new Candidat("Louis"));
    	listeDeCandidats.add(new Candidat("Nicolas"));
    	
    	List<Candidat> listeInverse = new ArrayList<>();
    	listeInverse.add(listeDeCandidats.get(1));
    	listeInverse.add(listeDeCandidats.get(0));
    	
    	Electeur electeur1 = new Electeur(listeDeCandidats);
    	Electeur electeur2 = new Electeur(listeDeCandidats);
    	Electeur electeur3 = new Electeur(listeInverse);
    	Electeur electeur4 = new Electeur(listeInverse);
    	Electeur electeur5 = new Electeur(listeInverse);
    	
    	List<Electeur> corpsElectoral = new ArrayList<>();
    	corpsElectoral.add(electeur1);
    	corpsElectoral.add(electeur2);
    	corpsElectoral.add(electeur3);
    	corpsElectoral.add(electeur4);
    	corpsElectoral.add(electeur5);*/
    	
    	

    	Situation situation1 = new Situation(new Contrainte(10000, 6));
    	
    	Election election1 = new Election(situation1, new ByMajority1Round());		//Le premier test consiste à regarder les résultats d'élections ayant la même situation mais des scrutins différents.
    					//On affiche les résultats de tous les tours pour vérifier que le logiciel fonctionne.
    	System.out.println("The winner of this " + election1.getScrutin() + " is: " + election1.getResultat().getGagnant());
    	System.out.println("\nCandidate 1 gets " + election1.getResultat().getListeDeResultats().get(0) + " voices.");
    	System.out.println("Candidate 2 gets " + election1.getResultat().getListeDeResultats().get(1) + " voices.");
    	System.out.println("Candidate 3 gets " + election1.getResultat().getListeDeResultats().get(2) + " voices.");
    	System.out.println("Candidate 4 gets " + election1.getResultat().getListeDeResultats().get(3) + " voices.");
    	System.out.println("Candidate 5 gets " + election1.getResultat().getListeDeResultats().get(4) + " voices.");
    	System.out.println("Candidate 6 gets " + election1.getResultat().getListeDeResultats().get(5) + " voices.\n");
    	
    	System.out.println("Testing imprimeResultat");
    	for (int i=0; i<5; i++) {
    		System.out.println("Function imprimeResutat("+i+") returns : " + election1.getResultat().imprimeResultat(i));
    	}
    	
    	System.out.println("\nThe hapiness of the voters in this election is : " + new AverageGap().calculBonheur(election1));
    	
    	    	
    	Election election2 = new Election(situation1, new ByMajority2Rounds());
    	
    	System.out.println("\n\n\nLe gagnant de ce " + election2.getScrutin() + " est : " + election2.getResultat().getGagnant());
    	System.out.println("\nAu premier tour, le candidat1 obtient " + ((List<?>) election2.getResultat().getListeDeResultats().get(0)).get(0) + " voix.");
    	System.out.println("Au premier tour, le candidat2 obtient " + ((List<?>) election2.getResultat().getListeDeResultats().get(1)).get(0) + " voix.");
    	System.out.println("Au premier tour, le candidat3 obtient " + ((List<?>) election2.getResultat().getListeDeResultats().get(2)).get(0) + " voix.");
    	System.out.println("Au premier tour, le candidat4 obtient " + ((List<?>) election2.getResultat().getListeDeResultats().get(3)).get(0) + " voix.");
    	System.out.println("Au premier tour, le candidat5 obtient " + ((List<?>) election2.getResultat().getListeDeResultats().get(4)).get(0) + " voix.");
    	System.out.println("Au premier tour, le candidat6 obtient " + ((List<?>) election2.getResultat().getListeDeResultats().get(5)).get(0) + " voix.");
    	System.out.println("\nLes candidats sélectionnés pour le second tour sont " + election1.getResultat().getGagnant() + " et " + ((ResultatByMajority1Round) election1.getResultat()).getSecond());
    	System.out.println("\nAu second tour, le candidat1 obtient " + ((List<?>) election2.getResultat().getListeDeResultats().get(0)).get(1) + " voix.");
    	System.out.println("Au second tour, le candidat2 obtient " + ((List<?>) election2.getResultat().getListeDeResultats().get(1)).get(1) + " voix.");
    	System.out.println("Au second tour, le candidat3 obtient " + ((List<?>) election2.getResultat().getListeDeResultats().get(2)).get(1) + " voix.");
    	System.out.println("Au second tour, le candidat4 obtient " + ((List<?>) election2.getResultat().getListeDeResultats().get(3)).get(1) + " voix.");
    	System.out.println("Au second tour, le candidat5 obtient " + ((List<?>) election2.getResultat().getListeDeResultats().get(4)).get(1) + " voix.");
    	System.out.println("Au second tour, le candidat6 obtient " + ((List<?>) election2.getResultat().getListeDeResultats().get(5)).get(1) + " voix.\n");
    	
    	System.out.println("Test de imprimeResultat");
    	for (int i=0; i<5; i++) {
    		System.out.println("La fonction imprimeResutat("+i+") donne : " + election2.getResultat().imprimeResultat(i));
    	}
    	
    	System.out.println("\nLe bonheur du corps électoral sur cette élection est : " + new AverageGap().calculBonheur(election2));
    	
    	
    	Election election3 = new Election(situation1, new ByElimination());
    	System.out.println("\n\n\nLe gagnant de ce " + election3.getScrutin() + " est : " + election3.getResultat().getGagnant());
    	System.out.println("\nAu premier tour, le candidat1 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(0)).get(0) + " voix.");
    	System.out.println("Au premier tour, le candidat2 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(1)).get(0) + " voix.");
    	System.out.println("Au premier tour, le candidat3 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(2)).get(0) + " voix.");
    	System.out.println("Au premier tour, le candidat4 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(3)).get(0) + " voix.");
    	System.out.println("Au premier tour, le candidat5 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(4)).get(0) + " voix.");
    	System.out.println("Au premier tour, le candidat6 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(5)).get(0) + " voix.");
    	System.out.println("\nAu second tour, le candidat1 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(0)).get(1) + " voix.");
    	System.out.println("Au second tour, le candidat2 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(1)).get(1) + " voix.");
    	System.out.println("Au second tour, le candidat3 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(2)).get(1) + " voix.");
    	System.out.println("Au second tour, le candidat4 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(3)).get(1) + " voix.");
    	System.out.println("Au second tour, le candidat5 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(4)).get(1) + " voix.");
    	System.out.println("Au second tour, le candidat6 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(5)).get(1) + " voix.");
    	System.out.println("\nAu troisième tour, le candidat1 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(0)).get(2) + " voix.");
    	System.out.println("Au troisième tour, le candidat2 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(1)).get(2) + " voix.");
    	System.out.println("Au troisième tour, le candidat3 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(2)).get(2) + " voix.");
    	System.out.println("Au troisième tour, le candidat4 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(3)).get(2) + " voix.");
    	System.out.println("Au troisième tour, le candidat5 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(4)).get(2) + " voix.");
    	System.out.println("Au troisième tour, le candidat6 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(5)).get(2) + " voix.");
    	System.out.println("\nAu quatrième tour, le candidat1 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(0)).get(3) + " voix.");
    	System.out.println("Au quatrième tour, le candidat2 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(1)).get(3) + " voix.");
    	System.out.println("Au quatrième tour, le candidat3 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(2)).get(3) + " voix.");
    	System.out.println("Au quatrième tour, le candidat4 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(3)).get(3) + " voix.");
    	System.out.println("Au quatrième tour, le candidat5 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(4)).get(3) + " voix.");
    	System.out.println("Au quatrième tour, le candidat6 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(5)).get(3) + " voix.");
    	System.out.println("\nAu cinquième tour, le candidat1 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(0)).get(4) + " voix.");
    	System.out.println("Au cinquième tour, le candidat2 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(1)).get(4) + " voix.");
    	System.out.println("Au cinquième tour, le candidat3 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(2)).get(4) + " voix.");
    	System.out.println("Au cinquième tour, le candidat4 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(3)).get(4) + " voix.");
    	System.out.println("Au cinquième tour, le candidat5 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(4)).get(4) + " voix.");
    	System.out.println("Au cinquième tour, le candidat6 obtient " + ((List<?>) election3.getResultat().getListeDeResultats().get(5)).get(4) + " voix.\n");    	

    	System.out.println("Test de imprimeResultat");
    	for (int i=0; i<5; i++) {
    		System.out.println("La fonction imprimeResutat("+i+") donne : " + election3.getResultat().imprimeResultat(i));
    	}

    	System.out.println("\nLe bonheur du corps électoral sur cette élection est : " + new AverageGap().calculBonheur(election3));
    	
		float bonheurScrutin1 = 0, bonheurScrutin2 = 0, bonheurScrutin3 = 0;
				//Ce test correspond plus à l'utilité du logiciel : on calcule le bonheur moyen généré par chaque scrutin sur 100 élections.
    	for (int i=0; i<100; i++) {
    		Situation situation = new Situation(new Contrainte(100000, 10));
    		bonheurScrutin1 += new AverageGap().calculBonheur(new Election(situation, new ByMajority1Round()));
    		bonheurScrutin2 += new AverageGap().calculBonheur(new Election(situation, new ByMajority2Rounds()));
    		bonheurScrutin3 += new AverageGap().calculBonheur(new Election(situation, new ByElimination()));
    	}
    	System.out.println("\n\nLe bonheur moyen pour 100 situations de 100000 électeurs du scrutin 1 est : " + (bonheurScrutin1 / 100));
    	System.out.println("Le bonheur moyen pour 100 situations de 100000 électeurs du scrutin 2 est : " + (bonheurScrutin2 / 100));
    	System.out.println("Le bonheur moyen pour 100 situations de 100000 électeurs du scrutin 3 est : " + (bonheurScrutin3 / 100));
    }


}
