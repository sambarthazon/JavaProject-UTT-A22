package javaproject.utt.a22;

/**
 * Classe Etudiant
 * Classe fille de la classe Pion. Ce pion a des caractéristiques différentes.
 */
public class Etudiant extends Pion{

    /**
     * Valeur minimale de la dexterite d'un Etudiant.
     */
    private final int minDexterite = 0;
    /**
     * Valeur maximal de la dexterite d'un Etudiant.
     */
    private final int maxDexterite = 10;
    
    /**
     * Valeur minimale de la force d'un Etudiant.
     */
    private final int minForce = 0;
    /**
     * Valeur maximale de la force d'un Etudiant.
     */
    private final int maxForce = 10;
   
    /**
     * Valeur minimale de la resistance d'un Etudiant.
     */
    private final int minResistance = 0;
    /**
     * Valeur maximale de la resistance d'un Etudiant.
     */
    private final int maxResistance = 10;
    
    /**
     * Valeur minimale de la constitution d'un Etudiant.
     */
    private final int minConstitution = 0;
    /**
     * Valeur maximale de la constitution d'un Etudiant.
     */
    private final int maxConstitution = 30;
    
    /**
     * Valeur minimale de l'initiative d'un Etudiant.
     */
    private final int minInitiative = 0;
    /**
     * Valeur maximale de l'initiative d'un Etudiant.
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
     * Constrcuteur de la classe Etudiant.
     * @param joueur Joueur de l'Etudiant.
     * @param nom Nom de l'Etudiant.
     */
    public Etudiant(Joueur joueur, String nom){
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
     * Methode pour recuperer la dexterite minimum d'un "Etudiant".
     * @return this.minDexterite Valeur minimum de la dextérité que l'"Etudiant" peut avoir.
     */
    @Override
    public int getMinDexterite(){
        return this.minDexterite;
    }

    /**
     * Methode pour recuperer la dexterite maximum d'un "Etudiant".
     * @return this.maxDexterite Valeur maximum de la dextérité que l'"Etudiant" peut avoir.
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
     * Methode pour recuperer la force minimum d'un "Etudiant".
     * @return this.minForce Valeur minimum de la force que l'"Etudiant" peut avoir.
     */
    @Override
    public int getMinForce(){
        return this.minForce;
    }

    /**
     * Methode pour recuperer la force maximum d'un "Etudiant".
     * @return this.maxForce Valeur maximum de la force que l'"Etudiant" peut avoir.
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
     * Methode pour recuperer la resistance minimum d'un "Etudiant".
     * @return this.minResistance Valeur minimum de la résistance que l'"Etudiant" peut avoir.
     */
    @Override
    public int getMinResistance(){
        return this.minResistance;
    }

    /**
     * Methode pour recuperer la resistance maximum d'un "Etudiant".
     * @return this.maxResistance Valeur maximum de la résistance que l'"Etudiant" peut avoir.
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
     * Methode pour recuperer la constitution minimum d'un "Etudiant".
     * @return this.minConstitution Valeur minimum de la constitution que l'"Etudiant" peut avoir.
     */
    @Override
    public int getMaxConstitution(){
        return this.maxConstitution;
    }

    /**
     * Methode pour recuperer la constitution maximum d'un "Etudiant".
     * @return this.maxConstitution Valeur maximum de la constitution que l'"Etudiant" peut avoir.
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
     * Methode pour recuperer l'initiative minimum d'un "Etudiant".
     * @return this.minInitiative Valeur minimum de l'initiative que l'"Etudiant" peut avoir.
     */
    @Override
    public int getMinInitiative(){
        return this.minInitiative;
    }

    /**
     * Methode pour recuperer l'initiative maximum d'un "Etudiant".
     * @return this.maxInitiative Valeur maximum de l'initiative que l'"Etudiant" peut avoir.
     */
    @Override
    public int getMaxInitiative(){
        return this.maxInitiative;
    }
}
