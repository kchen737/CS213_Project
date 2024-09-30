package ruclinic;

public class MedicalRecord {
    private Patient[] patients;
    private int size; //number of patient objects in array

    public Patient[] getPatients() {
        return patients;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPatients(Patient[] patients) {
        this.patients = patients;
    }

    public void add(Appointment appointment){
        Patient patient = new Patient(appointment.getProfile(),new Visit(appointment, null));
        for (int i = 0; i<size;i++){
            if(patients[i].getProfile().equals(patient.getProfile())){
                patients[i].setVisits(patients[i].getVisits().setLast(appointment));
            }
        }
        Patient [] patientList = new Patient[size+1];
        for(int i = 0; i<size;i++){
            patientList[i]=this.patients[i];
        }
        patientList[size] = patient;
        this.patients=patientList;
    }

}
