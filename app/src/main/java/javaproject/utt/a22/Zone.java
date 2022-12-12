package javaproject.utt.a22;

import java.util.*;

public class Zone{

    /**
     * Attribut indiquant la partie de la zone.
     */
    private Partie partie;

    /**
     * Attribut indiquant le nom de la zone.
     */
    private NomZone nom;

    /**
     * Attribut indiquant l'appelation de la zone.
     */
    private String label;

    /**
     * Attribut indiquant le status de la zone.
     */
    private boolean estControlee = true;

    /**
     * Attribut indiquant si nous sommes dans le premier tour de combat.
     */
    private boolean isFirst = true;

    /**
     * Liste de pion de la zone (triee par initiative par ordre decroissant).
     */
    private LinkedList<Pion> linkedPion = new LinkedList<Pion>();

    /**
     * Liste de pion de la team 1 present sur la zone (triee par ECTS par ordre decroissant).
     */
    private LinkedList<Pion> linkedPionTeam1 = new LinkedList<Pion>();

    /**
     * Liste de pion de la team 2 present sur la zone (triee par ECTS par ordre decroissant).
     */
    private LinkedList<Pion> linkedPionTeam2 = new LinkedList<Pion>();


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
     * Constructeur de la classe Zone.
     */
    public Zone(Partie partie, NomZone nomZone, String label){
        this.partie = partie;
        this.nom = nomZone;
        this.label = label;

        this.partie.addZone(this);
    }



    //******************************************************//
    //                                                      //
    //                        Partie                        //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour recuperer la partie de la zone.
     * @return this.partie
     */
    public Partie getPartie(){
        return this.partie;
    }



    //******************************************************//
    //                                                      //
    //                        Nom                           //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour recuperer le nom de la zone.
     * @return this.nom
     */
    public NomZone getNomZone(){
        return this.nom;
    }

    /**
     * Methode pour recuperer le label de la zone.
     * @return this.label
     */
    public String getLabel(){
        return this.label;
    }



    //******************************************************//
    //                                                      //
    //                        Status                        //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour changer le status de la zone.
     * 1 pour controlée et 0 pour non controlée.
     * @param status
     */
    public void setStatus(boolean status){
        this.estControlee = status;
    }

    /**
     * Methode pour recuperer le status de la zone.
     * @return this.status
     */
    public boolean getStatus(){
        return this.estControlee;
    }



    //******************************************************//
    //                                                      //
    //                         Pions                        //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour ajouter le pion a la liste globale (linkedPion).
     * Si le pion appartient au "Joueur 1", ajout de ce pion dans sa liste.
     * Si le pion appartient au "Joueur 2", ajout de ce pion dans sa liste.
     * @param pion
     */
    public void addPion(Pion pion){
        if(!this.linkedPion.contains(pion)){
            this.linkedPion.add(pion);
        }

        if(pion.getJoueur().getNom().equals("Joueur 1")){
            if(!this.linkedPionTeam1.contains(pion)){
                this.linkedPionTeam1.add(pion);
            }
        } else if(pion.getJoueur().getNom().equals("Joueur 2")){
            if(!this.linkedPionTeam2.contains(pion)){
                this.linkedPionTeam2.add(pion);
            }
        }
    }

    /**
     * Methode pour retirer le pion de la zone.
     * Cela retirera le pion de "linkedPion" et "linkedPionTeam1" ou "linkedPionTeam2".
     * @param pion
     */
    public void removePion(Pion pion){
        if(pion.getJoueur().getNom().equals("Joueur 1")){
            if(this.linkedPionTeam1.contains(pion)){
                this.linkedPionTeam1.remove(pion);
            }
        } else if(pion.getJoueur().getNom().equals("Joueur 2")){
            if(this.linkedPionTeam2.contains(pion)){
                this.linkedPionTeam2.remove(pion);
            }
        }

        if(this.linkedPion.contains(pion)){
            this.linkedPion.remove(pion);
            pion.setZone(null);
        }
    }



    //******************************************************//
    //                                                      //
    //                        Global                        //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour trier la liste de la zone par initiative (plus grand au plus petit).
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
     * Methode pour récuperer la liste de pion présent dans la zone.
     * @return
     */
    public LinkedList<Pion> getLinkedPion(){
        return this.linkedPion;
    }



    //******************************************************//
    //                                                      //
    //                        Team 1                        //
    //                                                      //
    //******************************************************//

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
     * Methode pour recuperer la liste des pions de la team 1.
     * @return this.linkedPionTeam1
     */
    public LinkedList<Pion> getLinkedListTeam1(){
        return this.linkedPionTeam1;
    }

    /**
     * Methode pour trier la liste de la team 1 par ECTS (plus petit au plus grand).
     */
    public void sortLinkedPionTeam1(){
        Collections.sort(linkedPionTeam1, new Comparator<Pion>(){
            @Override
            public int compare(Pion p1, Pion p2){
                return p1.ECTS - p2.ECTS;
            }
        });
    }



    //******************************************************//
    //                                                      //
    //                        Team 2                        //
    //                                                      //
    //******************************************************//

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
     * Methode pour recuperer la liste des pions de la team 2.
     * @return this.linkedPionTeam2
     */
    public LinkedList<Pion> getLinkedListTeam2(){
        return this.linkedPionTeam2;
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

    

    //******************************************************//
    //                                                      //
    //                        Combat                        //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour lancer la methode run pour le multi-threading.
     */
    public void combattre(){
        if(this.getECTSTeam1() > 0 && this.getECTSTeam2() > 0){
            this.init();
            PreSet.setTerminal();
            System.out.println("Combat zone : " + this.label);
            System.out.println(this.linkedPion);
            //PreSet.tempo(2500);

            this.firstTour();
            
            Pion pionActeur = this.linkedPion.getFirst();
            pionActeur.executerStrategie();

            linkedPion.removeFirst();
            linkedPion.addLast(pionActeur);

            this.pionMort();
        }
    }


    public void init(){
        this.estControlee = false;
        this.partie.getListJoueur().get(0).removeZoneControlee(this);
        this.partie.getListJoueur().get(1).removeZoneControlee(this);
    }

    /**
     * Methode pour organiser les listes au premier tour de combat.
     */
    public void firstTour(){
        if(isFirst){
            this.sortLinkedPion();
            this.sortLinkedPionTeam1();
            this.sortLinkedPionTeam2();
            isFirst = false;
        }
    }

    /**
     * Methode pour gérer les pions morts.
     */
    public void pionMort(){
        Pion pion = null;

        Iterator<Pion> it = this.linkedPion.iterator();
        while(it.hasNext()){
            pion = it.next();
            if(pion.getECTS() <= 0){
                break;
            }
        }

        if(pion.getECTS() <= 0){
            this.removePion(pion);
            pion.getJoueur().removePion(pion);
            System.out.println(this.linkedPion);
            this.enTreve();
        }
    }

    /**
     * Methode pour mettre en trève la zone.
     */
    public void enTreve(){
        System.out.println("Est ce qu'il vas y avoir une treve ?");
        PreSet.tempo(1000);
        if(this.getECTSTeam1() == 0){
            System.out.println("La zone est controlee par l'equipe 2 car les ects de l'equipe 1 sont : " + this.getECTSTeam1());
            this.estControlee = true;
            this.partie.getListJoueur().get(1).addZoneControlee(this);
            this.partie.setStatus(StatusPartie.Treve);
        } else if(this.getECTSTeam2() == 0){
            System.out.println("La zone est controlee par l'equipe 1 car les ects de l'equipe 2 sont : " + this.getECTSTeam2());
            this.estControlee = true;
            this.partie.getListJoueur().get(0).addZoneControlee(this);
            this.partie.setStatus(StatusPartie.Treve);
        }
    }



    //******************************************************//
    //                                                      //
    //                        Affichage                     //
    //                                                      //
    //******************************************************//

    @Override
    public String toString(){
        return this.label;
    }

}
