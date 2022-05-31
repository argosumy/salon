package spdu2022.java.project.beutysalon.staffregistration.persistence.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import spdu2022.java.project.beutysalon.entities.Staff;
import spdu2022.java.project.beutysalon.staffregistration.persistence.mappers.StaffResultSetExtractor;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class PersistenceStaffRepository implements StaffRepository {
    private final JdbcTemplate jdbcTemplate;
    private final int firstParam = 1;
    private final int secondParam = 2;
    private final int thirdParam = 3;

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
        final String sql = "INSERT INTO staff(salon_id, user_id, staff_foto) VALUES (?,?,?) RETURNING id";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(firstParam, newStaff.getSalonId());
            ps.setLong(secondParam, newStaff.getUserId());
            ps.setString(thirdParam, newStaff.getLinkPhoto());
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
        if (findById(staffId).getId() == staffId) {
            final String sql = "UPDATE staff SET salon_id = ?, staff_foto = ? WHERE id = ?";
            jdbcTemplate.update(sql, ps -> {
                ps.setLong(firstParam, salonId);
                ps.setString(secondParam, linkPhoto);
                ps.setLong(thirdParam, staffId);
            });
        }
    }

    @Override
    public int getCountStaffByUserId(Staff staff) {
        final String sql = "SELECT count(*) FROM staff WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, staff.getUserId());
    }
}