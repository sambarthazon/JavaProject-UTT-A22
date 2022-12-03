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

        pionCible.setECTS(heal);

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
