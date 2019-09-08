package Entites;

public class PremiereFonctionDeSatisfaction extends FonctionSatisfaction {
		//Cette première fonction de satisfaction est basique : elle regarde le malheur d'un électeur en mesurant l'écart entre le candidat élu et le candidat préféré. Elle fait ensuite la moyenne de cet écart et renvoie 1-moyenne.
		//Cela permet donc d'avoir des valeurs entre 0 et 1. 1 si tous les électeurs sont satisfaits, 0 si tout le monde l'avait en dernier voeu.
	
    //ATTRIBUT(S)

    
    //CONSTRUCTEUR(S)
	public PremiereFonctionDeSatisfaction() {
		this.setNom("Moyenne de l'écart entre le vœu et le candidat élu");
	}
    
    
    //SETTER(S) AND GETTER(S)
    
    
    //METHODE(S)
	public float calculBonheur(Election election) {
		Situation situation = election.getSituation();
		
		Electeur[] corpsElectoral = situation.getCorpsElectoral();
		
		Candidat candidatElu = election.getResultat().getGagnant();
		
		int nombreElecteurs = corpsElectoral.length;
		int nombreCandidatsMoinsUn = situation.getListeDeCandidats().length;
		int somme = 0;
		
		for (int i=0; i<nombreElecteurs; i++) {
			int ecart = corpsElectoral[i].ecart(candidatElu) - 1;
			somme += ecart;
		}
		
		float moyenneEcart = (float) somme / nombreElecteurs / nombreCandidatsMoinsUn;
		
		return 1 - moyenneEcart;
	}
    
    
    //MAIN


}
