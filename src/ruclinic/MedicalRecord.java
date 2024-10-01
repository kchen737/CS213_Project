package ruclinic;

public class MedicalRecord {
    private Patient[] patients;
    private int size; //number of patient objects in array

    public MedicalRecord(Patient[] patients, int size){
        this.patients = patients;
        this.size = size;
    }

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

    public void add(Patient patient){
        patients[this.size] = patient;
        if (this.size<patients.length){
            Patient[] patientList = new Patient[size+4];
            for (int i=0; i<=size; i++){
                patientList[i] = patients[i];
            }
            this.patients = patientList;
        }
        size++;

    }


}
