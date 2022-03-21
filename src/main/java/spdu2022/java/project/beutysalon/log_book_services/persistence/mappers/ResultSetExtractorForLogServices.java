package spdu2022.java.project.beutysalon.log_book_services.persistence.mappers;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ResultSetExtractorForLogServices implements ResultSetExtractor<List<Map<String, String>>> {
    @Override
    public List<Map<String, String>> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Map<String, String>> result = new ArrayList<>();
        while (rs.next()) {
            String salonIdDB = rs.getString("salon_id");
            String staffId = rs.getString("staff_id");
            String userId = rs.getString("user_id");
            String start = rs.getString("start_service");
            String end = rs.getString("end_service");
            result.add(Map.of(
                    "salonId", salonIdDB,
                    "staffId", staffId,
                    "userId", userId,
                    "start", start,
                    "end", end));
        }
        result.sort(((Comparator<Map<String, String>>) (o1, o2) -> CharSequence.compare(o1.get("start"), o2.get("start")))
                .thenComparing((o1, o2) -> CharSequence.compare(o1.get("staffId"), o2.get("staffId"))));
        return result;
    }
}
