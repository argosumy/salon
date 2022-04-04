package spdu2022.java.project.beutysalon.log_book_services.services;

import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.entities.*;
import spdu2022.java.project.beutysalon.log_book_services.persistence.repositories.LogBookRepository;
import spdu2022.java.project.beutysalon.log_book_services.services.mappers.SlotsLogCreator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PersistenceLogBookSelectService implements LogBookSelectService {
    private final LogBookRepository logBookRepository;
    private final SlotsLogCreator slotsLogCreator;

    public PersistenceLogBookSelectService(LogBookRepository logBookRepository, SlotsLogCreator slotsLogCreator) {
        this.logBookRepository = logBookRepository;
        this.slotsLogCreator = slotsLogCreator;
    }

    @Override
    public List<SlotsLog> findLogBookServiceByCity(String city, LocalDate start, LocalDate end) {
        List<SlotsLog> template = new ArrayList<>();
        List<Map<String, String>> logBookService = logBookRepository.getLogServiceByCityAndPeriod(city, start, end);
        List<Salon> salonList = logBookRepository.getSalonsByCity(city);
        for(Salon salon : salonList) {
            for(LocalDate i = start; i.isBefore(end.plusDays(1)); i = i.plusDays(1)) {
                SlotsLog workingPeriod = getWorkingPeriodTemplate(salon.getId(), i);
                template.add(workingPeriod);
            }
        }
        return slotsLogCreator.createSlotsWithLogBookService(template, logBookService);
    }

    @Override
    public List<SlotsLog> findLogBookServiceBySalonId(long salonId, LocalDate start, LocalDate end) {
        List<SlotsLog> template = new ArrayList<>();
        List<Map<String, String>> logBookService = logBookRepository.getLogServiceBySalonIdAndPeriod(salonId, start, end);
        for(LocalDate i = start; i.isBefore(end.plusDays(1)); i = i.plusDays(1)) {
            SlotsLog workingPeriod = getWorkingPeriodTemplate(salonId, i);
            template.add(workingPeriod);
        }
        return slotsLogCreator.createSlotsWithLogBookService(template, logBookService);
    }

    private SlotsLog getWorkingPeriodTemplate(long salonId, LocalDate date) {
        Map<Long, WorkingDay> staffIdUniqWorkingPeriod = getUniqWorkingMode(salonId, date);
        if(staffIdUniqWorkingPeriod.isEmpty()) {
            Map<Long, WorkingDay> weekPeriod = getWorkingDayOfWeekPeriod(salonId, date);
            return slotsLogCreator.createFreeSlotsBySalon(salonId,weekPeriod);
        } else {
            return slotsLogCreator.createFreeSlotsBySalon(salonId, staffIdUniqWorkingPeriod);
        }
    }

    //key - staffId
    private Map<Long, WorkingDay> getWorkingDayOfWeekPeriod(long salonId, LocalDate date) {
        return logBookRepository.getWeekWorkingMode(salonId, date);
    }

    //key - staffId
    private Map<Long, WorkingDay> getUniqWorkingMode(long salonId, LocalDate localDate) {
        return logBookRepository.getUniqWorkingMode(salonId, localDate);
    }

}