package spdu2022.java.project.beutysalon.staffregistration.controllers;

import org.springframework.stereotype.Component;
import spdu2022.java.project.beutysalon.entities.Staff;
import spdu2022.java.project.beutysalon.staffregistration.controllers.dto.StaffModificationDTO;

@Component
public class StaffModificationMapper {
    protected Staff convertStaffDTOLightToStaff(StaffModificationDTO staffModificationDTO) {
        Staff staff = new Staff();
        staff.setUserId(staffModificationDTO.getUserId());
        staff.setSalonId(staffModificationDTO.getSalonId());
        return staff;
    }

    protected StaffModificationDTO convertStaffToStaffDtoLight(Staff staff) {
        StaffModificationDTO staffModificationDTOLight = new StaffModificationDTO();
        staffModificationDTOLight.setId(staff.getId());
        staffModificationDTOLight.setSalonId(staff.getSalonId());
        staffModificationDTOLight.setUserId(staff.getUserId());
        return staffModificationDTOLight;
    }
}
