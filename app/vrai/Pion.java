import java.util.Iterator;

public class Pion {
    protected Joueur joueur;
    protected String nom;
    protected int ECTS = 30;
    protected final int ECTSDefault = 30;
    protected int ECTSMax;
    protected int dexterite;
    private final int maxDexterite;
    private final int minDexterite;
    protected int force;
    private final int maxForce;
    private final int minForce;
    protected int resistance;
    private final int maxResistance;
    private final int minResistance;
    protected int constitution;
    private final int maxConstitution;
    private final int minConstitution;
    protected int initiative;
    private final int maxInitiative;
    private final int minInitiative;
    private StatusPion status;
    protected Strategie strategie;
    private Zone zone;



    public Pion() {
        this.ECTSMax = 30 + this.constitution;
        this.maxDexterite = 10;
        this.minDexterite = 0;
        this.maxForce = 10;
        this.minForce = 0;
        this.maxResistance = 10;
        this.minResistance = 0;
        this.maxConstitution = 30;
        this.minConstitution = 0;
        this.maxInitiative = 10;
        this.minInitiative = 0;
        this.status = StatusPion.Indefini;
        this.zone = null;
        this.joueur=joueur;
    }

    public void setECTS(int ECTS) {
        this.ECTS += ECTS;
        if (this.ECTS > this.ECTSMax) {
            this.ECTS = this.ECTSMax;
        }

    }

    public int getECTS() {
        return this.ECTS;
    }

    public void setDexterite(int dexterite) {
        if (this.joueur.arrayPion.contains(this)) {
            if (this.joueur.getPoint() > dexterite) {
                if (dexterite <= 10 && dexterite >= 0) {
                    this.joueur.ajouterPoint(this.dexterite);
                    this.dexterite = dexterite;
                    this.joueur.retirerPoint(dexterite);
                } else {
                    System.out.println("Rentrez un chiffre entre : " + 0 + " et " + 10);
                }
            } else {
                System.out.println("Vous n'avez pas assez de points à attribuer.");
            }
        } else {
            System.out.println(this.nom + " n'est pas le pion de " + this.joueur.getNom());
        }

    }

    public int getDexterite() {
        return this.dexterite;
    }

    public void setForce(int force) {
        if (this.joueur.arrayPion.contains(this)) {
            if (this.joueur.getPoint() > force) {
                if (force <= 10 && force >= 0) {
                    this.joueur.ajouterPoint(this.force);
                    this.force = force;
                    this.joueur.retirerPoint(force);
                } else {
                    System.out.println("Rentrez un chiffre entre : " + 0 + " et " + 10);
                }
            } else {
                System.out.println("Vous n'avez pas assez de points à attribuer.");
            }
        } else {
            System.out.println(this.nom + " n'est pas le pion de " + this.joueur.getNom());
        }

    }

    public int getForce() {
        return this.force;
    }

    public void setResistance(int resistance) {
        if (this.joueur.arrayPion.contains(this)) {
            if (this.joueur.getPoint() > resistance) {
                if (resistance <= 10 && resistance >= 0) {
                    this.joueur.ajouterPoint(this.resistance);
                    this.resistance = resistance;
                    this.joueur.retirerPoint(resistance);
                } else {
                    System.out.println("Rentrez un chiffre entre : " + 0 + " et " + 10);
                }
            } else {
                System.out.println("Vous n'avez pas assez de points à atrribuer.");
            }
        } else {
            System.out.println(this.nom + " n'est pas le pion de " + this.joueur.getNom());
        }

    }

    public int getResistance() {
        return this.resistance;
    }

    public void setConstitution(int constitution) {
        if (this.joueur.arrayPion.contains(this)) {
            if (this.joueur.getPoint() > constitution) {
                if (constitution <= 30 && constitution >= 0) {
                    this.joueur.ajouterPoint(this.constitution);
                    this.constitution = constitution;
                    this.joueur.retirerPoint(constitution);
                } else {
                    System.out.println("Rentrez un chiffre entre : " + 0 + " et " + 30);
                }
            } else {
                System.out.println("Vous n'avez pas assez de points à attribuer.");
            }
        } else {
            System.out.println(this.nom + " n'est pas le pion de " + this.joueur.getNom());
        }

        this.ECTSMax = 30 + this.constitution;
        if (this.joueur.getPartie().getStatus() == StatusPartie.Parametrage) {
            this.ECTS = this.ECTSMax;
        }

    }

    public int getConstitution() {
        return this.constitution;
    }

    public void setInitiative(int initiative) {
        if (this.joueur.arrayPion.contains(this)) {
            if (this.joueur.getPoint() > initiative) {
                if (initiative <= 10 && initiative >= 0) {
                    this.joueur.ajouterPoint(this.initiative);
                    this.initiative = initiative;
                    this.joueur.retirerPoint(initiative);
                } else {
                    System.out.println("Rentrez un chiffre entre : " + 0 + " et " + 10);
                }
            } else {
                System.out.println("Vous n'avez pas assez de points à attribuer");
            }
        } else {
            System.out.println(this.nom + " n'est pas le pion de " + this.joueur.getNom());
        }

    }

    public int getInitiative() {
        return this.initiative;
    }

    public void setStatus(StatusPion status) {
        if (status == StatusPion.Combattant) {
            int var = 0;
            Iterator<Pion> it = this.joueur.arrayPion.iterator();

            while(it.hasNext()) {
                Pion pion = (Pion)it.next();
                if (pion.status == StatusPion.Combattant) {
                    ++var;
                }
            }

            if (var >= 15) {
                System.out.println("Vous avez trop de combattant.");
            } else {
                this.status = StatusPion.Combattant;
            }
        } else if (status == StatusPion.Reserviste) {
            this.status = StatusPion.Reserviste;
        } else {
            System.out.println("Votre pion ne peut que être un Combattant ou un Reserviste.");
        }

    }

    public StatusPion getStatus() {
        return this.status;
    }

    public void setZone(Zone zone) {
        if (this.zone.equals(zone)) {
            System.out.println("Ce pion appartient déjà à cette zone.");
        } else {
            this.zone.removePion(this);
            this.zone = null;
        }

        if (this.zone == null) {
            this.zone = zone;
            this.zone.addPion(this);
        }

    }

    public Zone getZone() {
        return this.zone;
    }

    public void setStrategie(Strategie strategie) {
        this.strategie = strategie;
    }

    public String getStrategie() {
        return this.strategie.getNom();
    }

    public void executerStrategie() {
        this.strategie.combattre(this);
    }

    public void setJoueur(Joueur joueur) {
        if (this.joueur.equals(joueur)) {
            System.out.println("Ce pion appartient déjà à ce joueur.");
        } else {
            this.joueur.removePion(this);
            this.joueur = null;
        }

        if (this.joueur == null) {
            this.joueur = joueur;
            this.joueur.addPion(this);
        }

    }

    public Joueur getJoueur(){
        return joueur;
    }

    public String toString() {
        return this.nom + " {\n\tECTS = " + this.ECTS + ", Stratégie = " + this.strategie + ", Zone = " + this.zone + ", Status = " + this.status + "\n\tDextérité = " + this.dexterite + ", Force = " + this.force + ", Résistance = " + this.resistance + ", Constitution = " + this.constitution + ", Initiative = " + this.initiative + "\n}";
    }


}
