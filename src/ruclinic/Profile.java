package ruclinic;

/**
 * @Author Kaiming Chen
 */

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
        return fname + " " +  lname + " " + date;
    }

    @Override
    public int compareTo(Profile prof){
        int lnameComparison = this.lname.compareTo(prof.lname);
        int fnameComparison = this.fname.compareTo(prof.fname);
        int dobComparison = this.dob.compareTo(prof.dob);

        if(lnameComparison<0){
            return -1;
        }
        else if (lnameComparison == 0 && fnameComparison <0){
            return -1;
        }
        else if (lnameComparison == 0 && fnameComparison == 0 && dobComparison <0){
            return -1;
        }
        else if (lnameComparison == 0 && fnameComparison == 0 && dobComparison == 0){
            return 0;
        }
        else {
            return 1;
        }

    }
    public static void main(String[] args){
        testCompareTo();

    }
    private static void testCompareTo(){
        Date date1 = new Date("3/12/2023");
        Date date2 = new Date("3/12/2024");
        Profile profile1 = new Profile("kaiming", "chen", date1);
        Profile profile2 = new Profile("kaiminy", "chen", date2);
        int compare = profile1.compareTo(profile2);
        System.out.println(compare);
    }
}
