package ruclinic;

public class Profile implements Comparable<Profile> {
    private String fname; //first name
    private String lname; //last name
    private Date dob;

    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob1){
        this.dob=dob1;
    }
    public Profile(String fn, String ln, Date dateOfBirth){
        fname= fn;
        lname= ln;
        dob= dateOfBirth;
    }
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Profile){
            Profile anotherProfile = (Profile) obj;
            return this.fname.equals(anotherProfile.fname)&&
                    this.lname.equals(anotherProfile.lname)&&
                    this.dob.equals(anotherProfile.dob);
        }
        return false;
    }

    @Override
    public String toString(){
        String date = dob.toString();
        return fname + lname + date;
    }

    @Override
    public int compareTo(Profile prof){
        int lnameComparison = this.lname.compareTo(prof.lname);
        int fnameComparison = this.fname.compareTo(prof.fname);
        int dobComparison = this.dob.compareTo(prof.dob);


        return 1;
    }
}
