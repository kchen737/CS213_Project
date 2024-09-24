package ruclinic;

public class Visit {
    private Appointment appointment; //a reference to an appointment object
    private Visit next; //a ref. to the next appointment object in the list

    public Visit(Appointment appointment, Visit next){
        this.appointment = appointment;
        this.next = next;
    }
    public Visit getNext(){
        return this.next;
    }
    public Appointment getAppointment(){
        return this.appointment;
    }
    public void setNext(Visit next){
        this.next = next;
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Visit){
            Visit anotherVisit = (Visit) obj;
            return this.appointment.equals(anotherVisit.appointment)&&
                    this.next.equals(anotherVisit.next);
        }
        return false;
    }
}
