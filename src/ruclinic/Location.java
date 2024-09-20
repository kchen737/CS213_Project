package ruclinic;

public enum Location {
    ;
    private final String country;
    private final String zip;

    Location(String country, String zip){
        this.country = country;
        this.zip = zip;
    }
}
