package ruclinic;

/**
 * Enum class with all the charges of different specialty
 * @Author: Kaiming Chen
 */
public enum Specialty {
    FAMILY(250),
    PEDIATRICIAN(300),
    ALLERGIST(350);

    private final int charge;

    /**
     * To retrieve charge from the class
     * @param charge
     */
    private Specialty(int charge) {
        this.charge = charge;
    }

    /**
     * get charge
     * @return integer
     */
    public int getCharge(){
        return this.charge;
    }

}
