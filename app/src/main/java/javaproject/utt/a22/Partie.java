package javaproject.utt.a22;

import java.util.*;

/**
 * Classe de la partie de notre jeu.
 */
public class Partie {

    public static final String NOM_JOUEUR1 = "Joueur 1";
    public static final String NOM_JOUEUR2 = "Joueur 2";

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
        //Creation de la partie.
        Partie partie = new Partie();

        //Creation des joueurs.
        Joueur joueur1 = new Joueur(partie, NOM_JOUEUR1);
        Joueur joueur2 = new Joueur(partie, NOM_JOUEUR2);

        //Creation des "Etudiants" alias "Ei".
        for(int i = 1; i < 16; i++){
            Pion etudiantJoueur1 = new Etudiant(joueur1, "E"+i);
            Pion etudiantJoueur2 = new Etudiant(joueur2, "E"+i);
        }

        //Creation des "Etudiants d'Elite" alias "EIi".
        for(int i = 1; i < 5; i++){
            Pion eliteJoueur1 = new Elite(joueur1, "EI"+i);
            Pion eliteJoueur2 = new Elite(joueur2, "EI"+i);
        }

        //Creation des "Maitre du Gobi" alias "M".
        Pion maitreJoueur1 = new Maitre(joueur1, "M");
        Pion maitreJoueur2 = new Maitre(joueur2, "M");

        //Creation des zones
        Zone zoneBU = new Zone(partie, NomZone.BU);
        Zone zoneBDE = new Zone(partie, NomZone.BDE);
        Zone zoneQA = new Zone(partie, NomZone.QuartierAdmin);
        Zone zoneHI = new Zone(partie, NomZone.HalleIndus);
        Zone zoneHS = new Zone(partie, NomZone.HalleSport);

        System.out.println("Partie.");

        Iterator<Joueur> itJoueur = partie.arrayJoueur.iterator();
        while(itJoueur.hasNext()){
            Joueur joueurActif = itJoueur.next();
            partie.phaseParametrage(joueurActif);
        }
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
            this.phaseTreve();
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
    public void phaseParametrage(Joueur joueur){
        /**
         * Affichage a l'utilisateur des pions qu'il possede.
         * Demande a l'utilisateur s'il veut faire des modifications.
         * Possibilitees : changement de zone, attribution de stats, changement de strategie,
         * changement de status du pion, changement du status du joueur.
         */
        while(joueur.getStatus().equals(StatusJoueur.Preparation)){
            System.out.println(joueur.getListPion());
            Scanner sc = new Scanner(System.in);
            System.out.println("Voici vos pions, voulez-vous faire des modifications ?\nTapez Y pour Oui et N pour Non");
            String input = sc.nextLine();
            // if(input.equals("Y")){
            //     this.selectionPion(joueur);
            // } else if(input.equals("N")){
            //     this.parametrageStatusJoueur(joueur);
            // } else{
            //     System.out.println("Invalide");
            // }
            /*switch(input){
                case "Y":
                    this.selectionPion(joueur);
                    break;
                case "N":
                    this.parametrageStatusJoueur(joueur);
                    break;
                default:
                    break;
            }*/
            sc.close();
        }
        
    }


    /**
     * Methode pour le parametrage des pions en phase de "Treve".
     */
    public void phaseTreve(){
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
    public void selectionPion(Joueur joueur){
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
    public void parametragePion(Joueur joueur, Pion pion){
        /**
         * Demande a l'utilisateur quel parametrage il veut faire sur le pion qu'il a choisi.
         * Possibilites : zone, strategie, dexterite, constitution, force, initiative resistance.
         */
    }

    /**
     * 
     * @param pion
     */
    public void parametrageDexterite(Joueur joueur, Pion pion){
        /**
         * Demande a l'utilisateur combien de dexterite il veut ajouter au pion.
         */
    }

    public void parametrageForce(Joueur joueur, Pion pion){
        /**
         * Demande a l'utilisateur combien de force il veut ajouter au pion.
         */
    }

    public void parametrageResistance(Joueur joueur, Pion pion){
        /**
         * Demande a l'utilisateur combien de resistance il veut ajouter au pion.
         */
    }

    public void parametrageConstitution(Joueur joueur, Pion pion){
        /**
         * Demande a l'utilisateur combien de constitution il veut ajouter au pion.
         */
    }

    public void parametrageInitiative(Joueur joueur, Pion pion){
        /**
         * Demande a l'utilisateur combien d'initiative il veut ajouter au pion.
         */
    }

    public void parametrageStatusPion(Joueur joueur, Pion pion){
        /**
         * Demande a l'utilisateur quel status il veut mettre au pion.
         */
    }

    public void parametrageStrategie(Joueur joueur, Pion pion){
        /**
         * Demande a l'utilisateur quel strategie il veut mettre au pion.
         */
    }

    public void parametrageZone(Joueur joueur, Pion pion){
        /**
         * Demande a l'utilisateur la zone d'affectation du pion.
         */
    }

    public StatusJoueur parametrageStatusJoueur(Joueur joueur){
        /**
         * Check si tous les pre-requis sont present pour le mettre "Ready".
         * Est-ce qu'il a 15 combattants et 5 reservistes ?
         * Est-ce que les 15 combattants ont une zone ?
         * Est ce que les 15 combattants ont une strategie ?
         */
        return joueur.getStatus();
    }
}
