package spdu2022.java.project.beutysalon.staff_registration.controllers;

import org.springframework.stereotype.Component;
import spdu2022.java.project.beutysalon.entities.Staff;
import spdu2022.java.project.beutysalon.staff_registration.controllers.dto.StaffDTOLight;

@Component
public class StaffMapper {
    protected Staff convertStaffDTOLightToStaff(StaffDTOLight staffDTOLight) {
        Staff staff = new Staff();
        staff.setUserId(staffDTOLight.getUserId());
        staff.setSalonId(staffDTOLight.getSalonId());
        staff.setLinkPhoto(staffDTOLight.getLinkPhoto());
        return staff;
    }

    protected StaffDTOLight convertStaffToStaffDtoLight(Staff staff) {
        StaffDTOLight staffDTOLight = new StaffDTOLight();
        staffDTOLight.setId(staff.getId());
        staffDTOLight.setSalonId(staff.getSalonId());
        staffDTOLight.setUserId(staff.getUserId());
        staffDTOLight.setLinkPhoto(staff.getLinkPhoto());
        return staffDTOLight;
    }
}
