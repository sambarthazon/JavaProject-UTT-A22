package javaproject.utt.a22;

import java.util.*;

/**
 * Classe permettant le controle du pion (combattant).
 */
public class Pion{

    /**
     * Attribut indiquant a quel joueur appartient le pion.
     */
    protected Joueur joueur;

    /**
     * Nom du pion.
     */
    protected String nom;

    /**
     * L'attribut ECTS equivaut à la vie du pion, une fois à 0 notre pion meurt.
     */
    protected int ECTS = this.ECTSDefault;
    /**
     * ECTS de base du pion.
     */
    protected final int ECTSDefault = 30;
    /**
     * ECTS maximum que le pion peut avoir.
     */
    protected int ECTSMax = this.ECTSDefault + this.constitution;

    /**
     * Statistique permettant l'esquive d'une attaque ou l'atteinte de la cible lors de l'attque (0 à 10).
     */
    protected int dexterite;
    /**
     * Valeur maximal de la dexterite.
     */
    private final int maxDexterite = 10;
    /**
     * Valeur minimale de la dexterite.
     */
    private final int minDexterite = 0;

    /**
     * Statistique de force de l'attaque. Augmente les degats de 10% par points affectes (0 à 10).
     */
    protected int force;
    /**
     * Valeur maximale de la force.
     */
    private final int maxForce = 10;
    /**
     * Valeur minimale de la force.
     */
    private final int minForce = 0;
    
    /**
     * Statistique de resistance aux attaques. Augmente la resistance de 5% par points affectes (0 à 10).
     */
    protected int resistance;
    /**
     * Valeur maximale de la resistance.
     */
    private final int maxResistance = 10;
    /**
     * Valeur minimale de la resistance.
     */
    private final int minResistance = 0;
    
    /**
     * Statistique de vie supplémentaire. Augmente la vie initiale du nombre de points affectes (0 à 30).
     */
    protected int constitution;
    /**
     * Valeur maximale de la constitution.
     */
    private final int maxConstitution = 30;
    /**
     * Valeur minimale de la constitution.
     */
    private final int minConstitution = 0;
    
    /**
     * Statistique d'initiative. Determine quel pion attaque le premier (0 à 10).
     */
    protected int initiative;
    /**
     * Valeur maximale de l'initiative.
     */
    private final int maxInitiative = 10;
    /**
     * Valeur minimale de l'initiative.
     */
    private final int minInitiative = 0;

    /**
     * Status de combat du pion (Combattant, Reserviste ou Indefini).
     */
    private StatusPion status = StatusPion.Indefini;

    /**
     * Strategie que le pion adaptera pour combattre (Offensif, Defensif, Aleatoire ou Preferentielle).
     */
    protected Strategie strategie;

    /**
     * Zone a laquelle le pion est affecte.
     */
    private Zone zone = null;


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
     * Constructeur Pion
     */
    public Pion(){

    }



    //******************************************************//
    //                                                      //
    //                        Joueur                        //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour recuprer le joueur du pion.
     * @return this.joueur
     */
    public Joueur getJoueur(){
        return this.joueur;
    }



    //******************************************************//
    //                                                      //
    //                          Nom                         //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour recuperer le nom du pion.
     * @return this.nom
     */
    public String getNom(){
        return this.nom;
    }



    //******************************************************//
    //                                                      //
    //                          ECTS                        //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour ajouter ou retirer des ECTS au pion.
     * Les ECTS ne pourront pas etre au dessus des ECTS maximum du pion.
     * @param ECTS
     */
    public void setECTS(int ECTS){
        this.ECTS += ECTS;
        if(this.ECTS > this.ECTSMax){
            this.ECTS = this.ECTSMax;
        }
    }

    /**
     * Methode pour recuperer les ECTS du pion.
     * @return this.ECTS
     */
    public int getECTS(){
        return this.ECTS;
    }



    //******************************************************//
    //                                                      //
    //                        Dexterite                     //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour changer la dexterite du pion.
     * Le dexterite est bornee par maxDexterite et minDexterite.
     * @param dexterite
     */
    public void setDexterite(int dexterite){
        if(this.joueur.getListPion().contains(this)){ //Verification que ce soit le pion du joueur.
            if(this.joueur.getPoint() > dexterite){
                if(dexterite <= this.maxDexterite && dexterite >= this.minDexterite){ //Verification si les points sont entre le max et le min.
                    this.joueur.ajouterPoint(this.dexterite); //On redonne des points au joueur.
                    this.dexterite = dexterite; //Le pion a les nouveaux points.
                    this.joueur.retirerPoint(dexterite); //On retire les points attribués au joueur.
                } else {
                    System.out.println("Rentrez un chiffre entre : " + this.minDexterite + " et " + this.maxDexterite);
                }
            } else{
                System.out.println("Vous n'avez pas assez de points à attribuer.");
            }
        } else{
            System.out.println(this.nom + " n'est pas le pion de " + this.joueur.getNom());
        }
    }

    /**
     * Methode pour recuperer la dexterite du pion.
     * @return this.dexterite
     */
    public int getDexterite(){
        return this.dexterite;
    }



    //******************************************************//
    //                                                      //
    //                          Force                       //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour changer la force du pion.
     * La force est bornee par maxForce et minForce.
     * @param force
     */
    public void setForce(int force){
        if(this.joueur.getListPion().contains(this)){ //Verification que ce soit le pion du joueur.
            if(this.joueur.getPoint() > force){
                if(force <= this.maxForce && force >= this.minForce){ //Verification si les points sont entre le max et le min.
                    this.joueur.ajouterPoint(this.force); //On redonne des points au joueur.
                    this.force = force; //Le pion a les nouveaux points.
                    this.joueur.retirerPoint(force); //On retire les points attribués au joueur.
                } else {
                    System.out.println("Rentrez un chiffre entre : " + this.minForce + " et " + this.maxForce);
                }
            } else{
                System.out.println("Vous n'avez pas assez de points à attribuer.");
            }
        } else{
            System.out.println(this.nom + " n'est pas le pion de " + this.joueur.getNom());
        }
    }

    /**
     * Methode pour recuperer la force du pion.
     * @return this.force
     */
    public int getForce(){
        return this.force;
    }



    //******************************************************//
    //                                                      //
    //                      Resistance                      //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour changer la resistance du pion.
     * La resistance est bornee par maxResistance et minResistance.
     * @param resistance
     */
    public void setResistance(int resistance){
        if(this.joueur.getListPion().contains(this)){ //Verification que ce soit le pion du joueur.
            if(this.joueur.getPoint() > resistance){
                if(resistance <= this.maxResistance && resistance >= this.minResistance){ //Verification si les points sont entre le max et le min.
                    this.joueur.ajouterPoint(this.resistance); //On redonne des points au joueur.
                    this.resistance = resistance; //Le pion a les nouveaux points.
                    this.joueur.retirerPoint(resistance); //On retire les points attribués au joueur.
                } else {
                    System.out.println("Rentrez un chiffre entre : " + this.minResistance + " et " + this.maxResistance);
                }
            } else{
                System.out.println("Vous n'avez pas assez de points à atrribuer.");
            }
        } else{
            System.out.println(this.nom + " n'est pas le pion de " + this.joueur.getNom());
        }
    }

    /**
     * Methode pour recuperer la resistance du pion.
     * @return this.resistance
     */
    public int getResistance(){
        return this.resistance;
    }



    //******************************************************//
    //                                                      //
    //                      Constitution                    //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour changer la constitution du pion.
     * La constitution est bornee par maxConsitution et minConstitution.
     * Les ECTS max vont evolue en fonction de l'evolution de la consitution.
     * @param constitution
     */
    public void setConstitution(int constitution){
        if(this.joueur.getListPion().contains(this)){ //Verification que ce soit le pion du joueur.
            if(this.joueur.getPoint() > constitution){
                if(constitution <= this.maxConstitution && constitution >= this.minConstitution){ //Verification si les points sont entre le max et le min.
                    this.joueur.ajouterPoint(this.constitution); //On redonne des points au joueur.
                    this.constitution = constitution; //Le pion a les nouveaux points.
                    this.joueur.retirerPoint(constitution); //On retire les points attribués au joueur.
                } else {
                    System.out.println("Rentrez un chiffre entre : " + this.minConstitution + " et " + this.maxConstitution);
                }
            } else{
                System.out.println("Vous n'avez pas assez de points à attribuer.");
            }
        } else{
            System.out.println(this.nom + " n'est pas le pion de " + this.joueur.getNom());
        }

        this.ECTSMax = this.ECTSDefault + this.constitution;

        //Si nous sommes au debut de la partie, notre pion aura tous ses points de vie.
        if(this.joueur.getPartie().getStatus() == StatusPartie.Parametrage){
            this.ECTS = this.ECTSMax;
        } else if(this.joueur.getPartie().getStatus() == StatusPartie.Treve){
            if(this.ECTS > this.ECTSMax){
                this.ECTS = this.ECTSMax;
            }
        }
    }

    /**
     * Methode pour recuperer la constitution du pion.
     * @return this.constitution
     */
    public int getConstitution(){
        return this.constitution;
    }



    //******************************************************//
    //                                                      //
    //                      Initiative                      //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour changer l'initiative du pion.
     * @param initiative
     */
    public void setInitiative(int initiative){
        if(this.joueur.getListPion().contains(this)){ //Verification que ce soit le pion du joueur.
            if(this.joueur.getPoint() > initiative){
                if(initiative <= this.maxInitiative && initiative >= this.minInitiative){ //Verification si les points sont entre le max et le min.
                    this.joueur.ajouterPoint(this.initiative); //On redonne des points au joueur.
                    this.initiative = initiative; //Le pion a les nouveaux points.
                    this.joueur.retirerPoint(initiative); //On retire les points attribués au joueur.
                } else {
                    System.out.println("Rentrez un chiffre entre : " + this.minInitiative + " et " + this.maxInitiative);
                }
            } else{
                System.out.println("Vous n'avez pas assez de points à attribuer");
            }
        } else{
            System.out.println(this.nom + " n'est pas le pion de " + this.joueur.getNom());
        }
    }

    /**
     * Methode pour recuperer l'initiative du pion.
     * @return this.initiative
     */
    public int getInitiative(){
        return this.initiative;
    }



    //******************************************************//
    //                                                      //
    //                         Status                       //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour changer le status de combat du pion.
     * Verification du status souhaite puis verification du nombre de pion sous ce status.
     * @param status
     */
    public void setStatus(StatusPion status){
        if(status.equals(StatusPion.Combattant)){
            int var = 0;
            Iterator<Pion> it = this.joueur.getListPion().iterator();
            while(it.hasNext()){
                Pion pion = it.next();
                if(pion.status == StatusPion.Combattant){
                    var++;
                }
            }
            if(var >= 15){
                System.out.println("Vous avez trop de combattant.");
            } else{
                this.status = StatusPion.Combattant;
            }
        } else if(status.equals(StatusPion.Reserviste)){
            this.status = StatusPion.Reserviste;
        } else{
            System.out.println("Votre pion ne peut que être un Combattant ou un Reserviste.");
        }
    }

    /**
     * Methode pour recuperer le status de combat du pion.
     * @return this.status
     */
    public StatusPion getStatus(){
        return this.status;
    }



    //******************************************************//
    //                                                      //
    //                          Zone                        //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour changer la zone du pion.
     * Verification si nous changeons avec la meme zone.
     * Sinon, enlever le pion de la zone et mettre la nouvelle zone au pion.
     * @param zone
     */
    public void setZone(Zone zone){
        if(this.zone.equals(zone)){
            System.out.println("Ce pion appartient déjà à cette zone.");
        } else{
            this.zone.removePion(this);
            this.zone = null;
        }

        if(this.zone == null){
            this.zone = zone;
            this.zone.addPion(this);
        }
    }

    /**
     * Methode pour recuperer la zone du pion.
     * @return
     */
    public Zone getZone(){
        return this.zone;
    }



    //******************************************************//
    //                                                      //
    //                        Strategie                     //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour changer la strategie du pion.
     * @param strategie
     */
    public void setStrategie(Strategie strategie){
        this.strategie = strategie;
    }

    /**
     * Methode pour recuperer la strategie du pion.
     * @return this.strategie.getNom();
     */
    public String getStrategie(){
        return this.strategie.getNom();
    }

    /**
     * Methode pour combattre en fonction de la strategie attribuee.
     */
    public void executerStrategie(){
        this.strategie.combattre(this);
    }



    //******************************************************//
    //                                                      //
    //                        Affichage                     //
    //                                                      //
    //******************************************************//

    /**
     * Redefinition de la methode toString.
     * @return
     */
    @Override
    public String toString(){
        return this.nom + " {\n\tECTS = " + this.ECTS + ", Stratégie = " + this.strategie + 
                            ", Zone = " + this.zone + ", Status = " + this.status +
                            "\n\tDextérité = " + this.dexterite +
                            ", Force = " + this.force +
                            ", Résistance = " + this.resistance +
                            ", Constitution = " + this.constitution +
                            ", Initiative = " + this.initiative +
                            "\n}";
    }
}