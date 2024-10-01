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
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public int validMaxDateOfLargeMonth = 31;
    public int validMaxDateOfSmallMonth = 30;
    public int validMaxDateOfFebruaryNonLeapYear = 28;
    public int getValidMaxDateOfFebruaryLeapYear = 29;
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
     * @return
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

    public boolean leap_year_correct_days(){
        int isLeapYear = leap_year();
        boolean correctDays = false;
        switch(isLeapYear){
            case 0:
                if (day<29 && day>0){
                    return true;
                }
            case 1:
                if (day<30 && day>0){
                    return true;
                }
        }
        //System.out.println("is not a valid calendar date.");
        return false;
        //return ("is not a valid calendar date.");
    }



    public boolean isValid(){
        boolean bMonth = false;//checks to see which month does it belong
        boolean sMonth = false;
        System.out.println(month+ " "+ day+" "+ year);
        if (year<1900) {
            return false;
        }
        if (month==2){
            return leap_year_correct_days();
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

    @Override
    public String toString(){
        String dayStr = String.valueOf(day);
        String monthStr = String.valueOf(month);
        String yearStr = String.valueOf(year);
        return monthStr + '/' + dayStr + '/' + yearStr;
    }

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
        //testMonth_OutOfRange();
        //testDaysInFeb_Nonleap();
        //testCompareTo();
        testisValid();


    }

    /**Test case #1**/
    private static void testDaysInFeb_Nonleap(){
        Date date = new Date("09/31/2024");
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        //boolean testing = date.isToday();
        String test1 = date.notSixMonth();
        System.out.println(actualOutput);

    }
    /**Test case for compareTo()**/
    private static void testCompareTo(){
        Date date1 = new Date("08/13/2015");
        Date date2 = new Date("08/13/2013");
        System.out.println(date1.compareTo(date2));
    }

    private static void testisValid(){
        Date date1 = new Date("9/10/2024");
        System.out.println("date is " + date1.toString() + " " + date1.isValidAppointmentDate());
        Date date2 = new Date("9/40/2024");
        System.out.println("date is " + date2.toString() + " " + date2.isValidAppointmentDate());
        Date date3 = new Date("13/11/2024");
        System.out.println("date is " + date3.toString() + " " + date3.isValidAppointmentDate());
        Date date4 = new Date("02/29/2024");
        System.out.println("date is " + date4.toString() + " " + date4.isValidAppointmentDate());
        Date date5 = new Date("02/29/2025");
        System.out.println("date is " + date5.toString() + " " + date5.isValidAppointmentDate());
        Date date6 = new Date("");
        System.out.println("date is " + date6.toString() + " " + date6.isValidAppointmentDate());
        return;


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

