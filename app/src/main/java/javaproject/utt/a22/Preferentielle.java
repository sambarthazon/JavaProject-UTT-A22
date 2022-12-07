package javaproject.utt.a22;

public class Preferentielle implements Strategie{
    
    /**
     * Nom de la strategie.
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
     */
    @Override
    public void combattre(Pion pionActeur){
        //Calcul des vies par equipe sur la zone du pion.
        final int ECTSTeam1 = pionActeur.getZone().getECTSTeam1();
        final int ECTSTeam2 = pionActeur.getZone().getECTSTeam2();


        if(pionActeur.joueur.getNom().equals("Joueur 1")){ //Si c'est un pion du joueur 1.
            if(ECTSTeam1 > ECTSTeam2){ //Si la vie de son equipe est superieur a l'equipe adverse il va attaquer.
                pionActeur.setStrategie(new Offensif());
            } else if(ECTSTeam1 < ECTSTeam2){ //Si la vie de son equipe est inferieur a l'equipe adverse il va soigner.
                pionActeur.setStrategie(new Defensif());
            } else{ //Si les vies sont equitables, la strategie sera aleatoire.
                pionActeur.setStrategie(new Aleatoire());
            }
        } else{ //Si c'est un pion du joueur 2.
            if(ECTSTeam2 > ECTSTeam1){ //Si la vie de son equipe est superieur a l'equipe adverse il va attaquer.
                pionActeur.setStrategie(new Offensif());
            } else if(ECTSTeam2 < ECTSTeam1){ //Si la vie de son equipe est inferieur a l'equipe adverse il va soigner.
                pionActeur.setStrategie(new Defensif());
            } else{ //Si les vies sont equitables, la strategie sera aleatoire.
                pionActeur.setStrategie(new Aleatoire());
            }
        }

        //Remise de la strategie de base du pion
        pionActeur.setStrategie(new Preferentielle());
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

