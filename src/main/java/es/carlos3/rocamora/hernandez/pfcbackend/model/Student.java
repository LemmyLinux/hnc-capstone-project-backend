package es.carlos3.rocamora.hernandez.pfcbackend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "login_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="lastname")
    private String lastName;
    @Column(name="phone", unique=true)
    private String phone;
    @OneToMany(targetEntity = Booking.class, cascade = CascadeType.ALL)
    private List<Booking> bookings;
    @OneToOne
    @MapsId
    @JoinColumn(name = "login_id")
    private Login login;

    public Student(){}

    @JsonCreator
    public Student(
            @JsonProperty("name") String name,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("phone") String phone,
            @JsonProperty("login") Login login
    ) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.login = login;
        this.bookings = new ArrayList<>();
    }
}
