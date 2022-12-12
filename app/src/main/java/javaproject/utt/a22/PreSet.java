package javaproject.utt.a22;

import java.util.*;

/**
 * Classe permettant une auto-initialisation des pions ...
 */
public class PreSet{

    public static final String NOM_JOUEUR1 = "Joueur 1";
    public static final String NOM_JOUEUR2 = "Joueur 2";
    public static void main(String[] args) {
        //Creation de la partie.
        Partie partie = new Partie();

        //Creation des zones
        Zone zoneBU = new Zone(partie, NomZone.BU, "BU");
        Zone zoneBDE = new Zone(partie, NomZone.BDE, "BDE");
        Zone zoneQA = new Zone(partie, NomZone.QuartierAdmin, "QA");
        Zone zoneHI = new Zone(partie, NomZone.HalleIndus, "HI");
        Zone zoneHS = new Zone(partie, NomZone.HalleSport, "HS");

        //Creation des joueurs.
        Joueur joueur1 = new Joueur(partie, NOM_JOUEUR1);
        Joueur joueur2 = new Joueur(partie, NOM_JOUEUR2);

        //Creation des "Etudiants" alias "Ei".
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

        //Creation des "Etudiants d'Elite" alias "EIi".
        for(int i = 1; i < 5; i++){
            Pion eliteJoueur1 = new Elite(joueur1, "EI"+i);
            eliteJoueur1.setStatus(StatusPion.Reserviste);
            Pion eliteJoueur2 = new Elite(joueur2, "EI"+i);
            eliteJoueur2.setStatus(StatusPion.Reserviste);
        }

        //Creation des "Maitre du Gobi" alias "M".
        Pion maitreJoueur1 = new Maitre(joueur1, "M");
        maitreJoueur1.setStatus(StatusPion.Reserviste);
        Pion maitreJoueur2 = new Maitre(joueur2, "M");
        maitreJoueur2.setStatus(StatusPion.Reserviste);

        /**
         * Lancement de la partie.
         */
        partie.lancement();
    }

    /**
     * Methode pour clear la console.
     * Cette methode est utile lors de l'utilisation de l'application en console.
     */
    public static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Methode pour temporisation.
     * @param time
     */
    public static void tempo(int time){
        try{
            Thread.sleep(time);
        } catch(Exception e){

        }
    }


    public static void setTerminal(){
        clearConsole();
        System.out.println("======================================== C'est du Brutal ! ========================================");
    }
}
