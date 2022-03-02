package spdu2022.java.project.beutysalon.salons_registration.persistence.repositories.mappers;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import spdu2022.java.project.beutysalon.entities.Salon;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalonResultSetExtractor implements ResultSetExtractor<Salon> {
    @Override
    public Salon extractData(ResultSet rs) throws SQLException, DataAccessException {
        if (rs.next()) {
            return resultSetToSalon(rs);
        }
        return new Salon();
    }

    private Salon resultSetToSalon(ResultSet resultSet) throws SQLException {
        Salon salon = new Salon();
        salon.setId(resultSet.getLong("id"));
        salon.setSalonName(resultSet.getString("salon_name"));
        salon.setPhone(resultSet.getString("phone"));
        salon.setCityLocation(resultSet.getString("city"));
        return salon;
    }


}
