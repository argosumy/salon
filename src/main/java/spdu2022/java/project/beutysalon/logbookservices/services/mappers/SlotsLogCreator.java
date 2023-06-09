package spdu2022.java.project.beutysalon.logbookservices.services.mappers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import spdu2022.java.project.beutysalon.entities.BookedService;
import spdu2022.java.project.beutysalon.entities.Slot;
import spdu2022.java.project.beutysalon.entities.SlotsLog;
import spdu2022.java.project.beutysalon.entities.WorkingDay;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Component
public class SlotsLogCreator {
    @Value("${interval-for-slots}")
    private int interval;

    public SlotsLogCreator() {
    }

    public SlotsLogCreator(int interval) {
        this.interval = interval;
    }

    public SlotsLog createFreeSlotsBySalon(long salonId, Map<Long, WorkingDay> workingPeriodForSalon) {
        SlotsLog slotsLogForSalon = new SlotsLog();
        for (Map.Entry<Long, WorkingDay> entryStaff : workingPeriodForSalon.entrySet()) {
            slotsLogForSalon.setSalonId(salonId);
            Long staffId = entryStaff.getKey();
            WorkingDay workingPeriod = entryStaff.getValue();
            LocalDate day = workingPeriod.getWorkingDay();
            slotsLogForSalon.setDate(day);
            LocalTime start = workingPeriod.getWorkingTimePeriod().getStartWorking();
            LocalTime end = workingPeriod.getWorkingTimePeriod().getEndWorking();
            slotsLogForSalon.getSlotMap().put(staffId, createSlotList(start, end));
        }
        return slotsLogForSalon;
    }

    public List<SlotsLog> createSlotsWithLogBookService(List<SlotsLog> templateSlotsLog, List<BookedService> logBookingService) {
        logBookingService.forEach(x -> {
            long staffId = x.getStaffId();
            LocalDate date = x.getWorkingDay();
            LocalTime startTime = x.getWorkingTimePeriod().getStartWorking();
            LocalTime endTime = x.getWorkingTimePeriod().getEndWorking();

            Set<Slot> logBookSlots = createSlotList(startTime, endTime);
            Set<Slot> templateSlots = findSlotsByDateAndStaffId(templateSlotsLog, date, staffId);

            for (Slot slot : templateSlots) {
                if (logBookSlots.contains(slot)) {
                    slot.setFreeSlot(false);
                }
            }
        });
        return templateSlotsLog.stream().filter(x -> x.getSalonId() != 0).collect(Collectors.toList());
    }

    private Set<Slot> findSlotsByDateAndStaffId(List<SlotsLog> templateSlotsLog, LocalDate date, long staffId) {
        return templateSlotsLog.stream()
                .filter(x -> x.getDate() != null && x.getDate().equals(date))
                .filter(x -> x.getSlotMap().containsKey(staffId))
                .map(x -> x.getSlotMap().get(staffId))
                .findFirst()
                .orElse(Set.of());
    }

    private Set<Slot> createSlotList(LocalTime start, LocalTime end) {
        Set<Slot> slots = new TreeSet<>();
        while (start.isBefore(end)) {
                Slot slot = new Slot(start, interval);
                start = slot.getEndTime();
                slots.add(slot);
        }
        return slots;
    }
}