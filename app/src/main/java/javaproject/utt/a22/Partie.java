package javaproject.utt.a22;

import java.util.*;

/**
 * Classe de la partie de notre jeu.
 */
public class Partie {

    /**
     * Nom du joueur 1.
     */
    public static final String NOM_JOUEUR1 = "Joueur 1";
    /**
     * Nom du joueur 2.
     */
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
            new Etudiant(joueur1, "E"+i);
            new Etudiant(joueur2, "E"+i);
        }

        //Creation des "Etudiants d'Elite" alias "EIi".
        for(int i = 1; i < 5; i++){
            new Elite(joueur1, "EI"+i);
            new Elite(joueur2, "EI"+i);
        }

        //Creation des "Maitre du Gobi" alias "M".
        new Maitre(joueur1, "M");
        new Maitre(joueur2, "M");

        //Creation des zones
        new Zone(partie, NomZone.BU, "BU");
        new Zone(partie, NomZone.BDE, "BDE");
        new Zone(partie, NomZone.QuartierAdmin, "QA");
        new Zone(partie, NomZone.HalleIndus, "HI");
        new Zone(partie, NomZone.HalleSport, "HS");

        /**
         * Parametrage des pions par le joueur.
         */
        Iterator<Joueur> itJoueur = partie.arrayJoueur.iterator();
        while(itJoueur.hasNext()){
            Joueur joueurActif = itJoueur.next();
            PreSet.clearConsole();
            System.out.println("Le joueur : " + joueurActif.getNom() + " va pouvoir commencer le param??trage.");
            partie.selectionEquipe(joueurActif, new Scanner(System.in));
        }

        /**
         * Lancement de la partie.
         */
        partie.lancement();
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
     * M??thode pour changer le status de la partie.
     * Les status sont visibles dans l'enumeration "StatusPartie".
     * @param status Status appliqu?? au joueur.
     */
    public void setStatus(StatusPartie status){
        this.status = status;
    }

    /**
     * M??thode pour r??cuperer le status de la partie.
     * @return this.status Status du joueur
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
     * M??thode pour ajouter un joueur ?? la partie.
     * V??rification si le joueur est d??j?? dans la liste de la partie.
     * @param joueur Joueur que nous ajoutons ?? la partie.
     */
    public void addJoueur(Joueur joueur){
        if(!this.arrayJoueur.contains(joueur)){
            this.arrayJoueur.add(joueur);
        }
    }

    /**
     * M??thode pour retirer un joueur de la partie.
     * Verification si le joueur est dans la partie.
     * @param joueur Joueur que nous voulons retirer de la partie.
     */
    public void removeJoueur(Joueur joueur){
        if(this.arrayJoueur.contains(joueur)){
            this.arrayJoueur.remove(joueur);
        }
    }

    /**
     * M??thode pour r??cup??rer la liste de joueur de la partie.
     * @return this.arrayJoueur Liste des joueurs de la partie.
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
     * M??thode pour ajouter une zone ?? la partie.
     * @param zone Zone ?? ajouter ?? la partie.
     */
    public void addZone(Zone zone){
        if(!this.arrayZone.contains(zone)){
            this.arrayZone.add(zone);
        }
    }

    /**
     * M??thode pour retirer une zone de la partie.
     * V??rification que la zone ?? retirer est bien dans la liste.
     * @param zone Zone ?? retirer de la partie.
     */
    public void removeZone(Zone zone){
        if(this.arrayZone.contains(zone)){
            this.arrayZone.remove(zone);
        }
    }

    /**
     * M??thode pour recuperer la liste de zones de la partie.
     * @return this.arrayZone Liste des zones de la partie.
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
     * M??thode pour le param??trage des pions en phase de "Param??trage".
     */
    public void phaseParametrage(Joueur joueur, Scanner sc){
        while(joueur.getStatus().equals(StatusJoueur.Preparation)){
            //Affichage des pions du joueur
            System.out.println(joueur.getListPion());

            //Demande utilisateur
            System.out.print("Voulez-vous faire des modifications sur vos pions ?\nOui(Y), Non(N) : ");
            String input = sc.nextLine();

            //Action d?? ?? la saisi utilisateur
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
     * M??thode pour que le joueur choisisse une ??quipe.
     * Les ??quipes sont disponibles dans l'??numeration NomEquipe.
     * @param joueur Joueur faisant le param??trage.
     * @param sc Scanner pour demande utilisateur.
     */
    public void selectionEquipe(Joueur joueur, Scanner sc){
        String equipe = " ";

        while(true) {
            //Demande de l'??quipe au joueur.
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
            
        //Phase de param??trage des pions.
        this.phaseParametrage(joueur, new Scanner(System.in));
    }


    /**
     * M??thode pour permettre au joueur de s??lectionner le pion a param??trer.
     * @param joueur Joueur faisant le param??trage.
     * @param sc Scanner pour demande utilisateur.
     */
    public void selectionPion(Joueur joueur, Scanner sc){
        Pion pion = null;

        while(true){
            //Clear du terminal
            PreSet.setTerminal();

            //Affichage des pions du joueur
            System.out.println(joueur.getListPion());

            //Demande utilisateur
            System.out.print("Quel pion voulez-vous modifier ? (Entrez son nom ou \'0\' pour quitter) : ");
            String input = sc.nextLine();

            //Si le joueur decide de quitter
            if(input.equals("0")){
                if(this.parametrageStatusJoueur(joueur)){
                    break;
                } else{
                    System.out.println("Vous n'avez pas configurer correctement vos pions.");
                    PreSet.tempo(2500);
                }
            } else{
                //R??cup??ration du pion demand?? par le joueur
                pion = joueur.getListPion().get(this.findPion(joueur, input));
                if(!pion.equals(null)){ //S'il a trouv??
                    if(pion.getZone() != null){ //Si la zone du pion n'est pas nulle
                        if(pion.getZone().getStatus()){ //Si sa zone est en tr??ve
                            //Appel de param??trage du pion en mode tr??ve.
                            this.parametragePionTreve(joueur, pion, new Scanner(System.in));
                        } else{ //Si la zone n'est pas en tr??ve.
                            System.out.println("La zone de ce pion n'est pas en tr??ve.");
                            PreSet.tempo(1500);
                        }
                    } else{
                        //Appel de param??trage pion normal
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
     * M??thode pour r??cup??rer le pion demand?? par l'utilisateur.
     * @param joueur Joueur faisant la demande.
     * @param str Nom du pion demand??.
     * @return index Index du pion dans la liste du joueur.
     */
    public int findPion(Joueur joueur, String str){
        int index = 0;
        Pion pion = null;

        //Iteration sur la liste de pion du joueur
        Iterator<Pion> it = joueur.getListPion().iterator();
        while(it.hasNext()){
            pion = it.next();
            if(pion.getNom().equals(str)){ //Si notre recherche correspond ?? ce qui est demand??
                index = joueur.getListPion().indexOf(pion);
                break; //On sort de la boucle
            }
        }
        return index;
    }

    /**
     * M??thode pour le param??trage du pion.
     * @param joueur Joueur faisant le param??trage.
     * @param pion Pion s??lectionner par le joueur.
     * @param sc Scanner pour demande utilisateur.
     */
    public void parametragePion(Joueur joueur, Pion pion, Scanner sc){
        while(true){
            //Clear du terminal
            PreSet.setTerminal();

            //Affichage du pion ?? param??trer
            System.out.println(pion);

            //Affichage des points restant ?? l'utilisateur
            System.out.println("Point restant a distribuer : " + joueur.getPoint());

            //Demande utilisateur
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
                    if(!pion.getStatus().equals(StatusPion.Combattant)){ //On ne peut pas param??trer une zone ?? un non combattant
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
     * M??thode pour param??trer un pion dont la zone est en tr??ve.
     * @param joueur Joueur faisant le param??trage.
     * @param pion Pion s??lectionn?? par le joueur.
     * @param sc Scanner pour demande utilisateur.
     */
    public void parametragePionTreve(Joueur joueur, Pion pion, Scanner sc){
        while(true){
            //Clear de la console
            PreSet.setTerminal();

            //Affichage du pion ?? param??trer
            System.out.println(pion);

            //Demande utilisateur
            System.out.print("Modification de Strategie(1), Zone(2),  Quitter(0) : ");
            int choix = sc.nextInt();

            switch(choix){
                case 1:
                    this.parametrageStrategie(pion, new Scanner(System.in));
                    break;
                case 2:
                    if(!pion.getStatus().equals(StatusPion.Combattant)){ //On ne peut pas param??trer une zone ?? un non combattant
                        System.out.println("Votre pion doit avoir le status \'Combattant\' pour avoir une zone.");
                        PreSet.tempo(2500);
                    } else{
                        if(pion.getZone() != null){
                            if(pion.getZone().getStatus()){ //Param??trage possible uniquement si la zone est control??e
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
     * M??thode pour param??trer la dext??rit?? du pion.
     * @param pion Pion s??lectionn?? par le joueur.
     * @param sc Scanner pour demande utilisateur.
     */
    public void parametrageDexterite(Pion pion, Scanner sc){
        int dexterite = 0;

        //Affichage de la dext??rit?? actuelle du pion
        System.out.println("Dexterite actuelle de votre pion : " + pion.getDexterite());
        while(true){
            //Demande utilisateur
            System.out.print("Combien de points de dexterite voulez-vous lui mettre ?" +
                            "(Valeur entre " + pion.getMinDexterite() + " et " + pion.getMaxDexterite() + ") : ");
            dexterite = sc.nextInt();

            //Nous sortons de la boucle si la saisie est correcte
            if(dexterite >= pion.getMinDexterite() && dexterite <= pion.getMaxDexterite()){
                break;
            }
        }

        //Changement de la dext??rit?? du pion
        pion.setDexterite(dexterite);
    }

    /**
     * M??thode pour param??trer la force du pion.
     * @param pion Pion s??lectionn?? par le joueur.
     * @param sc Scanner pour demande utilisateur.
     */
    public void parametrageForce(Pion pion, Scanner sc){
        int force = 0;

        //Affichage de la force actuelle du pion
        System.out.println("Force actuelle de votre pion : " + pion.getForce());
        while(true){
            //Demande utilisateur
            System.out.print("Combien de points de force voulez-vous lui mettre ?" +
                            "(Valeur entre " + pion.getMinForce() + " et " + pion.getMaxForce() + ") : ");
            force = sc.nextInt();

            //On sort de la boucle si la saisie est correcte
            if(force >= pion.getMinForce() && force <= pion.getMaxForce()){
                break;
            }
        }

        //Changement de la force du pion
        pion.setForce(force);
    }

    /**
     * M??thode pour param??trer la r??sistance du pion.
     * @param pion Pion s??lectionn?? par le joueur.
     * @param sc Scanner pour demande utilisateur.
     */
    public void parametrageResistance(Pion pion, Scanner sc){
        int resistance = 0;

        //Affichage de la r??sistance actuelle du pion
        System.out.println("Resistance actuelle de votre pion : " + pion.getResistance());
        while(true){
            //Demande utilisateur
            System.out.print("Combien de points de resistance voulez-vous lui mettre ?" +
                            "(Valeur entre " + pion.getMinResistance() + " et " + pion.getMaxResistance() + ") : ");
            resistance = sc.nextInt();

            //On sort de la boucle si la saisie est correcte
            if(resistance >= pion.getMinResistance() && resistance <= pion.getMaxResistance()){
                break;
            }
        }

        //Changement de la r??sistance du pion
        pion.setResistance(resistance);
    }

    /**
     * M??thode pour param??trer la constitution du pion.
     * @param pion Pion s??lectionn?? par le joueur.
     * @param sc Scanner pour demande utilisateur.
     */
    public void parametrageConstitution(Pion pion, Scanner sc){
        int constitution = 0;

        //Affichage de la constitution actuelle du pion
        System.out.println("Constitution actuelle de votre pion : " + pion.getConstitution());
        while(true){
            //Demande utilisateur
            System.out.print("Combien de points de constitution voulez-vous lui mettre ?" +
                            "(Valeur entre " + pion.getMinConstitution() + " et " + pion.getMaxConstitution() + ") : ");
            constitution = sc.nextInt();

            //On sort de la boucle si la saisie est correcte
            if(constitution >= pion.getMinConstitution() && constitution <= pion.getMaxConstitution()){
                break;
            }
        }

        //On change la constitution du pion
        pion.setConstitution(constitution);
    }

    /**
     * M??thode pour param??trer l'initiative du pion.
     * @param pion Pion s??lectionn?? par le joueur.
     * @param sc Scanner pour demande utilisateur.
     */
    public void parametrageInitiative(Pion pion, Scanner sc){
        int inititative = 0;

        //Affichage de l'initiative actuelle du pion
        System.out.println("Inititative actuelle de votre pion : " + pion.getInitiative());
        while(true){
            //Demande utilisateur
            System.out.print("Combien de points d'initiative voulez-vous lui mettre ?" +
                            "(Valeur entre " + pion.getMinInitiative() + " et " + pion.getMaxInitiative() + ") : ");
            inititative = sc.nextInt();

            //On sort de la boucle si la saisie est correcte
            if(inititative >= pion.getMinInitiative() && inititative <= pion.getMaxInitiative()){
                break;
            }
        }

        //Changement de l'initiative du pion
        pion.setInitiative(inititative);
    }

    /**
     * M??thode pour param??trer le status du pion.
     * @param pion Pion s??lectionn?? par le joueur.
     * @param sc Scanner pour demande utilisateur.
     */
    public void parametrageStatusPion(Pion pion, Scanner sc){
        int status = 0;

        //affichage du status actuelle du pion
        System.out.println("Status actuel de votre pion : " + pion.getStatus());
        while(true){
            //Demande utilisateur
            System.out.print("Quel status voulez-vous lui mettre ? Combattant(1), Reserviste(2) : ");
            status = sc.nextInt();

            if(status == 1){
                //Changement du status du pion
                pion.setStatus(StatusPion.Combattant);
                break;
            } else if(status == 2){
                //Changement de status du pion
                pion.setStatus(StatusPion.Reserviste);

                //On enl??ve la zone du pion s'il en avait une
                if(pion.getZone() != null){
                    pion.setZone(null);
                }
                break;
            }
        }
    }

    /**
     * M??thode pour param??trer la strat??gie du pion.
     * @param pion Pion s??lectionn?? par le joueur.
     * @param sc Scanner pour demande utilisateur.
     */
    public void parametrageStrategie(Pion pion, Scanner sc){
        int strategie = 0;

        //Affichage de la strat??gie actuelle du pion
        System.out.println("Strategie actuelle de votre pion : " + pion.getStrategie());
        while(true){
            //Demande utilisateur
            System.out.print("Strategie : Offensif(1), Defensif(2), Aleatoire(3), Preferentielle(4)" +
                                ", Doc(9), Quitter(0) : ");
            strategie = sc.nextInt();

            if(strategie == 1){
                //Changement de la strat??gie
                pion.setStrategie(new Offensif());
                break;
            } else if(strategie == 2){
                //Changement de la strat??gie
                pion.setStrategie(new Defensif());
                break;
            } else if(strategie == 3){
                //Changement de la strat??gie
                pion.setStrategie(new Aleatoire());
                break;
            } else if(strategie == 4){
                //Changement de la strat??gie
                pion.setStrategie(new Preferentielle());
                break;
            } else if(strategie == 9){
                //Affichage des caract??ristiques des strat??gies
                System.out.println("Strategie \'Offensif\' : Votre pion attaquera l'adversaire tous les tours." +
                                    "\nStrategie \'Defensif\' : Votre pion soignera vos pions tous les tours." +
                                    "\nStrategie \'Aleatoire\' : Votre pion attaquera ou soignera de maniere aleatoire." +
                                    "\nStrategie \'Preferentielle\' : Votre pion attaquera si votre equipe a plus de points de vie " +
                                    "ou soignera si votre equipe a moins de point de vie que l'adversaire.");
            } else if(strategie == 0){
                break;
            } else{
                System.out.println("Saisie incorrecte.");
            }
        }
    }

    /**
     * M??thode pour param??trer la zone du pion.
     * @param pion Pion s??lectionn?? par le joueur.
     * @param sc Scanner pour demande utilisateur.
     */
    public void parametrageZone(Pion pion, Scanner sc){
        while(true){
            String label;

            //Affichage des ECTS de l'??quipe du joueur pr??sent sur chaque zones
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
                //Demande utilisateur
                System.out.print("Quelle zone voulez-vous attribuer ? BU(BU), BDE(BDE), Quartier Admin(QA), Halle Indus(HI), Halle Sportive(HS), Quitter(0) : ");
                label = sc.nextLine();

                //On sort de la boucle si la saisie utilisateur est correcte
                if(label.equals("BU") || label.equals("BDE") || label.equals("QA") ||
                label.equals("HI") || label.equals("HS") || label.equals("0")){
                    break;
                }
            }

            //Si la saisie est 0 on quitte
            if(label.equals("0")){
                break;
            }

            //On recherche la zone demand?? par l'utilisateur
            Zone zone = this.arrayZone.get(this.findZone(label));
            System.out.println(zone);

            //On applique la zone au pion
            if(!zone.equals(null)){
                pion.setZone(zone);
                break;
            } else{
                System.out.println("Cette zone n'existe pas.");
            }
        } 
    }

    /**
     * M??thode pour trouver la zone demand?? par l'utilisateur.
     * @param str Label de la zone recherch??.
     * @return index Position de la zone dans la liste de zone de la partie.
     */
    public int findZone(String str){
        int index = 0;
        Zone zone = null;

        //Iteration sur la liste de zone de la partie
        Iterator<Zone> it = this.arrayZone.iterator();
        while(it.hasNext()){
            zone = it.next();
            if(zone.getLabel().equals(str)){ //Lorsque notre recherche correspond, nous sortons de la boucle
                index = this.arrayZone.indexOf(zone);
                break;
            }
        }
        return index;
    }

    /**
     * M??thode pour modifier le status du joueur.
     * V??rification que le joueur ai bien au moins un pion sur chaque zone, ou que tous ses pions aient une zone dans le cas o?? il a moins de 5 pions.
     * @param joueur
     * @return
     */
    public boolean parametrageStatusJoueur(Joueur joueur){
        //Variable si le joueur peut ??tre pr??t
        boolean isOk = false;

        //Variable pour savoir s'il y a un pion sur chaque zone
        boolean zoneBU_OK = false, zoneBDE_OK = false, zoneQA_OK = false, zoneHI_OK = false, zoneHS_OK = false;

        //Variable pour savoir combien de pions ont une zone
        int nbPionWithZone = 0;

        Pion pion = null;

        //Iteration sur la liste de pion du joueur
        Iterator<Pion> it = joueur.getListPion().iterator();
        while(it.hasNext()){
            pion = it.next();
            //Si le pion a une zone
            if(pion.getZone() != null){
                //Changement des variables correspondantes
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
     * M??thode pour lancer le combat sur les zones.
     */
    public void lancement(){
        Zone zone = null;
        Joueur joueur = null;

        //Tant que la partie n'est pas termin??e
        while(!this.getStatus().equals(StatusPartie.Terminee)){
            //La partie passe en mode combat
            this.setStatus(StatusPartie.Combat);

            //Tant que la partie est en combat
            while(this.getStatus().equals(StatusPartie.Combat)){
                //It??ration sur la liste de zone
                Iterator<Zone> itZone = this.arrayZone.iterator();
                while(itZone.hasNext()){
                    //V??rification si la partie est en combat avant de faire une action sur la zone
                    if(this.status.equals(StatusPartie.Combat)){
                        zone = itZone.next();
                        zone.combattre();
                    } else{
                        break;
                    }
                }
            }

            //Calcul des nombres de zone que chaque joueur poss??de
            int nbZoneControleeJoueur1 = this.arrayJoueur.get(0).getZoneControlee().size();
            int nbZoneControleeJoueur2 = this.arrayJoueur.get(1).getZoneControlee().size();

            //Si un des joueurs a 3 zones ou plus, la partie se termine sinon une tr??ve est d??clench??e
            if(nbZoneControleeJoueur1 >= 3){
                this.status = StatusPartie.Terminee;
                System.out.println("L'??quipe " + this.arrayJoueur.get(0).getEquipe() + " a gagn?? (" + this.arrayJoueur.get(0) + ").");
                break;
            } else if(nbZoneControleeJoueur2 >= 3){
                this.status = StatusPartie.Terminee;
                System.out.println("L'??quipe " + this.arrayJoueur.get(1).getEquipe() + " a gagn?? (" + this.arrayJoueur.get(1) + ").");
                break;
            } else{
                this.status = StatusPartie.Treve;
            }

            //Affichage des zones poss??d??es par chaque joueur / debug
            System.out.println("Le " + this.arrayJoueur.get(0).getNom() + " possede " + this.arrayJoueur.get(0).getZoneControlee());
            System.out.println("Le " + this.arrayJoueur.get(1).getNom() + " possede " + this.arrayJoueur.get(1).getZoneControlee());
            PreSet.tempo(2500);

            //It??ration sur la liste de joueur de la partie pour qu'ils fassent des param??trages.
            Iterator<Joueur> itJoueur = this.arrayJoueur.iterator();
            while(itJoueur.hasNext()){
                joueur = itJoueur.next();
                this.selectionPion(joueur, new Scanner(System.in));
            }
        }

    }

    /**
     * M??thode pour enlever un pion de la partie quand il est mort.
     * Ce pion sera enlev?? de toutes les collections o?? il est pr??sent.
     * @param pion Pion a enlever
     */
    public void pionMort(Pion pion){
        pion.getZone().removePion(pion);
        pion.getJoueur().removePion(pion);
    }
}
