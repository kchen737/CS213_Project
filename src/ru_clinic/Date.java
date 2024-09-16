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

