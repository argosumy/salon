package spdu2022.java.project.beutysalon.entities;

import java.util.Objects;

public class Salon {
    private long id;
    private String salonName;
    private String phone;
    private String cityLocation;

    public Salon() {
    }

    public Salon(long id, String salonName, String phone, String cityLocation) {
        this.id = id;
        this.salonName = salonName;
        this.phone = phone;
        this.cityLocation = cityLocation;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCityLocation() {
        return cityLocation;
    }

    public void setCityLocation(String cityLocation) {
        this.cityLocation = cityLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Salon salon = (Salon) o;
        return id == salon.id &&
                Objects.equals(salonName, salon.salonName) &&
                Objects.equals(phone, salon.phone) &&
                Objects.equals(cityLocation, salon.cityLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phone);
    }
}
