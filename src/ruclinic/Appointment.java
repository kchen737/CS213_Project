package ruclinic;

/**
 *
 *
 *
 *
 * @Author Kaiming Chen
 * You can add necessary constants, constructors, and methods. However, you CANNOT change or add
 * instance variables. -2 points for each violation.
 * You MUST override equals(), toString() and the comapreTo() methods, with the @Override tags. -2
 * points for each violation. The equals() method returns true if two appointments have the same date,
 * timeslot, and patient; returns false otherwise. The toString() method returns a textual representation of an
 * appointment in the following formats.
 */


public class Appointment implements Comparable<Appointment> {
    private Date date;
    private Timeslot timeslot;
    private Profile patient;
    private Provider provider;

    //Getter and setter methods for each instance variables

    /**
     * getter medthod of date
     * @return date of appointment
     */
    public Date getDate(){
        return date;
    }

    /**
     * getter method of timeslot
     * @return timeslot
     */
    public Timeslot getTimeslot(){
        return timeslot;
    }

    /**
     * getter method of profile
     *
     * @return profile
     */
    public Profile getProfile(){
        return patient;
    }

    /**
     * getter method of provider
     * @return provider
     */
    public Provider getProvider(){
        return provider;
    }

    public Appointment(Date date, Timeslot timeslot, Profile patient, Provider provider){
        this.date = date;
        this.timeslot = timeslot;
        this.patient = patient;
        this.provider = provider;
    }

    /**
     *  takes integer and returns corresponding timeslot
     * @param numberOfTimeslot integer representation of timeslot
     * @return timeslot
     */
    public String returnTime(int numberOfTimeslot){

        return switch (numberOfTimeslot) {
            case 1 -> Timeslot.SLOT1.toString();
            case 2 -> Timeslot.SLOT2.toString();
            case 3 -> Timeslot.SLOT3.toString();
            case 4 -> Timeslot.SLOT4.toString();
            case 5 -> Timeslot.SLOT5.toString();
            case 6 -> Timeslot.SLOT6.toString();
            default -> "is not a valid time slot";
        };

    }

    @Override
    idk how this one works
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }
        if (obj==null || getClass() != obj.getClass()){
            return false;
        }
        Appointment otherAppointment = (Appointment) obj;

        return this.date.equals(otherAppointment.date) &&
                this.timeslot.equals(otherAppointment.timeslot) &&
                this.patient.equals(otherAppointment.patient);
    }

    /**
     * gets string representation of appointment for output
     * @return string representation of appointment
     */
    @Override
    public String toString(){

        String dateStr = date.toString();
        String timeStr = timeslot.toString();
        String proStr = patient.toString();
        String provStr = provider.toString();
        String s = dateStr +" " +  timeStr + " " + proStr + provStr;
        return s;
    }

    //Sort by appointment date, time then provider's name

    /**
     *
     * @param anotherappointment the object to be compared.
     * @return integer of whether greater, lesser or equal, 1,-1,0 respectively
     */
    @Override
    public int compareTo(Appointment anotherappointment){
        int dateComparison = this.date.compareTo(anotherappointment.date);
        int timeComparison = this.timeslot.compareTo(anotherappointment.timeslot);
        int providerComparison = this.provider.name().compareToIgnoreCase(anotherappointment.provider.name());
        if (dateComparison<0){
            return -1;
        }
        else if (dateComparison==0 && timeComparison <0){
            return -1;
        }
        else if(dateComparison==0 && timeComparison == 0 && providerComparison<0){
            return -1;
        }
        else if(dateComparison==0 && timeComparison == 0 && providerComparison==0){
            return 0;
        }

        return 1;
    }

    /**
     * tester method of comparison class
     * @param args
     */
    public static void main(String[] args){

        testToString();
        testCompareTo();
    }

    private static void testToString(){
        Date date1 = new Date("01/01/2020");
        Timeslot timeslot1 = Timeslot.SLOT4;
        Profile profile1 = new Profile("c", "l", date1);
        Provider provider1 = Provider.PATEL;

        Appointment appointment1 = new Appointment(date1, timeslot1, profile1, provider1);
        System.out.println(appointment1.toString());
    }

    private static void testCompareTo(){
        Date date1 = new Date("01/01/2020");
        Timeslot timeslot1 = Timeslot.SLOT4;
        Profile profile1 = new Profile("c", "l", date1);
        Provider provider1 = Provider.PATEL;

        Provider provider2 = Provider.LIM;
        Appointment appointment1 = new Appointment(date1, timeslot1, profile1, provider1);
        Appointment appointment2 = new Appointment(date1, timeslot1, profile1, provider2);
        int compare = appointment1.compareTo(appointment2);
        System.out.println(compare);
    }

}
