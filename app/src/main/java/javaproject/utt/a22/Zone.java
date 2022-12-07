package javaproject.utt.a22;

import java.util.*;

public class Zone extends Thread{

    /**
     * List de pion de la team 1 present sur la zone.
     */
    private LinkedList<Pion> linkedPionTeam1 = new LinkedList<Pion>();

    /**
     * List de pion de la team 2 present sur la zone.
     */
    private LinkedList<Pion> linkedPionTeam2 = new LinkedList<Pion>();

    /**
     * List de pion de la zone.
     */
    private LinkedList<Pion> linkedPion = new LinkedList<Pion>();

    /**
     * Attribut correspondant au nom de la zone.
     */
    private NomZone nom;

    /**
     * Attribut correspondant au status de la zone.
     */
    private StatusZone status;

    /**
     * Attribut correspondant a la partie de la zone.
     */
    private Partie partie;


    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {
        Partie partie = new Partie();

        Joueur joueur = new Joueur(partie, "Joueur 1");

        Zone zoneBU = new Zone(partie, NomZone.BU);
        Zone zoneBDE = new Zone(partie, NomZone.BDE);
        Zone zoneQA = new Zone(partie, NomZone.QuartierAdmin);

        Pion pion1 = new Etudiant(joueur, "Etudiant 1");
        Pion pion2 = new Etudiant(joueur, "Etudiant 2");
        Pion pion3 = new Etudiant(joueur, "Etudiant 3");

        zoneBU.addPion(pion1);
        zoneBDE.addPion(pion2);
        zoneQA.addPion(pion3);

        partie.setStatus(StatusPartie.Combat);

        zoneBU.start();
        zoneBDE.start();
        zoneQA.start();
    }


    /**
     * Constructeur de la classe Zone.
     */
    public Zone(Partie partie, NomZone nomZone){
        this.partie = partie;
        this.nom = nomZone;

        this.partie.addZone(this);
    }


    /**
     * Methode pour ajouter le pion a la liste du joueur 1 ou 2.
     * @param pion
     */
    public void addPion(Pion pion){
        if(!this.linkedPion.contains(pion)){
            this.linkedPion.add(pion);
        }
        if(!this.linkedPionTeam1.contains(pion)){
            this.linkedPionTeam1.add(pion);
        }
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
        int ECTSTotal = 0;
        Pion pion = null;
        
        Iterator<Pion> it = this.linkedPionTeam1.iterator();
        while(it.hasNext()){
            pion = it.next();
            ECTSTotal += pion.getECTS();;
        }

        return ECTSTotal;
    }

    /**
     * Methode pour recuperer les ECTS de la team 2 sur la zone.
     */
    public int getECTSTeam2(){
        int ECTSTotal = 0;
        Pion pion = null;
        
        Iterator<Pion> it = this.linkedPionTeam2.iterator();
        while(it.hasNext()){
            pion = it.next();
            ECTSTotal += pion.getECTS();;
        }

        return ECTSTotal;
    }


    /**
     * Methode pour recuperer la liste des pions de la team 1.
     * @return this.linkedPionTeam1
     */
    public LinkedList<Pion> getLinkedListTeam1(){
        return this.linkedPionTeam1;
    }

    /**
     * Methode pour recuperer la liste des pions de la team 2.
     * @return this.linkedPionTeam2
     */
    public LinkedList<Pion> getLinkedListTeam2(){
        return this.linkedPionTeam2;
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
     * Methode pour trier la liste de la team 1 par ECTS (plus petit au plus grand).
     * FONCTIONNEL
     */
    public void sortLinkedPionTeam1(){
        Collections.sort(linkedPionTeam1, new Comparator<Pion>(){
            @Override
            public int compare(Pion p1, Pion p2){
                return p1.ECTS - p2.ECTS;
            }
        });
    }

    /**
     * Methode pour trier la liste de la team 2 par ECTS (plus petit au plus grand).
     * FONCTIONNEL
     */
    public void sortLinkedPionTeam2(){
        Collections.sort(linkedPionTeam2, new Comparator<Pion>(){
            @Override
            public int compare(Pion p1, Pion p2){
                return p1.ECTS - p2.ECTS;
            }
        });
    }

    /**
     * Methode pour trier la liste de la zone par initiative (plus grand au plus petit).
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


    public void combattre(){
        this.start();
    }

    /**
     * Methode pour lancer le combat de la zone.
     */
    public void run(){

        this.sortLinkedPion();
        this.sortLinkedPionTeam1();
        this.sortLinkedPionTeam2();

        while(this.getECTSTeam1() > 0 && this.getECTSTeam2() > 0 && this.partie.getStatus().equals(StatusPartie.Combat)){

            System.out.println(this.nom);
            this.linkedPionTeam1.get(0).setECTS(-5);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        /*
        Iterator<Pion> it = this.linkedPion.iterator();
        while(it.hasNext()){
            Pion pionActeur = it.next();
            pionActeur.executerStrategie();
            this.sortLinkedPionTeam1();
            this.sortLinkedPionTeam2();
        }
        */
    }

}
