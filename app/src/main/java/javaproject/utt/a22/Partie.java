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
        Zone zoneBU = new Zone(partie, NomZone.BU, "BU");
        Zone zoneBDE = new Zone(partie, NomZone.BDE, "BDE");
        Zone zoneQA = new Zone(partie, NomZone.QuartierAdmin, "QA");
        Zone zoneHI = new Zone(partie, NomZone.HalleIndus, "HI");
        Zone zoneHS = new Zone(partie, NomZone.HalleSport, "HS");

        /**
         * Parametrage des pions par le joueur.
         */
        Iterator<Joueur> itJoueur = partie.arrayJoueur.iterator();
        while(itJoueur.hasNext()){
            Joueur joueurActif = itJoueur.next();
            PreSet.clearConsole();
            System.out.println("Le joueur : " + joueurActif.getNom() + " va pouvoir commencer le paramétrage.");
            partie.selectionEquipe(joueurActif, new Scanner(System.in));
        }


        /**
         * Lancement de la partie.
         */
        while(!partie.getStatus().equals(StatusPartie.Terminee)){
            partie.lancement();
            
            partie.verifierFinPartie();
            if(partie.getStatus().equals(StatusPartie.Treve)){
                partie.phaseTreve(new Scanner(System.in));
            }
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

    /**
     * Méthode pour récupérer la liste de joueur de la partie.
     * @return this.arrayJoueur
     */
    public ArrayList<Joueur> getListJoueur(){
        return this.arrayJoueur;
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
    public void phaseTreve(Scanner sc){
        /**
         * Affichage a l'utilisateur des pions qu'il lui reste,
         * des zones qu'il a controle, des ECTS de son equipe sur les zones non controlees.
         * Demande a l'utilisateur s'il veut faire des modifications.
         * Possibilitees : changement de zone, attribution de stats, changement de strategie,
         * changement de status du pion, changement du status du joueur.
         */
        Joueur joueur = null;

        Iterator<Joueur> it = this.arrayJoueur.iterator();
        while(it.hasNext()){
            PreSet.clearConsole();
            joueur = it.next();
            System.out.println(joueur.getListPion());
            this.selectionPion(joueur, new Scanner(System.in));
        }
    }


    /**
     * Methode pour que le joueur choisisse une equipe.
     * Les equipes sont disponibles dans l'enumeration NomEquipe.
     * @param joueur
     */
    public void selectionEquipe(Joueur joueur, Scanner sc){
        String equipe = " ";

        while(true) {
            System.out.print("Equipes disponibles : A2I(A2I), GI(GI), GM(GM), ISI(ISI), MM(MM), MTE(MTE), RT(RT) : ");
            equipe = sc.nextLine();
            if (equipe.equals("A2I") || equipe.equals("GI") || equipe.equals("GM") || equipe.equals("ISI") ||
                equipe.equals("MM") || equipe.equals("MTE") || equipe.equals("RT")){
                break;
            }
        }

        switch (equipe) {
            case "A2I":
                joueur.setEquipe(NomEquipe.A2I);
                break;
            case "GI":
                joueur.setEquipe(NomEquipe.GI);
                break;
            case "GM":
                joueur.setEquipe(NomEquipe.GM);
                break;
            case "ISI":
                joueur.setEquipe(NomEquipe.ISI);
                break;
            case "MM":
                joueur.setEquipe(NomEquipe.MM);
                break;
            case "MTE":
                joueur.setEquipe(NomEquipe.MTE);
                break;
            case "RT":
                joueur.setEquipe(NomEquipe.RT);
                break;
            default:
                System.out.println("Choix d'equipe incorrect");
                break;
        }
            
        this.phaseParametrage(joueur, new Scanner(System.in));
    }


    /**
     * Methode pour permetre au joueur de selectionner le pion a parametrer.
     */
    public void selectionPion(Joueur joueur, Scanner sc){
        Pion pion = null;

        while(true){
            PreSet.setTerminal();
            System.out.println(joueur.getListPion());

            System.out.print("Quel pion voulez-vous modifier ? (Entrez son nom ou \'0\' pour quitter) : ");
            String input = sc.nextLine();

            //Si le joueur decide de quitter.
            if(input.equals("0")){
                if(this.parametrageStatusJoueur(joueur)){
                    break;
                } else{
                    System.out.println("Vous n'avez pas configurer correctement vos pions.");
                    PreSet.tempo(2500);
                }
            } else{
                pion = joueur.getListPion().get(this.findPion(joueur, input));
                if(!pion.equals(null)){
                    if(pion.getZone() != null){
                        if(pion.getZone().getStatus()){
                            this.parametragePionTreve(joueur, pion, new Scanner(System.in));
                        } else{
                            System.out.println("La zone de ce pion n'est pas en trêve.");
                            PreSet.tempo(1500);
                        }
                    } else{
                        this.parametragePion(joueur, pion, new Scanner(System.in));
                    }
                } else{
                    System.out.println("Ce pion n'existe pas.");
                    PreSet.tempo(1500);
                }
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
     * @param sc
     */
    public void parametragePion(Joueur joueur, Pion pion, Scanner sc){
        while(true){
            PreSet.setTerminal();
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
                    if(!pion.getStatus().equals(StatusPion.Combattant)){
                        System.out.println("Votre pion doit avoir le status \'Combattant\' pour avoir une zone.");
                        PreSet.tempo(2500);
                    } else{
                        this.parametrageZone(pion, new Scanner(System.in));
                    }     
                    break;
                case 0:
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
     * @param joueur
     * @param pion
     * @param sc
     */
    public void parametragePionTreve(Joueur joueur, Pion pion, Scanner sc){
        while(true){
            PreSet.setTerminal();
            System.out.println(pion);
            System.out.println("Point restant a distribuer : " + joueur.getPoint());

            System.out.print("Modification de Status(1), Strategie(2), Zone(3),  Quitter(0) : ");
            int choix = sc.nextInt();

            switch(choix){
                case 1:
                    this.parametrageStatusPion(pion, new Scanner(System.in));
                    break;
                case 2:
                    this.parametrageStrategie(pion, new Scanner(System.in));
                    break;
                case 3:
                    if(!pion.getStatus().equals(StatusPion.Combattant)){
                        System.out.println("Votre pion doit avoir le status \'Combattant\' pour avoir une zone.");
                        PreSet.tempo(2500);
                    } else{
                        if(pion.getZone() != null){
                            if(pion.getZone().getStatus()){
                                this.parametrageZone(pion, new Scanner(System.in));
                            } else{
                                System.out.println("La zone de votre pion n'est pas controlee.");
                            }
                        } else{
                            this.parametrageZone(pion, new Scanner(System.in));
                        }
                    }    
                    break;
                case 0:
                    break;
                default:
                    break;
            }

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
                break;
            case 2:
                pion.setStatus(StatusPion.Reserviste);
                if(pion.getZone() != null){
                    pion.setZone(null);
                }
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
            System.out.print("Strategie : Offensif(1), Defensif(2), Aleatoire(3), Preferentielle(4)" +
                                ", Doc(9), Quitter(0) : ");
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
     * 
     * @param pion
     */
    public void parametrageZone(Pion pion, Scanner sc){
        while(true){
            String label;

            if(pion.getJoueur().getNom().equals(NOM_JOUEUR1)){
                System.out.println("ECTS de votre equipe sur la BU : " + this.arrayZone.get(this.findZone("BU")).getECTSTeam1() +
                                "\nECTS de votre equipe sur le BDE : " + this.arrayZone.get(this.findZone("BDE")).getECTSTeam1() +
                                "\nECTS de votre equipe sur le QA : " + this.arrayZone.get(this.findZone("QA")).getECTSTeam1() +
                                "\nECTS de votre equipe sur la HI : " + this.arrayZone.get(this.findZone("HI")).getECTSTeam1() +
                                "\nECTS de votre equipe sur la HS : " + this.arrayZone.get(this.findZone("HS")).getECTSTeam1());
            } else if(pion.getJoueur().getNom().equals(NOM_JOUEUR2)){
                System.out.println("ECTS de votre equipe sur la BU : " + this.arrayZone.get(this.findZone("BU")).getECTSTeam2() +
                                "\nECTS de votre equipe sur le BDE : " + this.arrayZone.get(this.findZone("BDE")).getECTSTeam2() +
                                "\nECTS de votre equipe sur le QA : " + this.arrayZone.get(this.findZone("QA")).getECTSTeam2() +
                                "\nECTS de votre equipe sur la HI : " + this.arrayZone.get(this.findZone("HI")).getECTSTeam2() +
                                "\nECTS de votre equipe sur la HS : " + this.arrayZone.get(this.findZone("HS")).getECTSTeam2());
            }

            while(true){
                System.out.print("Quelle zone voulez-vous attribuer ? BU(BU), BDE(BDE), Quartier Admin(QA), Halle Indus(HI), Halle Sportive(HS), Quitter(0) : ");
                label = sc.nextLine();

                if(label.equals("BU") || label.equals("BDE") || label.equals("QA") ||
                label.equals("HI") || label.equals("HS") || label.equals("0")){
                    break;
                }
            }

            if(label.equals("0")){
                break;
            }

            Zone zone = this.arrayZone.get(this.findZone(label));
            System.out.println(zone);
            if(!zone.equals(null)){
                pion.setZone(zone);
                break;
            } else{
                System.out.println("Cette zone n'existe pas.");
            }
        } 
    }


    public int findZone(String str){
        int index = 0;
        Zone zone = null;

        Iterator<Zone> it = this.arrayZone.iterator();
        while(it.hasNext()){
            zone = it.next();
            if(zone.getLabel().equals(str)){
                index = this.arrayZone.indexOf(zone);
                break;
            }
        }
        return index;
    }

    /**
     * Methode pour modifier le status du joueur.
     * Verification que le joueur ai bien au moins un pion sur chaque zone.
     * @param joueur
     * @return
     */
    public boolean parametrageStatusJoueur(Joueur joueur){
        boolean isOk = false;
        boolean zoneBU_OK = false, zoneBDE_OK = false, zoneQA_OK = false, zoneHI_OK = false, zoneHS_OK = false;
        int nbPionWithZone = 0;
        Pion pion = null;

        Iterator<Pion> it = joueur.getListPion().iterator();
        while(it.hasNext()){
            pion = it.next();
            if(pion.getZone() != null){
                if(pion.getZone().getLabel().equals("BU")){
                    zoneBU_OK = true;
                    nbPionWithZone ++;
                } else if(pion.getZone().getLabel().equals("BDE")){
                    zoneBDE_OK = true;
                    nbPionWithZone ++;
                } else if(pion.getZone().getLabel().equals("QA")){
                    zoneQA_OK = true;
                    nbPionWithZone ++;
                } else if(pion.getZone().getLabel().equals("HI")){
                    zoneHI_OK = true;
                    nbPionWithZone ++;
                } else if(pion.getZone().getLabel().equals("HS")){
                    zoneHS_OK = true;
                    nbPionWithZone ++;
                } else{

                }
            }

            //Soit une des deux conditions au cas ou le joueur a moins de 5 pions.
            if((zoneBU_OK && zoneBDE_OK && zoneQA_OK && zoneHI_OK && zoneHS_OK) || (nbPionWithZone == joueur.getListPion().size())){
                System.out.println(nbPionWithZone + ", " + joueur.getListPion().size());
                joueur.setStatus(StatusJoueur.Ready);
                isOk = true;
                break;
            }
        }
        return isOk;
    }



    //******************************************************//
    //                                                      //
    //                      Deroulement                     //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour lancer le combat sur les zones.
     */
    public void lancement(){
        Zone zone = null;
        Joueur joueur = null;

        while(!this.getStatus().equals(StatusPartie.Terminee)){
            this.setStatus(StatusPartie.Combat);

            while(this.getStatus().equals(StatusPartie.Combat)){
                Iterator<Zone> itZone = this.arrayZone.iterator();
                while(itZone.hasNext()){
                    if(this.getStatus().equals(StatusPartie.Combat)){
                        zone = itZone.next();
                        zone.combattre();
                    } else{
                        break;
                    }
                }
            }

            int nbZoneControleeJoueur1 = this.arrayJoueur.get(0).getZoneControlee().size();
            int nbZoneControleeJoueur2 = this.arrayJoueur.get(1).getZoneControlee().size();

            if(nbZoneControleeJoueur1 >= 3){
                this.status = StatusPartie.Terminee;
                System.out.println("L'équipe " + this.arrayJoueur.get(0).getEquipe() + " a gagné.");
                break;
            } else if(nbZoneControleeJoueur2 >= 3){
                this.status = StatusPartie.Terminee;
                System.out.println("L'équipe " + this.arrayJoueur.get(1).getEquipe() + " a gagné.");
                break;
            } else{
                this.status = StatusPartie.Treve;
            }
            System.out.println("Le " + this.arrayJoueur.get(0).getNom() + " possede " + this.arrayJoueur.get(0).getZoneControlee());
            System.out.println("Le " + this.arrayJoueur.get(1).getNom() + " possede " + this.arrayJoueur.get(1).getZoneControlee());
            PreSet.tempo(5000);


            Iterator<Joueur> itJoueur = this.arrayJoueur.iterator();
            while(itJoueur.hasNext()){
                joueur = itJoueur.next();
                this.selectionPion(joueur, new Scanner(System.in));
            }
        }

    }

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
        }
    }
}
