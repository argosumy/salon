package spdu2022.java.project.beutysalon.salons_working_mode.persistence.mappers;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.entities.WorkingDay;

import java.sql.ResultSet;
import java.sql.SQLException;

import static spdu2022.java.project.beutysalon.utils.LocaleDateTimeConverter.convertToLocalDate;
import static spdu2022.java.project.beutysalon.utils.LocaleDateTimeConverter.convertToLocalTime;

public class WorkingModeForDaysPeriodResultSetExtractor implements ResultSetExtractor<SalonWorkingMode> {
    @Override
    public SalonWorkingMode extractData(ResultSet rs) throws SQLException, DataAccessException {
        SalonWorkingMode result = new SalonWorkingMode();
        WorkingDay period;
        while (rs.next()) {
            period = new WorkingDay(convertToLocalDate(rs.getTimestamp("start_working")));
            if(result.getSalonId() == 0) {
                result.setSalonId(rs.getLong("salon_id"));
            }
            period.addWorkingTimePeriod(convertToLocalTime(rs.getTimestamp("start_working")),
                                        convertToLocalTime(rs.getTimestamp("finish_working")));
            result.getSalonWorkingModeUniq().add(period);
        }
        return result;
    }
}
