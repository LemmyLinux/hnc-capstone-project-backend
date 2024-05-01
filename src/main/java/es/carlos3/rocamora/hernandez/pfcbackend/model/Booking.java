package es.carlos3.rocamora.hernandez.pfcbackend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "booking")
@ToString
public class Booking {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @JsonProperty("date")
    @Column(name = "creation_date")
    private Timestamp date;
    @JsonProperty("start")
    @Column(name = "start")
    private Timestamp start;
    @JsonProperty("end")
    @Column(name = "end")
    private Timestamp end;
    @JsonProperty("status")
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @JsonProperty("lesson")
    @OneToOne(cascade = CascadeType.ALL)
    private Lesson lesson;

    @Transient
    private long durationInMinutes;

    @JsonCreator
    public Booking(Timestamp date, Timestamp start, Timestamp end, BookingStatus status, Lesson lesson) {
        this.date = date;
        this.start = start;
        this.end = end;
        this.status = status;
        this.lesson = lesson;
        this.durationInMinutes = TimeUnit.MILLISECONDS.toMinutes(end.getTime() - start.getTime());
    }

    public Booking(){}

    public Booking(Timestamp date, Timestamp start, Timestamp end, BookingStatus status) {
        this(date, start, end, status, null);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
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
