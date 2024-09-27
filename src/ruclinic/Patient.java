package ruclinic;

/**
 * @author LBSaka, Kaiming Chen
 */
public class Patient {
    private Profile profile;
    private Visit visits;

    public int charge() {
        int cost = 0;
        while (visits!= null) {
            Appointment appointment = visits.getAppointment();
            Provider provider = appointment.getProvider();
            cost = cost + provider.getCharge();
            System.out.println(cost);
            Visit newVisit = visits.getNext();
            if (newVisit == null){
                break;
            }
            visits = newVisit;

        }
        return cost;
    }

    public Profile getProfile() {
        return profile;
    }

    public Visit getVisits() {
        return visits;
    }

    public int getCharge() {
        return this.charge();
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setVisits(Visit visits) {
        this.visits = visits;
    }
    public Patient(Profile profile, Visit visits){
        this.profile = profile;
        this.visits = visits;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Patient) {
            Patient otherPatient = (Patient) obj;

            return this.profile.equals(otherPatient.profile) &&
                    this.visits.equals(otherPatient.visits);
        }
        return false;

    }
    @Override
    public String toString(){
        return profile.toString();
    }
    public static void main(String[] args){
        testCalculatingCharge();

    }
    private static void testCalculatingCharge(){
        Date date1 = new Date("01/01/2020");
        Timeslot timeslot1 = Timeslot.SLOT4;
        Profile profile1 = new Profile("c", "l", date1);
        Provider provider1 = Provider.HARPER;

        Appointment appointment1 = new Appointment(date1, timeslot1, profile1, provider1);
        Appointment appointment2 = new Appointment(date1, timeslot1, profile1, provider1);
        Visit visit = new Visit(appointment2, null);
        Visit visit1 = new Visit(appointment1, visit);
        Patient patient1 = new Patient(profile1, visit1);
        int cost = patient1.charge();
        System.out.println(cost);
    }
}

