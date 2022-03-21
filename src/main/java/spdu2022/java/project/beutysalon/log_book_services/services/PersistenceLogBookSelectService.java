package spdu2022.java.project.beutysalon.log_book_services.services;

import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.entities.SlotsLog;
import spdu2022.java.project.beutysalon.entities.WorkingPeriod;
import spdu2022.java.project.beutysalon.log_book_services.persistence.repositories.LogBookRepository;
import spdu2022.java.project.beutysalon.log_book_services.services.mappers.SlotsLogCreator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PersistenceLogBookSelectService implements LogBookSelectService {
    private final LogBookRepository logBookRepository;
    private final SlotsLogCreator slotsLogCreator;

    public PersistenceLogBookSelectService(LogBookRepository logBookRepository, SlotsLogCreator slotsLogCreator) {
        this.logBookRepository = logBookRepository;
        this.slotsLogCreator = slotsLogCreator;
    }

    @Override
    public List<SlotsLog> findLogBookServiceByCity(String city, String startPeriod, String endPeriod) {
        LocalDate start = LocalDate.parse(startPeriod, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse(endPeriod, DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusDays(1);
        List<SlotsLog> template = new ArrayList<>();
        List<Map<String, String>> logBookService = logBookRepository.getLogServiceByCityAndPeriod(city, start, end);
        List<Long> salonIdList = logBookService
                .stream()
                .map(x -> Long.valueOf(x.get("salonId")))
                .distinct()
                .collect(Collectors.toList());
        for(long salonId : salonIdList) {
            for(LocalDate i = start; i.isBefore(end.plusDays(1)); i = i.plusDays(1)) {
                SlotsLog workingPeriod = getWorkingPeriodTemplate(salonId, i);
                template.add(workingPeriod);
            }
        }
        return slotsLogCreator.createSlotsWithLogBookService(template, logBookService);
    }

    @Override
    public List<SlotsLog> findLogBookServiceBySalonId(long salonId, String startPeriod, String endPeriod) {
        LocalDate start = LocalDate.parse(startPeriod, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse(endPeriod, DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusDays(1);
        List<SlotsLog> template = new ArrayList<>();
        List<Map<String, String>> logBookService = logBookRepository.getLogServiceBySalonIdAndPeriod(salonId, start, end);

        for(LocalDate i = start; i.isBefore(end.plusDays(1)); i = i.plusDays(1)) {
            SlotsLog workingPeriod = getWorkingPeriodTemplate(salonId, i);
            template.add(workingPeriod);
        }
        return slotsLogCreator.createSlotsWithLogBookService(template, logBookService);
    }

    private SlotsLog getWorkingPeriodTemplate(long salonId, LocalDate date) {
        Map<Long, WorkingPeriod> staffIdUniqWorkingPeriod = getUniqWorkingMode(salonId, date);
        if(staffIdUniqWorkingPeriod.isEmpty()) {
            Map<Long, WorkingPeriod> weekPeriod = getWorkingDayOfWeekPeriod(salonId, date);
            return slotsLogCreator.createFreeSlotsBySalon(salonId,weekPeriod, "HH:mm");
        } else {
            return slotsLogCreator.createFreeSlotsBySalon(salonId, staffIdUniqWorkingPeriod, "yyyy-MM-dd HH:mm:ss");
        }
    }

    //key - staffId
    private Map<Long,WorkingPeriod> getWorkingDayOfWeekPeriod(long salonId, LocalDate date) {
        return logBookRepository.getWeekWorkingMode(salonId, date);
    }

    //key - staffId
    private Map<Long, WorkingPeriod> getUniqWorkingMode(long salonId, LocalDate localDate) {
        return logBookRepository.getUniqWorkingMode(salonId, localDate);
    }

}