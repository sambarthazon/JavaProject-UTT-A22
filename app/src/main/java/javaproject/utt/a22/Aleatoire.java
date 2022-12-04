package javaproject.utt.a22;

/**
 * Classe Aleatoire implementant Strategie pour combattre offensivement ou defensivement.
 */
public class Aleatoire implements Strategie{

    /**
     * Nom de la strategie.
     */
    private final String nom = "Aléatoire";
    
    /**
     * Main
     * @param args
     */
    public static void main(String[] args){
        
    }

    /**
     * Implementation de la methode combattre.
     */
    @Override
    public void combattre(Pion pionActeur){
        //Une chance sur deux pour etre attaquant ou defenseur.
        double trueFalse = Math.round(Math.random());

        if(trueFalse == 1){ //Attaquant
            pionActeur.setStrategie(new Offensif());
            pionActeur.executerStrategie();
        } else{ //Defenseur
            pionActeur.setStrategie(new Defensif());
            pionActeur.executerStrategie();
        }

        //Remise de la strategie de base du pion
        pionActeur.setStrategie(new Aleatoire());
    }


    /**
     * Methode pour recuperer le nom de la strategie.
     * @return this.nom
     */
    public String getNom(){
        return this.nom;
    }


    /**
     * Redefinition de la methode toString
     * @return this.nom
     */
    @Override
    public String toString(){
        return this.nom;
    }
}
