package javaproject.utt.a22;

/**
 * Classe Defensif implementant Strategie pour combattre defensivement.
 */
public class Defensif implements Strategie{
    
    /**
     * Nom de la strategie
     */
    private final String nom = "Défensif";

    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {
        
    }

    /**
     * Implementation de la methode combattre.
     */
    @Override
    public void combattre(){
        System.out.println("Defense");
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
