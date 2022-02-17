package spdu2022.java.project.beutysalon.salons.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class SalonsDTOModification {
    private long id;
    @NotBlank(message = "Salon name not valid")
    private String salonName;
    @NotBlank(message = "City not valid")
    private String cityLocation;
    @Pattern(regexp = "^\\+380\\d{3}\\d{2}\\d{2}\\d{2}$", message = "Phone not valid -> +380XXXXXXXXX")
    private String phone;

    public SalonsDTOModification(String salonName, String cityLocation, String phone) {
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
