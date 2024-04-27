package es.carlos3.rocamora.hernandez.pfcbackend.controller;

import es.carlos3.rocamora.hernandez.pfcbackend.model.Booking;
import es.carlos3.rocamora.hernandez.pfcbackend.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controlador de la clase Booking
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class BookingController {
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    /**
     * Envía todas las Bookings cuya propiedad start se sitúa entre los parámetros start y end ambos incluídos.
     * La propiedad @GetMapping configura el endpoint que recibirá las peticiones del cliente, en este caso /bookings
     * @param start Fecha inicio
     * @param end Fecha fin
     * @return Listado de Bookings encontrados
     */
    @GetMapping("/bookings")
    List<Booking> findBookings(@RequestParam(value = "start", required = false) LocalDateTime start,
                               @RequestParam(value = "end", required = false) LocalDateTime end){
        if(start != null && end != null){
            return (List<Booking>) bookingRepository.findByStartDateBetween(start, end);
        }
        // Si no se envían parámetros devuelvo todas las Bookings.
        return (List<Booking>) bookingRepository.findAll();
    }

    /**
     * Crea una nueva entrada en la tabla de reservas a partir del parámetro booking
     * La propiedad @PostMapping configura el endpoint que recibirá las peticiones del cliente, en este caso /booking
     * mediante el método POST
     * @param booking Datos de la reserva a crear
     * @return Objeto Booking creado
     */
    @PostMapping("/booking")
    Booking newBooking(@RequestBody Booking booking) {

        return bookingRepository.save(booking);
    }

}
