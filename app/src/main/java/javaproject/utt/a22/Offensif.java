package javaproject.utt.a22;

/**
 * Classe Offensif implementant Strategie pour combattre offensivement.
 */
public class Offensif implements Strategie{

    /**
     * Implementation de la methode combattre.
     */
    @Override
    public void combattre(){
        System.out.println("Attaque");
    }
}
