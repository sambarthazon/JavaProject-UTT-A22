package javaproject.utt.a22;

import java.util.*;

public class Zone extends Thread{

    /**
     * Attribut indiquant la partie de la zone.
     */
    private Partie partie;

    /**
     * Attribut indiquant le nom de la zone.
     */
    private NomZone nom;

    /**
     * Attribut indiquant le status de la zone.
     */
    private StatusZone status;

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
    public Zone(Partie partie, NomZone nomZone){
        this.partie = partie;
        this.nom = nomZone;

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



    //******************************************************//
    //                                                      //
    //                        Status                        //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour changer le status de la zone.
     * Les status sont visibles dans l'enumeration "StatusZone".
     * @param status
     */
    public void setStatus(StatusZone status){
        this.status = status;
    }

    /**
     * Methode pour recuperer le status de la zone.
     * @return this.status
     */
    public StatusZone getStatus(){
        return this.status;
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
        this.start();
    }

    /**
     * Methode pour lancer le combat de la zone.
     */
    public void run(){
        this.sortLinkedPion();
        this.sortLinkedPionTeam1();
        this.sortLinkedPionTeam2();

        while(true){
            Iterator<Pion> it = this.linkedPion.iterator();
            while(it.hasNext()){
                Pion pionActeur = it.next();
                pionActeur.executerStrategie();
                this.sortLinkedPionTeam1();
                this.sortLinkedPionTeam2();

                if(this.getECTSTeam1() <= 0){
                    System.out.println("La team 2 possede la zone.");
                    this.status = StatusZone.Controlee;
                    break;
                } else if(this.getECTSTeam2() <= 0){
                    System.out.println("La team 1 possede la zone.");
                    this.status = StatusZone.Controlee;
                    break;
                }
            }

            if(this.status.equals(StatusZone.Controlee)){
                break;
            }
        }
        this.partie.verifierFinPartie();
    }

}
