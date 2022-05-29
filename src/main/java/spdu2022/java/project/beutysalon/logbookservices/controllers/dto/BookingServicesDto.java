package spdu2022.java.project.beutysalon.logbookservices.controllers.dto;

import org.springframework.format.annotation.DateTimeFormat;
import spdu2022.java.project.beutysalon.constraintsvalidaion.annotations.DateValid;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalTime;

public class BookingServicesDto {
    @Min(1)
    private long userId;
    @DateValid
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookingDate;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime start;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime end;

    public BookingServicesDto() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }
}
