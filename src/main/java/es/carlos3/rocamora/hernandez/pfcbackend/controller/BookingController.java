package es.carlos3.rocamora.hernandez.pfcbackend.controller;

import es.carlos3.rocamora.hernandez.pfcbackend.model.Booking;
import es.carlos3.rocamora.hernandez.pfcbackend.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class BookingController {
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("/bookings")
    List<Booking> findBookings(@RequestParam(value = "start", required = false) LocalDateTime start,
                               @RequestParam(value = "end", required = false) LocalDateTime end){
        if(start != null && end != null){
            return (List<Booking>) bookingRepository.findByStartDateBetween(start, end);
        }
        return (List<Booking>) bookingRepository.findAll();
    }

    @PostMapping("/booking")
    Booking newBooking(@RequestBody Booking booking) {
        return bookingRepository.save(booking);
    }

}
