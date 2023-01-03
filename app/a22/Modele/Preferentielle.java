package javaproject.utt.a22;

/**
 * Classe Preferentielle implementant Strategie pour combattre offensivement ou defensivement.
 */
public class Preferentielle implements Strategie{
    
    /**
     * Nom de la stratégie.
     */
    private final String nom = "Préférentielle";
    
    /**
     * Main
     * @param args
     */
    public static void main(String[] args){

    }

    /**
     * Implementation de la methode combattre.
     * @param pionActeur Pion allant effectuer la stratégie.
     */
    @Override
    public void combattre(Pion pionActeur){
        //Calcul des vies par equipe sur la zone du pion
        final int ECTSTeam1 = pionActeur.getZone().getECTSTeam1();
        final int ECTSTeam2 = pionActeur.getZone().getECTSTeam2();


        if(pionActeur.joueur.getNom().equals("Joueur 1")){ //Si c'est un pion du joueur 1
            if(ECTSTeam1 > ECTSTeam2){ //Si la vie de son équipe est supérieur à l'équipe adverse il va attaquer
                pionActeur.setStrategie(new Offensif());
            } else if(ECTSTeam1 < ECTSTeam2){ //Si la vie de son équipe est inférieur à l'équipe adverse il va soigner
                pionActeur.setStrategie(new Defensif());
            } else{ //Si les vies sont équitables, la stratégie sera aléatoire
                pionActeur.setStrategie(new Aleatoire());
            }
        } else{ //Si c'est un pion du joueur 2
            if(ECTSTeam2 > ECTSTeam1){ //Si la vie de son équipe est supérieur à l'équipe adverse il va attaquer
                pionActeur.setStrategie(new Offensif());
            } else if(ECTSTeam2 < ECTSTeam1){ //Si la vie de son équipe est inférieur à l'équipe adverse il va soigner
                pionActeur.setStrategie(new Defensif());
            } else{ //Si les vies sont équitables, la stratégie sera aléatoire
                pionActeur.setStrategie(new Aleatoire());
            }
        }

        //Remise de la stratégie de base du pion
        pionActeur.setStrategie(new Preferentielle());
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

