package spdu2022.java.project.beutysalon.staff_registration.persistence.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import spdu2022.java.project.beutysalon.entities.Staff;
import spdu2022.java.project.beutysalon.staff_registration.persistence.mappers.StaffResultSetExtractor;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class PersistenceStaffRepository implements StaffRepository {
    private final JdbcTemplate jdbcTemplate;

    public PersistenceStaffRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Staff findById(long id) {
        final String sql = "SELECT * FROM staff WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.query(sql, new StaffResultSetExtractor(), id)).orElse(new Staff());
    }

    @Override
    public List<Staff> getAllSalonsFromCity(String city) {
        return Collections.emptyList();
    }

    @Override
    public Staff insertNewStaff(Staff newStaff) {
        final String INSERT_STAFF = "INSERT INTO staff(salon_id, user_id, staff_foto) VALUES (?,?,?) RETURNING id";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT_STAFF, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, newStaff.getSalonId());
            ps.setLong(2, newStaff.getUserId());
            ps.setString(3, newStaff.getLinkPhoto());
            return ps;
        }, holder);
        newStaff.setId(Objects.requireNonNull(holder.getKey()).intValue());
        return newStaff;
    }

    @Override
    public boolean deleteStaffById(long id) {
        return false;
    }

    @Override
    public void updateStaff(Staff staff) {
        long staffId = staff.getId();
        long salonId = staff.getSalonId();
        String linkPhoto = staff.getLinkPhoto();
        if(findById(staffId).getId() == staffId) {
            final String sql = "UPDATE staff SET salon_id = ?, staff_foto = ? WHERE id = ?";
            jdbcTemplate.update(sql, ps -> {
                ps.setLong(1, salonId);
                ps.setString(2, linkPhoto);
                ps.setLong(3, staffId);
            });
        }
    }

    @Override
    public int getCountStaffByUserId(Staff staff) {
        final String sql = "SELECT count(*) FROM staff WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, staff.getUserId());
    }


}
