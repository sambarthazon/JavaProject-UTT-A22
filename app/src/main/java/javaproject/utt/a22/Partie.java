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
    private ArrayList<Zone> arrayZone = new ArrayList<Zone>();


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

        Iterator<Joueur> itJoueur = partie.arrayJoueur.iterator();
        while(itJoueur.hasNext()){
            Joueur joueurActif = itJoueur.next();
            partie.phaseParametrage(joueurActif, new Scanner(System.in));
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
            Iterator<Zone> it = this.arrayZone.iterator();
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
        if(!this.arrayZone.contains(zone)){
            this.arrayZone.add(zone);
        }
    }

    /**
     * Methode pour retirer une zone de la partie.
     * Verification que la zone a retirer est bien dans la liste.
     * @param zone
     */
    public void removeZone(Zone zone){
        if(this.arrayZone.contains(zone)){
            this.arrayZone.remove(zone);
        }
    }

    /**
     * Methode pour recuperer la liste de zones de la partie.
     * @return
     */
    public ArrayList<Zone> getListZone(){
        return this.arrayZone;
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
    public void phaseParametrage(Joueur joueur, Scanner sc){
        while(joueur.getStatus().equals(StatusJoueur.Preparation)){
            System.out.println(joueur.getListPion());
            System.out.print("Voulez-vous faire des modifications sur vos pions ?\nOui(Y), Non(N) : ");
            String input = sc.nextLine();

            if(input.equals("Y")){
                this.selectionPion(joueur, new Scanner(System.in));
            } else if(input.equals("N")){
                this.parametrageStatusJoueur(joueur);
            } else{
                System.out.println("Invalide");
            }
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
    public void selectionPion(Joueur joueur, Scanner sc){
        Pion pion = null;

        while(true){
            PreSet.clearConsole();
            System.out.println(joueur.getListPion());

            System.out.print("Quel pion voulez-vous modifier ? (Entrez son nom ou \'0\' pour quitter) : ");
            String input = sc.nextLine();

            //Si le joueur decide de quitter.
            if(input.equals("0")){
                break;
            }

            pion = joueur.getListPion().get(this.findPion(joueur, input));
            if(!pion.equals(null)){
                System.out.println("Points qu'il vous reste a attribuer : " + joueur.getPoint());
                this.parametragePion(joueur, pion, new Scanner(System.in));
            } else{
                System.out.println("Ce pion n'existe pas.");
            }
        }
    }

    /**
     * 
     * @param joueur
     * @param str
     * @return index
     */
    public int findPion(Joueur joueur, String str){
        int index = 0;
        Pion pion = null;

        Iterator<Pion> it = joueur.getListPion().iterator();
        while(it.hasNext()){
            pion = it.next();
            if(pion.getNom().equals(str)){
                index = joueur.getListPion().indexOf(pion);
                break;
            }
        }
        return index;
    }

    /**
     * Methode pour le parametrage du pion.
     * @param joueur
     * @param pion
     */
    public void parametragePion(Joueur joueur, Pion pion, Scanner sc){
        while(true){
            PreSet.clearConsole();
            System.out.println(pion);
            System.out.println("Point restant a distribuer : " + joueur.getPoint());

            System.out.print("Modification de Dexterite(1), Force(2), Resistance(3), Constitution(4), Initiative(5), " +
                                "Status(6), Strategie(7), Zone(8),  Quitter(0) : ");
            int choix = sc.nextInt();
            
            switch(choix){
                case 1:
                    this.parametrageDexterite(pion, new Scanner(System.in));
                    break;
                case 2:
                    this.parametrageForce(pion, new Scanner(System.in));
                    break;
                case 3:
                    this.parametrageResistance(pion, new Scanner(System.in));
                    break;
                case 4:
                    this.parametrageConstitution(pion, new Scanner(System.in));
                    break;
                case 5:
                    this.parametrageInitiative(pion, new Scanner(System.in));
                    break;
                case 6:
                    this.parametrageStatusPion(pion, new Scanner(System.in));
                    break;
                case 7:
                    this.parametrageStrategie(pion, new Scanner(System.in));
                    break;
                case 8:
                    this.parametrageZone(pion);
                    break;
                case 0:
                    choix = 0;
                    break;
                default:
                    break;
            }
            //Si le joueur a fini le parametrage de ce pion.
            if(choix == 0){
                break;
            }
        }
    }

    /**
     * 
     * @param pion
     */
    public void parametrageDexterite(Pion pion, Scanner sc){
        int dexterite = 0;

        System.out.println("Dexterite actuelle de votre pion : " + pion.getDexterite());
        while(true){
            System.out.print("Combien de points de dexterite voulez-vous lui mettre ?" +
                            "(Valeur entre " + pion.getMinDexterite() + " et " + pion.getMaxDexterite() + ") : ");
            dexterite = sc.nextInt();

            if(dexterite >= pion.getMinDexterite() && dexterite <= pion.getMaxDexterite()){
                break;
            }
        }

        pion.setDexterite(dexterite);
    }

    /**
     * 
     * @param pion
     */
    public void parametrageForce(Pion pion, Scanner sc){
        int force = 0;

        System.out.println("Force actuelle de votre pion : " + pion.getForce());
        while(true){
            System.out.print("Combien de points de force voulez-vous lui mettre ?" +
                            "(Valeur entre " + pion.getMinForce() + " et " + pion.getMaxForce() + ") : ");
            force = sc.nextInt();

            if(force >= pion.getMinForce() && force <= pion.getMaxForce()){
                break;
            }
        }

        pion.setForce(force);
    }

    /**
     * 
     * @param pion
     */
    public void parametrageResistance(Pion pion, Scanner sc){
        int resistance = 0;

        System.out.println("Resistance actuelle de votre pion : " + pion.getResistance());
        while(true){
            System.out.print("Combien de points de resistance voulez-vous lui mettre ?" +
                            "(Valeur entre " + pion.getMinResistance() + " et " + pion.getMaxResistance() + ") : ");
            resistance = sc.nextInt();

            if(resistance >= pion.getMinResistance() && resistance <= pion.getMaxResistance()){
                break;
            }
        }

        pion.setResistance(resistance);
    }

    /**
     * 
     * @param pion
     */
    public void parametrageConstitution(Pion pion, Scanner sc){
        int constitution = 0;

        System.out.println("Constitution actuelle de votre pion : " + pion.getConstitution());
        while(true){
            System.out.print("Combien de points de constitution voulez-vous lui mettre ?" +
                            "(Valeur entre " + pion.getMinConstitution() + " et " + pion.getMaxConstitution() + ") : ");
            constitution = sc.nextInt();

            if(constitution >= pion.getMinConstitution() && constitution <= pion.getMaxConstitution()){
                break;
            }
        }

        pion.setConstitution(constitution);
    }

    /**
     * 
     * @param pion
     */
    public void parametrageInitiative(Pion pion, Scanner sc){
        int inititative = 0;

        System.out.println("Inititative actuelle de votre pion : " + pion.getInitiative());
        while(true){
            System.out.print("Combien de points d'initiative voulez-vous lui mettre ?" +
                            "(Valeur entre " + pion.getMinInitiative() + " et " + pion.getMaxInitiative() + ") : ");
            inititative = sc.nextInt();

            if(inititative >= pion.getMinInitiative() && inititative <= pion.getMaxInitiative()){
                break;
            }
        }

        pion.setInitiative(inititative);
    }

    /**
     * 
     * @param pion
     */
    public void parametrageStatusPion(Pion pion, Scanner sc){
        int status = 0;

        System.out.println("Status actuel de votre pion : " + pion.getStatus());
        while(true){
            System.out.print("Quel status voulez-vous lui mettre ? Combattant(1), Reserviste(2) : ");
            status = sc.nextInt();

            if(status == 1 || status == 2){
                break;
            }
        }

        switch(status){
            case 1:
                pion.setStatus(StatusPion.Combattant);
                if(pion.getStatus().equals(StatusPion.Indefini)){
                    System.out.println("Vous avez trop de combattants.");
                }
                break;
            case 2:
                pion.setStatus(StatusPion.Reserviste);
                break;
            default:
                break;
        }
    }

    /**
     * 
     * @param pion
     */
    public void parametrageStrategie(Pion pion, Scanner sc){
        int strategie = 0;

        System.out.println("Strategie actuelle de votre pion : " + pion.getStrategie());
        while(true){
            System.out.print("Strategie : Offensif(1), Defensif(2), Aleatoire(3), Preferentielle(4)\n" +
                                "Aide(9), Quitter(0) : ");
            strategie = sc.nextInt();

            if(strategie >= 1 && strategie <= 4 || strategie == 9 || strategie == 0){
                break;
            }
        }

        switch(strategie){
            case 1:
                pion.setStrategie(new Offensif());
                break;
            case 2:
                pion.setStrategie(new Defensif());
                break;
            case 3:
                pion.setStrategie(new Aleatoire());
                break;
            case 4:
                pion.setStrategie(new Preferentielle());
                break;
            case 9:
                System.out.println("Strategie \'Offensif\' : Votre pion attaquera l'adversaire tous les tours." +
                                    "\nStrategie \'Defensif\' : Votre pion soignera vos pions tous les tours." +
                                    "\nStrategie \'Aleatoire\' : Votre pion attaquera ou soignera de maniere aleatoire." +
                                    "\nStrategie \'Preferentielle\' : Votre pion attaquera si votre equipe a plus de points de vie " +
                                    "ou soignera si votre equipe a moins de point de vie que l'adversaire.");
                break;
            case 0:
                break;
            default:
                break;
        }
    }

    /**
     * A REVOIR
     * @param pion
     */
    public void parametrageZone(Pion pion){
        /**
         * Demande a l'utilisateur la zone d'affectation du pion.
         */
    }

    /**
     * 
     * @param joueur
     * @return
     */
    public void parametrageStatusJoueur(Joueur joueur){
        Pion pion = null;
        ArrayList<Pion> pionsCombattant = new ArrayList<>();
        ArrayList<Pion> pionsStrategie = new ArrayList<>();
        ArrayList<Pion> pionsZone = new ArrayList<>();

        Iterator<Pion> itForStatus = joueur.getListPion().iterator();
        while(itForStatus.hasNext()){
            pion = itForStatus.next();
            if(pion.getStatus().equals(StatusPion.Combattant)){
                if(!pionsCombattant.contains(pion)){
                    pionsCombattant.add(pion);
                }
            }
        }

        Iterator<Pion> itForStrategieZone = joueur.getListPion().iterator();
        while(itForStrategieZone.hasNext()){
            pion = itForStrategieZone.next();

            if(!pion.getStrategie().equals(null)){
                if(!pionsStrategie.contains(pion)){
                    pionsStrategie.add(pion);
                }
            }
            if(!pion.getZone().equals(null)){
                if(!pionsZone.contains(pion)){
                    pionsZone.add(pion);
                }
            }
        }

        if(pionsCombattant.size() == pionsStrategie.size() && pionsCombattant.size() == pionsZone.size()){
            joueur.setStatus(StatusJoueur.Ready);
        } else{
            System.out.println("Il vous manque des parametrages a faire.");
        }
    }
}
