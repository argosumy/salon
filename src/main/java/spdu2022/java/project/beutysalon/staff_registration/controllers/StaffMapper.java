package spdu2022.java.project.beutysalon.staff_registration.controllers;

import org.springframework.stereotype.Component;
import spdu2022.java.project.beutysalon.entities.Staff;
import spdu2022.java.project.beutysalon.staff_registration.controllers.dto.StaffDTO;

@Component
public class StaffMapper {
    protected Staff convertStaffDTOLightToStaff(StaffDTO staffDTO) {
        Staff staff = new Staff();
        staff.setUserId(staffDTO.getUserId());
        staff.setSalonId(staffDTO.getSalonId());
        staff.setLinkPhoto(staffDTO.getLinkPhoto());
        return staff;
    }

    protected StaffDTO convertStaffToStaffDtoLight(Staff staff) {
        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setId(staff.getId());
        staffDTO.setSalonId(staff.getSalonId());
        staffDTO.setUserId(staff.getUserId());
        staffDTO.setLinkPhoto(staff.getLinkPhoto());
        return staffDTO;
    }
}
