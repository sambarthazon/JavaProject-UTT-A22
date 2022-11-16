package javaproject.utt.a22;

public class Maitre extends Pion{
    
    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {
        
    }


    /**
     * Constructeur de la classe Maitre.
     */
    public Maitre(Joueur joueur, String nom){
        this.joueur = joueur;
        this.nom = nom;

        this.dexterite = 2;
        this.force = 2;
        this.resistance = 2;
        this.constitution = 10;
        this.initiative = 2;
        this.strategie = new Offensif();

        this.joueur.addPion(this);
    }
}
