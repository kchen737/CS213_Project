package ruclinic;

/**
 * The timeslot class defines the time for scheduling an appointment
 * @Author Kaiming Chen
 */

public enum Timeslot {
    SLOT1(9,0),
    SLOT2(10,45),
    SLOT3(11,15),
    SLOT4(13,30),
    SLOT5(15,0),
    SLOT6(16,15);


    public static final int NOON = 12;
    private final int hour;
    private final int minute;

    private Timeslot(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public String toString(){
        String hstr = String.valueOf(hour);
        String mstr = String.valueOf(minute);
        String time = "AM";
        if (hour > NOON){
            time = "PM";
        }
        return hstr +":" +  mstr + " " + time;
    }


}
