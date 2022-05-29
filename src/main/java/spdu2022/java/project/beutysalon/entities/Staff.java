package spdu2022.java.project.beutysalon.entities;

import java.util.Objects;

public class Staff {
    private long id;
    private User user;
    private Salon salon;
    private String linkPhoto;

    public Staff() {
        user = new User();
        salon = new Salon();
    }

    public Staff(User user, Salon salon, String linkPhoto) {
        this.user = user;
        this.salon = salon;
        this.linkPhoto = linkPhoto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public String getLinkPhoto() {
        return linkPhoto;
    }

    public void setLinkPhoto(String linkPhoto) {
        this.linkPhoto = linkPhoto;
    }

    public long getUserId() {
        return user.getId();
    }

    public void setUserId(long id) {
        user.setId(id);
    }

    public long getSalonId() {
        return salon.getId();
    }

    public void setSalonId(long id) {
        salon.setId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Staff staff = (Staff) o;
        return this.getUserId() == staff.getUserId() && this.getSalonId() == staff.getSalonId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(user.getId(), salon.getId());
    }
}