package ru_clinic;
import java.util.StringTokenizer;

/**
 * Verify if the dates are valid based on the following order
 * The date of an appointment is
 *  an invalid calendar date,
 *  today,
 *  a date before today
 *  a date on Saturday or Sunday
 *  a date not within six months from today.
 * @author Kaiming Chen
 */


public class Date {
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public int validMaxDateOfLargeMonth = 31;
    public int validMaxDateOfSmallMonth = 30;
    public int[] smallMonth = {4,6,9,11};
    public int[] bigMonth = {1,3,5,7,8,10,12};

    //isValid() method checks whether the date is a valid calendar date


    private int year;
    private int month;
    private int day;
    /**
     * isValid() method checks whether the date is a valid calendar date
     * Check is it 1 or 2 digits for day and month
     * Check is it 4 digits for year
     * Check for month 1,3,5,7,8,10,12 is till 31
     * Check for month 4,6,9,11 is till 30
     * Check
     * @param
     * @return
     */
    public Date(String date){
        StringTokenizer dateEntered = new StringTokenizer(date, "/");
        day = Integer.parseInt(dateEntered.nextToken());
        month = Integer.parseInt(dateEntered.nextToken());
        year = Integer.parseInt(dateEntered.nextToken());

    }
    public boolean isValid(){
        boolean bMonth = false;//checks to see which month does it belong
        boolean sMonth = false;
        System.out.println(day+" "+ month+" "+ year);
        if (year<1900) {
            return false;
        }
        if (month==2){
            System.out.println("received");
            return true;
        }
        else {
            for (int i=0; i<7; i++){
                if(month == bigMonth[i]){
                    bMonth = true;
                    if(day>31 || day<1){
                        return false;
                    }
                }
            }
            if (!bMonth){
                for (int i=0; i<4; i++){
                    if(month == smallMonth[i]){
                        sMonth = true;
                        if(day>30 || day<1){
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * leapYear() method checks for leap year for february
     */
    public boolean leapYear(){
        return true;
    }

    /**
     * Testbed main to exercise the isValid method
     * @param args
     */
    public static void main(String args[]){
        //testMonth_OutOfRange();
        testDaysInFeb_Nonleap();

    }

    /**Test case #1**/
    private static void testDaysInFeb_Nonleap(){
        Date date = new Date("2/29/2011");
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        System.out.println(actualOutput);
        /*if (expectedOutput != actualOutput){

        }*/
    }


}

