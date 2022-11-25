package javaproject.utt.a22;

/**
 * Interface strategie permettant au pion de combattre.
 */
public interface Strategie{

    /**
     * Methode permettant au pion de combattre.
     */
    public int combattre(Pion pionActeur, Pion pionCible);


    /**
     * Methode pour recuperer le nom de la strategie.
     * @return this.nom
     */
    public String getNom();


    /**
     * Redefinition de la methode toString
     * @return this.nom
     */
    @Override
    public String toString();
}
