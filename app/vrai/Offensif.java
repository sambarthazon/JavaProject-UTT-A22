public class Offensif implements Strategie{
    private String nom = "Offensif";

    public Offensif() {
    }

    public static void main(String[] args) {
    }

    public void combattre(Pion pionActeur) {
        int x = 0;
        int x_Min = 0;
        int x_Max = 0;
        int degatReference =0;
        double varFuncMin = 0.0;
        double varFuncMax = 0.0;
        int damage = 0;
        x = (int)Math.floor(Math.random() * 101.0 + 0.0);
        Boolean reussi = x >= 0 && x <= 40 + 3 * pionActeur.getDexterite();
        if (reussi) {
            Pion pionCible = this.getPionCible(pionActeur);
            double y = 0.0;
            y = Math.floor(Math.random() * 2.0 + 0.0);
            varFuncMin = this.min(pionActeur.getForce(), pionCible.getResistance());
            varFuncMax = this.max(varFuncMin);
            double coefDegat = varFuncMax / 100.0;
            damage = (int)(y * (1.0 + coefDegat) * 10.0);
            pionCible.setECTS(-damage);
            if (pionCible.getECTS() <= 0) {
                pionCible.joueur.partie.pionMort(pionCible);
            }
        }

    }

    public double min(int forceActeur, int resistanceCible) {
        boolean valeurComparaison = true;
        int valeurAComparer = 0;
        valeurAComparer = 10 * forceActeur - 5 * resistanceCible;
        return valeurAComparer < 100 ? (double)valeurAComparer : 100.0;
    }

    public double max(double valeurAComparer) {
        boolean valeurComparaison = false;
        return valeurAComparer > 0.0 ? valeurAComparer : 0.0;
    }

    public Pion getPionCible(Pion pionActeur) {
        Pion pionCible = null;
        if (pionActeur.joueur.getNom().equals("Joueur 1")) {
            pionCible = (Pion)pionActeur.getZone().getLinkedListTeam2().getFirst();
        } else {
            pionCible = (Pion)pionActeur.getZone().getLinkedListTeam1().getFirst();
        }

        return pionCible;
    }

    public String getNom() {
        return this.nom;
    }

    public String toString() {
        return this.nom;
    }
}
