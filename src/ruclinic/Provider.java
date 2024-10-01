package ruclinic;

/**
 * Provider class
 * @Author Kaiming Chen
 */
public enum Provider {
    PATEL(Location.BRIDGEWATER, Specialty.FAMILY),
    LIM(Location.BRIDGEWATER, Specialty.PEDIATRICIAN),
    ZIMNES(Location.CLARK, Specialty.FAMILY),
    HARPER(Location.CLARK, Specialty.FAMILY),
    KAUR(Location.PRINCETON, Specialty.ALLERGIST),
    TAYLOR(Location.PISCATAWAY, Specialty.PEDIATRICIAN),
    RAMESH(Location.MORRISTOWN, Specialty.ALLERGIST),
    CERAVOLO(Location.EDISON, Specialty.PEDIATRICIAN);

    private final Location location; //Instance variables
    private final Specialty specialty;
    //Initiate class
    private Provider(Location location, Specialty specialty){
        this.location = location;
        this.specialty = specialty;
    }

    /**
     * to get charge of provider
     * @return integer of charge
     */
    public int getCharge(){
        return this.specialty.getCharge();
    }

    /**
     * get location
     * @return string of location
     */
    public String getLocation(){
        return this.location.getCounty();
    }

    /**
     * override toString() method
     * @return string
     */
    @Override
    public String toString(){
        String locationStr = location.toString();
        String specialtyStr = specialty.name();
        return "[" + this.name() + ", " + locationStr + ", " + specialtyStr + "]";
    }
}
