package javaproject.utt.a22;

public class Etudiant extends Pion{

    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {
        
    }
    

    /**
     * Constructeur de la classe Etudiant.
     */
    public Etudiant(Joueur joueur, String nom){
        this.joueur = joueur;
        this.nom = nom;

        this.dexterite = 0;
        this.force = 0;
        this.resistance = 0;
        this.constitution = 0;
        this.initiative = 0;
        this.strategie = new Offensif();

        this.joueur.addPion(this);
    }
}
