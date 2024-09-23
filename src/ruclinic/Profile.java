package ruclinic;

public class Profile {
    private String fname; //first name
    private String lname; //last name
    private Date dob;

    public Profile(String c, String l, Date date) {
        this.fname = c;
        this.lname = l;
        this.dob = date;
    }
    //private Date dob;//date of birth
}
