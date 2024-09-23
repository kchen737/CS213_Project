package ruclinic;
/**
@author
 */
public class Patient {
    private Profile profile;
    private  Visit visits;
    public int charge() {
        return 1;
    }

    public Profile getProfile() {
        return profile;
    }

    public Visit getVisits() {
        return visits;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setVisits(Visit visits) {
        this.visits = visits;
    }

}
