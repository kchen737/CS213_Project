package ruclinic;

public enum Specialty {
    FAMILY(250),
    PEDIATRICIAN(300),
    ALLERGIST(350);

    private final int charge;

    private Specialty(int charge) {
        this.charge = charge;
    }
}
