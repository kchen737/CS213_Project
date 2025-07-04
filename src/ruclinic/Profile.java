package ruclinic;

/**
 * Profile class with patient's first name, last name and date of birth
 * @Author Kaiming Chen, Luke
 */

public class Profile implements Comparable<Profile> {
    private String fname; //first name
    private String lname; //last name
    private Date dob;

    /**
     * getName()
     * @return String
     */
    public String getName(){
        return fname + " " + lname;
    }

    /**
     * getDob()
     * @return date
     */
    public Date getDob() {
        return dob;
    }

    /**
     * set date of birth
     * @param dob1
     */
    public void setDob(Date dob1){
        this.dob=dob1;
    }
    //Initiate class with first name, last name and dob stored
    public Profile(String fn, String ln, Date dateOfBirth){
        fname= fn;
        lname= ln;
        dob= dateOfBirth;
    }

    /**
     * Overriding equals method to check if both of the profile
     * @param obj
     * @return boolean
     */
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

    /**
     * Overriding toString method to print firstname, lastname and date
     * @return String
     */
    @Override
    public String toString(){
        String date = dob.toString();
        return fname + " " +  lname + " " + date;
    }

    /**
     * Overriding compareTo method to see if the two profile are the same or not
     * @param prof the object to be compared.
     * @return integer
     */
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
        testCompareToSame();
        testCompareToDifferent();
        testCompare3();
        testCompare4();
        testCompare5();
        testCompare6();
        testCompare7();
    }
    /** Test case #1**/
    private static void testCompareToSame(){
            String fname = "kaiming";
            String lname = "chen";
            Date dob = new Date("09/10/2003");
            Profile profile = new Profile(fname, lname, dob);
            Profile profile1 = new Profile(fname, lname, dob);
            int actualOutput = profile.compareTo(profile1);
            int expectedOutput = 0;
            System.out.println("/** Testcase 1: Same profile return 0 **/");
            testcase(profile, profile1, actualOutput, expectedOutput);


    }
    /** Test case #2**/
    private static void testCompareToDifferent(){
        String fname = "Luke";
        String lname = "Buscca";
        Date dob = new Date("09/15/2002");
        String fname1 = "Kaiming";
        String lname1 = "Chen";
        Date dob1 = new Date("09/20/2003");
        Profile profile = new Profile(fname, lname, dob);
        Profile profile1 = new Profile(fname1, lname1, dob1);
        int actualOutput = profile.compareTo(profile1);
        int expectedOutput = -1;
        System.out.println("/** Testcase 2: Different profile, first is smaller than second return -1 **/");
        testcase(profile, profile1, actualOutput, expectedOutput);
    }
    /**Test case #3**/
    private static void testCompare3(){
        String fname = "Kaiming";
        String lname = "Lin";
        String lname2 = "Chen";
        Date dob = new Date("12/30/2006");
        Profile profile = new Profile(fname, lname, dob);
        Profile profile1 = new Profile(fname, lname2, dob);
        int actualOutput = profile.compareTo(profile1);
        int expectedOutput = 1;
        System.out.println("/** Testcase 3: Different profile, return 1");
        testcase(profile, profile1, actualOutput, expectedOutput);
    }
    /** Test case #4**/
    private static void testCompare4(){
        String fname = "Luke";
        String lname = "John";
        String fname1 = "Kaiming";
        Date dob = new Date("3/14/2012");
        Profile profile = new Profile(fname, lname, dob);
        Profile profile1 = new Profile(fname1, lname, dob);
        int actualOutput = profile.compareTo(profile1);
        int expectedOutput = 1;
        System.out.println("/** Testcase 4: different profile");
        testcase(profile, profile1, actualOutput, expectedOutput);
    }
    /** Test case #5**/
    private static void testCompare5(){
        String fname = "Luk";
        String lname = "Calvin";
        Date dob = new Date("3/14/2012");
        Date dob1 = new Date("5/13/2005");
        Profile profile = new Profile(fname, lname, dob);
        Profile profile1 = new Profile(fname, lname, dob1);
        int actualOutput = profile.compareTo(profile1);
        int expectedOutput = 1;
        System.out.println("/** Testcase 4: different profile with different birthday");
        testcase(profile, profile1, actualOutput, expectedOutput);
    }
    /** Test case #6**/
    private static void testCompare6(){
        String fname = "Levin";
        String lname = "Calvin";
        Date dob = new Date("3/14/2012");
        Date dob1 = new Date("5/13/2005");
        Profile profile = new Profile(fname, lname, dob);
        Profile profile1 = new Profile(fname, lname, dob1);
        int actualOutput = profile1.compareTo(profile);
        int expectedOutput = -1;
        System.out.println("/** Testcase 5: different profile with different birthday");
        testcase(profile, profile1, actualOutput, expectedOutput);
    }
    /** Test case #7**/
    private static void testCompare7(){
        String fname = "Luke";
        String lname = "John";
        String fname1 = "Kaiming";
        Date dob = new Date("3/14/2012");
        Profile profile = new Profile(fname, lname, dob);
        Profile profile1 = new Profile(fname1, lname, dob);
        int actualOutput = profile1.compareTo(profile);
        int expectedOutput = -1;
        System.out.println("/** Testcase 7: different profile");
        testcase(profile, profile1, actualOutput, expectedOutput);
    }



    private static void testcase(Profile profile1, Profile profile2, int actualOutput, int expectedOutput){
        System.out.println("Profile1 is: " + profile1.toString());
        System.out.println("Profile2 is: "+ profile2.toString());
        System.out.println("The actualOutput is: " + actualOutput);
        System.out.println("The expectedOutput is: " + expectedOutput);
    }

}
