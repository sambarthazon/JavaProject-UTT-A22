package javaproject.utt.a22;

import java.util.*;

/**
 * Classe de la partie de notre jeu.
 */
public class Partie {

    /**
     * Status de la partie en cours.
     */
    private StatusPartie status;
    
    /**
     * Liste des joueurs dans la partie.
     */
    private ArrayList<Joueur> arrayJoueur = new ArrayList<Joueur>();

    /**
     * Liste des zone dans la partie.
     */
    private Set<Zone> setZone = new HashSet<Zone>();


    /**
     * Main
     * @param args
     */
    public static void main(String[] args){
        
    }



    //******************************************************//
    //                                                      //
    //                      Constructeur                    //
    //                                                      //
    //******************************************************//

    /**
     * Constructeur de la classe Partie.
     */
    public Partie(){
        this.status = StatusPartie.Parametrage;
    }



    //******************************************************//
    //                                                      //
    //                       Status                         //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour changer le status de la partie.
     * Les status sont visibles dans l'enumeration "StatusPartie".
     * Si les deux joueurs de la partie sont "Ready", le status de la partie est "Combat" ce qui lance les combats sur toutes les zones.
     * @param status
     */
    public void setStatus(StatusPartie status){
        this.status = status;
        if(this.status.equals(StatusPartie.Combat)){
            Zone zone = null;
            Iterator<Zone> it = this.setZone.iterator();
            while(it.hasNext()){
                zone = it.next();
                zone.start();
            }
        }
    }

    /**
     * Methode pour recuperer le status de la partie.
     * @return this.status
     */
    public StatusPartie getStatus(){
        return this.status;
    }



    //******************************************************//
    //                                                      //
    //                        Joueurs                       //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour ajouter un joueur a la partie.
     * Verification si le joueur est deja dans la liste de la partie.
     * @param joueur
     */
    public void addJoueur(Joueur joueur){
        if(!this.arrayJoueur.contains(joueur)){
            this.arrayJoueur.add(joueur);
        }
    }

    /**
     * Methode pour retirer un joueur du Set donc de la partie.
     * Verification si le joueur est dans la partie.
     * @param joueur
     */
    public void removeJoueur(Joueur joueur){
        if(this.arrayJoueur.contains(joueur)){
            this.arrayJoueur.remove(joueur);
        }
    }



    //******************************************************//
    //                                                      //
    //                        Zone                          //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour ajouter une zone a la partie.
     * @param zone
     */
    public void addZone(Zone zone){
        this.setZone.add(zone);
    }

    /**
     * Methode pour retirer une zone de la partie.
     * Verification que la zone a retirer est bien dans la liste.
     * @param zone
     */
    public void removeZone(Zone zone){
        if(this.setZone.contains(zone)){
            this.setZone.remove(zone);
        }
    }

    /**
     * Methode pour recuperer la liste de zones de la partie.
     * @return
     */
    public Set<Zone> getListZone(){
        return this.setZone;
    }



    //******************************************************//
    //                                                      //
    //                      Action partie                   //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour enlever un pion de la partie quand il est mort.
     * Ce pion sera enleve de toutes les collections ou il est present.
     * @param pion
     */
    public void pionMort(Pion pion){
        pion.getZone().removePion(pion);
        pion.getJoueur().removePion(pion);
    }


    /**
     * Methode pour verifier si la partie se termine ou non).
     * Si oui, annonce du gagnant et fin de la partie.
     * Sinon, lancement de parametrage de la "Treve".
     */
    public void verifierFinPartie(){
        int nbZoneControleeJoueur1 = this.arrayJoueur.get(0).getZoneControlee().size();
        int nbZoneControleeJoueur2 = this.arrayJoueur.get(1).getZoneControlee().size();

        if(nbZoneControleeJoueur1 >= 3){
            this.status = StatusPartie.Terminee;
            System.out.println("L'équipe " + this.arrayJoueur.get(0).getEquipe() + " a gagné.");
        } else if(nbZoneControleeJoueur2 >= 3){
            this.status = StatusPartie.Terminee;
            System.out.println("L'équipe " + this.arrayJoueur.get(1).getEquipe() + " a gagné.");
        } else{
            this.status = StatusPartie.Treve;
            this.parametrageTreve();
        }
    }




    //******************************************************//
    //                                                      //
    //                      Parametrage                     //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour le parametrage des pions en phase de "Parametrage".
     */
    public void parametrageDebut(){
        /**
         * Affichage a l'utilisateur des pions qu'il possede.
         * Demande a l'utilisateur s'il veut faire des modifications.
         * Possibilitees : changement de zone, attribution de stats, changement de strategie,
         * changement de status du pion, changement du status du joueur.
         */
    }


    /**
     * Methode pour le parametrage des pions en phase de "Treve".
     */
    public void parametrageTreve(){
        /**
         * Affichage a l'utilisateur des pions qu'il lui reste,
         * des zones qu'il a controle, des ECTS de son equipe sur les zones non controlees.
         * Demande a l'utilisateur s'il veut faire des modifications.
         * Possibilitees : changement de zone, attribution de stats, changement de strategie,
         * changement de status du pion, changement du status du joueur.
         */
    }


    /**
     * Methode pour permetre au joueur de selectionner le pion a parametrer.
     */
    public void selectionPion(){
        /**
         * Demande a l'utilisateur quel pion il veut parametrer.
         * Il devra renseigner son nom exact afin de selectionner le bon pion dans la liste du joueur.
         * Une fois le pion obtenu, appel de la methode "parametragePion".
         */
    }


    /**
     * Methode pour le parametrage du pion.
     * @param pion
     */
    public void parametragePion(Pion pion){
        /**
         * Demande a l'utilisateur quel parametrage il veut faire sur le pion qu'il a choisi.
         * Possibilites : zone, strategie, dexterite, constitution, force, initiative resistance.
         */
    }
}
