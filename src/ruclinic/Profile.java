package ruclinic;

public class Profile {
    private String fname; //first name
    private String lname; //last name
    private Date dob; //date of birth
    public void setFName(String firstName){
        this.fname= firstName;
    }
    public String getFName(){
        return fname;
    }
    public void setLname(String lastName){
        this.lname=lastName;
    }
    public String getLname(){
        return lname;
    }

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
}

