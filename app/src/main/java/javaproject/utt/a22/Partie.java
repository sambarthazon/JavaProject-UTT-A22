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
            System.out.println("Le joueur : " + joueurActif.getNom() + " va pouvoir commencer le paramétrage.");
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
     * Méthode pour changer le status de la partie.
     * Les status sont visibles dans l'enumeration "StatusPartie".
     * @param status Status appliqué au joueur.
     */
    public void setStatus(StatusPartie status){
        this.status = status;
    }

    /**
     * Méthode pour récuperer le status de la partie.
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
     * Méthode pour ajouter un joueur à la partie.
     * Vérification si le joueur est déjà dans la liste de la partie.
     * @param joueur Joueur que nous ajoutons à la partie.
     */
    public void addJoueur(Joueur joueur){
        if(!this.arrayJoueur.contains(joueur)){
            this.arrayJoueur.add(joueur);
        }
    }

    /**
     * Méthode pour retirer un joueur de la partie.
     * Verification si le joueur est dans la partie.
     * @param joueur Joueur que nous voulons retirer de la partie.
     */
    public void removeJoueur(Joueur joueur){
        if(this.arrayJoueur.contains(joueur)){
            this.arrayJoueur.remove(joueur);
        }
    }

    /**
     * Méthode pour récupérer la liste de joueur de la partie.
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
     * Méthode pour ajouter une zone à la partie.
     * @param zone Zone à ajouter à la partie.
     */
    public void addZone(Zone zone){
        if(!this.arrayZone.contains(zone)){
            this.arrayZone.add(zone);
        }
    }

    /**
     * Méthode pour retirer une zone de la partie.
     * Vérification que la zone à retirer est bien dans la liste.
     * @param zone Zone à retirer de la partie.
     */
    public void removeZone(Zone zone){
        if(this.arrayZone.contains(zone)){
            this.arrayZone.remove(zone);
        }
    }

    /**
     * Méthode pour recuperer la liste de zones de la partie.
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
     * Méthode pour le paramétrage des pions en phase de "Paramétrage".
     */
    public void phaseParametrage(Joueur joueur, Scanner sc){
        while(joueur.getStatus().equals(StatusJoueur.Preparation)){
            //Affichage des pions du joueur
            System.out.println(joueur.getListPion());

            //Demande utilisateur
            System.out.print("Voulez-vous faire des modifications sur vos pions ?\nOui(Y), Non(N) : ");
            String input = sc.nextLine();

            //Action dû à la saisi utilisateur
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
     * Méthode pour que le joueur choisisse une équipe.
     * Les équipes sont disponibles dans l'énumeration NomEquipe.
     * @param joueur Joueur faisant le paramétrage.
     * @param sc Scanner pour demande utilisateur.
     */
    public void selectionEquipe(Joueur joueur, Scanner sc){
        String equipe = " ";

        while(true) {
            //Demande de l'équipe au joueur.
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
            
        //Phase de paramétrage des pions.
        this.phaseParametrage(joueur, new Scanner(System.in));
    }


    /**
     * Méthode pour permettre au joueur de sélectionner le pion a paramétrer.
     * @param joueur Joueur faisant le paramétrage.
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
                //Récupération du pion demandé par le joueur
                pion = joueur.getListPion().get(this.findPion(joueur, input));
                if(!pion.equals(null)){ //S'il a trouvé
                    if(pion.getZone() != null){ //Si la zone du pion n'est pas nulle
                        if(pion.getZone().getStatus()){ //Si sa zone est en trêve
                            //Appel de paramétrage du pion en mode trêve.
                            this.parametragePionTreve(joueur, pion, new Scanner(System.in));
                        } else{ //Si la zone n'est pas en trêve.
                            System.out.println("La zone de ce pion n'est pas en trêve.");
                            PreSet.tempo(1500);
                        }
                    } else{
                        //Appel de paramétrage pion normal
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
     * Méthode pour récupérer le pion demandé par l'utilisateur.
     * @param joueur Joueur faisant la demande.
     * @param str Nom du pion demandé.
     * @return index Index du pion dans la liste du joueur.
     */
    public int findPion(Joueur joueur, String str){
        int index = 0;
        Pion pion = null;

        //Iteration sur la liste de pion du joueur
        Iterator<Pion> it = joueur.getListPion().iterator();
        while(it.hasNext()){
            pion = it.next();
            if(pion.getNom().equals(str)){ //Si notre recherche correspond à ce qui est demandé
                index = joueur.getListPion().indexOf(pion);
                break; //On sort de la boucle
            }
        }
        return index;
    }

    /**
     * Méthode pour le paramétrage du pion.
     * @param joueur Joueur faisant le paramétrage.
     * @param pion Pion sélectionner par le joueur.
     * @param sc Scanner pour demande utilisateur.
     */
    public void parametragePion(Joueur joueur, Pion pion, Scanner sc){
        while(true){
            //Clear du terminal
            PreSet.setTerminal();

            //Affichage du pion à paramétrer
            System.out.println(pion);

            //Affichage des points restant à l'utilisateur
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
                    if(!pion.getStatus().equals(StatusPion.Combattant)){ //On ne peut pas paramétrer une zone à un non combattant
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
     * Méthode pour paramétrer un pion dont la zone est en trêve.
     * @param joueur Joueur faisant le paramétrage.
     * @param pion Pion sélectionné par le joueur.
     * @param sc Scanner pour demande utilisateur.
     */
    public void parametragePionTreve(Joueur joueur, Pion pion, Scanner sc){
        while(true){
            //Clear de la console
            PreSet.setTerminal();

            //Affichage du pion à paramétrer
            System.out.println(pion);

            //Demande utilisateur
            System.out.print("Modification de Strategie(1), Zone(2),  Quitter(0) : ");
            int choix = sc.nextInt();

            switch(choix){
                case 1:
                    this.parametrageStrategie(pion, new Scanner(System.in));
                    break;
                case 2:
                    if(!pion.getStatus().equals(StatusPion.Combattant)){ //On ne peut pas paramétrer une zone à un non combattant
                        System.out.println("Votre pion doit avoir le status \'Combattant\' pour avoir une zone.");
                        PreSet.tempo(2500);
                    } else{
                        if(pion.getZone() != null){
                            if(pion.getZone().getStatus()){ //Paramétrage possible uniquement si la zone est controlée
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
     * Méthode pour paramétrer la dextérité du pion.
     * @param pion Pion sélectionné par le joueur.
     * @param sc Scanner pour demande utilisateur.
     */
    public void parametrageDexterite(Pion pion, Scanner sc){
        int dexterite = 0;

        //Affichage de la dextérité actuelle du pion
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

        //Changement de la dextérité du pion
        pion.setDexterite(dexterite);
    }

    /**
     * Méthode pour paramétrer la force du pion.
     * @param pion Pion sélectionné par le joueur.
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
     * Méthode pour paramétrer la résistance du pion.
     * @param pion Pion sélectionné par le joueur.
     * @param sc Scanner pour demande utilisateur.
     */
    public void parametrageResistance(Pion pion, Scanner sc){
        int resistance = 0;

        //Affichage de la résistance actuelle du pion
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

        //Changement de la résistance du pion
        pion.setResistance(resistance);
    }

    /**
     * Méthode pour paramétrer la constitution du pion.
     * @param pion Pion sélectionné par le joueur.
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
     * Méthode pour paramétrer l'initiative du pion.
     * @param pion Pion sélectionné par le joueur.
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
     * Méthode pour paramétrer le status du pion.
     * @param pion Pion sélectionné par le joueur.
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

                //On enlève la zone du pion s'il en avait une
                if(pion.getZone() != null){
                    pion.setZone(null);
                }
                break;
            }
        }
    }

    /**
     * Méthode pour paramétrer la stratégie du pion.
     * @param pion Pion sélectionné par le joueur.
     * @param sc Scanner pour demande utilisateur.
     */
    public void parametrageStrategie(Pion pion, Scanner sc){
        int strategie = 0;

        //Affichage de la stratégie actuelle du pion
        System.out.println("Strategie actuelle de votre pion : " + pion.getStrategie());
        while(true){
            //Demande utilisateur
            System.out.print("Strategie : Offensif(1), Defensif(2), Aleatoire(3), Preferentielle(4)" +
                                ", Doc(9), Quitter(0) : ");
            strategie = sc.nextInt();

            if(strategie == 1){
                //Changement de la stratégie
                pion.setStrategie(new Offensif());
                break;
            } else if(strategie == 2){
                //Changement de la stratégie
                pion.setStrategie(new Defensif());
                break;
            } else if(strategie == 3){
                //Changement de la stratégie
                pion.setStrategie(new Aleatoire());
                break;
            } else if(strategie == 4){
                //Changement de la stratégie
                pion.setStrategie(new Preferentielle());
                break;
            } else if(strategie == 9){
                //Affichage des caractéristiques des stratégies
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
     * Méthode pour paramétrer la zone du pion.
     * @param pion Pion sélectionné par le joueur.
     * @param sc Scanner pour demande utilisateur.
     */
    public void parametrageZone(Pion pion, Scanner sc){
        while(true){
            String label;

            //Affichage des ECTS de l'équipe du joueur présent sur chaque zones
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

            //On recherche la zone demandé par l'utilisateur
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
     * Méthode pour trouver la zone demandé par l'utilisateur.
     * @param str Label de la zone recherché.
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
     * Méthode pour modifier le status du joueur.
     * Vérification que le joueur ai bien au moins un pion sur chaque zone, ou que tous ses pions aient une zone dans le cas où il a moins de 5 pions.
     * @param joueur
     * @return
     */
    public boolean parametrageStatusJoueur(Joueur joueur){
        //Variable si le joueur peut être prêt
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
     * Méthode pour lancer le combat sur les zones.
     */
    public void lancement(){
        Zone zone = null;
        Joueur joueur = null;

        //Tant que la partie n'est pas terminée
        while(!this.getStatus().equals(StatusPartie.Terminee)){
            //La partie passe en mode combat
            this.setStatus(StatusPartie.Combat);

            //Tant que la partie est en combat
            while(this.getStatus().equals(StatusPartie.Combat)){
                //Itération sur la liste de zone
                Iterator<Zone> itZone = this.arrayZone.iterator();
                while(itZone.hasNext()){
                    //Vérification si la partie est en combat avant de faire une action sur la zone
                    if(this.status.equals(StatusPartie.Combat)){
                        zone = itZone.next();
                        zone.combattre();
                    } else{
                        break;
                    }
                }
            }

            //Calcul des nombres de zone que chaque joueur possède
            int nbZoneControleeJoueur1 = this.arrayJoueur.get(0).getZoneControlee().size();
            int nbZoneControleeJoueur2 = this.arrayJoueur.get(1).getZoneControlee().size();

            //Si un des joueurs a 3 zones ou plus, la partie se termine sinon une trêve est déclenchée
            if(nbZoneControleeJoueur1 >= 3){
                this.status = StatusPartie.Terminee;
                System.out.println("L'équipe " + this.arrayJoueur.get(0).getEquipe() + " a gagné (" + this.arrayJoueur.get(0) + ").");
                break;
            } else if(nbZoneControleeJoueur2 >= 3){
                this.status = StatusPartie.Terminee;
                System.out.println("L'équipe " + this.arrayJoueur.get(1).getEquipe() + " a gagné (" + this.arrayJoueur.get(1) + ").");
                break;
            } else{
                this.status = StatusPartie.Treve;
            }

            //Affichage des zones possédées par chaque joueur / debug
            System.out.println("Le " + this.arrayJoueur.get(0).getNom() + " possede " + this.arrayJoueur.get(0).getZoneControlee());
            System.out.println("Le " + this.arrayJoueur.get(1).getNom() + " possede " + this.arrayJoueur.get(1).getZoneControlee());
            PreSet.tempo(2500);

            //Itération sur la liste de joueur de la partie pour qu'ils fassent des paramétrages.
            Iterator<Joueur> itJoueur = this.arrayJoueur.iterator();
            while(itJoueur.hasNext()){
                joueur = itJoueur.next();
                this.selectionPion(joueur, new Scanner(System.in));
            }
        }

    }

    /**
     * Méthode pour enlever un pion de la partie quand il est mort.
     * Ce pion sera enlevé de toutes les collections où il est présent.
     * @param pion Pion a enlever
     */
    public void pionMort(Pion pion){
        pion.getZone().removePion(pion);
        pion.getJoueur().removePion(pion);
    }
}
