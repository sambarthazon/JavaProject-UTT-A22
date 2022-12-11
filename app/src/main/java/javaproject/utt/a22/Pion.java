package javaproject.utt.a22;

/**
 * Classe permettant le controle du pion (combattant).
 */
public abstract class Pion{

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
    protected int ECTS = this.ECTSDefault + this.getMinConstitution();
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
     * Statistique de force de l'attaque. Augmente les degats de 10% par points affectes (0 à 10).
     */
    protected int force;
    
    /**
     * Statistique de resistance aux attaques. Augmente la resistance de 5% par points affectes (0 à 10).
     */
    protected int resistance;
    
    /**
     * Statistique de vie supplémentaire. Augmente la vie initiale du nombre de points affectes (0 à 30).
     */
    protected int constitution;
    
    /**
     * Statistique d'initiative. Determine quel pion attaque le premier (0 à 10).
     */
    protected int initiative;

    /**
     * Status de combat du pion (Combattant, Reserviste ou Indefini).
     */
    protected StatusPion status = StatusPion.Indefini;

    /**
     * Strategie que le pion adaptera pour combattre (Offensif, Defensif, Aleatoire ou Preferentielle).
     */
    protected Strategie strategie;

    /**
     * Zone a laquelle le pion est affecte.
     */
    protected Zone zone = null;


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


    public void pionAttaque(int ECTS){
        this.ECTS -= ECTS;
    }

    public void pionSoigne(int ECTS){
        this.ECTS += ECTS;
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
            if(this.joueur.getPoint() + this.dexterite >= dexterite){
                if(dexterite <= this.getMaxDexterite() && dexterite >= this.getMinDexterite()){ //Verification si les points sont entre le max et le min.
                    this.joueur.ajouterPoint(this.dexterite - this.getMinDexterite()); //On redonne des points au joueur.
                    this.dexterite = dexterite; //Le pion a les nouveaux points.
                    this.joueur.retirerPoint(dexterite - this.getMinDexterite()); //On retire les points attribués au joueur.
                } else {
                    System.out.println("Rentrez un chiffre entre : " + this.getMinDexterite() + " et " + this.getMinDexterite());
                }
            } else{
                System.out.println("Vous n'avez pas assez de points à attribuer.");
            }
        } else{
            System.out.println(this.nom + " n'est pas le pion de " + this.joueur.getNom());
        }
    }

    /**
     * Methodes pour recuperer la dexterite minimum d'un pion.
     * @return
     */
    public abstract int getMinDexterite();

    /**
     * Methode pour recuperer la dexterite maxmimum d'un pion.
     * @return
     */
    public abstract int getMaxDexterite();

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
            if(this.joueur.getPoint() + this.force >= force){
                if(force <= this.getMaxForce() && force >= this.getMinForce()){ //Verification si les points sont entre le max et le min.
                    this.joueur.ajouterPoint(this.force - this.getMinForce()); //On redonne des points au joueur.
                    this.force = force; //Le pion a les nouveaux points.
                    this.joueur.retirerPoint(force - this.getMinForce()); //On retire les points attribués au joueur.
                } else {
                    System.out.println("Rentrez un chiffre entre : " + this.getMinForce() + " et " + this.getMaxForce());
                }
            } else{
                System.out.println("Vous n'avez pas assez de points à attribuer.");
            }
        } else{
            System.out.println(this.nom + " n'est pas le pion de " + this.joueur.getNom());
        }
    }

    /**
     * Methodes pour recuperer la force minimum d'un pion.
     * @return
     */
    public abstract int getMinForce();

    /**
     * Methode pour recuperer la force maxmimum d'un pion.
     * @return
     */
    public abstract int getMaxForce();
    
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
            if(this.joueur.getPoint() + this.resistance >= resistance){
                if(resistance <= this.getMaxResistance() && resistance >= this.getMinResistance()){ //Verification si les points sont entre le max et le min.
                    this.joueur.ajouterPoint(this.resistance - this.getMinResistance()); //On redonne des points au joueur.
                    this.resistance = resistance; //Le pion a les nouveaux points.
                    this.joueur.retirerPoint(resistance - this.getMinResistance()); //On retire les points attribués au joueur.
                } else {
                    System.out.println("Rentrez un chiffre entre : " + this.getMinResistance() + " et " + this.getMaxResistance());
                }
            } else{
                System.out.println("Vous n'avez pas assez de points à atrribuer.");
            }
        } else{
            System.out.println(this.nom + " n'est pas le pion de " + this.joueur.getNom());
        }
    }

    /**
     * Methodes pour recuperer la resistance minimum d'un pion.
     * @return
     */
    public abstract int getMinResistance();

    /**
     * Methode pour recuperer la resistance maxmimum d'un pion.
     * @return
     */
    public abstract int getMaxResistance();
    
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
            if(this.joueur.getPoint() + this.constitution >= constitution){
                if(constitution <= this.getMaxConstitution() && constitution >= this.getMinConstitution()){ //Verification si les points sont entre le max et le min.
                    this.joueur.ajouterPoint(this.constitution - this.getMinConstitution()); //On redonne des points au joueur.
                    this.constitution = constitution; //Le pion a les nouveaux points.
                    this.joueur.retirerPoint(constitution - this.getMinConstitution()); //On retire les points attribués au joueur.
                } else {
                    System.out.println("Rentrez un chiffre entre : " + this.getMinConstitution() + " et " + this.getMaxConstitution());
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
     * Methodes pour recuperer la consitution minimum d'un pion.
     * @return
     */
    public abstract int getMinConstitution();

    /**
     * Methode pour recuperer la constitution maxmimum d'un pion.
     * @return
     */
    public abstract int getMaxConstitution();
    
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
            if(this.joueur.getPoint() + this.initiative >= initiative){
                if(initiative <= this.getMaxInitiative() && initiative >= this.getMinInitiative()){ //Verification si les points sont entre le max et le min.
                    this.joueur.ajouterPoint(this.initiative - this.getMinInitiative()); //On redonne des points au joueur.
                    this.initiative = initiative; //Le pion a les nouveaux points.
                    this.joueur.retirerPoint(initiative - this.getMinInitiative()); //On retire les points attribués au joueur.
                } else {
                    System.out.println("Rentrez un chiffre entre : " + this.getMinInitiative() + " et " + this.getMaxInitiative());
                }
            } else{
                System.out.println("Vous n'avez pas assez de points à attribuer");
            }
        } else{
            System.out.println(this.nom + " n'est pas le pion de " + this.joueur.getNom());
        }
    }

    /**
     * Methodes pour recuperer l'initiative minimum d'un pion.
     * @return
     */
    public abstract int getMinInitiative();

    /**
     * Methode pour recuperer l'initiative maxmimum d'un pion.
     * @return
     */
    public abstract int getMaxInitiative();
    
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
            if(this.joueur.getNbCombattant() < 15){
                this.status = status;
            }
        } else if(status.equals(StatusPion.Reserviste)){
            this.status = status;
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
        if(this.zone == null){
            this.zone = zone;
            this.zone.addPion(this);
        } else{
            if(!this.zone.equals(zone)){
                this.zone.removePion(this);
                this.zone = null;
            }
    
            if(this.zone == null && zone != null){
                this.zone = zone;
                this.zone.addPion(this);
            }
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
    //                        Stratégie                     //
    //                                                      //
    //******************************************************//

    /**
     * Méthode pour changer la stratégie du pion.
     * @param strategie
     */
    public void setStrategie(Strategie strategie){
        this.strategie = strategie;
    }

    /**
     * Méthode pour récuperer la stratégie du pion.
     * @return this.strategie.getNom();
     */
    public String getStrategie(){
        return this.strategie.getNom();
    }

    /**
     * Méthode pour combattre en fonction de la stratégie attribuée.
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
     * Redéfinition de la méthode toString.
     * @return
     */
    @Override
    public String toString(){
        return this.nom + " {\n\tECTS = " + this.ECTS + ", Status = " + this.status + 
                            ", Stratégie = " + this.strategie + ", Zone = " + this.zone +
                            "\n\tDextérité = " + this.dexterite +
                            ", Force = " + this.force +
                            ", Résistance = " + this.resistance +
                            ", Constitution = " + this.constitution +
                            ", Initiative = " + this.initiative +
                            "\n}";
    }
}