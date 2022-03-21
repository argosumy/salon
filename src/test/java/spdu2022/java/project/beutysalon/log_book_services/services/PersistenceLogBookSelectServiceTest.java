package spdu2022.java.project.beutysalon.log_book_services.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import spdu2022.java.project.beutysalon.log_book_services.persistence.repositories.PersistenceLogBookRepository;
import spdu2022.java.project.beutysalon.log_book_services.services.mappers.SlotsLogCreator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Salons Modification Service")
class PersistenceLogBookSelectServiceTest {
    @Mock
    PersistenceLogBookRepository repository;
    @Mock
    SlotsLogCreator slotsLogCreator;

    @InjectMocks
    PersistenceLogBookSelectService selectService;

    @Test
    void findLogBookServiceByCity() {
        String city = "Sumy";
        String date = "2022-05-01";
        LocalDate start = LocalDate.parse(date);
        LocalDate end = start;
        //List<Map<String, String>> logBookService
        when(repository.getLogServiceByCityAndPeriod(city, start, end)).thenReturn(getLogService());
//        when(repository.getWeekWorkingMode()).thenReturn();
//        when(repository.getUniqWorkingMode()).thenReturn();
//
//        selectService.findLogBookServiceByCity(city, start.toString(), end.toString());

    }

    @Test
    void findLogBookServiceBySalonId() {
    }

    private List<Map<String, String>> getLogService() {
        List<Map<String, String>> result = new ArrayList<>();
        result.add(Map.of("",""));
        return result;
    }
}