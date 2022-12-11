public class Preferentiel implements Strategie{
    private final String nom = "Préférentiel";

    public Preferentiel() {
    }

    public static void main(String[] args) {
    }

    public void combattre(Pion pionActeur) {
        int ECTSTeam1 = pionActeur.getZone().getECTSTeam1();
        int ECTSTeam2 = pionActeur.getZone().getECTSTeam2();
        if (pionActeur.joueur.getNom().equals("Joueur 1")) {
            if (ECTSTeam1 > ECTSTeam2) {
                pionActeur.setStrategie(new Offensif());
            } else if (ECTSTeam1 < ECTSTeam2) {
                pionActeur.setStrategie(new Defensif());
            } else {
                pionActeur.setStrategie(new Aleatoire());
            }
        } else if (ECTSTeam2 > ECTSTeam1) {
            pionActeur.setStrategie(new Offensif());
        } else if (ECTSTeam2 < ECTSTeam1) {
            pionActeur.setStrategie(new Defensif());
        } else {
            pionActeur.setStrategie(new Aleatoire());
        }

        pionActeur.setStrategie(new Preferentiel());
    }

    public String getNom() {
        return "Préférentiel";
    }

    public String toString() {
        return "Préférentiel";
    }
}
