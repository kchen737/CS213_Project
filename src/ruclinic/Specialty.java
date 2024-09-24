package ruclinic;

/**
 * Timeslot class. This Enum class defines the timeslots of a day on weekdays.
 * @Author: Kaiming Chen
 */
public enum Specialty {
    FAMILY(250),
    PEDIATRICIAN(300),
    ALLERGIST(350);

    private final int charge;

    private Specialty(int charge) {
        this.charge = charge;
    }
}
