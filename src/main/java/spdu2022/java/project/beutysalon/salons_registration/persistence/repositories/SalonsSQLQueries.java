package spdu2022.java.project.beutysalon.salons_registration.persistence.repositories;

public class SalonsSQLQueries {
    public static final String SELECT_SALON_BY_ID =  "SELECT * FROM salons AS s inner join salon_working_mode AS swm ON s.id = swm.salon_id WHERE salon_id = ?";
    public static final String INSERT_SALON = "INSERT INTO salons (salon_name, phone, city) VALUES (?,?,?) RETURNING id";
    public static final String INSERT_SALON_WORKING_MODE = "INSERT INTO salon_working_mode (salon_id, start_working, finish_working) VALUES (?,?,?)";
}
