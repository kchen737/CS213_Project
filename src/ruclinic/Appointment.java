package ruclinic;

/**
 * You can add necessary constants, constructors, and methods. However, you CANNOT change or add
 * instance variables. -2 points for each violation.
 * You MUST override equals(), toString() and the comapreTo() methods, with the @Override tags. -2
 * points for each violation. The equals() method returns true if two appointments have the same date,
 * timeslot, and patient; returns false otherwise. The toString() method returns a textual representation of an
 * appointment in the following formats.
 * 10/30/2024 10:45 AM John Doe 12/13/1989 [PATEL, BRIDGEWATER, Somerset 08807, FAMILY]
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
    //Overrides .equals method for Appointment class to compare if the date, timeslot, patient matches
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Appointment){
            Appointment otherAppointment = (Appointment) obj;
            return this.date.equals(otherAppointment.date) &&
                    this.timeslot.equals(otherAppointment.timeslot) &&
                    this.patient.equals(otherAppointment.patient) &&
                    this.provider.equals(otherAppointment.provider);
        }
        return false;

    }

    @Override
    public String toString(){
        String str = "hello" ;
        return str;
    }


}
