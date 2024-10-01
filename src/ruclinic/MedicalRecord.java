package ruclinic;

/**
 * Medical record class stores the patient array with patients and size of the patient array
 * @Author Kaiming Chen, Luke
 */
public class MedicalRecord {
    private Patient[] patients;
    private int size; //number of patient objects in array
    //Initiate the class
    public MedicalRecord(Patient[] patients, int size){
        this.patients = patients;
        this.size = size;
    }

    /**
     *
     * @return patient array with patient from medical record
     */
    public Patient[] getPatients() {
        return patients;
    }

    /**
     *
     * @return integer number of the size
     */
    public int getSize() {
        return size;
    }

    /**
     * set size of the array
     * @param size of the patient array
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     *
     * @param patients patient array
     */
    public void setPatients(Patient[] patients) {
        this.patients = patients;
    }

    /**
     *  add patient to the patient array in the medical record
     *  If the size is going to overflow the length, the array is incread by 4
     * @param patient class
     */
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
