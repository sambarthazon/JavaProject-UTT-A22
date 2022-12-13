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
     * ECTS minimum que le pion peut avoir.
     */
    protected final int ECTSMin = 0;

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
    protected StatusPion status = StatusPion.Reserviste;

    /**
     * Strategie que le pion adaptera pour combattre (Offensif, Defensif, Aleatoire ou Preferentielle).
     */
    protected Strategie strategie;

    /**
     * Zone a laquelle le pion est affecté.
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
     * Constructeur de la classe Pion.
     */
    public Pion(){

    }



    //******************************************************//
    //                                                      //
    //                        Joueur                        //
    //                                                      //
    //******************************************************//

    /**
     * Méthode pour récupérer le joueur du pion.
     * @return this.joueur Joueur du pion.
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
     * Méthode pour récupérer le nom du pion.
     * @return this.nom Nom du pion.
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
     * Méthode pour enlever de la vie au pion lorsqu'il est attaqué.
     * @param ECTS Vie à enlever.
     */
    public void pionAttaque(int ECTS){
        this.ECTS -= ECTS;
        if(this.ECTS < this.ECTSMin){
            this.ECTS = this.ECTSMin;
        }
    }

    /**
     * Méthode pour ajouter de la vie au pion lorsqu'il est soigné.
     * @param ECTS Vie à soigner.
     */
    public void pionSoigne(int ECTS){
        this.ECTS += ECTS;
        if(this.ECTS > this.ECTSMax){
            this.ECTS = this.ECTSMax;
        }
    }


    /**
     * Méthode pour récuperer les ECTS du pion.
     * @return this.ECTS Vie du pion.
     */
    public int getECTS(){
        return this.ECTS;
    }



    //******************************************************//
    //                                                      //
    //                        Dextérité                     //
    //                                                      //
    //******************************************************//

    /**
     * Méthode pour changer la dextérité du pion.
     * Le dextérité est bornée par maxDexterite et minDexterite.
     * @param dexterite Dextérité à attribuer au pion.
     */
    public void setDexterite(int dexterite){
        if(this.joueur.getListPion().contains(this)){ //Verification que ce soit le pion du joueur
            if(this.joueur.getPoint() + this.dexterite >= dexterite){ //Vérification si le joueur a assez de points
                if(dexterite <= this.getMaxDexterite() && dexterite >= this.getMinDexterite()){ //Verification si les points sont entre le max et le min
                    this.joueur.ajouterPoint(this.dexterite - this.getMinDexterite()); //On redonne des points au joueur
                    this.dexterite = dexterite; //Le pion a les nouveaux points
                    this.joueur.retirerPoint(dexterite - this.getMinDexterite()); //On retire les points attribués au joueur
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
     * Méthode pour recupérer la dextérité minimum d'un pion.
     * @return this.minDexterite Dextérité minimum d'un pion.
     */
    public abstract int getMinDexterite();

    /**
     * Méthode pour récupérer la dextérité maximum d'un pion.
     * @return this.maxDexterite Dextérité maximum d'un pion.
     */
    public abstract int getMaxDexterite();

    /**
     * Méthode pour récuperer la dextérité du pion.
     * @return this.dexterite Dextérité du pion.
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
     * Méthode pour changer la force du pion.
     * La force est bornée par maxForce et minForce.
     * @param force Force à attribuer au pion.
     */
    public void setForce(int force){
        if(this.joueur.getListPion().contains(this)){ //Verification que ce soit le pion du joueur
            if(this.joueur.getPoint() + this.force >= force){ //Vérification si le joueur a assez de points
                if(force <= this.getMaxForce() && force >= this.getMinForce()){ //Verification si les points sont entre le max et le min
                    this.joueur.ajouterPoint(this.force - this.getMinForce()); //On redonne des points au joueur
                    this.force = force; //Le pion a les nouveaux points
                    this.joueur.retirerPoint(force - this.getMinForce()); //On retire les points attribués au joueur
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
     * Méthode pour récupérer la force minimum d'un pion.
     * @return this.minForce Force minimum d'un pion.
     */
    public abstract int getMinForce();

    /**
     * Méthode pour récupérer la force maxmimum d'un pion.
     * @return this.maxForce Force maximum d'un pion.
     */
    public abstract int getMaxForce();
    
    /**
     * Méthode pour récupérer la force du pion.
     * @return this.force Force du pion.
     */
    public int getForce(){
        return this.force;
    }



    //******************************************************//
    //                                                      //
    //                      Résistance                      //
    //                                                      //
    //******************************************************//

    /**
     * Méthode pour changer la résistance du pion.
     * La résistance est bornée par maxResistance et minResistance.
     * @param resistance Resistance à attribuer au pion.
     */
    public void setResistance(int resistance){
        if(this.joueur.getListPion().contains(this)){ //Verification que ce soit le pion du joueur
            if(this.joueur.getPoint() + this.resistance >= resistance){ //Vérification si le joueur a assez de points
                if(resistance <= this.getMaxResistance() && resistance >= this.getMinResistance()){ //Verification si les points sont entre le max et le min
                    this.joueur.ajouterPoint(this.resistance - this.getMinResistance()); //On redonne des points au joueur
                    this.resistance = resistance; //Le pion a les nouveaux points
                    this.joueur.retirerPoint(resistance - this.getMinResistance()); //On retire les points attribués au joueur
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
     * Méthode pour recuperer la résistance minimum d'un pion.
     * @return this.minResistance Résistance minimum d'un pion.
     */
    public abstract int getMinResistance();

    /**
     * Méthode pour récupérer la résistance maxmimum d'un pion.
     * @return this.maxResistance Résistance maximum d'un pion.
     */
    public abstract int getMaxResistance();
    
    /**
     * Méthode pour récupérer la résistance du pion.
     * @return this.resistance Résistance du pion.
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
     * Méthode pour changer la constitution du pion.
     * La constitution est bornée par maxConsitution et minConstitution.
     * Les ECTS max vont évoluer en fonction de l'évolution de la consitution.
     * @param constitution Constitution à attribuer au pion.
     */
    public void setConstitution(int constitution){
        if(this.joueur.getListPion().contains(this)){ //Verification que ce soit le pion du joueur
            if(this.joueur.getPoint() + this.constitution >= constitution){ //Vérification si le joueur a assez de points
                if(constitution <= this.getMaxConstitution() && constitution >= this.getMinConstitution()){ //Verification si les points sont entre le max et le min
                    this.joueur.ajouterPoint(this.constitution - this.getMinConstitution()); //On redonne des points au joueur
                    this.constitution = constitution; //Le pion a les nouveaux points
                    this.joueur.retirerPoint(constitution - this.getMinConstitution()); //On retire les points attribués au joueur
                } else {
                    System.out.println("Rentrez un chiffre entre : " + this.getMinConstitution() + " et " + this.getMaxConstitution());
                }
            } else{
                System.out.println("Vous n'avez pas assez de points à attribuer.");
            }
        } else{
            System.out.println(this.nom + " n'est pas le pion de " + this.joueur.getNom());
        }

        //Changement des ECTS maximum que le pion peut avoir
        this.ECTSMax = this.ECTSDefault + this.constitution;

        //Si nous sommes au début de la partie, notre pion aura tous ses points de vie
        if(this.joueur.getPartie().getStatus() == StatusPartie.Parametrage){
            this.ECTS = this.ECTSMax;
        } else if(this.joueur.getPartie().getStatus() == StatusPartie.Treve){
            if(this.ECTS > this.ECTSMax){
                this.ECTS = this.ECTSMax;
            }
        }
    }

    /**
     * Méthode pour récupérer la consitution minimum d'un pion.
     * @return this.minConstitution Constitution minimum d'un pion.
     */
    public abstract int getMinConstitution();

    /**
     * Méthode pour récupérer la constitution maxmimum d'un pion.
     * @return this.maxConstitution Constitution maximum d'un pion.
     */
    public abstract int getMaxConstitution();
    
    /**
     * Méthode pour récupérer la constitution du pion.
     * @return this.constitution Consitution du pion.
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
     * Méthode pour changer l'initiative du pion.
     * @param initiative
     */
    public void setInitiative(int initiative){
        if(this.joueur.getListPion().contains(this)){ //Verification que ce soit le pion du joueur
            if(this.joueur.getPoint() + this.initiative >= initiative){ //Vérification si le joueur a assez de points
                if(initiative <= this.getMaxInitiative() && initiative >= this.getMinInitiative()){ //Verification si les points sont entre le max et le min
                    this.joueur.ajouterPoint(this.initiative - this.getMinInitiative()); //On redonne des points au joueur
                    this.initiative = initiative; //Le pion a les nouveaux points
                    this.joueur.retirerPoint(initiative - this.getMinInitiative()); //On retire les points attribués au joueur
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
     * Méthodes pour récupérer l'initiative minimum d'un pion.
     * @return this.minInitiative Initiative minimum d'un pion.
     */
    public abstract int getMinInitiative();

    /**
     * Méthode pour récuperer l'initiative maxmimum d'un pion.
     * @return this.maxInitiative Initiative maximum d'un pion.
     */
    public abstract int getMaxInitiative();
    
    /**
     * Méthode pour récupérer l'initiative du pion.
     * @return this.initiative Initiative du pion.
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
     * Méthode pour changer le status de combat du pion.
     * Vérification du status souhaité puis vérification du nombre de pion sous ce status.
     * @param status Status souhaité au pion.
     */
    public void setStatus(StatusPion status){
        //Si nous voulons un combattant, on vérifie le nombre de combattant déjà existant
        if(status.equals(StatusPion.Combattant)){
            if(this.joueur.getNbCombattant() < 15){
                this.status = status;
            } else{
                System.out.println("Vous avez trop de combattant.");
                PreSet.tempo(2500);
            }
        } else if(status.equals(StatusPion.Reserviste)){
            this.status = status;
        } else{
            System.out.println("Votre pion ne peut que être un Combattant ou un Reserviste.");
        }
    }

    /**
     * Méthode pour récupérer le status de combat du pion.
     * @return this.status Status du pion.
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
     * Méthode pour changer la zone du pion.
     * Retrait du pion de sa zone puis affectation à la nouvelle zone.
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
     * Méthode pour récupérer la zone du pion.
     * @return this.zone Zone du pion.
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
     * @param strategie Stratégie appliquée au pion.
     */
    public void setStrategie(Strategie strategie){
        this.strategie = strategie;
    }

    /**
     * Méthode pour récuperer la stratégie du pion.
     * @return this.strategie.toString();
     */
    public String getStrategie(){
        return this.strategie.toString();
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
     * @return Caractéristiques du pion.
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