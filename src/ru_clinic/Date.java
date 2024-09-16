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
    @Override
    public boolean equals(Object obj){

        return false;
    }
    public boolean isValid(String given){
        int numOfSlashes;
        int digits;
        int index=0;
        for (int i=0; i<given.length(); i++){
            char charOfDate = given.charAt(i);

        }
        return false;
    }

    private int year;
    private int month;
    private int day;
    //public boolean isValid();
}

