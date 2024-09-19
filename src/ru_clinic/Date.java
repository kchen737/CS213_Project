package ru_clinic;
import java.util.StringTokenizer;
import java.util.Calendar;


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

    /**
     * isToday() method is used to check if the date is today, could be used for appointment making
     * and also used to check if a birthday is valid or not
     * @return
     */
    public boolean isToday(){
        boolean correct = false;
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        //String todayDate = dateFormat.format(currentDate);
        return correct;
    }


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
        month = Integer.parseInt(dateEntered.nextToken());
        day = Integer.parseInt(dateEntered.nextToken());
        year = Integer.parseInt(dateEntered.nextToken());

    }



    public boolean leap_year(){
        boolean correct = false;
        if (year % 4 == 0){
            if (day < 31 && day > 0){
                System.out.println("leap year, correct day");
                correct = true;
            }
        }
        else if (year % 100 == 0 && year % 400 == 0){
            if (day < 31 && day > 0 ){
                correct = true;
            }
        }
        else if (day < 30 && day >0){
            System.out.println("not a leap year, but correct day");
            correct = true;
        }
        else{
            System.out.println("not a leap year, but incorrect day");
            correct = false;
        }
        return correct;
    }
    public boolean isValid(){
        boolean bMonth = false;//checks to see which month does it belong
        boolean sMonth = false;
        System.out.println(month+ " "+ day+" "+ year);
        if (year<1900) {
            return false;
        }
        if (month==2){
            System.out.println("received");
            return leap_year();
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
        if (!sMonth && !bMonth){
            return false;
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
        Date date = new Date("02/31/2016");
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        System.out.println(actualOutput);
        /*if (expectedOutput != actualOutput){

        }*/
    }


}

