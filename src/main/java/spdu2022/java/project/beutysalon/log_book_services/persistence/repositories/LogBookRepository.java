package spdu2022.java.project.beutysalon.log_book_services.persistence.repositories;

import spdu2022.java.project.beutysalon.entities.Salon;
import spdu2022.java.project.beutysalon.entities.WorkingPeriod;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface LogBookRepository {
    WorkingPeriod findWorkingWeekPeriodBySalonIdAndDayOfWeek(long salonId, String dayOfWeek);

    WorkingPeriod findWorkingDayPeriodBySalonIdAndDate(long salonId, LocalDate date);

    List<Map<String, String>> getLogServiceByCityAndPeriod(String city, LocalDate startPeriod, LocalDate endPeriod);

    List<Map<String, String>> getLogServiceBySalonIdAndPeriod(long salonId, LocalDate startPeriod, LocalDate endPeriod);

    Map<Long, WorkingPeriod> getUniqWorkingMode(long salonId, LocalDate workDay);

    Map<Long, WorkingPeriod> getWeekWorkingMode(long salonId, LocalDate workDay);

    List<Salon> getSalonsByCity(String city);

}
