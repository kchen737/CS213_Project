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
     * @return
     */
    public boolean isToday(){
        boolean correct = true;
        Calendar calendar = Calendar.getInstance();

        int year_of_today = calendar.get(Calendar.YEAR); //uses the calendar class to retrieve year of today
        int month_of_today = calendar.get(Calendar.MONTH)+1; //uses the calendar class to retrieve month of today
        int day_of_today = calendar.get(Calendar.DAY_OF_MONTH); //uses the calendar class to retrieve day of month of today

        if (year_of_today == year && month_of_today == month && day_of_today == day){
            correct = false;
            System.out.println("false");
        }

        System.out.println(month_of_today + " " + day_of_today + " " + year_of_today);
        return correct;
    }

    public boolean satOrSun(){
        boolean correct = true;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        if(dayOfWeek == Calendar.SATURDAY){
            correct = false;
        }
        else if(dayOfWeek == Calendar.SUNDAY){
            correct = false;
        }


        return correct;
    }

    private static void testisValid(){
        Date date1 = new Date("2/30/2024");
        System.out.println(date1.isValid());
    }


    public boolean leap_year(){
        boolean correct = false;
        if (year % QUADRENNIAL == 0){
            if (day < 31 && day > 0){
                System.out.println("leap year, correct day");
                correct = true;
            }
        }
        else if (year % CENTENNIAL == 0 && year % QUATERCENTENNIAL == 0){
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
            System.out.println("year is larger");
            return 1;
        }
        else if(this.month>anotherDate.month){
            if (this.year == anotherDate.year){
                System.out.println("year is the same, month is larger");
                return 1;
            }
            else return -1;
        }
        else if(this.day>anotherDate.day){
            if (this.year == anotherDate.year && this.month == anotherDate.month){
                System.out.println("year, month are the same, day is larger");
                return 1;
            }
            else return -1;
        }
        else{
            System.out.println("year is smaller");
            return -1;
        }
        //return 1;
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
        Date date = new Date("09/19/2024");
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        boolean testing = date.isToday();
        System.out.println(actualOutput);
        /*if (expectedOutput != actualOutput){

        }*/
    }
    /**Test case for compareTo()**/
    private static void testCompareTo(){
        Date date1 = new Date("08/13/2015");
        Date date2 = new Date("08/13/2013");
        System.out.println(date1.compareTo(date2));
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

