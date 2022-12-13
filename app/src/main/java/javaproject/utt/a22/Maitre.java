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
     * @param joueur Joueur du maitre.
     * @param nom Nom du maitre.
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
     * Méthode pour récuperer la dextérité minimum d'un "Maître du Gobi".
     * @return this.minDexterite Valeur minimum de la dextérité que le "Maître du Gobi" peut avoir.
     */
    @Override
    public int getMinDexterite(){
        return this.minDexterite;
    }

    /**
     * Méthode pour récuperer la dextérité maximum d'un "Maitre du Gobi".
     * @return this.maxDexterite Valeur maximum de la dextérité que le "Maître du Gobi" peut avoir.
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
     * Méthode pour récuperer la force minimum d'un "Maître du Gobi".
     * @return this.minForce Valeur minimum de la force que le "Maître du Gobi" peut avoir.
     */
    @Override
    public int getMinForce(){
        return this.minForce;
    }

    /**
     * Méthode pour récuperer la force maximum d'un "Maître du Gobi".
     * @return this.maxForce Valeur maximum de la force que le "Maître du Gobi" peut avoir.
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
     * Méthode pour récuperer la r"sistance minimum d'un "Maître du Gobi".
     * @return this.minResistance Valeur minimum de la résistance que le "Maître du Gobi" peut avoir.
     */
    @Override
    public int getMinResistance(){
        return this.minResistance;
    }

    /**
     * Méthode pour récuperer la résistance maximum d'un "Maître du Gobi".
     * @return this.maxResistance Valeur maximum de la résistance que le "Maître du Gobi" peut avoir.
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
     * Méthode pour récupérer la constitution minimum d'un "Maître du Gobi".
     * @return this.minConstitution Valeur minimum de la constitution que le "Maître du Gobi" peut avoir.
     */
    @Override
    public int getMaxConstitution(){
        return this.maxConstitution;
    }

    /**
     * Méthode pour récuperer la constitution maximum d'un "Maître du Gobi".
     * @return this.maxConstitution Valeur maximum de la constitution que le "Maître du Gobi" peut avoir.
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
     * Méthode pour récuperer l'initiative minimum d'un "Maître du Gobi".
     * @return this.minInitiative Valeur minimum de l'initiative que le "Maître du Gobi" peut avoir.
     */
    @Override
    public int getMinInitiative(){
        return this.minInitiative;
    }

    /**
     * Méthode pour récuperer l'initiative maximum d'un Maitre du Gobi.
     * @return this.maxInitiative Valeur maximum de l'initiative que le "Maître du Gobi" peut avoir.
     */
    @Override
    public int getMaxInitiative(){
        return this.maxInitiative;
    }
}
