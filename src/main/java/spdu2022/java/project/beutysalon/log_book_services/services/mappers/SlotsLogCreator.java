package spdu2022.java.project.beutysalon.log_book_services.services.mappers;

import org.springframework.stereotype.Component;
import spdu2022.java.project.beutysalon.entities.Slot;
import spdu2022.java.project.beutysalon.entities.SlotsLog;
import spdu2022.java.project.beutysalon.entities.WorkingDayPeriod;
import spdu2022.java.project.beutysalon.entities.WorkingPeriod;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Component
public class SlotsLogCreator {

    public SlotsLog createFreeSlotsBySalon(long salonId, Map<Long, WorkingPeriod> workingPeriodForSalon, String timePattern) {
        SlotsLog slotsLogForSalon = new SlotsLog();
        for(Map.Entry<Long, WorkingPeriod> entryStaff : workingPeriodForSalon.entrySet()) {
            slotsLogForSalon.setSalonId(salonId);
            Long staffId = entryStaff.getKey();
            WorkingDayPeriod workingPeriod = (WorkingDayPeriod) entryStaff.getValue();
            LocalDate day = LocalDate.parse(workingPeriod.getWorkingDay());
            slotsLogForSalon.setDate(day);
            LocalTime start = LocalTime.parse(workingPeriod.getStartWorking(), DateTimeFormatter.ofPattern(timePattern));
            LocalTime end = LocalTime.parse(workingPeriod.getEndWorking(), DateTimeFormatter.ofPattern(timePattern));
            slotsLogForSalon.getSlotMap().put(staffId, createSlotList(start,end));
        }
        return slotsLogForSalon;
    }

    public List<SlotsLog> createSlotsWithLogBookService(List<SlotsLog> templateSlotsLog, List<Map<String, String>> logBookingService) {
        logBookingService.forEach(x -> {
            long staffId = Long.parseLong(x.get("staffId"));
            LocalDate date = LocalDate.parse(x.get("start"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalTime startTime = LocalTime.parse(x.get("start"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalTime endTime = LocalTime.parse(x.get("end"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            System.out.println("StaffId " + staffId + " Date " + date + " Start " + startTime + " End " + endTime);

            Set<Slot> logBookSlots = createSlotList(startTime, endTime);
            Set<Slot> templateSlots = findSlotsByDateAndStaffId(templateSlotsLog, date, staffId);

            templateSlots.forEach(templateSlot ->
                    logBookSlots.forEach(slot -> {
                        if(templateSlot.equals(slot)) {
                            templateSlot.setFreeSlot(false);
                        }
                    }));
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
                Slot slot = new Slot(start);
                start = slot.getEndTime();
                slots.add(slot);
        }
        return slots;
    }
}
