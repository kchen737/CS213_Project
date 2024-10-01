package ruclinic;

/**
 * Patient class with profile and visits
 * @author LBSaka, Kaiming Chen
 */
public class Patient implements Comparable<Patient>{
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

    /**
     * Used to remove visit from the linkedlist
     * @param appointment
     */
    public void removeVisit(Appointment appointment){
        if(visits == null){
            return;
        }
        if (visits.getAppointment().equals(appointment)){
            visits = visits.getNext();
            return;
        }
        Visit newVisitList = visits;
        Visit prev = null;
        while(newVisitList != null && !(newVisitList.getAppointment().equals(appointment))){
            prev = newVisitList;
            newVisitList = newVisitList.getNext();
        }
        if(newVisitList == null){
            return;
        }
        prev.setNext(newVisitList.getNext());

    }

    /**
     * get profile
     * @return profile
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * get visit
     * @return visit
     */
    public Visit getVisits() {
        return visits;
    }

    /**
     * get charge
     * @return integer
     */
    public int getCharge() {
        return this.charge();
    }

    /**
     * set profile
     * @param profile
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * set visits
     * @param visits
     */
    public void setVisits(Visit visits) {
        this.visits = visits;
    }

    /**
     * Initiate patient
     * @param profile
     * @param visits
     */
    public Patient(Profile profile, Visit visits){
        this.profile = profile;
        this.visits = visits;
    }

    /**
     * Overriding equals method to see if two patients are the same
     * @param obj patient
     * @return boolean of true or false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Patient) {
            Patient otherPatient = (Patient) obj;

            return this.profile.equals(otherPatient.profile) &&
                    this.visits.equals(otherPatient.visits);
        }
        return false;

    }

    /**
     * Overriding toString() to return patient in correct format
     * @return string of patient
     */
    @Override
    public String toString(){
        return profile.toString();
    }
    public static void main(String[] args){
        //testCalculatingCharge();
        //testRemoveVisit();

    }

    /**
     * Overriding compareTo
     * @param OtherPatient the object to be compared.
     * @return integer
     */
    @Override
    public int compareTo(Patient OtherPatient){
        return this.profile.compareTo(OtherPatient.profile);
    }
    //test case to check charge
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
    //test case to test remove visit
    public static void testRemoveVisit(){
        Date date1 = new Date("01/01/2020");
        Date date2 = new Date("04/03/2020");
        Date date3 = new Date("01/01/2021");
        Timeslot timeslot1 = Timeslot.SLOT4;
        Profile profile1 = new Profile("c", "l", date1);
        Provider provider1 = Provider.HARPER;

        Appointment appointment1 = new Appointment(date1, timeslot1, profile1, provider1);
        Appointment appointment2 = new Appointment(date1, timeslot1, profile1, provider1);
        Appointment appointment3 = new Appointment(date1, timeslot1, profile1, provider1);

        Visit visit = new Visit(appointment2, null);
        Visit visit1 = new Visit(appointment1, visit);
        Visit visit2 = new Visit(appointment3, visit1);
        Patient patient1 = new Patient(profile1, visit2);
        patient1.removeVisit(appointment1);

    }
}

