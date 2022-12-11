import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class test {

    public static void main(String[] args){
        String [] team = {"ISI", "RT", "A2I", "GI", "GM", "MTE", "MM"};
        ArrayList<String> testList = new ArrayList<String>();
        Scanner myObj = new Scanner(System.in);
        System.out.println("Repond ?");
        String situation = myObj.nextLine();  // Read user input
        System.out.println(situation);
        for(int i=0;i< team.length;i++){
            testList.add(team[i]);
        }
        testList.remove(situation);
        System.out.println(testList);
    }


}
