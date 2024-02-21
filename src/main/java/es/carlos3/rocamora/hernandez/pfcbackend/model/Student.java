package es.carlos3.rocamora.hernandez.pfcbackend.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(name="mail", unique=true)
    private String mail;
    @Column(name="name")
    private String name;
    @Column(name="lastname")
    private String lastName;
    @Column(name="phone", unique=true)
    private String phone;
    @OneToMany(targetEntity = Booking.class)
    private List<Booking> bookings;

    public Student(String mail, String name, String lastName, String phone) {
        this.mail = mail;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.bookings = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
