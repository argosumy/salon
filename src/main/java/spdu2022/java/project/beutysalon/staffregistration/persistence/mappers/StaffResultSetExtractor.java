package spdu2022.java.project.beutysalon.staffregistration.persistence.mappers;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import spdu2022.java.project.beutysalon.entities.Staff;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffResultSetExtractor implements ResultSetExtractor<Staff> {
    @Override
    public Staff extractData(ResultSet rs) throws SQLException, DataAccessException {
        Staff staff = new Staff();
        if (rs.next()) {
            staff.setId(rs.getLong("id"));
            staff.setUserId(rs.getLong("user_id"));
            staff.setSalonId(rs.getLong("salon_id"));
            staff.setLinkPhoto(rs.getString("staff_foto"));
        }
        return staff;
    }
}
