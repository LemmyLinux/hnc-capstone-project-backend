package es.carlos3.rocamora.hernandez.pfcbackend.model;

import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "booking")
@ToString
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
    @OneToOne(cascade = CascadeType.ALL)
    private Lesson lesson;

    @Transient
    private long durationInMinutes;

    public Booking(LocalDateTime date, LocalDateTime start, LocalDateTime end, BookingStatus status, Lesson lesson) {
        this.date = date;
        this.start = start;
        this.end = end;
        this.status = status;
        this.lesson = lesson;
        this.durationInMinutes = ChronoUnit.MINUTES.between(start, end);
    }

    public Booking(LocalDateTime date, LocalDateTime start, LocalDateTime end, BookingStatus status) {
        this(date, start, end, status, null);
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
