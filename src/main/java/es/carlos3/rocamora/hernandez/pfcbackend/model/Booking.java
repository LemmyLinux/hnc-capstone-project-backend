package es.carlos3.rocamora.hernandez.pfcbackend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @JsonProperty("date")
    @Column(name = "creation_date")
    @NotNull(message = "Debe especificar la fecha de creación.")
    private Timestamp date;
    @JsonProperty("start")
    @Column(name = "start")
    @NotNull(message = "Debe especificar la fecha y hora de inicio.")
    private Timestamp start;
    @JsonProperty("end")
    @Column(name = "end")
    @NotNull(message = "Debe especificar la fecha y hora de fin.")
    private Timestamp end;
    @JsonProperty("status")
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @JsonProperty("lesson")
    @OneToOne(cascade = CascadeType.ALL)
    @NotNull(message = "Debe especificar la clase.")
    private Lesson lesson;

    @Transient
    private long durationInMinutes;

    @Transient
    private long studentId;

    @JsonProperty("userMail")
    @Column(name = "user_mail")
    private String userMail;

    @JsonCreator
    public Booking(
            @JsonProperty("date") Timestamp date,
            @JsonProperty("start") Timestamp start,
            @JsonProperty("end") Timestamp end,
            @JsonProperty("status") BookingStatus status,
            @JsonProperty("lesson") Lesson lesson,
            @JsonProperty("studentId") long studentId,
            @JsonProperty("userMail") String userMail) {
        this.date = date;
        this.start = start;
        this.end = end;
        this.status = status;
        this.lesson = lesson;
        this.durationInMinutes = TimeUnit.MILLISECONDS.toMinutes(end.getTime() - start.getTime());
        this.studentId = studentId;
        this.userMail = userMail;
    }

    public Booking(){}
}
