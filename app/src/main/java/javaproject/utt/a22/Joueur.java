package javaproject.utt.a22;

import java.util.*;

/**
 * Classe representant les joueurs jouant dans une partie.
 */
public class Joueur{

    /**
     * Attribut indiquant la partie du joueur.
     */
    private Partie partie;

    /**
     * Nom du joueur (predefini en "Joueur 1" et "Joueur 2").
     */
    private String nom;

    /**
     * Nombre de point que le joueur peut distribuer à ses pions.
     */
    private int point = 400;
    /**
     * Nombre maximum de point que le joueur peut avoir.
     */
    private final int maxPoint = 400;
    /**
     * Nombre minimum de point que le joueur peut avoir.
     */
    private final int minPoint = 0;

    /**
     * Attribut indiquant l'equipe que le joueur represente.
     */
    private NomEquipe equipe;

    /**
     * Attribut indiquant le status du joueur (Preparation ou Ready).
     */
    private StatusJoueur status;
    
    /*
     * Liste des pions du joueur.
     */
    private ArrayList<Pion> arrayPion = new ArrayList<Pion>();

    /**
     * Liste des zones controlee par le joueur.
     */
    private Set<Zone> setZoneControlee = new HashSet<Zone>();


    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {
        
    }



    //******************************************************//
    //                                                      //
    //                      Constructeur                    //
    //                                                      //
    //******************************************************//

    /**
     * Constructeur de la classe Joueur.
     */
    public Joueur(Partie partie, String nom){
        this.partie = partie;
        this.nom = nom;

        this.partie.addJoueur(this);
    }



    //******************************************************//
    //                                                      //
    //                          Nom                         //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour recuperer le nom du joueur.
     * @return this.nom
     */
    public String getNom(){
        return this.nom;
    }



    //******************************************************//
    //                                                      //
    //                         Partie                       //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour recuperer la partie du joueur.
     * @return this.partie
     */
    public Partie getPartie(){
        return this.partie;
    }



    //******************************************************//
    //                                                      //
    //                       Points                         //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour ajouter des points au joueur.
     * Le nombre de point que le joueur possede (point) ne peut pas etre superieur le maximum fixe (maxPoint).
     * @param point
     */
    public void ajouterPoint(int point){
        this.point += point;
        if(this.point > this.maxPoint){
            this.point = this.maxPoint;
        }
    }

    /**
     * Methode pour retirer des point au joueur.
     * Le nombre de point que le joueur possede (point) ne peut pas etre inferieur au minimum fixe (minPoint).
     * @param point
     */
    public void retirerPoint(int point){
        this.point -= point;
        if(this.point < this.minPoint){
            this.point = this.minPoint;
        }
    }

    /**
     * Methode pour recuperer le nombre de point que le joueur possede.
     * @return this.point
     */
    public int getPoint(){
        return this.point;
    }



    //******************************************************//
    //                                                      //
    //                        Equipe                        //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour changer l'equipe du joueur.
     * Les equipes sont visibles dans l'enumeration "NomEquipe".
     * @param equipe
     */
    public void setEquipe(NomEquipe equipe){
        this.equipe = equipe;
    }

    /**
     * Methode pour recuperer l'equipe du joueur.
     * @return this.equipe
     */
    public NomEquipe getEquipe(){
        return this.equipe;
    }



    //******************************************************//
    //                                                      //
    //                         Status                       //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour changer le status du joueur.
     * Les status sont visibles dans l'enumeration "StatusJoueur".
     * @param status
     */
    public void setStatus(StatusJoueur status){
        this.status = status;
    }

    /**
     * Methode pour recuperer le status du joueur.
     * @return this.status
     */
    public StatusJoueur getStatus(){
        return this.status;
    }



    //******************************************************//
    //                                                      //
    //                          Pion                        //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour ajouter un pion dans la liste.
     * Verification que la liste du joueur ne contient pas deja ce pion.
     * @param pion
     */
    public void addPion(Pion pion){
        if(!this.arrayPion.contains(pion)){
            this.arrayPion.add(pion);
        }
    }

    /**
     * Methode pour retirer un pion de la liste.
     * Verification que la liste du joueur contient se pion.
     * @param pion
     */
    public void removePion(Pion pion){
        if(this.arrayPion.contains(pion)){
            this.arrayPion.remove(pion);
        }
    }

    /**
     * Methode pour recuperer la liste de pion que le joueur possede.
     * @return this.arrayPion
     */
    public ArrayList<Pion> getListPion(){
        return this.arrayPion;
    }



    //******************************************************//
    //                                                      //
    //                          Zone                        //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour ajouter une zone controlee par le joueur dans le set de zone controlee (setZoneControlee).
     * @param zone
     */
    public void addZoneControlee(Zone zone){
        this.setZoneControlee.add(zone);
    }

    /**
     * Methode pour retirer une zone controlee par le joueur du set de zone controlee (setZoneControlee).
     * Verification que la liste des zones contient cette zone.
     * @param zone
     */
    public void removeZoneControlee(Zone zone){
        if(this.setZoneControlee.contains(zone)){
            this.setZoneControlee.remove(zone);
        }
    }

    /**
     * Methode pour recuperer les zones que le joueur possede.
     * @return this.setZoneControlee
     */
    public Set<Zone> getZoneControlee(){
        return this.setZoneControlee;
    }
}
