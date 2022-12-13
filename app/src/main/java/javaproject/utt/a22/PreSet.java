package javaproject.utt.a22;

/**
 * Classe permettant une auto-initialisation des pions ...
 */
public class PreSet{

    public static final String NOM_JOUEUR1 = "Joueur 1";
    public static final String NOM_JOUEUR2 = "Joueur 2";
    public static void main(String[] args) {
        //Création de la partie.
        Partie partie = new Partie();

        //Création des zones
        Zone zoneBU = new Zone(partie, NomZone.BU, "BU");
        Zone zoneBDE = new Zone(partie, NomZone.BDE, "BDE");
        Zone zoneQA = new Zone(partie, NomZone.QuartierAdmin, "QA");
        Zone zoneHI = new Zone(partie, NomZone.HalleIndus, "HI");
        Zone zoneHS = new Zone(partie, NomZone.HalleSport, "HS");

        //Création des joueurs.
        Joueur joueur1 = new Joueur(partie, NOM_JOUEUR1);
        Joueur joueur2 = new Joueur(partie, NOM_JOUEUR2);

        //Création des "Etudiants" alias "Ei".
        for(int i = 1; i < 16; i++){
            Pion etudiantJoueur1 = new Etudiant(joueur1, "E"+i);
            if(i < 16){
                etudiantJoueur1.setStatus(StatusPion.Combattant);
                etudiantJoueur1.setDexterite(5);
                etudiantJoueur1.setForce(5);
                etudiantJoueur1.setResistance(5);
                etudiantJoueur1.setConstitution(5);
                etudiantJoueur1.setInitiative(5);
                if(i < 4){
                    etudiantJoueur1.setZone(zoneBU);
                } else if(i < 7){
                    etudiantJoueur1.setZone(zoneBDE);
                } else if(i < 10){
                    etudiantJoueur1.setZone(zoneQA);
                } else if(i < 13){
                    etudiantJoueur1.setZone(zoneHI);
                } else if(i < 16){
                    etudiantJoueur1.setZone(zoneHS);
                }
                if(i % 2 == 0){
                    etudiantJoueur1.setStrategie(new Defensif());
                }
            }

            Pion etudiantJoueur2 = new Etudiant(joueur2, "E"+i);
            if(i < 16){
                etudiantJoueur2.setStatus(StatusPion.Combattant);
                etudiantJoueur2.setDexterite(5);
                etudiantJoueur2.setForce(5);
                etudiantJoueur2.setResistance(5);
                etudiantJoueur2.setConstitution(5);
                etudiantJoueur2.setInitiative(5);
                if(i < 4){
                    etudiantJoueur2.setZone(zoneBU);
                } else if(i < 7){
                    etudiantJoueur2.setZone(zoneBDE);
                } else if(i < 10){
                    etudiantJoueur2.setZone(zoneQA);
                } else if(i < 13){
                    etudiantJoueur2.setZone(zoneHI);
                } else if(i < 16){
                    etudiantJoueur2.setZone(zoneHS);
                }
                if(i % 2 == 0){
                    etudiantJoueur2.setStrategie(new Defensif());
                }
            }
        }

        //Création des "Etudiants d'Elite" alias "EIi".
        for(int i = 1; i < 5; i++){
            new Elite(joueur1, "EI"+i);
            new Elite(joueur2, "EI"+i);
        }

        //Création des "Maitre du Gobi" alias "M".
        new Maitre(joueur1, "M");
        new Maitre(joueur2, "M");

        /**
         * Lancement de la partie.
         */
        partie.lancement();
    }

    /**
     * Méthode pour clear la console.
     * Cette méthode est utile lors de l'utilisation de l'application en console.
     */
    public static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Méthode pour temporisation.
     * @param time Temps que nous souhaitons attendre.
     */
    public static void tempo(int time){
        try{
            Thread.sleep(time);
        } catch(Exception e){

        }
    }

    /**
     * Méthode pour avoir le même affichage de console.
     */
    public static void setTerminal(){
        clearConsole();
        System.out.println("======================================== C'est du Brutal ! ========================================");
    }
}
