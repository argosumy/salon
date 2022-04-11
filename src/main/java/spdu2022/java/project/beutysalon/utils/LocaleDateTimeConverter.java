package spdu2022.java.project.beutysalon.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocaleDateTimeConverter {
    public static LocalDate convertToLocalDate(Timestamp timestamp) {
        return timestamp.toLocalDateTime().toLocalDate();
    }

    public static LocalDate convertToLocalDate(String date) {
        return LocalDate.parse(date);
    }
    public static LocalTime convertToLocalTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime().toLocalTime();
    }

    public static LocalTime convertToLocalTime(String textTime, String pattern) {
        return LocalTime.parse(textTime, DateTimeFormatter.ofPattern(pattern));
    }

    public static Timestamp convertToTimestamp(LocalDate localDate) {
        return Timestamp.valueOf(LocalDateTime.of(localDate, LocalTime.of(0,0)));
    }

    public static Timestamp convertToTimestamp(LocalDate localDate, LocalTime localTime) {
        return Timestamp.valueOf(LocalDateTime.of(localDate, localTime));
    }

}
