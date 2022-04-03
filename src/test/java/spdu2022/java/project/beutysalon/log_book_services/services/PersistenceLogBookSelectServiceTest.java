package spdu2022.java.project.beutysalon.log_book_services.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spdu2022.java.project.beutysalon.entities.*;
import spdu2022.java.project.beutysalon.log_book_services.persistence.repositories.PersistenceLogBookRepository;
import spdu2022.java.project.beutysalon.log_book_services.services.mappers.SlotsLogCreator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Get slots")
class PersistenceLogBookSelectServiceTest {
    private final long salonId = 1;
    private final String city = "Sumy";
    private final LocalDate date = LocalDate.parse("2022-05-01");
    private final LocalDate start = date;
    private final LocalDate end = date;

    @Mock
    PersistenceLogBookRepository repository;
    PersistenceLogBookSelectService selectService;

    @BeforeEach
    void init() {
        selectService = new PersistenceLogBookSelectService(repository, new SlotsLogCreator(60));
    }

    @Test
    @DisplayName("Method must return List<Slots> by City. Unique working mode has main priority.")
    void findLogBookServiceByCityWithUniqueWorkingMode() {
        when(repository.getLogServiceByCityAndPeriod(city, start, end)).thenReturn(getLogService(salonId));
        when(repository.getSalonsByCity(city)).thenReturn(List.of(new Salon(salonId, "Ludmila", "+380994869938", city)));
        when(repository.getUniqWorkingMode(salonId, date)).thenReturn(getWorkingMode(date));

        List<SlotsLog> actual = selectService.findLogBookServiceByCity(city,start, end);
        assertEquals(expectedSlotsLog(salonId, date), actual);
    }

    @Test
    @DisplayName("Method must return List<Slots> by City. Unique working mode is not exist." +
            "Week working mode has second priority.")
    void findLogBookServiceByCityWithWeekWorkingMode() {
        when(repository.getLogServiceByCityAndPeriod(city, start, end)).thenReturn(getLogService(salonId));
        when(repository.getSalonsByCity(city)).thenReturn(List.of(new Salon(salonId, "Ludmila", "+380994869938", city)));
        when(repository.getUniqWorkingMode(salonId, date)).thenReturn(new HashMap<>());
        when(repository.getWeekWorkingMode(salonId, date)).thenReturn(getWorkingMode(date));

        List<SlotsLog> actual = selectService.findLogBookServiceByCity(city,start, end);
        assertEquals(expectedSlotsLog(salonId, date), actual);
    }

    @Test
    @DisplayName("Method must return List<Slots> by salonId. Unique working mode has main priority.")
    void findLogBookServiceBySalonIdWithUniqueWorkingMode() {
        when(repository.getUniqWorkingMode(salonId, date)).thenReturn(getWorkingMode(date));
        when(repository.getLogServiceBySalonIdAndPeriod(salonId, start, end)).thenReturn(getLogService(salonId));

        List<SlotsLog> actual = selectService.findLogBookServiceBySalonId(1,start, end);

        assertEquals(expectedSlotsLog(salonId, date), actual);
    }

    @Test
    @DisplayName("Method must return List<Slots> by salonId. Unique working mode is not exist. " +
            "Week working mode has second priority.")
    void findLogBookServiceBySalonIdWithWeekWorkingMode() {
        when(repository.getUniqWorkingMode(salonId, date)).thenReturn(new HashMap<>());
        when(repository.getWeekWorkingMode(salonId, date)).thenReturn(getWorkingMode(date));
        when(repository.getLogServiceBySalonIdAndPeriod(salonId, start, end)).thenReturn(getLogService(salonId));

        List<SlotsLog> actual = selectService.findLogBookServiceBySalonId(1, start, end);

        assertEquals(expectedSlotsLog(salonId, date), actual);
    }

    private List<Map<String, String>> getLogService(long salonId) {
        List<Map<String, String>> result = new ArrayList<>();
            String salonIdDB = String.valueOf(salonId);
            String staffId = "1";
            String userId = "2";
            String start = "2022-05-01 10:00:00";
            String end = "2022-05-01 13:00:00";
            result.add(Map.of(
                    "salonId", salonIdDB,
                    "staffId", staffId,
                    "userId", userId,
                    "start", start,
                    "end", end));
        result.sort(((Comparator<Map<String, String>>) (o1, o2) -> CharSequence.compare(o1.get("start"), o2.get("start")))
                .thenComparing((o1, o2) -> CharSequence.compare(o1.get("staffId"), o2.get("staffId"))));
        return result;
    }

    private Map<Long, WorkingPeriod> getWorkingMode(LocalDate date) {
        Map<Long, WorkingPeriod> result = new HashMap<>();
            WorkingDayPeriod period = new WorkingDayPeriod();
            Long staffId = 1L;
            period.setWorkingDay(date);
            period.setStartWorking(LocalTime.parse("10:00"));
            period.setEndWorking(LocalTime.parse("18:00"));
            result.put(staffId, period);
        return result;
    }

    private List<SlotsLog> expectedSlotsLog(long salonId, LocalDate date) {
        List<SlotsLog> result = new ArrayList<>();
        SlotsLog slotsLog =  new SlotsLog();
        slotsLog.setSalonId(salonId);
        slotsLog.setDate(date);
        Map<Long, Set<Slot>> slotListForAllStaff = slotsLog.getSlotMap();
        Set<Slot> slots =new HashSet<>();
        slots.add(creatorSlots(LocalTime.parse("10:00"), 60, false));
        slots.add(creatorSlots(LocalTime.parse("11:00"), 60, false));
        slots.add(creatorSlots(LocalTime.parse("12:00"), 60, false));
        slots.add(creatorSlots(LocalTime.parse("13:00"), 60, false));
        slots.add(creatorSlots(LocalTime.parse("14:00"), 60, true));
        slots.add(creatorSlots(LocalTime.parse("15:00"), 60, true));
        slots.add(creatorSlots(LocalTime.parse("16:00"), 60, true));
        slots.add(creatorSlots(LocalTime.parse("17:00"), 60, true));
        slots.add(creatorSlots(LocalTime.parse("18:00"), 60, true));
        slotListForAllStaff.put(1L,slots);
        result.add(slotsLog);
        return result;
    }

    private Slot creatorSlots(LocalTime startTime, int interval, boolean isFree) {
        Slot slot =  new Slot(startTime, interval);
        slot.setFreeSlot(isFree);
        return slot;
    }

}