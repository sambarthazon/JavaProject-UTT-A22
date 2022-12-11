import java.util.ArrayList;

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
    private Equipe equipe;

    /**
     * Attribut indiquant le status du joueur (Preparation ou Ready).
     */
    private StatusJoueur status = StatusJoueur.Preparation;

    /*
     * Liste des pions du joueur.
     */
    ArrayList<Pion> arrayPion = new ArrayList<Pion>();

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
    public Joueur(Partie partie, String nom, Equipe equipe){
        this.partie = partie;
        this.nom = nom;
        this.equipe=equipe;
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

    public String getEquipeStr(){
        return ""+this.equipe;

    }

    public Equipe getEquipe(){
        return this.equipe;
    }



    public Joueur paramJoueur(Joueur joueur, String equip, String nom){

        boolean verifEquipe=false;
        String recupEquipe="";
        while(!verifEquipe) {
            System.out.println("Liste des equipes pour joueur1 : " + Equipe.GM + "," + Equipe.GI + "," + Equipe.MM + "," + Equipe.RT
                    + "," + Equipe.A2I + "," + Equipe.ISI + " ou " + Equipe.MTE + " ?");
            Scanner myequipe1 = new Scanner(System.in);  // Create a Scanner object
            String equipe = myequipe1.nextLine();
            if (equipe.equals(equip)){
                System.out.println("L'equipe a deja ete selectionnée, choix impossible");
            }else{
                switch (equipe) {
                    case "GM":
                        joueur=new Joueur(partie, nom,Equipe.GM);
                        recupEquipe+=equipe;
                        verifEquipe=true;
                        break;
                    case "GI":
                        joueur=new Joueur(partie, nom,Equipe.GI);
                        recupEquipe+=equipe;
                        verifEquipe=true;
                        break;
                    case "MM":
                        joueur=new Joueur(partie, nom,Equipe.MM);
                        recupEquipe+=equipe;
                        verifEquipe=true;
                        break;
                    case "RT":
                        joueur=new Joueur(partie, nom,Equipe.RT);
                        recupEquipe+=equipe;
                        verifEquipe=true;
                        break;
                    case "A2I":
                        joueur=new Joueur(partie, nom,Equipe.A2I);
                        recupEquipe+=equipe;
                        verifEquipe=true;
                        break;
                    case "ISI":
                        joueur=new Joueur(partie, nom,Equipe.ISI);
                        recupEquipe+=equipe;
                        verifEquipe=true;
                        break;
                    case "MTE":
                        joueur=new Joueur(partie, nom,Equipe.MTE);
                        recupEquipe+=equipe;
                        verifEquipe=true;
                        break;
                    default:
                        System.out.println("Choix d'equipe incorrect");
                }
            }

        return joueur;


    }
        //******************************************************//
        //                                                      //
        //      Phase de sélection des pions                    //
        //                                                      //
        //******************************************************//
    public void init(Partie partie,Joueur joueur){
        partie.phaseParametrage(joueur);

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
    /*
    public Partie getPartie(){
        return this.partie;
    }
    */


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
    public void retirerPoint( int point){
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

    /*
    public void setEquipe(String equip){
        this.equipe = equip;
    }
    */
    /**
     * Methode pour recuperer l'equipe du joueur.
     * @return this.equipe
     */
    /*
    public NomEquipe getEquipe(){
        return this.equipe;
    }
    */


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