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
     * @param pionActeur Pion allant effectuer sa stratégie.
     */
    @Override
    public void combattre(Pion pionActeur){
        //Varaible x et ses bornes
        int x = 0;
        final int x_Min = 0;
        final int x_Max = 100;

        //Dégâts de référence
        final int degatReference = 10;

        //Valeurs de min et max
        double varFuncMin = 0;
        double varFuncMax = 0;

        //Degats que va subir le pion cible.
        int damage = 0;

        //Calcul si l'attaque est réussie ou non
        x = (int) Math.floor(Math.random()*(x_Max-x_Min+1)+x_Min);
        Boolean reussi = x >= 0 && x <= 40 + 3 * pionActeur.getDexterite() ? true : false;

        //Si l'attaque est réussie.
        if(reussi){
            //Affichage de debug
            System.out.println("Attaque de : " + pionActeur.getNom() + " du " + pionActeur.getJoueur().getNom() + " réussi.");
            //PreSet.tempo(1500);

            //Récupération du pion avec le moins de vie de l'équipe adverse
            Pion pionCible = getPionCible(pionActeur);

            //Génération d'un chiffre entre ]0; 1]
            double y = -(Math.random()-1);

            //Calcul des min et max
            varFuncMin = min(pionActeur.getForce(), pionCible.getResistance());
            varFuncMax = max(varFuncMin);

            double coefDegat = varFuncMax/100;

            //Calcul des dégâts que la cible va subir
            damage = (int) (y * (1 + coefDegat) * degatReference);
            
            //Affichage de debug
            System.out.println("Vie de " + pionCible.getNom() + " du " + pionCible.getJoueur().getNom() + " : " + pionCible.getECTS() + " avant.");
            //PreSet.tempo(1500);

            //Application des dégâts sur le pion cible.
            pionCible.pionAttaque(damage);

            //Affichage de debug
            System.out.println("Vie de " + pionCible.getNom() + " du " + pionCible.getJoueur().getNom() + " : " + pionCible.getECTS() + " après.");
            //PreSet.tempo(1500);
        } else{
            //Affichage de debug
            System.out.println("Attaque de : " + pionActeur.getNom() + " du " + pionActeur.getJoueur().getNom() + " raté.");
            //PreSet.tempo(1500);
        }
    }


    /**
     * Methode pour calculer une partie des coéfficients de dégât.
     * @param forceActeur Force du pion acteur.
     * @param resistanceCible Resistance du pion cible.
     * @return
     */
    public double min(int forceActeur, int resistanceCible){
        final int valeurComparaison = 100;
        int valeurAComparer = 0;

        //Calcul de la valeur à comparer
        valeurAComparer = 10 * forceActeur - 5 * resistanceCible;
        if(valeurAComparer < valeurComparaison){ //Si la valeur est inférieure à 100, on retourne cette valeur
            return valeurAComparer;
        } else{
            return valeurComparaison; //Sinon on retourne 1OO
        }
    }


    /**
     * Methode pour calculer l'autre partie des coéfficients de dégât.
     * @param valeurAComparer Première partie des coefficients de dégâts
     * @return
     */
    public double max(double valeurAComparer){
        final int valeurComparaison = 0;

        if(valeurAComparer > valeurComparaison){ //Si la valeur est supérieur à 0, on retourne cette valeur
            return valeurAComparer;
        } else{
            return valeurComparaison; //Sinon on retourne 0
        }
    }


    /**
     * Méthode pour savoir quel pion sera la cible du pion acteur.
     * @param pionActeur Pion qui exécute la stratégie.
     * @return pionCible Pion qui va subir les dégâts.
     */
    public Pion getPionCible(Pion pionActeur){
        Pion pionCible = null;

        //Récupération du pion le plus faible en fonction du joueur du pion acteur
        if(pionActeur.joueur.getNom().equals(Partie.NOM_JOUEUR1)){
            pionCible = pionActeur.getZone().getLinkedListTeam2().getFirst();
        } else{
            pionCible = pionActeur.getZone().getLinkedListTeam1().getFirst();
        }

        return pionCible;
    }


    /**
     * Redéfinition de la méthode toString.
     * @return this.nom Nom de la stratégie.
     */
    @Override
    public String toString(){
        return this.nom;
    }
}
