package spdu2022.java.project.beutysalon.logbookservices.persistence.repositories;

import spdu2022.java.project.beutysalon.entities.BookedService;
import spdu2022.java.project.beutysalon.entities.Salon;
import spdu2022.java.project.beutysalon.entities.WorkingDay;
import spdu2022.java.project.beutysalon.entities.WorkingDayOfWeek;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface LogBookRepository {
    WorkingDayOfWeek findWorkingWeekPeriodBySalonIdAndDayOfWeek(long salonId, String dayOfWeek);

    WorkingDay findWorkingDayPeriodBySalonIdAndDate(long salonId, LocalDate date);

    List<BookedService> getLogServiceByCityAndPeriod(String city, LocalDate startPeriod, LocalDate endPeriod);

    List<BookedService> getLogServiceBySalonIdAndPeriod(long salonId, LocalDate startPeriod, LocalDate endPeriod);

    Map<Long, WorkingDay> getUniqWorkingMode(long salonId, LocalDate workDay);

    Map<Long, WorkingDay> getWeekWorkingMode(long salonId, LocalDate workDay);

    List<Salon> getSalonsByCity(String city);
}