package spdu2022.java.project.beutysalon.salonsregistration.persistence.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.testcontainers.junit.jupiter.Testcontainers;
import spdu2022.java.project.beutysalon.entities.Salon;
import spdu2022.java.project.beutysalon.filestorage.FileStorageServices;
import spdu2022.java.project.beutysalon.notification.controllers.NotificationController;
import spdu2022.java.project.beutysalon.notification.services.UsersNotificationService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
@ActiveProfiles("test-containers")
class PersistenceSalonsRepositoryTest {
    @Autowired
    private PersistenceSalonsRepository repository;
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
    @DisplayName("Method must return Salon by phone")
    void findById() {
        Salon salon = repository.findByPhone("+380994869938");
        assertEquals("Ludmila", salon.getSalonName());
    }

    @Test
    void getAllSalonsFromCity() {
        List<Salon> salons = repository.getAllSalonsFromCity("Sumy");
        assertEquals(2, salons.size());
    }

    @Test
    @DisplayName("Method add new Salon to DB")
    void creatNewSalons() {
        int countExpected = jdbcTemplate.queryForObject("SELECT count(*) FROM salons", Integer.class);
        repository.createNewSalons(getSalon("+380884869938"));
        int countActual = jdbcTemplate.queryForObject("SELECT count(*) FROM salons", Integer.class);
        assertEquals(++countExpected, countActual);
    }

    @Test
    @DisplayName("Method must return Salon with id after created")
    void returnSalonAfterCreate() {
        Salon salon = repository.createNewSalons(getSalon("+380975569938"));
        assertTrue(salon.getId() > 0);
    }

    private Salon getSalon(String phone) {
        Salon salon = new Salon();
        salon.setSalonName("Frocia");
        salon.setPhone(phone);
        salon.setCityLocation("Poltava");
        return salon;
    }
}