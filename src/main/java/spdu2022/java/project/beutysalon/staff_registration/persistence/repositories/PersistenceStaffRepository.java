package spdu2022.java.project.beutysalon.staff_registration.persistence.repositories;

import org.springframework.stereotype.Repository;
import spdu2022.java.project.beutysalon.entities.Staff;
import spdu2022.java.project.beutysalon.exeptions.EntityNotUniqException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class PersistenceStaffRepository implements StaffRepository {
    private final DataSource dataSource;

    public PersistenceStaffRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Staff> findById(long id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Staff> getAllSalonsFromCity(String city) {
        return Collections.emptyList();
    }

    @Override
    public Staff insertNewStaff(Staff newStaff) throws SQLException, EntityNotUniqException {
        if(isStaffNotExist(newStaff)) {
            try (Connection connection = dataSource.getConnection()) {
                PreparedStatement ps = connection.prepareStatement(StaffSQLQueries.INSERT_STAFF, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, newStaff.getSalonId());
                ps.setLong(2, newStaff.getUserId());
                ps.setString(3, newStaff.getLinkPhoto());
                int i = ps.executeUpdate();
                if (i == 1) {
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        newStaff.setId(rs.getLong(1));
                    }
                }
                return newStaff;
            }
        } else {
            throw new EntityNotUniqException("Staff with User_ID " + newStaff.getUserId() + " already exist");
        }
    }

    @Override
    public boolean deleteStaffById(long id) {
        return false;
    }

    @Override
    public Staff updateStaff(Staff staffUpdate) {
        return new Staff();
    }

    private boolean isStaffNotExist(Staff newStaff) throws SQLException {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(StaffSQLQueries.COUNT_STAFF_BY_ID);
            ps.setLong(1, newStaff.getUserId());
            ps.executeQuery();
            ResultSet resultSet = ps.getResultSet();
            if(resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
            return true;
        }
    }
}
