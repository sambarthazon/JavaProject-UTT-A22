public class Elite extends Pion {
    public Elite(Joueur joueur,String nom){
        this.joueur = joueur;
        this.nom = nom;
        this.dexterite = 1;
        this.force = 1;
        this.resistance = 1;
        this.constitution = 5;
        this.initiative = 1;
        this.strategie = new Offensif();
        this.joueur.addPion(this);
    }

}
