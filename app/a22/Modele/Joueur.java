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
    private StatusJoueur status = StatusJoueur.Preparation;
    
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
     * @param partie Partie du joueur.
     * @param nom Nom du joueur.
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
     * Méthode pour récuperer le nom du joueur.
     * @return this.nom Nom du joueur.
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
     * Méthode pour récuperer la partie du joueur.
     * @return this.partie Partie du joueur.
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
     * Méthode pour ajouter des points au joueur.
     * Le nombre de point que le joueur possède ne peut pas etre supérieur le maximum fixé (maxPoint).
     * @param point Points ajoutés au joueur.
     */
    public void ajouterPoint(int point){
        this.point += point;
        if(this.point > this.maxPoint){
            this.point = this.maxPoint;
        }
    }

    /**
     * Méthode pour retirer des point au joueur.
     * Le nombre de point que le joueur possède ne peut pas etre inférieur au minimum fixé (minPoint).
     * @param point Points retirés au joueur.
     */
    public void retirerPoint(int point){
        this.point -= point;
        if(this.point < this.minPoint){
            this.point = this.minPoint;
        }
    }

    /**
     * Méthode pour récuperer le nombre de point que le joueur possède.
     * @return this.point Points du joueur.
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
     * Méthode pour changer l'équipe du joueur.
     * Les equipes sont visibles dans l'enumeration "NomEquipe".
     * @param equipe Equipe attribuée au joueur.
     */
    public void setEquipe(NomEquipe equipe){
        this.equipe = equipe;
    }

    /**
     * Méthode pour récuperer l'équipe du joueur.
     * @return this.equipe Equipe du joueur.
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
     * Méthode pour changer le status du joueur.
     * Les status sont visibles dans l'enumeration "StatusJoueur".
     * @param status Status appliqué au joueur.
     */
    public void setStatus(StatusJoueur status){
        this.status = status;
    }

    /**
     * Méthode pour récuperer le status du joueur.
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
     * Méthode pour ajouter un pion dans la liste.
     * Vérification que la liste du joueur ne contient pas déjà ce pion.
     * @param pion Pion à ajouter au joueur.
     */
    public void addPion(Pion pion){
        if(!this.arrayPion.contains(pion)){
            this.arrayPion.add(pion);
        }
    }

    /**
     * Méthode pour retirer un pion de la liste.
     * Vérification que la liste du joueur contient ce pion.
     * @param pion Pion à retirer au joueur.
     */
    public void removePion(Pion pion){
        if(this.arrayPion.contains(pion)){
            this.arrayPion.remove(pion);
        }
    }

    /**
     * Méthode pour récuperer la liste de pion que le joueur possède.
     * @return this.arrayPion Liste de pions que le joueur possède.
     */
    public ArrayList<Pion> getListPion(){
        return this.arrayPion;
    }

    /**
     * Méthode pour récuperer le nombre de combattant que le joueur a.
     * @return nbCombattant Nombre de combattant dans la liste du joueur.
     */
    public int getNbCombattant(){
        int nbCombattant = 0;
        Pion pion = null;

        //Itération sur la liste du joueur
        Iterator<Pion> it = this.arrayPion.iterator();
        while(it.hasNext()){
            pion = it.next();
            if(pion.getStatus().equals(StatusPion.Combattant)){ //Si le joueur est combattant
                nbCombattant ++; //Le nombre de combattant est incrémenté
            }
        }

        return nbCombattant;
    }



    //******************************************************//
    //                                                      //
    //                          Zone                        //
    //                                                      //
    //******************************************************//

    /**
     * Méthode pour ajouter une zone controlée par le joueur dans la liste de zone controlée du joueur.
     * @param zone Zone controlée par le joueur.
     */
    public void addZoneControlee(Zone zone){
        //Affichage de débug
        System.out.println("Ajout de " + zone + " au " + this.nom);
        PreSet.tempo(5000);

        //Ajout de la zone au joueur
        this.setZoneControlee.add(zone);
    }

    /**
     * Méthode pour retirer une zone controlée par le joueur de la liste de zone controlée du joueur.
     * Vérification que la liste des zones contient cette zone.
     * @param zone Zone plus controlée par le joueur.
     */
    public void removeZoneControlee(Zone zone){
        if(this.setZoneControlee.contains(zone)){
            this.setZoneControlee.remove(zone);
        }
    }

    /**
     * Méthode pour récuperer les zones que le joueur possède.
     * @return this.setZoneControlee Liste des zones controlée.s par le joueur.
     */
    public Set<Zone> getZoneControlee(){
        return this.setZoneControlee;
    }
}
