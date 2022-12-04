package javaproject.utt.a22;

import java.util.*;

/**
 * Classe de la partie de notre jeu.
 */
public class Partie {
    
    /**
     * Set de joueur present dans la partie.
     */
    ArrayList<Joueur> arrayJoueur = new ArrayList<Joueur>();

    /**
     * Attribut correspondant au plateau de la partie.
     */
    Plateau plateau;
    
    /**
     * Status de la partie en cours.
     */
    StatusPartie status;


    /**
     * Constructeur de la classe Partie.
     */
    public Partie(){
        this.status = StatusPartie.Parametrage;
    }

    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {
        
    }


    /**
     * Methode pour ajouter un joueur au Set donc a la partie.
     * @param joueur
     */
    public void addJoueur(Joueur joueur){
        /*
         * Ajouter le joueur au setJoueur.
         */
    }

    /**
     * Methode pour retirer un joueur du Set donc de la partie.
     * @param joueur
     */
    public void removeJoueur(Joueur joueur){
        /*
         * Retirer le joueur au setJoueur.
         */
    }


    public void pionMort(Pion pion){
        /*
         * Enlever le pion de toute collection.
         */
    }


    /**
     * Methode pour ajouter un plateau a la partie.
     * @param plateau
     */
    public void setPlateau(Plateau plateau){
        /*
         * Ajouter le plateau a la partie.
         */
    }

    /**
     * Methode pour retirer le plateau de la partie.
     * @param plateau
     */
    public void removePlateau(Plateau plateau){
        /*
         * Retirer le plateau de la partie.
         */
    }


    /**
     * Methode pour changer le status de la partie.
     * @param status
     */
    public void setStatus(StatusPartie status){
        this.status = status;
    }

    /**
     * Methode pour recuperer le status de la partie.
     * @return this.status
     */
    public StatusPartie getStatus(){
        return this.status;
    }
}
