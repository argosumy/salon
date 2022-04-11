package spdu2022.java.project.beutysalon.log_book_services.persistence.repositories;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import spdu2022.java.project.beutysalon.entities.BookedService;
import spdu2022.java.project.beutysalon.entities.Salon;
import spdu2022.java.project.beutysalon.entities.WorkingDay;
import spdu2022.java.project.beutysalon.entities.WorkingDayOfWeek;
import spdu2022.java.project.beutysalon.log_book_services.persistence.mappers.ResultSetExtractorForLogServices;
import spdu2022.java.project.beutysalon.log_book_services.persistence.mappers.ResultSetExtractorForWorkingPeriod;
import spdu2022.java.project.beutysalon.log_book_services.persistence.mappers.ResultSetExtractorForWorkingWeekPeriod;
import spdu2022.java.project.beutysalon.utils.LocaleDateTimeConverter;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static spdu2022.java.project.beutysalon.utils.LocaleDateTimeConverter.*;

@Repository
public class PersistenceLogBookRepository implements LogBookRepository{
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    private static final String SELECT_UNIQ_WORKING_MODE_BY_SALON_ID_AND_DAY =
            "SELECT staff_id, working_day AS staff_working_day, staff.salon_id AS salon_id, swm.start_working AS start_working, finish_working AS end_working " +
            "FROM staff " +
                "INNER JOIN salon_working_mode AS swm ON staff.salon_id = swm.salon_id " +
                "INNER JOIN staff_working_mode AS staff_w ON staff.id = staff_w.staff_id " +
                "INNER JOIN salons s on s.id = staff.salon_id " +
            "WHERE staff.salon_id =:id AND working_day =:workDay AND swm.start_working BETWEEN (:workDay) AND (:endPeriod)";
    private static final String SELECT_WORKING_MODE_WEEK_BY_SALON_ID =
            "SELECT staff_id, working_day AS staff_working_day, staff.salon_id AS salon_id, day_week, start_working, end_working " +
            "FROM staff " +
                "INNER JOIN salons_working_week_mode AS sweekmode ON staff.salon_id = sweekmode.salon_id " +
                "INNER JOIN staff_working_mode AS staff_w ON staff.id = staff_w.staff_id " +
                "INNER JOIN salons s ON s.id = staff.salon_id " +
            "WHERE staff.salon_id =:id AND TRIM(day_week) = TRIM(to_char(Date(:date),'DAY')) AND staff_w.working_day = :date";

    private static final String SELECT_LOG_SERVICE_BY_SALON_ID_AND_PERIOD = "SELECT salon_id, staff_id, log.user_id AS user_id, start_service, finish_service AS end_service " +
            "FROM log_book_services AS log " +
                "INNER JOIN staff AS s ON s.id = log.staff_id " +
            "WHERE salon_id =:salonId AND start_service BETWEEN (:startPeriod) AND (:endPeriod)";

    private static final String SELECT_LOG_SERVICE_BY_CITY_AND_PERIOD = "SELECT salon_id, staff_id, log.user_id AS user_id, start_service, finish_service AS end_service " +
            "FROM log_book_services AS log " +
                "INNER JOIN staff AS st ON st.id = log.staff_id " +
                "INNER JOIN salons AS sal ON sal.id = st.salon_id " +
            "WHERE sal.city =:city AND start_service BETWEEN (:startPeriod) AND (:endPeriod)";

    public PersistenceLogBookRepository(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;

    }

    @Override
    public List<BookedService> getLogServiceByCityAndPeriod(String city, LocalDate startPeriod, LocalDate endPeriod) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("city", city)
                .addValue("startPeriod", startPeriod)
                .addValue("endPeriod", endPeriod.plusDays(1));
        return namedJdbcTemplate.query(SELECT_LOG_SERVICE_BY_CITY_AND_PERIOD, parameters, new ResultSetExtractorForLogServices());

    }

    @Override
    public List<BookedService> getLogServiceBySalonIdAndPeriod(long salonId, LocalDate startPeriod, LocalDate endPeriod) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("salonId", salonId)
                .addValue("startPeriod", startPeriod)
                .addValue("endPeriod", endPeriod.plusDays(1));
        return namedJdbcTemplate.query(SELECT_LOG_SERVICE_BY_SALON_ID_AND_PERIOD, parameters, new ResultSetExtractorForLogServices());
    }

    @Override
    public Map<Long, WorkingDay> getUniqWorkingMode(long salonId, LocalDate workDay) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", salonId)
                .addValue("workDay", convertToTimestamp(workDay))
                .addValue("endPeriod", convertToTimestamp(workDay.plusDays(1)));
        return namedJdbcTemplate.query(SELECT_UNIQ_WORKING_MODE_BY_SALON_ID_AND_DAY, parameters, new ResultSetExtractorForWorkingPeriod());
    }

    @Override
    public Map<Long, WorkingDay> getWeekWorkingMode(long salonId, LocalDate workDay) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", salonId)
                .addValue("date", workDay);
            return namedJdbcTemplate.query(SELECT_WORKING_MODE_WEEK_BY_SALON_ID, parameters, new ResultSetExtractorForWorkingWeekPeriod());
    }

    @Override
    public WorkingDayOfWeek findWorkingWeekPeriodBySalonIdAndDayOfWeek(long salonId, String dayOfWeek) {
       String sql = "SELECT * FROM salons_working_week_mode WHERE salon_id =:salonId AND day_week =:dayWeek";
       SqlParameterSource parameters = new MapSqlParameterSource().addValue("salonId", salonId).addValue("dayWeek", dayOfWeek);
       return namedJdbcTemplate.query(sql, parameters, rs -> {
           if(rs.next()) {
               WorkingDayOfWeek workingPeriod = new WorkingDayOfWeek(DayOfWeek.valueOf(rs.getString("day_week")));
               String timeS = rs.getString("start_working");
               String timeE = rs.getString("end_working");
               workingPeriod.addWorkingTimePeriod(LocaleDateTimeConverter.convertToLocalTime(timeS, "HH:mm"),
                                                  LocaleDateTimeConverter.convertToLocalTime(timeE, "HH:mm"));
               return workingPeriod;
           }
           return null;
       });
    }

    @Override
    public WorkingDay findWorkingDayPeriodBySalonIdAndDate(long salonId, LocalDate date) {
        String sql = "SELECT * FROM salon_working_mode WHERE salon_id =:id AND start_working between (:start) AND (:end)";
        Timestamp firstTimestamp = convertToTimestamp(date);
        Timestamp lastTimestamp = convertToTimestamp(date.plusDays(1));
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", salonId)
                .addValue("start", firstTimestamp)
                .addValue("end", lastTimestamp);
        return namedJdbcTemplate.query(sql, parameters,
                rs -> {
                    if(rs.next()) {
                        WorkingDay workingDay = new WorkingDay(convertToLocalDate(rs.getTimestamp("start_working")));
                        workingDay.addWorkingTimePeriod(convertToLocalTime(rs.getTimestamp("start_working")),
                                                        convertToLocalTime(rs.getTimestamp("finish_working")));
                        return workingDay;
                    }
                    return null;
                });
    }

    @Override
    public List<Salon> getSalonsByCity(String city) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("city", city);
        String sql = "SELECT * FROM salons WHERE city=:city";
        return namedJdbcTemplate.query(sql, parameterSource, (rs, rowNum) -> {
            Salon salon = new Salon();
            salon.setId(rs.getLong("id"));
            salon.setSalonName(rs.getString("salon_name"));
            salon.setPhone(rs.getString("phone"));
            salon.setCityLocation(rs.getString("city"));
            return salon;
        });
    }
}