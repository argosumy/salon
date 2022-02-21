package spdu2022.java.project.beutysalon.staff_registration.persistence.repositories;

public class StaffSQLQueries {
    static final String INSERT_STAFF = "INSERT INTO staff(salon_id, user_id, staff_foto) VALUES (?,?,?) RETURNING id";
    static final String COUNT_STAFF_BY_ID = "SELECT count(*) FROM staff WHERE user_id = ?";
}
