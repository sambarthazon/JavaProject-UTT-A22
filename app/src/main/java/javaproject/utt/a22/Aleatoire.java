package javaproject.utt.a22;

/**
 * Classe Aleatoire implementant Strategie pour combattre offensivement ou defensivement.
 */
public class Aleatoire implements Strategie{
    
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
        System.out.println("Aleatoire");
    }
}