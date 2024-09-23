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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Profile getPatient() {
        return patient;
    }

    public void setPatient(Profile patient) {
        this.patient = patient;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }
    public Appointment(Date nDate, Timeslot nTimeslot, Profile nProfile, Provider nProvider){
        date=nDate;
        timeslot=nTimeslot;
        patient=nProfile;
        provider=nProvider;
    }
}
