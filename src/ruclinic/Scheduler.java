package ruclinic;
import java.io.InputStream;
import java.sql.Time;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.Scanner;
/**
 * @author Luke Alexander Busacca
 */

public class Scheduler {
    public void run(String[] args){
        System.out.println("Scheduler is running.");
        List list = new List();
        boolean breaker=false;
        while (!breaker){
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()&&!breaker){
                StringTokenizer commandLineInput = new StringTokenizer(scanner.nextLine());
                String functionSelect = commandLineInput.nextToken();
                if (functionSelect.equals("S")) list=scheduleNewAppointment(commandLineInput,list);
                else if (functionSelect.equals("C")) list=cancelAppointment(commandLineInput,list);
                else if (functionSelect.equals("R")) list=rescheduleAppointment(commandLineInput,list);
                else if (functionSelect.equals("PA")) outputP(list);
                else if (functionSelect.equals("PP")) outputA(list);
                else if (functionSelect.equals("PL")) outputL(list);
                else if (functionSelect.equals("PS")) outputS(list);
                else if (functionSelect.equals("Q")) breaker=true;
                else System.out.println("Invalid command!");
            }
        }
    System.out.println("Scheduler is terminated.");
        return;
    }


    public static void main(String[] args){

        new Scheduler().run(args);
    }
/*
    /**
     * schedules new appointment(line by line approach)
     * @param list
     * @return

    public List scheduleNewAppointment(List list){
        Date scheduledDate = getAppointmentDate();
        Timeslot enteredTimeslot = getTimeslot();
        Profile profile = getProfile();
        Provider provider = getProvider();
        Appointment appointment= new Appointment(scheduledDate,enteredTimeslot,profile,provider);
        list.add(appointment);
        return list;
    }

    /**
     * Should search list for appointment given only profile and date and time. Not yet feasible with list class's search and removal features.
     * @param list
     * @return list with appointment removed(if there even was one lol)
     *
    public  List cancelAppointment(List list){
        Date scheduledDate = getAppointmentDate();
        Timeslot enteredTimeslot = getTimeslot();
        Profile profile = getProfile();
        if(list.findAppointmentGivenDateTimeslotAndProfile(scheduledDate,enteredTimeslot,profile)==null)return list;
        else list.remove(list.findAppointmentGivenDateTimeslotAndProfile(scheduledDate,enteredTimeslot,profile));
        return list;
    }

    /**
     * reschedules appointment, Line by line input
     * @param list
     * @return
     *
    public  List rescheduleAppointment(List list){
        Date scheduledDate = getAppointmentDate();
        Timeslot enteredTimeslot = getTimeslot();
        Profile profile = getProfile();
        if(list.findAppointmentGivenDateTimeslotAndProfile(scheduledDate,enteredTimeslot,profile)==null) {
            System.out.println("Appointment not found");
            return list;
        }
        Appointment originalAppointment = list.findAppointmentGivenDateTimeslotAndProfile(scheduledDate,enteredTimeslot,profile);
        Provider provider = originalAppointment.getProvider();
        list.remove(originalAppointment);
        Appointment appointment = new Appointment(scheduledDate,getTimeslot(),profile,provider);
        return list;
    }
*/
    /**
     * schedules appointment with full single line input
     *
     * @param input
     * @param list
     * @return
     */
    public  List scheduleNewAppointment(StringTokenizer input, List list){
        if(input.countTokens()!=6){
            System.out.println("Invalid command!");
            return list;
        }
        Date appointmentDate = new Date(input.nextToken());
        if(!dateCheck(appointmentDate)) return list;
        String timeInput = input.nextToken();
        Timeslot timeslot = Timeslot.getTimeSlot(Integer.parseInt(timeInput));
        if(timeslot==(null)){
            System.out.println(timeInput+ "is not a valid timeslot.");
            return list;
        }
        String fn = input.nextToken();
        String ln = input.nextToken();
        Date dob = new Date(input.nextToken());
        if(!dob.isValid()){
            System.out.println("Patient dob: "+dob.toString()+ "is not a valid calendar date.");
        }
        if(dob.isToday()|| dob.isFuture()){
            System.out.println("Patient dob: "+dob.toString()+ " is today or a date after today");
            return list;
        }
        Profile profile = new Profile(fn,ln,dob);
        String provInput = input.nextToken();
        Provider provider = Provider.getProv(provInput);
        if(provider==(null)){
            System.out.println(provInput+" is not a valid time slot.");
            return list;
        }
        if(!list.providerIsAvailable(provider,appointmentDate,timeslot)){
            System.out.println(provider.toString()+" is not available at" + timeslot.toString());
        }
        Appointment appointment = new Appointment(appointmentDate,timeslot,profile,provider);
        list.add(appointment);
        System.out.println(appointment.toString()+" booked.");
        return list;
    }

    /**
     * cancels appointment(one line approach) if no appointment does nothing
     * @param input
     * @param list
     * @return
     */
    public  List cancelAppointment(StringTokenizer input, List list){
        if(input.countTokens()!=4){
            System.out.println("Invalid command.");
            return list;
        }
        Date appointmentDate = new Date(input.nextToken());
        if(!dateCheck(appointmentDate))return list;
        Timeslot timeslot = Timeslot.getTimeSlot(Integer.parseInt(input.nextToken()));
        if(timeslot==null){
            System.out.println("invalid timeslot");
            return list;
        }
        String fn = input.nextToken();
        String ln = input.nextToken();
        Date dob = new Date(input.nextToken());
        if(!dob.isValid()){
            System.out.println("Patient dob: "+dob.toString()+ "is not a valid calendar date.");
        }
        if(dob.isToday()|| dob.isFuture()){
            System.out.println("Patient dob: "+dob.toString()+ " is today or a date after today");
            return list;
        }
        Profile profile = new Profile(fn,ln,dob);
        if(list.findAppointmentGivenDateTimeslotAndProfile(appointmentDate,timeslot,profile)==null){
            System.out.println(appointmentDate.toString()+" "+timeslot.toString()+" "+profile.toString()+" does not exist.");
            return list;
        }
        Appointment appointment = list.findAppointmentGivenDateTimeslotAndProfile(appointmentDate,timeslot,profile);
        System.out.println(appointmentDate.toString()+" "+timeslot.toString()+" "+profile.toString()+" has been canceled.");
        list.remove(appointment);
        return list;
    }

    /**
     * this lets the user reschedule an appointment.
     * Method for one line input
     * @param inputs the command line inputs
     * @param list the list of all appointments
     * @return the same list with updated appointment
     */
    public  List rescheduleAppointment(StringTokenizer inputs, List list){
        if (inputs.countTokens()!=6){
            System.out.println("Invalid command.");
        }
        Date date = new Date(inputs.nextToken());
        if(!dateCheck(date)) return list;
        Timeslot timeslot = Timeslot.getTimeSlot(Integer.parseInt(inputs.nextToken()));
        if (timeslot==null){
            System.out.println("invalid timeslot");
            return list;
        }
        String fn = inputs.nextToken();
        String ln = inputs.nextToken();
        Date dob = new Date(inputs.nextToken());
        if(!dob.isValid()){
            System.out.println("Patient dob: "+dob.toString()+ "is not a valid calendar date.");
        }
        if(dob.isToday()|| dob.isFuture()){
            System.out.println("Patient dob: "+dob.toString()+ " is today or a date after today");
            return list;
        }
        Profile profile = new Profile(fn,ln,dob);
        Provider provider= (list.findAppointmentGivenDateTimeslotAndProfile(date,timeslot,profile)).getProvider();
        list.remove(list.findAppointmentGivenDateTimeslotAndProfile(date,timeslot,profile));
        Appointment appointment = new Appointment(date,Timeslot.getTimeSlot(Integer.parseInt(inputs.nextToken())),profile,provider);
        System.out.println("Rescheduled to "+ appointment.toString());
        return list;

    }

    public boolean dateCheck(Date appointmentDate){
        if(!appointmentDate.isValid()) {
            System.out.println("Appointment date: "+appointmentDate.toString()+" is not a valid calendar date.");
            return false;
        }
        if(!appointmentDate.isFuture()){
            System.out.println("Appointment date: "+ appointmentDate.toString()+" is today or a date before today.");
            return false;
        }
        if(appointmentDate.satOrSun()){
            System.out.println("Appointment date: "+appointmentDate.toString()+" is Saturday or Sunday.");
            return false;
        }
        if(appointmentDate.notSixMonth()){
            System.out.println("Appointment date: "+appointmentDate.toString()+" is not within six months.")
            return false;
        }
        return true;
    }

    /**
     * this prints out all appointments in order of appointment date and is called when user types in "PA"
     * @param list
     */
    public  void outputA(List list){
        list.printByAppointment();
    }

    /**
     * this prints out all appointments in order of patient name and is called when user types in "PP"(LOL)
     * @param list
     */
    public  void outputP(List list){
        list.printByPatient();
    }

    /**
     * this prints out all appointments in the list and is called when the user types in "PL"
     * @param list
     */
    public  void outputL(List list){
        list.printByLocation();
    }

    /**
     * this one is supposed to print out all the charges of Patients and is called when user types in "PS"
     * @param list
     */
    public  void outputS(List list){
        list.printCharges();
    }

    /**
     * this method reads from the user input and returns a string. it saves me from having to make a new scanner everytime >_<
     * @return
     */
    public String readIn(){
        Scanner scanner = new Scanner(System.in);
        return scanner.toString();
    }

    /**
     * this is a method used for reciving the timeslot number from a user commandline input and returning the timeslot associated with it.
     * FUN FEATURE: PROVIDES USER ALL TIMESLOTS' INDICES FOR EASE OF ACCESS ! WOOHOO
     * @return
     */
    public Timeslot getTimeslot(){
        System.out.println("Enter Timeslot:");
        System.out.println("9:00am: 1");
        System.out.println("10:45am: 2");
        System.out.println("11:15am: 3");
        System.out.println("1:30pm: 4");
        System.out.println("3:00pm: 5");
        System.out.println("5:15pm: 6");
        return Timeslot.getTimeSlot(Integer.parseInt(readIn()));
    }

    /**
     * this method gets the provider from a commandLine input, for usage when taking data from multiple lines of commands
     * @return
     */
    public Provider getProvider(){
        while(true){
            String egg = readIn();
            if (Provider.isReal(egg)) return getProvider();
            System.out.println("enter the name of a provider");
        }
    }

    /**
     * this here methoderino goes and asks our beloved user to input the date of the desired appointment and return the date when a valid date is returned.
     * if an invalid date is entered the user is prompted to enter the date again
     * @return
     */
    public Date getAppointmentDate(){
        while (true) {
            System.out.println("enter Appointment date");
            Date date = new Date(readIn());
            if(date.isValid()&&date.isFuture()&& !date.satOrSun()&& !date.notSixMonth())return date;
            System.out.println("Invalid date, try again");
        }
    }
    public Profile getProfile(){
        System.out.println("enter First name");
        String fn = readIn();
        System.out.println("enter last name");
        String ln = readIn();
        System.out.println("enter Date of Birth");
        Date dob = new Date(readIn());
        Profile profile= new Profile(fn,ln,dob);
        return profile;
    }
}



