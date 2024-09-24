package ruclinic;

/**
 * You can add necessary constants, constructors, and methods; however, you CANNOT change or add the
 * instance variables, or -2 points for each violation.
 *  You must override the equals(), toString() and compareTo(), with the @Override tag, or -2 points for
 * each violation.
 *  You MUST design the test cases to thoroughly test the compareTo() method, or you will lose 7 points.
 * The method compares last name, first name, and then dob for sorting. Follow the instructions under the
 * “Test Design” section in the Coding Standard and include the test cases in the test specification.
 *  You MUST include a testbed main() in this class or lose 8 points. You CAN use System.out in the
 * testbed main() to display the test results.
 */
public class Profile implements Comparable<Profile> {
    private String fname; //first name
    private String lname; //last name
    private Date dob;

    public Profile(String c, String l, Date date) {
        this.fname = c;
        this.lname = l;
        this.dob = date;
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

        return 1;
    }
}
