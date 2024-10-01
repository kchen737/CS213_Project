package ruclinic;

/**
 * Visit class with appointment and visit next, visit is a linkedlist that stores all the appointments
 * @Author Kaiming Chen
 */

public class Visit {
    private Appointment appointment; //a reference to an appointment object
    private Visit next; //a ref. to the next appointment object in the list

    //Initiate the class with two parameters
    public Visit(Appointment appointment, Visit next){
        this.appointment = appointment;
        this.next = next;
    }

    /**
     * used by other classes to getNext()
     * @return visit
     */
    public Visit getNext(){
        return this.next;
    }
    public Appointment getAppointment(){
        return this.appointment;
    }
    public void setNext(Visit next){
        this.next = next;
    }

    /**
     * Overriding equals in order to compare two visits to see if they are actually equal
     * compares appointment and next in the linkedlist
     * @param obj
     * @return boolean true if equal, false if otherwise
     */
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
