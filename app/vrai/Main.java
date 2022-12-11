import java.util.Scanner;
import java.util.*;
public class Main {
    public static void main(String[] args) {

        //-------------Nom des joueurs----------------------//
        Partie partie = new Partie();
        Joueur joueur1 = null;
        Joueur joueur2=null;


        //paramétrage des joueurs
        joueur1.paramJoueur(joueur1,"","Joueur1");
        joueur2.paramJoueur(joueur2,joueur1.getEquipeStr(),"Joueur2");

        //Phase d'initialisation des joueurs





    /*
        //--------------Phase de paramétrage du premier joueur-----------------------------//
        System.out.println("Vous etes a la phase de preparation. Initialise tes troupes avant " +
                "d'aller sur le champs de bataille");

        boolean estSortie = false;


        while(!estSortie){
            int point =400;
            Scanner myObj = new Scanner(System.in);
            System.out.println("Tu initialises tes troupes (repondre init) ou tu es pret (repondre pret) ?");
            String situation = myObj.nextLine();  // Read user input
            if(situation!="pret") {
                estSortie=true;
            }else if(situation=="init"){

            }else if(point==0) {
                estSortie=true;
            }else{
                System.out.println("Commande invalide");
            }


        }
    */
    }


}

