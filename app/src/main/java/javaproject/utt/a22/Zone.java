package javaproject.utt.a22;

import java.util.*;

public class Zone{
    
    /**
     * Attribut correspondant au plateau auquel appartient la zone.
     */
    Plateau plateau;

    /**
     * Set de pion de la team 1 present sur la zone.
     */
    Set<Pion> setPionTeam1 = new HashSet<Pion>();

    /**
     * Set de pion de la team 2 present sur la zone.
     */
    Set<Pion> setPionTeam2 = new HashSet<Pion>();

    /**
     * Attribut correspondant au nom de la zone.
     */
    NomZone nom;

    /**
     * Enumeration des possibilites de nom de zone.
     */
    enum NomZone{
        BU, BDE, QuartierAdmin, HalleIndus, HalleSport;
    }

    /**
     * Attribut correspondant au status de la zone.
     */
    StatusZone status;

    /**
     * Enumeration des status disponibles pour le status de la zone.
     */
    enum StatusZone{
        Controlee, Bataille;
    }

    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {
        
    }


    /**
     * Constructeur de la classe Zone.
     */
    public Zone(){

    }


    /**
     * Methode pour ajouter le pion au set de pion de la team 1 de la zone.
     * @param pion
     */
    public void addPionTeam1(Pion pion){
        /*
         * Ajouter le pion au set de pion de la team 1 de la zone.
         */
    }

    /**
     * Methode pour retirer le pion du set de pion de la team 1 de la zone.
     * @param pion
     */
    public void RemovePionTeam1(Pion pion){
        /*
         * Retirer le pion du set de pion de la team 1 de la zone.
         */
    }


    /**
     * Methode pour ajouter le pion au set de pion de la team 2 de la zone.
     * @param pion
     */
    public void addPionTeam2(Pion pion){
        /*
         * Ajouter le pion au set de pion de la team 2 de la zone.
         */
    }

    /**
     * Methode pour retirer le pion du set de pion de la team 2 de la zone.
     * @param pion
     */
    public void RemovePionTeam2(Pion pion){
        /*
         * Retirer le pion du set de pion de la team 2 de la zone.
         */
    }


    /**
     * Methode pour recuperer les ECTS de la team 1 sur la zone.
     * @return 
     */
    public double getECTSTeam1(){
        double ECTS = 0;
        /*
         * Parcour le setPionTeam1 et additionne tous les ECTS.
         */
        return ECTS;
    }

    /**
     * Methode pour recuperer les ECTS de la team 2 sur la zone.
     */
    public double getECTSTeam2(){
        double ECTS = 0;
        /*
         * Parcour le setPionTeam2 et additionne tous les ECTS.
         */
        return ECTS;
    }


    /**
     * Methode pour changer le nom de la zone. Deux zones ne peuvent pas avoir le meme nom.
     * @param nom
     */
    public void setNomZone(NomZone nom){
        /*
         * Changement du nom de la zone s.s.i. il n'est pas pris par une autre zone (verification dans le setZone de la classe Plateau).
         */
    }

    /**
     * Methode pour recuperer le nom de la zone.
     * @return this.nom
     */
    public NomZone getNomZone(){
        return this.nom;
    }


    /**
     * Methode pour changer le status de la zone.
     * @param status
     */
    public void setStatus(StatusZone status){
        /*
         * Changement du status de la zone uniquement si les conditions le permettent (plus de pions d'une des equipes ...).
         */
    }

    /**
     * Methode pour recuperer le status de la zone.
     * @return this.status
     */
    public StatusZone getStatus(){
        return this.status;
    }

}
