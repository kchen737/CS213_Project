package ruclinic;

/**
 * Profile class with patient's first name, last name and date of birth
 * @Author Kaiming Chen, Luke
 */

public class Profile implements Comparable<Profile> {
    private String fname; //first name
    private String lname; //last name
    private Date dob;

    public String getName(){
        return fname + " " + lname;
    }

    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob1){
        this.dob=dob1;
    }
    //Initiate class with first name, last name and dob stored
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
        int lnameComparison = this.lname.compareToIgnoreCase(prof.lname);
        int fnameComparison = this.fname.compareToIgnoreCase(prof.fname);
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
    return;
    }
    private static void testCompareTo(){
        Date date1 = new Date("3/12/2023");
        Date date2 = new Date("3/12/2024");
        Date date3 = new Date("09/21/2003");
        Date date4 = new Date("12/25/0000");
        Profile profile1 = new Profile("kaiming", "chen", date1);
        Profile profile2 = new Profile("kaiminy", "chen", date2);
        Profile profile3 = new Profile("Luke", "Busacca", date3);
        Profile profile4 = new Profile("Dwayne", "Johnson", date2);
        Profile profile5 = new Profile("Jesus", "Christ", date4);
        int compare = profile1.compareTo(profile2);
        System.out.println(compare);
        compare = profile1.compareTo(profile1);
        System.out.println(compare);
        compare = profile1.compareTo(profile3);
        System.out.println(compare);
        compare = profile3.compareTo(profile4);
        System.out.println(compare);
        compare = profile4.compareTo(profile5);
        System.out.println(compare);
        compare = profile5.compareTo(profile4);
        System.out.println(compare);
        compare = profile2.compareTo(profile1);
        System.out.println(compare);

    return;
    }
}
