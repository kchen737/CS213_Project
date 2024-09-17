package ru_clinic;

public enum Timeslot {
    SLOT1(9,0),
    SLOT2(10,45);

    public static final int NOON = 12;
    private final int hour;
    private final int minute;
    private Timeslot(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }


}
