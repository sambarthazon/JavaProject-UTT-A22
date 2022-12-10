package javaproject.utt.a22;

public class Maitre extends Pion{

    /**
     * Valeur minimale de la dexterite d'un Maitre du Gobi.
     */
    private final int minDexterite = 2;
    /**
     * Valeur maximal de la dexterite d'un Maitre du Gobi.
     */
    private final int maxDexterite = 10;
    
    /**
     * Valeur minimale de la force d'un Maitre du Gobi.
     */
    private final int minForce = 2;
    /**
     * Valeur maximale de la force d'un Maitre du Gobi.
     */
    private final int maxForce = 10;
   
    /**
     * Valeur minimale de la resistance d'un Maitre du Gobi.
     */
    private final int minResistance = 2;
    /**
     * Valeur maximale de la resistance d'un Maitre du Gobi.
     */
    private final int maxResistance = 10;
    
    /**
     * Valeur minimale de la constitution d'un Maitre du Gobi.
     */
    private final int minConstitution = 10;
    /**
     * Valeur maximale de la constitution d'un Maitre du Gobi.
     */
    private final int maxConstitution = 30;
    
    /**
     * Valeur minimale de l'initiative d'un Maitre du Gobi.
     */
    private final int minInitiative = 2;
    /**
     * Valeur maximale de l'initiative d'un Maitre du Gobi.
     */
    private final int maxInitiative = 10;
    

    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {
        
    }


    //******************************************************//
    //                                                      //
    //                      Constructeur                    //
    //                                                      //
    //******************************************************//

    /**
     * Constructeur de la classe Maitre.
     */
    public Maitre(Joueur joueur, String nom){
        this.joueur = joueur;
        this.nom = nom;

        this.dexterite = this.minDexterite;
        this.force = this.minForce;
        this.resistance = this.minResistance;
        this.constitution = this.minConstitution;
        this.initiative = this.minInitiative;
        this.strategie = new Offensif();

        this.joueur.addPion(this);
    }



    //******************************************************//
    //                                                      //
    //                        Dexterite                     //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour recuperer la dexterite minimum d'un Maitre du Gobi.
     * @return this.minDexterite
     */
    @Override
    public int getMinDexterite(){
        return this.minDexterite;
    }

    /**
     * Methode pour recuperer la dexterite maximum d'un Maitre du Gobi.
     * @return this.maxDexterite
     */
    @Override
    public int getMaxDexterite(){
        return this.maxDexterite;
    }



    //******************************************************//
    //                                                      //
    //                          Force                       //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour recuperer la force minimum d'un Maitre du Gobi.
     * @return this.minForce
     */
    @Override
    public int getMinForce(){
        return this.minForce;
    }

    /**
     * Methode pour recuperer la force maximum d'un Maitre du Gobi.
     * @return this.maxForce
     */
    @Override
    public int getMaxForce(){
        return this.maxForce;
    }



    //******************************************************//
    //                                                      //
    //                      Resistance                      //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour recuperer la resistance minimum d'un Maitre du Gobi.
     * @return this.minResistance
     */
    @Override
    public int getMinResistance(){
        return this.minResistance;
    }

    /**
     * Methode pour recuperer la resistance maximum d'un Maitre du Gobi.
     * @return this.maxResistance
     */
    @Override
    public int getMaxResistance(){
        return this.maxResistance;
    }



    //******************************************************//
    //                                                      //
    //                      Constitution                    //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour recuperer la constitution minimum d'un Maitre du Gobi.
     * @return this.minConstitution
     */
    @Override
    public int getMaxConstitution(){
        return this.maxConstitution;
    }

    /**
     * Methode pour recuperer la constitution maximum d'un Maitre du Gobi.
     * @return this.maxConstitution
     */
    @Override
    public int getMinConstitution(){
        return this.minConstitution;
    }



    //******************************************************//
    //                                                      //
    //                      Initiative                      //
    //                                                      //
    //******************************************************//

    /**
     * Methode pour recuperer l'initiative minimum d'un Maitre du Gobi.
     * @return this.minInitiative
     */
    @Override
    public int getMinInitiative(){
        return this.minInitiative;
    }

    /**
     * Methode pour recuperer l'initiative maximum d'un Maitre du Gobi.
     * @return this.maxInitiative
     */
    @Override
    public int getMaxInitiative(){
        return this.maxInitiative;
    }
}
