package spdu2022.java.project.beutysalon.notification.persistence.reposetories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.testcontainers.junit.jupiter.Testcontainers;
import spdu2022.java.project.beutysalon.entities.BookedService;
import spdu2022.java.project.beutysalon.filestorage.FileStorageServices;
import spdu2022.java.project.beutysalon.notification.controllers.NotificationController;
import spdu2022.java.project.beutysalon.notification.services.UsersNotificationService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
@ActiveProfiles("test-containers")
class PersistenceGetBookingRepositoryTest {
    @Autowired
    private PersistenceGetBookingRepository repository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @MockBean
    private FileStorageServices fileStorageServices;
    @MockBean
    private NotificationController notificationController;
    @MockBean
    private UsersNotificationService usersNotificationService;
    @MockBean
    private JavaMailSender emailSender;
    @MockBean
    private FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean;

    @Test
    void getBookingBySalonId() {
        List<BookedService> result = repository.getBookingBySalonId(1);
        assertEquals(2, result.size());
    }
}