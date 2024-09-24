package ruclinic;

public enum Location {
    Bridgewater("Somerset", "08807"),
    Edison("Middlesex", "08817"),
    Piscataway("Middlesex", "08854"),
    Princeton("Mercer", "08542"),
    Morristown("Morris", "07960"),
    Clark("Union", "07066");
    private final String county;
    private final String zip;

    Location(String county, String zip){
        this.county = county;
        this.zip = zip;
    }
}
