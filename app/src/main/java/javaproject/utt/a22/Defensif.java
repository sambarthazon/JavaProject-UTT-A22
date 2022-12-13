package javaproject.utt.a22;

import java.util.Random;

/**
 * Classe Defensif implementant Strategie pour combattre defensivement.
 */
public class Defensif implements Strategie{
    
    /**
     * Attribut indiquant le nom de la stratégie.
     */
    private String nom = "Defensif";

    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {
        
    }



    /**
     * Implémentation de la méthode combattre.
     * @param pionActeur Pion allant effectuer sa stratégie.
     */
    @Override
    public void combattre(Pion pionActeur){
        //Variable x et ses bornes
        int x = 0;
        final int x_Min = 0;
        final int x_Max = 100;

        //Le soin qui va être appliqué
        double heal = 0;

        //Calcul si le soin est réussi ou non
        x = (int) Math.floor(Math.random()*(x_Max-x_Min+1)+x_Min);
        Boolean reussi = x >= 0 && x <= 20 + 6 * pionActeur.dexterite ? true : false;

        //Si le soin est réussi
        if(reussi){
            //Affichage de debug
            System.out.println("Soin de : " + pionActeur.getNom() + " du " + pionActeur.getJoueur().getNom() + " réussi.");
            //PreSet.tempo(1500);

            //Récupération du pion avec le moins de vie de son équipe
            Pion pionCible = getPionCible(pionActeur);

            //Génération d'un chiffre aléatoire entre ]0; 0.6]
            Random rand = new Random();
            double y = 0;
            while(true){
                y = rand.nextDouble();
                if(y > 0 && y <= 0.6){
                    break;
                }
            }

            //Calcul du soin à appliquer
            heal = y * (10 + pionCible.constitution);

            //Affichage de debug
            System.out.println("Soin de valeur : " + heal);
            //PreSet.tempo(1500);

            //Affichage de debug
            System.out.println("Vie de " + pionCible.getNom() + " du " + pionCible.getJoueur().getNom() + " : " + pionCible.getECTS() + " avant.");
            //PreSet.tempo(1500);

            //Application du soin sur le pion cible.
            pionCible.pionSoigne((int)heal);

            //Affichage de debug
            System.out.println("Vie de " + pionCible.getNom() + " du " + pionCible.getJoueur().getNom() + " : " + pionCible.getECTS() + " après.");
            //PreSet.tempo(1500);
        } else{
            //Affichage de debug
            System.out.println("Soin de : " + pionActeur.getNom() + " du " + pionActeur.getJoueur().getNom() + " raté.");
            //PreSet.tempo(1500);
        }
    }


    /**
     * Méthode pour savoir quel pion doit être soigné par le pion acteur.
     * @param pionActeur Pion effectuant la stratégie.
     * @return pionCible Pion auquel la stratégie sera executée.
     */
    public Pion getPionCible(Pion pionActeur){
        Pion pionCible = null;

        //Récupération du pion avec le moins de vie en fonction du joueur auxquels ils appartiennent
        if(pionActeur.joueur.getNom().equals("Joueur 1")){
            pionCible = pionActeur.getZone().getLinkedListTeam1().getFirst();
        } else{
            pionCible = pionActeur.getZone().getLinkedListTeam2().getFirst();
        }

        return pionCible;
    }


    /**
     * Redefinition de la methode toString.
     * @return this.nom Nom de la stratégie.
     */
    @Override
    public String toString(){
        return this.nom;
    }
}
