package ruclinic;

public class List {


    private Appointment[] appointments;
    private int size; //number of appointments in the array, the indicies for the appointment will be size-1


    //helper method
    private int find(Appointment appointment){
        for (int i =0; i<size; i++){
            if(appointment.equals(appointments[i])){
                return i;
            }
        }
       return 1;
    }
    //helper method to increase the capacity by 4
    private void grow(){
        Appointment [] appointments = new Appointment[size+4];

    }
    //check before add/remove
    public boolean contains(Appointment appointment){
        boolean check = true;
        for(int i=0; i<size; i++){
            if(appointment.equals(appointments[i])){
            }
            else{
                check = false;
            }
        }
        return check;
    }
    public void add(Appointment appointment){
        Date date = new Date("01/01/2020");
        Timeslot timeslot = Timeslot.SLOT2;
        Profile profile = new Profile("cindy", "lin", date);
        Provider provider = Provider.Harper;
        appointments[size] = appointment;
        size++;

    }



    public void remove(Appointment appointment){
        for (int i=0; i<size; i++){
            if (appointment.equals(appointments[i])){
                appointments[i] = null;
                appointments[i] = appointments[size-1];
                size--;
            }
        }

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
