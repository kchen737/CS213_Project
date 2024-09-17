package ru_clinic;
import java.util.Scanner;
import java.util.Calendar;
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
    //isValid() method checks whether the date is a valid calendar date

    /**
     * isValid() method checks whether the date is a valid calendar date
     * Check is it 1 or 2 digits for day and month
     * Check is it 4 digits for year
     * Check for month 1,3,5,7,8,10,12 is till 31
     * Check for month 4,6,9,11 is till 30
     * Check
     * @param given
     * @return
     */
    public boolean isValid(String given){
        int numOfSlashes=0;
        int digits = 0;
        int index=0;
        for (int i=0; i<given.length(); i++){
            char charOfDate = given.charAt(i);
            if (Character.isDigit(charOfDate)){
                digits++;
            }
            else if(charOfDate == '/'){
                if (numOfSlashes<2 && digits>2){
                    return false;
                }
                else if(!(digits>2)){
                    digits=0;
                }
                if(numOfSlashes==2 && digits>4){
                    return false;
                }

            }

        }
        return false;
    }

    private int year;
    private int month;
    private int day;
    //public boolean isValid();
    public class main{

    }
}

