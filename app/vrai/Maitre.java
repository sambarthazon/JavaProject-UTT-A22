public class Maitre extends Pion{
    public Maitre(Joueur joueur,String nom){
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
