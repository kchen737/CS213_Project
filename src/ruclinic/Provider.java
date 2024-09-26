package ruclinic;

public enum Provider {
    Patel(Location.Bridgewater, Specialty.FAMILY),
    Lim(Location.Bridgewater, Specialty.PEDIATRICIAN),
    Zimnes(Location.Clark, Specialty.FAMILY),
    Harper(Location.Clark, Specialty.FAMILY),
    Kaur(Location.Princeton, Specialty.ALLERGIST),
    Taylor(Location.Piscataway, Specialty.PEDIATRICIAN),
    Ramesh(Location.Morristown, Specialty.ALLERGIST),
    Ceravolo(Location.Edison, Specialty.PEDIATRICIAN);

    private final Location location;
    private final Specialty specialty;

    private Provider(Location location, Specialty specialty){
        this.location = location;
        this.specialty = specialty;
    }

    @Override
    public String toString(){
        String locationStr = location.toString();
        String specialtyStr = specialty.name();
        return "[" + name() + ", " + locationStr + ", " + specialtyStr + "]";
    }
}
