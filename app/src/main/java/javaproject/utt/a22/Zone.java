package javaproject.utt.a22;

import java.util.*;

/**
 * Classe Zone.
 * Cette classe nous permettra de gérer les actions déroulées sur une zone.
 */
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
     * Attribut indiquant l'appélation de la zone.
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
     * Liste de pions de la zone (triée par initiative par ordre décroissant).
     */
    private LinkedList<Pion> linkedPion = new LinkedList<Pion>();

    /**
     * Liste de pions de la team 1 presents sur la zone (triée par ECTS par ordre croissant).
     */
    private LinkedList<Pion> linkedPionTeam1 = new LinkedList<Pion>();

    /**
     * Liste de pions de la team 2 présents sur la zone (triée par ECTS par ordre croissant).
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
     * @param partie Partie de la zone.
     * @param nomZone Nom de la zone.
     * @param label Label de la zone.
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
     * @return this.partie Partie où la zone est présente.
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
     * Les noms sont visibles dans l'énumération NomZone.
     * @return this.nom Nom de la zone.
     */
    public NomZone getNomZone(){
        return this.nom;
    }

    /**
     * Methode pour recuperer le label de la zone.
     * @return this.label Label de la zone.
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
     * @param status Status que nous voulons mettre à la zone.
     */
    public void setStatus(boolean status){
        this.estControlee = status;
    }

    /**
     * Methode pour recuperer le status de la zone.
     * @return this.status Status de la zone (controlée ou non).
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
     * @param pion Pion a ajouter à la zone.
     */
    public void addPion(Pion pion){
        //Ajout du pion dans la liste globale
        if(!this.linkedPion.contains(pion)){
            this.linkedPion.add(pion);
        }

        //Ajout du pion dans sa collection correspondante
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
     * Méthode pour retirer le pion de la zone.
     * Cela retirera le pion de "linkedPion" et "linkedPionTeam1" ou "linkedPionTeam2".
     * @param pion Pion a retiré de la zone.
     */
    public void removePion(Pion pion){
        //Verification si le pion appartient au joueur 1 ou au joueur 2
        if(pion.getJoueur().getNom().equals("Joueur 1")){
            if(this.linkedPionTeam1.contains(pion)){
                this.linkedPionTeam1.remove(pion);
            }
        } else if(pion.getJoueur().getNom().equals("Joueur 2")){
            if(this.linkedPionTeam2.contains(pion)){
                this.linkedPionTeam2.remove(pion);
            }
        }

        //Retrait du pion de la liste globale
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
     * Méthode pour trier la liste de la zone par initiative (plus grand au plus petit).
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
     * Méthode pour récuperer la liste de pion présent dans la zone.
     * @return this.linkedPion Liste globale des pions présents sur la zone.
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
     * Méthode pour récuperer les ECTS de la team 1 sur la zone.
     * @return ECTSTotal Vie totale de la team 1 sur la zone.
     */
    public int getECTSTeam1(){
        int ECTSTotal = 0;
        Pion pion = null;
        
        //Itération sur la liste de l'équipe 1
        Iterator<Pion> it = this.linkedPionTeam1.iterator();
        while(it.hasNext()){
            pion = it.next();
            ECTSTotal += pion.getECTS(); //On ajoute les ECTS de l'étudiant itéré
        }

        return ECTSTotal;
    }

    /**
     * Méthode pour récuperer la liste des pions de la team 1.
     * @return this.linkedPionTeam1 Liste de pion de la team 1 sur la zone.
     */
    public LinkedList<Pion> getLinkedListTeam1(){
        return this.linkedPionTeam1;
    }

    /**
     * Méthode pour trier la liste de la team 1 par ECTS (plus petit au plus grand).
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
     * Méthode pour recuperer les ECTS de la team 2 sur la zone.
     * @return ECTSTotal Vie totale de la team 2 sur la zone.
     */
    public int getECTSTeam2(){
        int ECTSTotal = 0;
        Pion pion = null;
        
        //Iteration sur la liste de l'équipe 2
        Iterator<Pion> it = this.linkedPionTeam2.iterator();
        while(it.hasNext()){
            pion = it.next();
            ECTSTotal += pion.getECTS(); //On ajoute les ECTS de l'étudiant itéré
        }

        return ECTSTotal;
    }

    /**
     * Méthode pour récuperer la liste des pions de la team 2.
     * @return this.linkedPionTeam2 Liste de pion de la team 2 sur la zone.
     */
    public LinkedList<Pion> getLinkedListTeam2(){
        return this.linkedPionTeam2;
    }

    /**
     * Méthode pour trier la liste de la team 2 par ECTS (plus petit au plus grand).
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
     * Méthode pour lancer une boucle de combat sur la zone.
     */
    public void combattre(){
        //Si les deux équipes sont présentes, le combat est lancé.
        if(this.getECTSTeam1() > 0 && this.getECTSTeam2() > 0){
            //Si la zone était controlée, nous la réinitialison
            if(this.estControlee){
                this.init();
            }

            //Affichage de debug
            PreSet.setTerminal();
            System.out.println("Combat zone : " + this.label);
            System.out.println(this.linkedPion);
            //PreSet.tempo(2500);

            //Si nous somme au premier tour, nous trions les listes
            if(this.isFirst){
                this.firstTour();
            }
            
            //Le premier pion de la liste global combat
            Pion pionActeur = this.linkedPion.getFirst();
            pionActeur.executerStrategie(); //Attaque sur le plus faible pion adverse ou soigne le plus faible pion allié

            //Le pion attaquant passe en dernier de la liste globale.
            linkedPion.removeFirst();
            linkedPion.addLast(pionActeur);

            //Vérification si un pion est mort.
            this.pionMort();
        }
    }

    /**
     * Méthode pour l'initialisation de la zone.
     */
    public void init(){
        this.estControlee = false; //La zone n'est plus controlée

        //La zone est retirée à tous les joueurs
        this.partie.getListJoueur().get(0).removeZoneControlee(this);
        this.partie.getListJoueur().get(1).removeZoneControlee(this);
    }

    /**
     * Méthode pour organiser les listes au premier tour de combat.
     * La liste globale sera triée par initiative (plus grand au plus petit).
     * Une liste par joueur qui seront triées par ECTS (plus petit au plus grand).
     */
    public void firstTour(){
        this.sortLinkedPion();
        this.sortLinkedPionTeam1();
        this.sortLinkedPionTeam2();
        isFirst = false;
    }

    /**
     * Méthode de vérification d'un pion mort.
     * Si un pion de la zone n'a plus de vie, il est retiré de la zone. Cela provoque une vérification d'une trêve.
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
     * Méthode de vérification de trêve de la zone.
     * Si un des deux joueurs n'a plus de pions sur la zone, la zone est obtenue par l'adversaire.
     */
    public void enTreve(){
        //Affichage de debug
        System.out.println("Est ce qu'il vas y avoir une treve ?");
        PreSet.tempo(1000);

        //Verification
        if(this.getECTSTeam1() == 0){ //Si le joueur 1 n'a plus de pions
            System.out.println("La zone est controlee par l'equipe 2 car les ects de l'equipe 1 sont : " + this.getECTSTeam1());
            this.estControlee = true; //La zon devient controlée
            this.partie.getListJoueur().get(1).addZoneControlee(this); //Le joueur adverse obtient la zone
            this.partie.setStatus(StatusPartie.Treve); //La partie est en mode trêve
        } else if(this.getECTSTeam2() == 0){ //Si le joueur 2 n'a plus de pions
            System.out.println("La zone est controlee par l'equipe 1 car les ects de l'equipe 2 sont : " + this.getECTSTeam2());
            this.estControlee = true; //La zone devient controlée
            this.partie.getListJoueur().get(0).addZoneControlee(this); //Le joueur adverse obtient la zone
            this.partie.setStatus(StatusPartie.Treve); //La partie est en mode trêve
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
