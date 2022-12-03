package javaproject.utt.a22;

import java.util.*;

public class Zone{
    
    /**
     * Attribut correspondant au plateau auquel appartient la zone.
     */
    Plateau plateau;

    /**
     * List de pion de la team 1 present sur la zone.
     */
    LinkedList<Pion> linkedPionTeam1 = new LinkedList<Pion>();

    /**
     * List de pion de la team 2 present sur la zone.
     */
    LinkedList<Pion> linkedPionTeam2 = new LinkedList<Pion>();

    /**
     * List de pion de la zone.
     */
    LinkedList<Pion> linkedPion = new LinkedList<Pion>();

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
        Partie partie = new Partie();

        Joueur joueur = new Joueur(partie, "Joueur 1");

        Zone zone = new Zone();

        Pion pion1 = new Etudiant(joueur, "Etudiant 1");
        Pion pion2 = new Etudiant(joueur, "Etudiant 2");
        Pion pion3 = new Etudiant(joueur, "Etudiant 3");
        Pion pion4 = new Etudiant(joueur, "Etudiant 4");

        zone.addPion(pion1);
        zone.addPion(pion2);
        zone.addPion(pion3);
        zone.addPion(pion4);

        Iterator<Pion> itBefore = zone.linkedPion.iterator();
        while(itBefore.hasNext()){
            System.out.println(itBefore.next());
        }

        pion1.setInitiative(3);
        pion2.setInitiative(8);
        pion3.setInitiative(7);

        zone.sortLinkedPion();

        Iterator<Pion> itAfter = zone.linkedPion.iterator();
        while(itAfter.hasNext()){
            System.out.println(itAfter.next());
        }
    }


    /**
     * Constructeur de la classe Zone.
     */
    public Zone(){
        
    }


    /**
     * Methode pour ajouter le pion a la liste du joueur 1 ou 2.
     * @param pion
     */
    public void addPion(Pion pion){
        if(!this.linkedPion.contains(pion)){
            this.linkedPion.add(pion);
        }
        /*
         * Ajouter le pion au set de pion de la team 1 de la zone.
         */
    }

    /**
     * Methode pour retirer le pion de la list du joueur 1 ou 2.
     * @param pion
     */
    public void removePion(Pion pion){
        if(pion.joueur.getNom() == "Joueur 1"){
            if(this.linkedPionTeam1.contains(pion)){
                this.linkedPionTeam1.remove(pion);
                pion.setZone(null);
            } else{
                System.out.println("Ce pion n'est pas dans la liste.");
            }
        } else if(pion.joueur.getNom() == "Joueur 2"){
            if(this.linkedPionTeam2.contains(pion)){
                this.linkedPionTeam2.remove(pion);
                pion.setZone(null);
            } else{
                System.out.println("Ce pion n'est pas dans la liste.");
            }
        }
    }


    /**
     * Methode pour recuperer les ECTS de la team 1 sur la zone.
     * @return 
     */
    public int getECTSTeam1(){
        int ECTS = 0;
        /*
         * Parcour le setPionTeam1 et additionne tous les ECTS.
         */
        return ECTS;
    }

    /**
     * Methode pour recuperer les ECTS de la team 2 sur la zone.
     */
    public int getECTSTeam2(){
        int ECTS = 0;
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
     * FONCTIONNEL
     */
    public StatusZone getStatus(){
        return this.status;
    }


    /**
     * Methode pour trier la liste de la team 1 par ECTS
     * FONCTIONNEL MAIS INVERSE
     */
    public void sortLinkedPionTeam1(){
        Collections.sort(linkedPionTeam1, new Comparator<Pion>(){
            @Override
            public int compare(Pion p1, Pion p2){
                return p2.ECTS - p1.ECTS;
            }
        });
    }

    /**
     * Methode pour trier la liste de la team 2 par ECTS
     * FONCTIONNEL MAIS INVERSE
     */
    public void sortLinkedPionTeam2(){
        Collections.sort(linkedPionTeam2, new Comparator<Pion>(){
            @Override
            public int compare(Pion p1, Pion p2){
                return p2.ECTS - p1.ECTS;
            }
        });
    }

    /**
     * Methode pour trier la liste de la zone par initiative
     * FONCTIONNEL
     */
    public void sortLinkedPion(){
        Collections.sort(linkedPion, new Comparator<Pion>(){
            @Override
            public int compare(Pion p1, Pion p2){
                return p2.initiative - p1.initiative;
            }
        });
    }


    /**
     * Methode pour lancer le combat de la zone.
     */
    public void combat(){
        this.sortLinkedPion();
        this.sortLinkedPionTeam1();
        this.sortLinkedPionTeam2();

        Iterator<Pion> itInitiative = this.linkedPion.iterator();
        while(itInitiative.hasNext()){
            Pion pionInitiative = itInitiative.next();
            Pion pionECTS = linkedPionTeam1.getLast();
            pionInitiative.executerStrategie(pionECTS);
        }
    }

}
