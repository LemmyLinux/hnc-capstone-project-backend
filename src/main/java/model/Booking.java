package model;

import java.time.LocalDateTime;
import java.util.Date;

public class Booking {
    private long id;
    private LocalDateTime date;
    private LocalDateTime start;
    private LocalDateTime end;
    private BookingStatus status;

    public Booking(long id, LocalDateTime date, LocalDateTime start, LocalDateTime end, BookingStatus status) {
        this.id = id;
        this.date = date;
        this.start = start;
        this.end = end;
        this.status = status;
    }

    public Booking(long id, LocalDateTime start, LocalDateTime end) {
        this(id, LocalDateTime.now(), start, end, BookingStatus.AVAILABLE);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}
