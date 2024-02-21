package es.carlos3.rocamora.hernandez.pfcbackend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column(name = "creation_date")
    private LocalDateTime date;
    @Column(name = "start")
    private LocalDateTime start;
    @Column(name = "end")
    private LocalDateTime end;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
    @OneToOne
    private Lesson lesson;

    @Transient
    private long durationInMinutes;

    public Booking(LocalDateTime date, LocalDateTime start, LocalDateTime end, BookingStatus status) {
        this.date = date;
        this.start = start;
        this.end = end;
        this.status = status;
        this.durationInMinutes = ChronoUnit.MINUTES.between(start, end);
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

    public long getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(long durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }
}
