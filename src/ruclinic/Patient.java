package ruclinic;
/**
@author LBSaka
 */
public class Patient {
    private Profile profile;
    private  Visit visits;
    public int charge() {
        while (visits.getNext()!=null){
            Visit newVisit = visits.getNext();
            Appointment appointment = visits.getAppointment();
            Provider provider = appointment.getProvider();

        }
        return 1;
    }

    @Override
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }
        Patient otherPatient = (Patient) obj;

        return this.profile.equals(otherPatient.profile) &&
                this.visits.equals(otherPatient.visits);
    }
}

