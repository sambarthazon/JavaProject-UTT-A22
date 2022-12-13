package javaproject.utt.a22;

/**
 * Classe Aleatoire implementant Strategie pour combattre offensivement ou defensivement.
 */
public class Aleatoire implements Strategie{

    /**
     * Attribut indiquant le nom de la strategie.
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
     * @param pionActeur Pion allant effectuer sa stratégie.
     */
    @Override
    public void combattre(Pion pionActeur){
        //Choix aléatoire entre attaquant ou défenseur
        if(Math.random() < 0.5){ //Attaquant
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
     * Redefinition de la methode toString.
     * @return this.nom Nom de la stratégie.
     */
    @Override
    public String toString(){
        return this.nom;
    }
}
