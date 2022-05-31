package spdu2022.java.project.beutysalon.notification.controllers;

import org.springframework.web.bind.annotation.*;
import spdu2022.java.project.beutysalon.notification.controllers.dto.UsersNotificationBySalonIdDTO;
import spdu2022.java.project.beutysalon.notification.services.UsersNotificationService;

@RestController
@RequestMapping("api/v1/salons/{salonId}/users/notifications")
public class NotificationController {
    private final UsersNotificationService notificationServices;

    public NotificationController(UsersNotificationService notificationServices) {
        this.notificationServices = notificationServices;
    }

    @PostMapping()
    String notificationUsersBySalonId(@RequestBody UsersNotificationBySalonIdDTO notificationDto, @PathVariable("salonId") long salonId) {
        notificationDto.setSalonId(salonId);
        int count = notificationServices.sendingNotificationToUsersBySalonId(notificationDto);
        return "Count notifications " + count;
    }
}
