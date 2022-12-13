package javaproject.utt.a22;

/**
 * Interface stratégie permettant au pion de combattre.
 */
public interface Strategie{

    /**
     * Méthode permettant au pion de combattre.
     */
    public void combattre(Pion pionActeur);


    /**
     * Redéfinition de la methode toString.
     * @return this.nom Nom de la stratégie.
     */
    @Override
    public String toString();
}
