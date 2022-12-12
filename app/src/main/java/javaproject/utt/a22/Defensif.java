package javaproject.utt.a22;

import java.util.Random;

/**
 * Classe Defensif implementant Strategie pour combattre defensivement.
 */
public class Defensif implements Strategie{
    
    /**
     * Nom de la strategie
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
     */
    @Override
    public void combattre(Pion pionActeur){
        //
        int x = 0;
        final int x_Min = 0;
        final int x_Max = 100;

        //
        double heal = 0;


        x = (int) Math.floor(Math.random()*(x_Max-x_Min+1)+x_Min);
        Boolean reussi = x >= 0 && x <= 20 + 6 * pionActeur.dexterite ? true : false;

        if(reussi){
            System.out.println("Soin de : " + pionActeur.getNom() + " du " + pionActeur.getJoueur().getNom() + " réussi.");
            //PreSet.tempo(1500);
            //Recuperation du joueur qui a le moins de point de vie de son equipe.
            Pion pionCible = getPionCible(pionActeur);

            Random rand = new Random();
            double y = 0;
            while(true){
                y = rand.nextDouble();
                if(y > 0 && y <= 0.6){
                    break;
                }
            }
            
            System.out.println(y);
            //PreSet.tempo(1500);


            heal = y * (10 + pionCible.constitution);
            System.out.println("Soin de valeur : " + heal);
            //PreSet.tempo(1500);

            System.out.println("Vie de " + pionCible.getNom() + " du " + pionCible.getJoueur().getNom() + " : " + pionCible.getECTS() + " avant.");
            //PreSet.tempo(1500);
            //Application du soin sur le pion cible.
            pionCible.setECTS((int)heal);
            System.out.println("Vie de " + pionCible.getNom() + " du " + pionCible.getJoueur().getNom() + " : " + pionCible.getECTS() + " après.");
            //PreSet.tempo(1500);
        } else{
            System.out.println("Soin de : " + pionActeur.getNom() + " du " + pionActeur.getJoueur().getNom() + " raté.");
            //PreSet.tempo(1500);
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
