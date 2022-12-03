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
    public void combattre(Pion pionActeur, Pion pionCible){
        //
        final int max = 100;
        final int min = 0;

        //
        final int degatReference = 10;

        //
        int damage = 0;
        double varMin = 0;
        double varMax = 0;

        int x = 0;
        x = (int) Math.floor(Math.random()*(max-min+1)+min);
        Boolean reussi = x >= 0 && x <= 40 + 3 * pionActeur.dexterite ? true : false;

        if(reussi){
            double y = 0;
            y = Math.floor(Math.random()*(1-0+1)+0);

            varMin = min(pionActeur.getForce(), pionCible.getResistance());
            varMax = max(varMin);

            double coefDegat = varMax/100;
            damage = (int) (y * (1 + coefDegat) * degatReference);
            
            pionCible.setECTS(-damage);
        }
    }


    /**
     * Methode pour calculer une partie des coefficients de degat.
     * @param forceActeur
     * @param resistanceCible
     * @return
     */
    public double min(int forceActeur, int resistanceCible){
        final int valueToCompare = 100;
        int var = 0;

        var = 10 * forceActeur - 5 * resistanceCible;
        if(var < valueToCompare){
            return var;
        } else{
            return valueToCompare;
        }
    }


    /**
     * Methode pour calculer l'autre partie des coefficients de degat.
     * @param varMin
     * @return
     */
    public double max(double varMin){
        final int valueToCompare = 0;

        if(varMin > valueToCompare){
            return varMin;
        } else{
            return valueToCompare;
        }
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
