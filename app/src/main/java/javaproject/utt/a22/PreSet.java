package javaproject.utt.a22;

/**
 * Classe permettant une auto-initialisation des pions ...
 */
public class PreSet{
    public static void main(String[] args) {
        
    }

    /**
     * Methode pour clear la console.
     * Cette methode est utile lors de l'utilisation de l'application en console.
     */
    public static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Methode pour temporisation.
     * @param time
     */
    public static void tempo(int time){
        try{
            Thread.sleep(time);
        } catch(Exception e){

        }
    }
}
