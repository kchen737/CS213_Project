package ruclinic;

public class List {


    private Appointment[] appointments;
    private int size; //number of appointments in the array


    //helper method
    private int find(Appointment appointment){

       return 1;
    }
    //helper method to increase the capacity by 4
    private void grow(){

    }
    //check before add/remove
    public boolean contains(Appointment appointment){
        return false;
    }
    public void add(Appointment appointment){
        Date date = new Date("01/01/2020");
        Timeslot timeslot = Timeslot.SLOT2;
        Profile profile = new Profile("cindy", "lin", date);
        //provider
        //location country zip (strings)
        Location location;
        Specialty specialty;
        Provider provider = new Provider(location, specialty);
        //specialty charge int
        appointment(date, timeslot, profile, provider);

    }
    public void remove(Appointment appointment){

    }
    //ordered by patient profile, date/timeslot
    public void printByPatient(){

    }
    //ordered by county, date/timeslot
    public void printByLocation(){

    }
    //ordered by date/timeslot, provider name
    public void printByAppointment(){

    }




}
