package spdu2022.java.project.beutysalon.log_book_services.persistence.repositories;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import spdu2022.java.project.beutysalon.entities.Salon;
import spdu2022.java.project.beutysalon.entities.WorkingDayOfWeekPeriod;
import spdu2022.java.project.beutysalon.entities.WorkingDayPeriod;
import spdu2022.java.project.beutysalon.entities.WorkingPeriod;
import spdu2022.java.project.beutysalon.log_book_services.persistence.mappers.ResultSetExtractorForLogServices;
import spdu2022.java.project.beutysalon.log_book_services.persistence.mappers.ResultSetExtractorForWorkingPeriod;
import spdu2022.java.project.beutysalon.log_book_services.persistence.mappers.ResultSetExtractorForWorkingWeekPeriod;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

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
    public List<Map<String, String>> getLogServiceByCityAndPeriod(String city, LocalDate startPeriod, LocalDate endPeriod) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("city", city)
                .addValue("startPeriod", startPeriod)
                .addValue("endPeriod", endPeriod.plusDays(1));
        return namedJdbcTemplate.query(SELECT_LOG_SERVICE_BY_CITY_AND_PERIOD, parameters, new ResultSetExtractorForLogServices());

    }

    @Override
    public List<Map<String, String>> getLogServiceBySalonIdAndPeriod(long salonId, LocalDate startPeriod, LocalDate endPeriod) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("salonId", salonId)
                .addValue("startPeriod", startPeriod)
                .addValue("endPeriod", endPeriod.plusDays(1));
        return namedJdbcTemplate.query(SELECT_LOG_SERVICE_BY_SALON_ID_AND_PERIOD, parameters, new ResultSetExtractorForLogServices());
    }

    @Override
    public Map<Long, WorkingPeriod> getUniqWorkingMode(long salonId, LocalDate workDay) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", salonId)
                .addValue("workDay", Timestamp.valueOf(LocalDateTime.of(workDay, LocalTime.of(0,0))))
                .addValue("endPeriod", Timestamp.valueOf(LocalDateTime.of(workDay.plusDays(1), LocalTime.of(0,0))));
        return namedJdbcTemplate.query(SELECT_UNIQ_WORKING_MODE_BY_SALON_ID_AND_DAY, parameters, new ResultSetExtractorForWorkingPeriod());
    }

    @Override
    public Map<Long, WorkingPeriod> getWeekWorkingMode(long salonId, LocalDate workDay) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", salonId)
                .addValue("date", workDay);
            return namedJdbcTemplate.query(SELECT_WORKING_MODE_WEEK_BY_SALON_ID, parameters, new ResultSetExtractorForWorkingWeekPeriod());
    }

    @Override
    public WorkingPeriod findWorkingWeekPeriodBySalonIdAndDayOfWeek(long salonId, String dayOfWeek) {
       String SELECT_SALON_WORKING_WEEK_MODE_BY_SALON_ID_AND_WEEK_DAY = "SELECT * FROM salons_working_week_mode WHERE salon_id =:salonId AND day_week =:dayWeek";
       SqlParameterSource parameters = new MapSqlParameterSource().addValue("salonId", salonId).addValue("dayWeek", dayOfWeek);
       return namedJdbcTemplate.query(SELECT_SALON_WORKING_WEEK_MODE_BY_SALON_ID_AND_WEEK_DAY, parameters,(ResultSetExtractor<WorkingPeriod>) rs -> {
           WorkingDayOfWeekPeriod workingPeriod = new WorkingDayOfWeekPeriod();
           if(rs.next()) {
               String timeS = rs.getString("start_working");
               String timeE = rs.getString("end_working");
               workingPeriod.setStartWorking(LocalTime.parse(timeS, DateTimeFormatter.ofPattern("HH:mm")));
               workingPeriod.setEndWorking(LocalTime.parse(timeE, DateTimeFormatter.ofPattern("HH:mm")));
               workingPeriod.setDayOfWeek(DayOfWeek.valueOf(rs.getString("day_week")));
           }
           return workingPeriod;
       });
    }

    @Override
    public WorkingPeriod findWorkingDayPeriodBySalonIdAndDate(long salonId, LocalDate date) {
        String GET_SALON_WORKING_MODE_BY_SALON_ID_AND_PERIOD = "SELECT * FROM salon_working_mode WHERE salon_id =:id AND start_working between (:start) AND (:end)";
        Timestamp firstTimestamp =  Timestamp.valueOf(LocalDateTime.of(date, LocalTime.of(0,0)));
        Timestamp lastTimestamp = Timestamp.valueOf(LocalDateTime.of(date.plusDays(1), LocalTime.of(0,0)));
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", salonId)
                .addValue("start", firstTimestamp)
                .addValue("end", lastTimestamp);
        return namedJdbcTemplate.query(GET_SALON_WORKING_MODE_BY_SALON_ID_AND_PERIOD, parameters,
                (ResultSetExtractor<WorkingPeriod>) rs -> {
                    WorkingDayPeriod workingDayPeriod = new WorkingDayPeriod();
                    if(rs.next()) {
                        workingDayPeriod.setWorkingDay(rs.getTimestamp("start_working").toLocalDateTime().toLocalDate());
                        workingDayPeriod.setStartWorking(rs.getTimestamp("start_working").toLocalDateTime().toLocalTime());
                        workingDayPeriod.setEndWorking(rs.getTimestamp("finish_working").toLocalDateTime().toLocalTime());
                    }
                    return workingDayPeriod;
                });
    }

    @Override
    public List<Salon> getSalonsByCity(String city) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("city", city);
        String GET_SALONS_BY_CITY = "SELECT * FROM salons WHERE city=:city";
        return namedJdbcTemplate.query(GET_SALONS_BY_CITY, parameterSource, (rs, rowNum) -> {
            Salon salon = new Salon();
            salon.setId(rs.getLong("id"));
            salon.setSalonName(rs.getString("salon_name"));
            salon.setPhone(rs.getString("phone"));
            salon.setCityLocation(rs.getString("city"));
            return salon;
        });
    }
}