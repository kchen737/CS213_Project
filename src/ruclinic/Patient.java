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

    public Profile getProfile() {
        return profile;
    }

    public Visit getVisits() {
        return visits;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setVisits(Visit visits) {
        this.visits = visits;
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

