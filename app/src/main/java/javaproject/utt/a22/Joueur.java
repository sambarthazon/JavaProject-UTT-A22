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
     * Set de zone que le joueur controle.
     */
    Set<Zone> setZoneControlee = new HashSet<Zone>();

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
     * Methode pour ajouter une zone controlee par le joueur dans le set de zone controlee (setZoneControlee).
     * @param zone
     */
    public void addZoneControlee(Zone zone){
        /*
         * Ajout de la zone controlee.
         */
    }

    /**
     * Methode pour retirer une zone controlee par le joueur du set de zone controlee (setZoneControlee).
     * @param zone
     */
    public void removeZoneControlee(Zone zone){
        /*
         * Retret de la zone controlee.
         */
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
