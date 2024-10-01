package ruclinic;
import java.util.StringTokenizer;
import java.util.Scanner;
/**
 * Scheduler class that process and combine other classes to read tokens from user
 * Use the token to generate output
 * Scheduler class will process scheduling, cancel appointment, reschedule, and print
 * @Author Kaiming Chen, Luke
 */

public class Scheduler {
    //Instances variables
    private static List list; //Used for keep track of appointments
    private static StringBuilder outputStr; //used to generate output
    private static Patient[] patients; //used to keep track of patients
    private static MedicalRecord medicalRecord; //used to keep track of patients stored in medical record with visits

    /**
     * Takes all the input from users and generate output based on the prompt
     * @param args takes from command line
     */
    public void run(String[] args){
        System.out.println("Scheduler is running.");
        Scanner scanner = new Scanner(System.in);
        String commandLine = "";
        StringTokenizer tokenizer = new StringTokenizer(commandLine, " ");
        outputStr = new StringBuilder();
        list = initialList();
        medicalRecord = initialMedicalRecord();
        while(!commandLine.equals("Q")){
            String firstToken;
            commandLine = scanner.nextLine();
            if(commandLine.isEmpty()){
                while(commandLine.isEmpty()){
                    commandLine = scanner.nextLine();
                }
                tokenizer = new StringTokenizer(commandLine, ",");
                firstToken = tokenizer.nextToken();
            }
            else{
                tokenizer = new StringTokenizer(commandLine, ",");
                firstToken = tokenizer.nextToken();
            }
            if(firstToken.equals("S"))scheduleNewAppointment(commandLine);
            else if(firstToken.equals("C"))cancelAppointment(commandLine);
            else if(firstToken.equals("R"))rescheduleAppointment(commandLine);
            else if(firstToken.equals("PA"))listByAppointment(commandLine);
            else if(firstToken.equals("PP"))listByPatient(commandLine);
            else if(firstToken.equals("PL"))listByLocation(commandLine);
            else if(firstToken.equals("PS")) listByBilling(commandLine);
            else if(firstToken.equals("Q")){}
            else  outputStr.append("\nInvalid command!\n");
            System.out.println(outputStr);
            outputStr = new StringBuilder();
        }
        if (commandLine.equals("Q")){
            //System.out.println(outputStr);
            System.out.println("Scheduler is terminated");
        }
    }

    /**
     * Checks for if there are six tokens for each command of S, C, and R
     * @param tokenizer
     * @return boolean value for if the token of each command is 6
     */
    public static boolean checkTokens(StringTokenizer tokenizer){
        int tokenCount = tokenizer.countTokens();
        if(tokenCount == 6){
            return true;
        }
        return false;
    }

    /**
     * Used by timeslot to check if an integer is entered
     * @param number
     * @return boolean value of true or false
     */
    public static boolean isInteger(String number){
        try{
            Integer.parseInt(number);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }

    /**
     * Used by timeslot to convert integer value entered in commandline into actual timeslot
     * @param number
     * @return timeslot
     */
    public static Timeslot convertIntTimeslot(String number){
        if (isInteger(number)){
            int num = Integer.parseInt(number);
            switch (num) {
                case 1 : return Timeslot.SLOT1;
                case 2 : return Timeslot.SLOT2;
                case 3 : return Timeslot.SLOT3;
                case 4 : return Timeslot.SLOT4;
                case 5 : return Timeslot.SLOT5;
                case 6 : return Timeslot.SLOT6;
            }
        }
        outputStr = outputStr.append(number + " is not a valid time slot.\n");
        return null;
    }

    /**
     * Used to check if the appointment date from the token is valid or not
     * @param date
     * @return Date
     */
    public static Date getAppointmentDate(String date){
        Date appointmentDate = new Date(date);
        boolean valid = appointmentDate.isValid();
        if(valid){
            String validStr = appointmentDate.isValidAppointmentDate();
            if (validStr != null){
                //System.out.println("Appointment date: "+ date + valid);
                outputStr = outputStr.append("Appointment date: " + date + validStr + "\n");
                return null;
            }
        }
        else{
            outputStr = outputStr.append("Appointment date: "+ date + " is not a valid calendar date.\n");
            return null;
        }
        return appointmentDate;
    }

    /**
     * Check of the date of birth from the token is valid or not
     * @param date
     * @return Date
     */
    public static Date getDOB(String date){
        Date DOBdate = new Date(date);
        boolean valid = DOBdate.isValid();
        if (valid){
            String validStr = DOBdate.isFuture();
            if (validStr != null){
                outputStr = outputStr.append("Patient dob: " + date + validStr + "\n");
                return null;
            }
        }
        if(!valid){
            String validStr = " is not a valid calendar date.";
            outputStr = outputStr.append("Patient dob: " + date + validStr + "\n");
            return null;
        }
        return DOBdate;
    }

    /**
     * To retrieve provider and not care about the case
     * @param name
     * @return provider
     */
    public static Provider getProvider(String name){
        if(name.equalsIgnoreCase(Provider.CERAVOLO.name())){
            return Provider.CERAVOLO;
        }
        else if(name.equalsIgnoreCase(Provider.HARPER.name())){
            return Provider.HARPER;
        }
        else if(name.equalsIgnoreCase(Provider.KAUR.name())){
            return Provider.KAUR;
        }
        else if(name.equalsIgnoreCase(Provider.LIM.name())){
            return Provider.LIM;
        }
        else if(name.equalsIgnoreCase(Provider.PATEL.name())){
            return Provider.PATEL;
        }
        else if(name.equalsIgnoreCase(Provider.RAMESH.name())){
            return Provider.RAMESH;
        }
        else if(name.equalsIgnoreCase(Provider.TAYLOR.name())){
            return Provider.TAYLOR;
        }
        else if(name.equalsIgnoreCase(Provider.ZIMNES.name())){
            return Provider.ZIMNES;
        }
        outputStr = outputStr.append(name+ " - provider doesn't exist.\n");
        //System.out.println(name+ " - provider doesn't exist.\n")
        return null;
    }
    public static List initialList(){
        Appointment appointmentList[] = new Appointment[1];
        list = new List(appointmentList, 0);
        return list;
    }

    /**
     * Adding to the list of appointments by checking if there is the appointment within the list or not
     * and see if the appointment is a valid appointment
     * @param appointment
     * @return List
     */
    public static List addToList(Appointment appointment){
        if (list.getSize()>0){
            if (list.contains(appointment)){
                //System.out.println("Appointment already booked");
                outputStr = outputStr.append(appointment.getProfile().getName() + " " + appointment.getDate().toString() + " has an existing appointment at the same timeslot.\n");
                return null;
            }
            if(list.providerIsAvailable(appointment)!=0){
                outputStr = outputStr.append(appointment.getProvider().toString() + " is not available at Slot " + list.providerIsAvailable(appointment) + ".\n");
                return null;
            }
            if(!list.patientIsAvailable(appointment)){
                outputStr = outputStr.append(appointment.getProfile().getName() + " " + appointment.getDate().toString() + " has an existing appointment at the same timeslot.\n");
                //System.out.println("Patient is not avaiable");
                return null;
            }
        }
        //System.out.println(appointment.toString());
        list.add(appointment);
        return list;

    }

    /**
     * Used to print valid scheduled appointments
     * @param appointment
     */
    public static void printBookedAppointment(Appointment appointment){
        String appointmentDate = appointment.getDate().toString();
        String timeslot = appointment.getTimeslot().toString();
        String name = appointment.getProfile().getName();
        String dob = appointment.getProfile().getDob().toString();
        String provider = appointment.getProvider().toString();
        outputStr = outputStr.append(appointmentDate + " " +  timeslot + " " + name + " " + dob + " " + provider + " booked.\n");
    }

    /**
     * Used to enter patient profile into the medical record
     * Checks to see if there is already existing patient in the medical record
     * if yes add to the visit
     * @param appointment
     */

    public static void enterPatientToMedicalRecord(Appointment appointment){
        boolean found = false;
        if (list.getSize()>1){
            //System.out.println(list.getAppointmentList().toString());
            for (int i=0; i<list.getSize()-1; i++){ //do not check for recently added to list
                if(appointment.getProfile().equals(list.getAppointmentList()[i].getProfile())){
                    //System.out.println("checked for existing patient and found in list");
                    Visit visit = new Visit(appointment, null);
                    for (int j=0; j<medicalRecord.getSize(); j++){
                        //System.out.println("medica record size is " + medicalRecord.getSize());
                        //System.out.println("what is in medical record is " + medicalRecord.getPatients()[i].getProfile().toString());
                        if(medicalRecord.getPatients()[j].getProfile().equals(appointment.getProfile())){
                            //System.out.println("checked for existing patient and found in medicalRecord");
                            found = true;
                            medicalRecord.getPatients()[j].getVisits().setNext(visit);
                            break;
                        }
                    }
                    break;
                }
            }
        }
        if(!found){
            //System.out.println("Adding new patient to medical Record");
            Visit visit = new Visit(appointment, null);
            Patient patient = new Patient(appointment.getProfile(), visit);
            medicalRecord.add(patient);
        }
    }
    //Initialize medical record
    public static MedicalRecord initialMedicalRecord(){
        patients = new Patient[1];
        MedicalRecord record = new MedicalRecord(patients, 0);
        return record;
    }

    /**
     * Schedules appointment
     * @param tokens from the command line
     */
    public static void scheduleNewAppointment(String tokens){
        StringTokenizer tokenizer = new StringTokenizer(tokens, ", ");
        //System.out.println(tokenizer.countTokens());
        tokenizer.nextToken();
        if(checkTokens(tokenizer)){
            //second token is the appointment date
            Date appointmentDate = getAppointmentDate(tokenizer.nextToken());
            if (appointmentDate != null){
                Timeslot timeslot = convertIntTimeslot((tokenizer.nextToken()));
                if (timeslot!=null){
                    String fname = tokenizer.nextToken();
                    String lname = tokenizer.nextToken();
                    Date DOBdate = getDOB(tokenizer.nextToken());
                    if (DOBdate != null){
                        Profile profile = new Profile(fname, lname, DOBdate);
                        Provider provider = getProvider(tokenizer.nextToken());
                        if (provider !=null){
                            Appointment appointment = new Appointment(appointmentDate, timeslot, profile, provider);
                            List returned = addToList(appointment);
                            if (returned!=null){
                                enterPatientToMedicalRecord(appointment);
                                printBookedAppointment(appointment);
                            }
                        }
                    }
                }
            }
        }
        else{
            outputStr = outputStr.append("Invalid command!\n");
        }
    }

    /**
     * Used by to cancel appointments
     * @param appointment
     */
    public static void cancelling(Appointment appointment){

        if(list.contains(appointment)){
            list.remove(appointment);
            //remove the appointment from medicalRecord, patient's visit
            //if patient's visit is null, remove from medicalRecord
            for (int i=0; i<medicalRecord.getSize(); i++){
                if(medicalRecord.getPatients()[i].getProfile().equals(appointment.getProfile())){
                    if(medicalRecord.getPatients()[i].getVisits()!=null){
                        medicalRecord.getPatients()[i].removeVisit(appointment);
                    }
                }
            }
            //add to outputStr
            outputStr = outputStr.append(appointment.getDate().toString() + " " + appointment.getTimeslot().toString() + " " + appointment.getProfile().toString() + " has been canceled.\n");
            System.out.println(appointment.getDate().toString() + " " + appointment.getTimeslot().toString() + " " + appointment.getProfile().toString() + " has been canceled.\n");
        }
        else{
            outputStr = outputStr.append(appointment.getDate().toString() + " " + appointment.getTimeslot().toString() + " " + appointment.getProfile().toString() + " does not exist.\n");
            System.out.println(appointment.getDate().toString() + " " + appointment.getTimeslot().toString() + " " + appointment.getProfile().toString() + " does not exist.\n");
        }

    }

    /**
     * Used to cancel Appointments
     * @param tokens read from command lines
     */
    public static void cancelAppointment(String tokens){
        StringTokenizer tokenizer = new StringTokenizer(tokens, ", ");
        tokenizer.nextToken();
        if(checkTokens(tokenizer)){
            while(tokenizer.hasMoreTokens()){
                Date appointmentDate = getAppointmentDate(tokenizer.nextToken());
                if (appointmentDate != null){
                    Timeslot timeslot = convertIntTimeslot((tokenizer.nextToken()));
                    if (timeslot!=null){
                        String fname = tokenizer.nextToken();
                        String lname = tokenizer.nextToken();
                        Date DOBdate = getDOB(tokenizer.nextToken());
                        if (DOBdate != null){
                            Profile profile = new Profile(fname, lname, DOBdate);
                            Provider provider = getProvider(tokenizer.nextToken());
                            if (provider!=null){
                                Appointment appointment = new Appointment(appointmentDate, timeslot, profile, provider);
                                cancelling(appointment);
                            }
                        }
                    }
                }
            }
        }
        else{
            outputStr = outputStr.append("Invalid command!\n");
        }
    }

    /**
     * Used to reschedule appoitnments with the appointment before and new timeslot
     * @param appointment from before
     * @param newTimeslot new time that want to reschedule to
     */
    public static void reschedulling(Appointment appointment, Timeslot newTimeslot){
        if(list.getAppointmentList()[0]!=null){
            if(list.contains(appointment)){
                Appointment rescheduledAppointment = new Appointment(appointment.getDate(), newTimeslot, appointment.getProfile(), appointment.getProvider());
                if(list.providerIsAvailable(rescheduledAppointment)!=0){
                    outputStr = outputStr.append(rescheduledAppointment.getProvider().toString() + " is not available at Slot " + list.providerIsAvailable(rescheduledAppointment) + ".\n");
                    return;
                }
                list.remove(appointment);
                list.add(rescheduledAppointment);
                outputStr = outputStr.append("Rescheduled to " + rescheduledAppointment.toString() + "\n");
            }
            else{
                outputStr = outputStr.append(appointment.getDate().toString() + " " + appointment.getTimeslot().toString() + " " + appointment.getProfile().toString() + " does not exist.\n");
            }
        }
        else{
            outputStr = outputStr.append(appointment.getDate().toString() + " " + appointment.getTimeslot().toString() + " " + appointment.getProfile().toString() + " does not exist.\n");
        }
    }

    /**
     * Used to reschedule an appointment
     * @param tokens read from command line
     */
    public static void rescheduleAppointment(String tokens){
        StringTokenizer tokenizer = new StringTokenizer(tokens, ", ");
        tokenizer.nextToken();
        if(checkTokens(tokenizer)){
            while(tokenizer.hasMoreTokens()){
                Date appointmentDate = getAppointmentDate(tokenizer.nextToken());
                if (appointmentDate != null){
                    Timeslot timeslot = convertIntTimeslot((tokenizer.nextToken()));
                    if (timeslot!=null){
                        String fname = tokenizer.nextToken();
                        String lname = tokenizer.nextToken();
                        Date DOBdate = getDOB(tokenizer.nextToken());
                        if (DOBdate != null){
                            Profile profile = new Profile(fname, lname, DOBdate);
                            Timeslot newtimeslot = convertIntTimeslot((tokenizer.nextToken()));
                            if (newtimeslot!= null){
                                Appointment appointment = list.findAppointmentGivenDateTimeslotAndProfile(appointmentDate,timeslot,profile);
                                if (appointment!= null){
                                    reschedulling(appointment, newtimeslot);
                                }
                                else{
                                    outputStr = outputStr.append(appointmentDate + " " + timeslot + " " + profile + " does not exist.\n");
                                }
                            }
                        }
                    }
                }

            }
        }
        else{
            outputStr = outputStr.append("Invalid command!\n");
        }

    }

    /**
     * Used to print by appointment
     * will print schedule calendar is empty if nothing is in the list
     * @param tokens
     */
    public static void listByAppointment(String tokens){
        if (list.getAppointmentList()[0] == null){
            outputStr = outputStr.append("The schedule calendar is empty.\n");
        }
        else{
            list.printByAppointment();
            outputStr = outputStr.append("** Appointments ordered by date/time/provider **\n");
            for (int i=0; i<list.getSize(); i++){
                outputStr = outputStr.append(list.getAppointmentList()[i].toString()+"\n");
            }
            outputStr = outputStr.append("** end of list **\n");
        }

    }

    /**
     * Used to print by patient/date/time
     * @param tokens read from command lines
     */
    public static void listByPatient(String tokens){
        if (list.getAppointmentList()[0] == null){
            outputStr = outputStr.append("The schedule calendar is empty.\n");
        }
        else{
            list.printByPatient();
            outputStr = outputStr.append("** Appointments ordered by patient/date/time **\n");
            for(int i=0; i<list.getSize(); i++){
                outputStr = outputStr.append(list.getAppointmentList()[i].toString()+"\n");
            }
            outputStr = outputStr.append("** end of list **\n");
        }

    }

    /**
     * Used to print by locations
     * @param tokens read from command lines
     */
    public static void listByLocation(String tokens){
        if (list.getAppointmentList()[0] == null){
            outputStr = outputStr.append("The schedule calendar is empty.\n");
        }
        else{
            outputStr = outputStr.append("** end of list **\n");
            list.printByLocation();
            outputStr = outputStr.append("** Appointments ordered by patient/date/time **\n");
            for(int i=0; i<list.getSize(); i++){
                outputStr = outputStr.append(list.getAppointmentList()[i].toString()+"\n");
            }
            outputStr = outputStr.append("** end of list **\n");
        }
    }

    /**
     * used to print by billling statement
     * @param tokens
     */
    public static void listByBilling(String tokens){
        outputStr = outputStr.append("** Billing statement ordered by patient **\n");
        if(medicalRecord.getPatients()!=null){
            for(int i=0; i<medicalRecord.getSize(); i++){
                outputStr = outputStr.append("("+(i+1)+") ");
                outputStr = outputStr.append(medicalRecord.getPatients()[i].getProfile().toString() + " ");
                outputStr = outputStr.append("[amount due: $"+medicalRecord.getPatients()[i].charge()+"]\n");
            }
        }
        outputStr = outputStr.append("** end of list **\n");
    }

    /**
     * testbed main used for testcases
     * @param args
     */
    public static void main(String[] args){
        //testRemoveVisit();
        new Scheduler().run(args);
        Date date = new Date("01/01/2020");
        Timeslot timeslot = Timeslot.SLOT2;
        Profile profile = new Profile("c", "l", date);
        Provider provider = Provider.HARPER;
        Appointment appointment1 = new Appointment(date, timeslot, profile, provider);
    }
    //test case
    public static void testRemoveVisit(){
        Date date1 = new Date("01/01/2020");
        Date date2 = new Date("04/03/2020");
        Date date3 = new Date("01/01/2021");
        Date date4 = new Date("03/14/2023");
        Timeslot timeslot1 = Timeslot.SLOT4;
        Profile profile1 = new Profile("c", "l", date1);
        Provider provider1 = Provider.HARPER;

        Appointment appointment1 = new Appointment(date1, timeslot1, profile1, provider1);
        Appointment appointment2 = new Appointment(date2, timeslot1, profile1, provider1);
        Appointment appointment3 = new Appointment(date3, timeslot1, profile1, provider1);
        Appointment appointment4 = new Appointment(date4, timeslot1, profile1, provider1);

        Visit visit = new Visit(appointment1, null);
        Visit visit1 = new Visit(appointment2, visit);
        Visit visit2 = new Visit(appointment3, visit1);
        Visit visit3 = new Visit(appointment4, visit2);
        Patient patient1 = new Patient(profile1, visit3);

        patient1.removeVisit(appointment1);

    }


}



