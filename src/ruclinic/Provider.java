package ruclinic;

public enum Provider {
    PATEL(Location.BRIDGEWATER, Specialty.FAMILY),
    LIM(Location.BRIDGEWATER, Specialty.PEDIATRICIAN),
    ZIMNES(Location.CLARK, Specialty.FAMILY),
    HARPER(Location.CLARK, Specialty.FAMILY),
    KAUR(Location.PRINCETON, Specialty.ALLERGIST),
    TAYLOR(Location.PISCATAWAY, Specialty.PEDIATRICIAN),
    RAMESH(Location.MORRISTOWN, Specialty.ALLERGIST),
    CERVAOLO(Location.EDISON, Specialty.PEDIATRICIAN);

    private final Location location;
    private final Specialty specialty;

    private Provider(Location location, Specialty specialty){
        this.location = location;
        this.specialty = specialty;
    }

    public int getCharge(){
        return this.specialty.getCharge();
    }

    public String getLocation(){
        return this.location.getCounty();
    }

    @Override
    public String toString(){
        String locationStr = location.toString();
        String specialtyStr = specialty.name();
        return "[" + this.name() + ", " + locationStr + ", " + specialtyStr + "]";
    }

    public static boolean isReal(String provName){
        for(Provider provider: Provider.values()){
            if(provider.name().equalsIgnoreCase(provName))return true;
        }
        return false;
    }
    public static Provider getProv(String provName){
        for(Provider provider: Provider.values()){
            if(provider.name().equalsIgnoreCase(provName))return provider;
        }
        return null;
    }

}
