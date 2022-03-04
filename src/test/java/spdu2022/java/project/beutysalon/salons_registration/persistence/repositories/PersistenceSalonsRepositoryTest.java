package spdu2022.java.project.beutysalon.salons_registration.persistence.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import spdu2022.java.project.beutysalon.entities.Salon;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
class PersistenceSalonsRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private PersistenceSalonsRepository repository;



    @BeforeEach
    void initRepositoty() {
        repository = new PersistenceSalonsRepository(jdbcTemplate);
    }

    @Test
    void findById() {
    }

    @Test
    void getAllSalonsFromCity() {
    }

    @Test
    void createNewSalons() {
    }

    @Test
    void deleteSalonsById() {
    }

    @Test
    void updateSalons() {
    }

    private Salon getSalon() {
        Salon salon = new Salon();
        salon.setSalonName("Ludmila");
        salon.setPhone("+380994869938");
        salon.setCityLocation("Sumy");
        return salon;
    }
}