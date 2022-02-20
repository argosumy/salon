package spdu2022.java.project.beutysalon.salons_registration.persistence.entities;

public class Salon {
    private long id;
    private String salonName;
    private String cityLocation;
    private String phone;

    public Salon() {
    }

    public Salon(long id, String salonName, String cityLocation, String phone) {
        this.id = id;
        this.salonName = salonName;
        this.cityLocation = cityLocation;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSalonName() {
        return salonName;
    }

    public void setSalonName(String salonName) {
        this.salonName = salonName;
    }

    public String getCityLocation() {
        return cityLocation;
    }

    public void setCityLocation(String cityLocation) {
        this.cityLocation = cityLocation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
