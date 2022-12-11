import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
public class Zone extends Thread{
    private LinkedList<Pion> linkedPionTeam1 = new LinkedList();
    private LinkedList<Pion> linkedPionTeam2 = new LinkedList();
    private LinkedList<Pion> linkedPion = new LinkedList();
    private NomZone nom;
    private StatusZone status;
    private Partie partie;
    /*
    public static void main(String[] args) {
        Partie partie = new Partie();
        Joueur joueur = new Joueur(partie, "Joueur 1");
        Zone zoneBU = new Zone(partie, NomZone.BU);
        Zone zoneBDE = new Zone(partie, NomZone.BDE);
        Zone zoneQA = new Zone(partie, NomZone.QuartierAdmin);
        Pion pion1 = new Etudiant(joueur, "Etudiant 1");
        Pion pion2 = new Etudiant(joueur, "Etudiant 2");
        Pion pion3 = new Etudiant(joueur, "Etudiant 3");
        zoneBU.addPion(pion1);
        zoneBDE.addPion(pion2);
        zoneQA.addPion(pion3);
        partie.setStatus(StatusPartie.Combat);
        zoneBU.start();
        zoneBDE.start();
        zoneQA.start();
    }
    */
    public Zone(Partie partie, NomZone nomZone) {
        this.partie = partie;
        this.nom = nomZone;
    }

    public void addPion(Pion pion) {
        if (!this.linkedPion.contains(pion)) {
            this.linkedPion.add(pion);
        }

        if (!this.linkedPionTeam1.contains(pion)) {
            this.linkedPionTeam1.add(pion);
        }

    }

    public void removePion(Pion pion) {
        if (pion.joueur.getNom() == "Joueur 1") {
            if (this.linkedPionTeam1.contains(pion)) {
                this.linkedPionTeam1.remove(pion);
                pion.setZone((Zone)null);
            } else {
                System.out.println("Ce pion n'est pas dans la liste.");
            }
        } else if (pion.joueur.getNom() == "Joueur 2") {
            if (this.linkedPionTeam2.contains(pion)) {
                this.linkedPionTeam2.remove(pion);
                pion.setZone((Zone)null);
            } else {
                System.out.println("Ce pion n'est pas dans la liste.");
            }
        }

    }

    public int getECTSTeam1() {
        int ECTSTotal = 0;
        Pion pion = null;

        for(Iterator<Pion> it = this.linkedPionTeam1.iterator(); it.hasNext(); ECTSTotal += pion.getECTS()) {
            pion = (Pion)it.next();
        }

        return ECTSTotal;
    }

    public int getECTSTeam2() {
        int ECTSTotal = 0;
        Pion pion = null;

        for(Iterator<Pion> it = this.linkedPionTeam2.iterator(); it.hasNext(); ECTSTotal += pion.getECTS()) {
            pion = (Pion)it.next();
        }

        return ECTSTotal;
    }

    public LinkedList<Pion> getLinkedListTeam1() {
        return this.linkedPionTeam1;
    }

    public LinkedList<Pion> getLinkedListTeam2() {
        return this.linkedPionTeam2;
    }

    public NomZone getNomZone() {
        return this.nom;
    }

    public void setStatus(StatusZone status) {
    }

    public StatusZone getStatus() {
        return this.status;
    }

    public void sortLinkedPionTeam1() {
        Collections.sort(this.linkedPionTeam1, new Comparator<Pion>() {
            public int compare(Pion p1, Pion p2) {
                return p1.ECTS - p2.ECTS;
            }
        });
    }

    public void sortLinkedPionTeam2() {
        Collections.sort(this.linkedPionTeam2, new Comparator<Pion>() {
            public int compare(Pion p1, Pion p2) {
                return p1.ECTS - p2.ECTS;
            }
        });
    }

    public void sortLinkedPion() {
        Collections.sort(this.linkedPion, new Comparator<Pion>() {
            public int compare(Pion p1, Pion p2) {
                return p2.initiative - p1.initiative;
            }
        });
    }

    public void combattre() {
        this.start();
    }

    public void run() {
        this.sortLinkedPion();
        this.sortLinkedPionTeam1();
        this.sortLinkedPionTeam2();

        while(this.getECTSTeam1() > 0 && this.getECTSTeam2() > 0 && this.partie.status.equals(StatusPartie.Combat)) {
            System.out.println(this.nom);
            ((Pion)this.linkedPionTeam1.get(0)).setECTS(-5);

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException var2) {
                var2.printStackTrace();
            }
        }

    }
}
