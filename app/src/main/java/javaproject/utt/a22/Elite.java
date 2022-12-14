package javaproject.utt.a22;

/**
 * Classe Elite
 * Classe fille de la classe Pion. Ce pion a des caractéristiques différentes.
 */
public class Elite extends Pion{

    /**
     * Valeur minimale de la dexterite d'un Etudiant d'Elite.
     */
    private final int minDexterite = 1;
    /**
     * Valeur maximal de la dexterite d'un Etudiant d'Elite.
     */
    private final int maxDexterite = 10;

    /**
     * Valeur minimale de la force d'un Etudiant d'Elite.
     */
    private final int minForce = 1;
    /**
     * Valeur maximale de la force d'un Etudiant d'Elite.
     */
    private final int maxForce = 10;
   
    /**
     * Valeur minimale de la resistance d'un Etudiant d'Elite.
     */
    private final int minResistance = 1;
    /**
     * Valeur maximale de la resistance d'un Etudiant d'Elite.
     */
    private final int maxResistance = 10;
    
    /**
     * Valeur minimale de la constitution d'un Etudiant d'Elite.
     */
    private final int minConstitution = 5;
    /**
     * Valeur maximale de la constitution d'un Etudiant d'Elite.
     */
    private final int maxConstitution = 30;
    
    /**
     * Valeur minimale de l'initiative d'un Etudiant d'Elite.
     */
    private final int minInitiative = 1;
    /**
     * Valeur maximale de l'initiative d'un Etudiant d'Elite.
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
     * Constructeur de la classe Elite.
     * @param joueur Joueur de l'Etudiant d'Elite.
     * @param nom Nom de l'Etudiant d'Elite.
     */
    public Elite(Joueur joueur, String nom){
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
     * Méthode pour récuperer la dextérite minimum d'un "Etudiant d'Elite".
     * @return this.minDexterite Valeur minimum de la dextérité que l'"Etudiant d'Elite" peut avoir.
     */
    @Override
    public int getMinDexterite(){
        return this.minDexterite;
    }

    /**
     * Méthode pour récuperer la dextérite maximum d'un "Etudiant d'Elite".
     * @return this.maxDexterite Valeur maximum de la dextérité que l'"Etudiant d'Elite" peut avoir.
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
     * Méthode pour récuperer la force minimum d'un "Etudiant d'Elite".
     * @return this.minForce Valeur minimum de la force que l'"Etudiant d'Elite" peut avoir.
     */
    @Override
    public int getMinForce(){
        return this.minForce;
    }

    /**
     * Methode pour recuperer la force maximum d'un "Etudiant d'Elite".
     * @return this.maxForce Valeur maximum de la force que l'"Etudiant d'Elite" peut avoir.
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
     * Methode pour recuperer la resistance minimum d'un "Etudiant d'Elite".
     * @return this.minResistance Valeur minimum de la résistance que l'"Etudiant d'Elite" peut avoir.
     */
    @Override
    public int getMinResistance(){
        return this.minResistance;
    }

    /**
     * Methode pour recuperer la resistance maximum d'un "Etudiant d'Elite".
     * @return this.maxResistance Valeur résistance de la dextérité que l'"Etudiant d'Elite" peut avoir.
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
     * Methode pour recuperer la constitution minimum d'un "Etudiant d'Elite".
     * @return this.minConstitution Valeur minimum de la constitution que l'"Etudiant d'Elite" peut avoir.
     */
    @Override
    public int getMaxConstitution(){
        return this.maxConstitution;
    }

    /**
     * Methode pour recuperer la constitution maximum d'un "Etudiant d'Elite".
     * @return this.maxConstitution Valeur maximum de la constitution que l'"Etudiant d'Elite" peut avoir.
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
     * Methode pour recuperer l'initiative minimum d'un "Etudiant d'Elite".
     * @return this.minInitiative Valeur minimum de l'initiative que l'"Etudiant d'Elite" peut avoir.
     */
    @Override
    public int getMinInitiative(){
        return this.minInitiative;
    }

    /**
     * Methode pour recuperer l'initiative maximum d'un "Etudiant d'Elite".
     * @return this.maxInitiative Valeur maximum de l'initiative que l'"Etudiant d'Elite" peut avoir.
     */
    @Override
    public int getMaxInitiative(){
        return this.maxInitiative;
    }
}
