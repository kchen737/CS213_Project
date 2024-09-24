package ruclinic;
import java.io.InputStream;
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
        if (commandLine.equals('Q')) {
            System.out.println("Scheduler is terminated");
            System.exit(1);
        }
        if(commandLine.equals("S")){
            scheduleNewAppointment();
        }


    }

    public static void main(String[] args){

        new Scheduler().run(args);
    }
    public static void scheduleNewAppointment(){
        System.out.println("Enter Appointment date: form mm/dd/yyyy");
        Scanner scheduleScanner = new Scanner(System.in);
        String enteredDate = scheduleScanner.nextLine();
        Date scheduledDate = new Date(enteredDate);
        System.out.println("Enter Timeslot:");
        System.out.println("9:00am: 1");
        System.out.println("10:45am: 2");
        System.out.println("11:15am: 3");
        System.out.println("1:30pm: 4");
        System.out.println("3:00pm: 5");
        System.out.println("5:15pm: 6");
        int enteredTimeslot = scheduleScanner.nextInt();
        System.out.println("Enter First Name");
        String firstName = scheduleScanner.nextLine();
        System.out.println("Enter Last Name");
        String lastName = scheduleScanner.nextLine();
        System.out.println("Enter Date of Birth; form: mm/dd/yyyy");
        Date dateOfBirth = new Date(scheduleScanner.nextLine());
        Profile newProfile = new Profile(firstName,lastName,dateOfBirth);



    }
}


