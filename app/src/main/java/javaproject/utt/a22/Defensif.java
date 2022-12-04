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
     * Implémentation de la méthode combattre.
     */
    @Override
    public void combattre(Pion pionActeur){
        //
        int x = 0;
        final int x_Min = 0;
        final int x_Max = 100;

        //
        int heal = 0;


        x = (int) Math.floor(Math.random()*(x_Max-x_Min+1)+x_Min);
        Boolean reussi = x >= 0 && x <= 20 + 6 * pionActeur.dexterite ? true : false;

        if(reussi){
            //Recuperation du joueur qui a le moins de point de vie de son equipe.
            Pion pionCible = getPionCible(pionActeur);

            double y = 0;
            y = Math.floor(Math.random()*(0.6-0+1)+0);

            heal = (int) y * (10 + pionCible.constitution);

            //Application du soin sur le pion cible.
            pionCible.setECTS(heal);
        }
    }


    /**
     * Methode pour savoir quel pion sera effecte par la strategie.
     * @param pionActeur
     * @return pionCible
     */
    public Pion getPionCible(Pion pionActeur){
        Pion pionCible = null;

        if(pionActeur.joueur.getNom().equals("Joueur 1")){
            pionCible = pionActeur.getZone().getLinkedListTeam1().getFirst();
        } else{
            pionCible = pionActeur.getZone().getLinkedListTeam2().getFirst();
        }

        return pionCible;
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
