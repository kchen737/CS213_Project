package ruclinic;
import java.util.StringTokenizer;
import java.util.Scanner;
/**
 *
 */

public class Scheduler {
    public void run(String[] args){
        System.out.println("Scheduler is running.");
        Scanner scanner = new Scanner(System.in);
        String commandLine = String.join(" ", args);
        StringTokenizer tokenizer = new StringTokenizer(commandLine, " ");
        while(!commandLine.equals("Q")){
            commandLine = scanner.nextLine();
            tokenizer = new StringTokenizer(commandLine, " ");
            //check to see what does the command begin with, could be C, R, PA, PP, PL, PS
            while (tokenizer.hasMoreTokens()){
                System.out.println(tokenizer.nextToken());
            }
        }


    }

    public static void main(String[] args){

        new Scheduler().run(args);
    }
}
