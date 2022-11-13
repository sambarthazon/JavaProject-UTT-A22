package javaproject.utt.a22;

/**
 * Classe Defensif implementant Strategie pour combattre defensivement.
 */
public class Defensif implements Strategie{
    
    /**
     * Implementation de la methode combattre.
     */
    @Override
    public void combattre(){
        System.out.println("Defense");
    }
}
