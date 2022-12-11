public class Defensif implements Strategie{
    private final String nom = "Défensif";

    public Defensif() {
    }

    public static void main(String[] args) {
    }

    public void combattre(Pion pionActeur) {
        int x = 0;
        int x_Min = 0;
        int x_Max = 0;
        int heal = 0;
        x = (int)Math.floor(Math.random() * 101.0 + 0.0);
        Boolean reussi = x >= 0 && x <= 20 + 6 * pionActeur.dexterite;
        if (reussi) {
            Pion pionCible = this.getPionCible(pionActeur);
            double y = 0.0;
            y = Math.floor(Math.random() * 1.6 + 0.0);
            heal = (int)y * (10 + pionCible.constitution);
            pionCible.setECTS(heal);
        }

    }

    public Pion getPionCible(Pion pionActeur) {
        Pion pionCible = null;
        if (pionActeur.joueur.getNom().equals("Joueur 1")) {
            pionCible = (Pion)pionActeur.getZone().getLinkedListTeam1().getFirst();
        } else {
            pionCible = (Pion)pionActeur.getZone().getLinkedListTeam2().getFirst();
        }

        return pionCible;
    }

    public String getNom() {
        return "Défensif";
    }

    public String toString() {
        return "Défensif";
    }
}
