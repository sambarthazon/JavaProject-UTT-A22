package javaproject.utt.a22;

/**
 * Classe Offensif implementant Strategie pour combattre offensivement.
 */
public class Offensif implements Strategie{

    /**
     * Nom de la strategie.
     */
    private String nom = "Offensif";

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
        System.out.println("Attaque");
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
