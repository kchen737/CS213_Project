package ruclinic;

public class Visit {
    private Appointment appointment; //a reference to an appointment object
    private Visit next; //a ref. to the next appointment object in the list

    public Visit getNext() {
        return next;
    }
    public Appointment getAppointment(){
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public void setNext(Visit next) {
        this.next = next;
    }
}
