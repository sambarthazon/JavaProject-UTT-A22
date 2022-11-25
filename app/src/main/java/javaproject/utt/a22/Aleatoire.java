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
    public static void main(String[] args) {
        
    }

    /**
     * Implementation de la methode combattre.
     */
    @Override
    public int combattre(Pion pionActeur, Pion pionCible){
        final int max = 100;
        final int min = 0;
        int heal = 0;

        int x = 0;
        x = (int) Math.floor(Math.random()*(max-min+1)+min);
        Boolean reussi = x >= 0 && x <= 20 + 6 * pionActeur.dexterite ? true : false;

        if(reussi){
            double y = 0;
            y = Math.floor(Math.random()*(0.6-0+1)+0);
            heal = (int) y * (10 + pionCible.constitution);
        }

        return heal;
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
