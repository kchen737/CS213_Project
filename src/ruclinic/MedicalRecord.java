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
}
