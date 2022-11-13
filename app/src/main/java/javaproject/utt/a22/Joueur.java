package javaproject.utt.a22;

/**
 * Classe representant les joueurs jouant dans une partie.
 */
public class Joueur{
    
    /*
     * List de pion (soit 20 Pion, soit 15 Etudiant, 4 Elite et 1 Maitre)
     */

    /**
     * Attribut representant l'equipe du joueur.
     */
    Equipe equipe;

    /**
     * Enumeration des equipes disponibles pour le joueur.
     */
    enum Equipe{
        A2I, GI, GM, ISI, MM, MTE, RT;
    }

    /**
     * Attribut representant le status du joueur.
     */
    StatusJoueur status;

    /**
     * Enumeration des status du joueur.
     */
    enum StatusJoueur{
        Ready, Preparation;
    }

    /**
     * Attribut indiquant que le joueur est dans une partie.
     */
    Partie partie;

    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {
        
    }

    /**
     * Constructeur de la classe Joueur.
     */
    public Joueur(){

    }


    /**
     * Methode pour changer le status du joueur.
     * @param status
     */
    public void setStatus(StatusJoueur status){

    }

    /**
     * Methode pour recuperer le status du joueur.
     * @return this.status
     */
    public StatusJoueur getStatus(){
        return this.status;
    }

}
