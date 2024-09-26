package ruclinic;

/**
 * You can add necessary constants, constructors, and methods. However, you CANNOT change or add
 * instance variables. -2 points for each violation.
 * You MUST override equals(), toString() and the comapreTo() methods, with the @Override tags. -2
 * points for each violation. The equals() method returns true if two appointments have the same date,
 * timeslot, and patient; returns false otherwise. The toString() method returns a textual representation of an
 * appointment in the following formats.
 */


public class Appointment {
    private Date date;
    private Timeslot timeslot;
    private Profile patient;
    private Provider provider;

    public Date getDate(){
        return date;
    }
    public Timeslot getTimeslot(){
        return timeslot;
    }
    public Profile getProfile(){
        return patient;
    }
    public Provider getProvider(){
        return provider;
    }
    public Appointment(Date date, Timeslot timeslot, Profile patient, Provider provider){
        this.date = date;
        this.timeslot = timeslot;
        this.patient = patient;
        this.provider = provider;
    }

    @Override
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
                this.patient.equals(otherAppointment.patient) &&
                this.provider.equals(otherAppointment.provider);
    }

    @Override
    public String toString(){

        String dateStr = date.toString();
        String timeStr = timeslot.toString();
        String proStr = patient.toString();
        String provStr = provider.toString();
        String s = dateStr +" " +  timeStr + " " + proStr + provStr;
        return s;
    }



    public static void main(String[] args){

        testToString();
    }

    private static void testToString(){
        Date date1 = new Date("01/01/2020");
        Timeslot timeslot1 = Timeslot.SLOT4;
        Profile profile1 = new Profile("c", "l", date1);
        Provider provider1 = Provider.Harper;

        Appointment appointment1 = new Appointment(date1, timeslot1, profile1, provider1);
        System.out.println(appointment1.toString());
    }

}
