package ruclinic;

/**
 * @Author Kaiming Chen
 */

public class List {


    private Appointment[] appointments;
    private int size; //number of appointments in the array, the indicies for the appointment will be size-1

    public List(Appointment[] appointments, int size){
        this.appointments = appointments;
        this.size = size;
    }
    public int getSize(){
        return this.size;
    }
    public Appointment[] getAppointmentList(){
        return this.appointments;
    }

    //helper method
    private int find(Appointment appointment){
        for (int i =0; i<size; i++){
            if(appointment.equals(appointments[i])){
                return i;
            }
        }
        return -1;
    }
    //helper method to increase the capacity by 4
    private void grow(){
        Appointment [] appointment = new Appointment[size+4];
        for(int i = 0; i<=size;i++){
            appointment[i]=this.appointments[i];
        }
        this.appointments=appointment;
    }


    //check before add/remove
    public boolean contains(Appointment appointment){
        boolean check = false;
        for(int i=0; i<size; i++){

            if(appointment.equals(appointments[i])){
                check = true;
                break;
            }
        }
        return check;
    }
    public void add(Appointment appointment){
        this.appointments[size] = appointment;
        if (this.size<appointments.length){
            grow();
        }
        size++;

    }

    public void initiateList(){
        this.appointments = new Appointment[10];
    }



    public void remove(Appointment appointment){
        for (int i=0; i<size; i++){
            if (appointment.equals(appointments[i])){
                appointments[i] = null;
                appointments[i] = appointments[size-1];
                size--;
            }
        }
        /*
        int index = find(appointment);
        appointments[index] = null;
        appointments[index] = appointments[size-1];
        size--;
        */

    }
    //ordered by patient profile, date/timeslot
    public void printByPatient(){
        for(int i=0; i<size; i++){
            for (int j=i+1; j<size; j++){
                if(appointments[i].getProfile().compareTo(appointments[j].getProfile())>0){
                    Appointment temp = appointments[i];
                    appointments[i] = appointments[j];
                    appointments[j] = temp;
                }
                else if(appointments[i].getProfile().compareTo(appointments[j].getProfile())==0){
                    if(appointments[i].getDate().compareTo(appointments[j].getDate())==0){
                        if(appointments[i].getTimeslot().compareTo(appointments[j].getTimeslot())>0){
                            Appointment temp = appointments[i];
                            appointments[i] = appointments[j];
                            appointments[j] = temp;
                        }
                    }
                    else if(appointments[i].getDate().compareTo(appointments[j].getDate())>0){
                        Appointment temp = appointments[i];
                        appointments[i] = appointments[j];
                        appointments[j] = temp;
                    }
                }
            }
        }
    }
    //ordered by county, date/timeslot
    public void printByLocation(){
        for(int i=0; i<size; i++){
            for(int j=i+1; j<size; j++){
                if(appointments[i].getProvider().getLocation().compareTo(appointments[j].getProvider().getLocation())>0){
                    Appointment temp = appointments[i];
                    appointments[i] = appointments[j];
                    appointments[j] = temp;
                }
                else if(appointments[i].getProvider().getLocation().compareTo(appointments[j].getProvider().getLocation())==0){
                    if (appointments[i].getDate().compareTo(appointments[j].getDate())>0){
                        Appointment temp = appointments[i];
                        appointments[i] = appointments[j];
                        appointments[j] = temp;
                    }
                    else if(appointments[i].getDate().compareTo(appointments[j].getDate())==0){
                        if (appointments[i].getTimeslot().compareTo(appointments[j].getTimeslot())>0){
                            Appointment temp = appointments[i];
                            appointments[i] = appointments[j];
                            appointments[j] = temp;
                        }

                    }
                }


            }
        }

    }
    //ordered by date/timeslot, provider name
    public void printByAppointment(){
        for(int i=0; i<size; i++){
            for(int j=i+1; j<size; j++){
                if(appointments[i].compareTo(appointments[j])>0){
                    Appointment temp = appointments[i];
                    appointments[i] = appointments[j];
                    appointments[j] = temp;
                }
            }
        }

    }


    public int providerIsAvailable(Appointment appointment){
        if (appointments[0]!=null){
            for (int i = 0; i < size; i++) {
                if(appointments[i].getProvider().name().equals(appointment.getProvider().name()) && appointments[i].getTimeslot().equals(appointment.getTimeslot()) && appointments[i].getDate().equals(appointment.getDate())){
                    System.out.println("provider is not available " + appointment.getTimeslot().ordinal());
                    return appointment.getTimeslot().ordinal()+1;
                }
            }
        }
        return 0;
    }

    public boolean patientIsAvailable(Appointment appointment){
        if (appointments[0]!=null){
            for (int i = 0; i < size; i++) {
                if(appointments[i].getProfile().equals(appointment.getProfile()) && appointments[i].getTimeslot().equals(appointment.getTimeslot()) && appointments[i].getDate().equals(appointment.getDate()))return false;
            }
        }
        return true;
    }

    public Appointment findAppointmentGivenDateTimeslotAndProfile(Date apptDate,Timeslot timeslot, Profile profile){
        if(appointments[0]!=null){
            for (int i = 0; i < size; i++) {
                if(appointments[i].getDate().equals(apptDate)&&appointments[i].getTimeslot().equals(timeslot)&&appointments[i].getProfile().equals(profile))return appointments[i];
            }
            return null;
        }
        return null;
    }

    public void main(String[] args){
        Date date = new Date("01/01/2020");
        Timeslot timeslot = Timeslot.SLOT2;
        Profile profile = new Profile("c", "l", date);
        Provider provider = Provider.HARPER;
        Appointment appointment1 = new Appointment(date, timeslot, profile, provider);
        size = 1;
        add(appointment1);
    }


}
