package ruclinic;
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


public class Date implements Comparable<Date>{
    //Global variables to be used
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public int validMaxDateOfLargeMonth = 31;
    public int validMaxDateOfSmallMonth = 30;
    public int getValidMaxDateOfFebruaryLeapYear = 29;
    public int minimumDayOfMonth = 1;
    public int invalidDayOfMonth = 0;
    public int nineteeHundred = 1900;
    public int lengthOfSmallMonth = 4;
    public int lengthOfLargeMonth = 7;
    public int feburary = 2;
    public int[] smallMonth = {4,6,9,11};
    public int[] bigMonth = {1,3,5,7,8,10,12};



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

    /**
     * isToday() method is used to check if the date is today, could be used for appointment making
     * and also used to check if a birthday is valid or not
     * @return whether or not date given is today
     */
    public boolean isToday(){
        Calendar calendar = Calendar.getInstance();

        int year_of_today = calendar.get(Calendar.YEAR); //uses the calendar class to retrieve year of today
        int month_of_today = calendar.get(Calendar.MONTH)+1; //uses the calendar class to retrieve month of today
        int day_of_today = calendar.get(Calendar.DAY_OF_MONTH); //uses the calendar class to retrieve day of month of today

        if (year_of_today == year && month_of_today == month && day_of_today == day){
            return true;
        }
        return false;
    }

    /**
     * determines if day is a weekend or not and returns string for output
     * @return string for output
     */
    public String satOrSun(){
        boolean correct = true;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek == Calendar.SATURDAY){
            correct = false;
            return (" is Saturday or Sunday.");
        }
        else if(dayOfWeek == Calendar.SUNDAY){
            correct = false;
            return (" is Saturday or Sunday.");

        }
        return null;
    }

    /**
     *
     * @return a string as output for the interface
     */
    public String notSixMonth(){

        Calendar sixMonth = Calendar.getInstance();

        Calendar date = Calendar.getInstance();
        date.set(year, month-1, day);
        sixMonth.add(Calendar.MONTH, 6);

        if(date.after(sixMonth)){
            return (" is not within six months.");
        }
        return null;

    }

    /**
     * Checks if the date is before today's date
     * @return a string for output
     */
    public String beforeToday(){
        Calendar calendar = Calendar.getInstance();

        Calendar date = Calendar.getInstance();
        date.set(year, month-1, day);
        if (date.before(calendar)){
            return (" is today or a date before today.");
        }
        else if (isToday()){
            return (" is today or a date before today");
        }
        return null;
    }

    /**
     * Checks if the date is a valid appointment Date
     * Cannot be before today, cannot be beyond six months and cannot be saturday or sunday
     * @return a string for output
     */
    public String isValidAppointmentDate(){
        if(beforeToday()!=null){
            return beforeToday();
        }
        if(notSixMonth()!=null){
            return notSixMonth();
        }
        if(satOrSun()!=null){
            return satOrSun();
        }
        return null;

    }

    /**
     * Checks to see if the date is a future date
     * @return a string for output
     */
    public String isFuture(){
        Calendar calendar = Calendar.getInstance();

        Calendar date = Calendar.getInstance();
        date.set(year, month-1, day);
        if (date.after(calendar)){
            return (" is today or a date after today.");
        }
        if (isToday()){
            return (" is today or a date after today.");
        }
        return null;

    }

    /**
     * Checks to see if the year is a leap year or not
     * @return integer for if it is leap year 1, not a leap year 0
     */


    public int leap_year(){
        int isLeapYear = 0;
        if (year % QUADRENNIAL == 0){
            if (year % CENTENNIAL == 0){
                if (year % QUATERCENTENNIAL == 0){
                    isLeapYear = 1;
                }
            }
            else {
                isLeapYear = 1;
            }
        }
        return isLeapYear;
    }

    /**
     * Checks to see if there are correct amount of days for leap and non-leap year
     * @return a boolean value to check if true it is correct days for leap or non-leap year
     * false for incorrect year of leap or non-leap year
     */
    public boolean leap_year_correct_days(){
        int isLeapYear = leap_year();
        boolean correctDays = false;
        switch(isLeapYear){
            case 0:
                if (day<getValidMaxDateOfFebruaryLeapYear && day>invalidDayOfMonth){
                    return true;
                }
            case 1:
                if (day<validMaxDateOfSmallMonth && day>invalidDayOfMonth){
                    return true;
                }
        }
        return false;
    }


    /**
     * Checks to see if the date is valid based on
     * if it is larger than 1900
     * if there are correct number of days in leap year
     * if there are correct days in each month
     * if the month is correct
     * @return a boolean to see if the date is valid, true for valid and false for nonvalid
     *
     */
    public boolean isValid(){
        boolean bMonth = false;//checks to see which month does it belong
        boolean sMonth = false;
        System.out.println(month+ " "+ day+" "+ year);
        if (year<nineteeHundred) {
            return false;
        }
        if (month==feburary){
            return leap_year_correct_days();
        }
        else {
            for (int i=0; i<lengthOfLargeMonth; i++){
                if(month == bigMonth[i]){
                    bMonth = true;
                    if(day>validMaxDateOfLargeMonth || day<minimumDayOfMonth){
                        return false;
                    }
                }
            }
            if (!bMonth){
                for (int i=0; i<lengthOfSmallMonth; i++){
                    if(month == smallMonth[i]){
                        sMonth = true;
                        if(day>validMaxDateOfSmallMonth || day<minimumDayOfMonth){
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
     * Checks to see if the dates are equal based on year, month and day
     * @param obj
     * @return a true or false for if the dates are equal
     */
    @Override
    public boolean equals(Object obj){

        if (obj instanceof Date ){
            Date anotherDate = (Date) obj;
            return this.year==anotherDate.year&&
                    this.month==anotherDate.month&&
                    this.day==anotherDate.day;
        }
        return false;
    }

    /**
     * write date in mm/dd/yyyy format
     * @return a string value of date
     */
    @Override
    public String toString(){
        String dayStr = String.valueOf(day);
        String monthStr = String.valueOf(month);
        String yearStr = String.valueOf(year);
        return monthStr + '/' + dayStr + '/' + yearStr;
    }

    /**
     * if the date is larger or smaller or equal to each other
     * @param anotherDate the object to be compared.
     * @return integer value for compareTo
     */
    @Override
    public int compareTo(Date anotherDate){
        if (this.year == anotherDate.year && this.month == anotherDate.month && this.day == anotherDate.day) return 0;
        if (this.year>anotherDate.year){
            return 1;
        }
        else if(this.month>anotherDate.month){
            if (this.year == anotherDate.year){
                return 1;
            }
            else return -1;
        }
        else if(this.day>anotherDate.day){
            if (this.year == anotherDate.year && this.month == anotherDate.month){
                return 1;
            }
            else return -1;
        }
        else{
            return -1;
        }
    }

    /**
     * Testbed main to exercise the isValid method
     * @param args
     */
    public static void main(String args[]){
        testBeforeNineteenth();
        testDaysInFeb_Nonleap();
        testWithinTwelveMonth();
        testDaysInFeb_Leap();
    }

    private static void testcases(Date date, boolean expectedOutput, boolean actualOutput){
        System.out.println("Test input: " + date.toString());
        System.out.println("Expected output: " + expectedOutput);
        System.out.println("Actual output: " + actualOutput);
    }


    /**Test case #1**/
    private static void testBeforeNineteenth(){
        Date date = new Date("09/13/1888");
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case #1: The valid year is before 1900");
        testcases(date, expectedOutput, actualOutput);
    }

    /**Test case #2**/
    private static void testDaysInFeb_Nonleap(){
        Date date = new Date("2/29/2024");
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();

        System.out.println("**Test case #2: #of days in feburary in a non-leap year is 28");
        testcases(date, expectedOutput, actualOutput);

    }
    /**Test case for #3**/
    private static void testWithinTwelveMonth(){
        Date date1 = new Date("13/13/2015");
        boolean expectedOutput = false;
        boolean actualOutput = date1.isValid();
        System.out.println("**Test case #3: #of month in a year is between and including 1 and 12");
        testcases(date1, expectedOutput, actualOutput);
    }

    /**Test case for #4**/
    private static void testDaysInFeb_Leap(){
        Date date = new Date("2/29/2012");
        boolean expectedOutput = true;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case #4: #of days in feburary in a leap year is 29");
        testcases(date, expectedOutput, actualOutput);
    }



    public int getDay(){
        return day;
    }

    public int getMonth() {
        return month;
    }
    public int getYear(){
        return  year;
    }

}

