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
    public void combattre(Pion pionActeur){
        //
        int x = 0;
        final int x_Min = 0;
        final int x_Max = 100;

        //
        final int degatReference = 10;

        //
        double varFuncMin = 0;
        double varFuncMax = 0;

        //Degats que va subir le pion cible.
        int damage = 0;

        //Generation d'un nombre aleatoire entre 0 et 100.
        x = (int) Math.floor(Math.random()*(x_Max-x_Min+1)+x_Min);
        Boolean reussi = x >= 0 && x <= 40 + 3 * pionActeur.getDexterite() ? true : false;

        //Si l'attaque est reussi.
        if(reussi){
            System.out.println("Attaque de : " + pionActeur.getNom() + " du " + pionActeur.getJoueur().getNom() + " réussi.");
            //PreSet.tempo(1500);

            //Recuperation du pion qui a le moins de point de vie de l'adversaire.
            Pion pionCible = getPionCible(pionActeur);

            double y = -(Math.random()-1);

            varFuncMin = min(pionActeur.getForce(), pionCible.getResistance());
            varFuncMax = max(varFuncMin);

            double coefDegat = varFuncMax/100;
            //Calcul des degats qui vont etre appliques.
            damage = (int) (y * (1 + coefDegat) * degatReference);
            
            System.out.println("Vie de " + pionCible.getNom() + " du " + pionCible.getJoueur().getNom() + " : " + pionCible.getECTS() + " avant.");
            //PreSet.tempo(1500);
            //Application des degats sur le pion cible.
            pionCible.pionAttaque(damage);
            System.out.println("Vie de " + pionCible.getNom() + " du " + pionCible.getJoueur().getNom() + " : " + pionCible.getECTS() + " après.");
            //PreSet.tempo(1500);

        } else{
            System.out.println("Attaque de : " + pionActeur.getNom() + " du " + pionActeur.getJoueur().getNom() + " raté.");
            //PreSet.tempo(1500);
        }
    }


    /**
     * Methode pour calculer une partie des coefficients de degat.
     * @param forceActeur
     * @param resistanceCible
     * @return
     */
    public double min(int forceActeur, int resistanceCible){
        final int valeurComparaison = 100;
        int valeurAComparer = 0;

        valeurAComparer = 10 * forceActeur - 5 * resistanceCible;
        if(valeurAComparer < valeurComparaison){
            return valeurAComparer;
        } else{
            return valeurComparaison;
        }
    }


    /**
     * Methode pour calculer l'autre partie des coefficients de degat.
     * @param varMin
     * @return
     */
    public double max(double valeurAComparer){
        final int valeurComparaison = 0;

        if(valeurAComparer > valeurComparaison){
            return valeurAComparer;
        } else{
            return valeurComparaison;
        }
    }


    /**
     * Methode pour savoir quel pion sera effecte par la strategie.
     * @param pionActeur
     * @return pionCible
     */
    public Pion getPionCible(Pion pionActeur){
        Pion pionCible = null;

        if(pionActeur.joueur.getNom().equals(Partie.NOM_JOUEUR1)){
            pionCible = pionActeur.getZone().getLinkedListTeam2().getFirst();
        } else{
            pionCible = pionActeur.getZone().getLinkedListTeam1().getFirst();
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
