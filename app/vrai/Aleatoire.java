public class Aleatoire implements Strategie{
    private final String nom = "Aléatoire";

    public Aleatoire() {
    }

    public static void main(String[] args) {
    }

    public void combattre(Pion pionActeur) {
        double trueFalse = (double)Math.round(Math.random());
        if (trueFalse == 1.0) {
            pionActeur.setStrategie(new Offensif());
            pionActeur.executerStrategie();
        } else {
            pionActeur.setStrategie(new Defensif());
            pionActeur.executerStrategie();
        }

        pionActeur.setStrategie(new Aleatoire());
    }

    public String getNom() {
        return "Aléatoire";
    }

    public String toString() {
        return "Aléatoire";
    }
}
