package ruclinic;

/**
 * The timeslot class defines the time for scheduling an appointment
 * @Author Kaiming Chen
 */

public enum Timeslot {
    SLOT1(9,00),
    SLOT2(10,45),
    SLOT3(11,15),
    SLOT4(13,30),
    SLOT5(15,00),
    SLOT6(16,15);


    public static final int NOON = 12;
    private final int hour;
    private final int minute;

    private Timeslot(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }

    public String checkValid(int number){
        return switch (number) {
            case 1 -> Timeslot.SLOT1.toString();
            case 2 -> Timeslot.SLOT2.toString();
            case 3 -> Timeslot.SLOT3.toString();
            case 4 -> Timeslot.SLOT4.toString();
            case 5 -> Timeslot.SLOT5.toString();
            case 6 -> Timeslot.SLOT6.toString();
            default -> "is not a valid time slot";
        };
    }

    @Override
    public String toString(){
        String hstr = String.valueOf(hour);
        String mstr = String.valueOf(minute);
        if (this.name().equals("SLOT1") || this.name().equals("SLOT5")){
            mstr = "00";
        }
        String time = "AM";
        if (hour > NOON){
            int newhour = hour-12;
            hstr = String.valueOf(newhour);
            time = "PM";
        }
        return hstr +":" +  mstr + " " + time;
    }


}
