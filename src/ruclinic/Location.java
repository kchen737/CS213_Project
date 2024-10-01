package ruclinic;

/**
 * Enum class of location that stored 6 locations
 * @Author Kaiming Chen
 */
public enum Location {
    BRIDGEWATER("Somerset", "08807"),
    EDISON("Middlesex", "08817"),
    PISCATAWAY("Middlesex", "08854"),
    PRINCETON("Mercer", "08542"),
    MORRISTOWN("Morris", "07960"),
    CLARK("Union", "07066");
    private final String county;
    private final String zip;

    Location(String county, String zip){
        this.county = county;
        this.zip = zip;
    }

    /**
     * Overrides toString() method to return the right format
     * @return string
     */
    @Override
    public String toString(){
        return name() + ", " + county +" " + zip;
    }
    //used by other classes to retrieve county name
    public String getCounty(){
        return this.county;
    }
}
