package model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private long id;
    private String mail;
    private String name;
    private String lastName;
    private String phone;
    private List<Booking> bookings;

    public Student(long id, String mail, String name, String lastName, String phone) {
        this.id = id;
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
