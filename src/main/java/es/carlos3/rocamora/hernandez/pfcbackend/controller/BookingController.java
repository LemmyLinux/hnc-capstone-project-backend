package es.carlos3.rocamora.hernandez.pfcbackend.controller;

import es.carlos3.rocamora.hernandez.pfcbackend.common.Common;
import es.carlos3.rocamora.hernandez.pfcbackend.model.Booking;
import es.carlos3.rocamora.hernandez.pfcbackend.repositories.BookingRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        try {
            if (start != null && end != null) {
                return (List<Booking>) bookingRepository.findByStartDateBetween(start, end);
            }
            // Si no se envían parámetros devuelvo todas las Bookings.
            return (List<Booking>) bookingRepository.findAll();
        } catch (Exception exception) {
            return new ArrayList<>();
        }
    }

    /**
     * Crea una nueva entrada en la tabla de reservas a partir del parámetro booking
     * La propiedad @PostMapping configura el endpoint que recibirá las peticiones del cliente, en este caso /booking
     * mediante el método POST
     * @param booking Datos de la reserva a crear
     * @return Objeto Booking creado
     */
    @PostMapping("/booking")
    ResponseEntity<String> newBooking(@RequestBody @Valid Booking booking) {
        // Devolvemos la reserva una vez guardada en la base de datos. Este paso asigna un valor al atributo id.
        try {
            bookingRepository.save(booking);
            return ResponseEntity.ok().body(Common.OK);
        } catch (ConstraintViolationException exception) {
            return ResponseEntity.internalServerError().body(
                    Common.parseToErrorMessage(Common.extractError(exception)));
        }
    }

    /**
     * Actualiza los datos de la reserva proporcionada por el parámetro booking
     * La propiedad @PutMapping configura el endpoint que recibirá las peticiones del cliente, en este caso /booking
     * mediante el método PUT
     * @param booking Datos de la reserva a actualizar
     * @return Objeto Booking actualizado
     */
    @PutMapping("/booking")
    ResponseEntity<String> updateBooking(@RequestBody @Valid Booking booking) {
        try {
            bookingRepository.save(booking);
            return ResponseEntity.ok().body(Common.OK);
        } catch (ConstraintViolationException exception) {
            return ResponseEntity.internalServerError().body(
                    Common.parseToErrorMessage(Common.extractError(exception)));
        }
    }

    @DeleteMapping("/booking")
    ResponseEntity<String> deleteBooking(@RequestBody @Valid Booking booking) {
        try {
            bookingRepository.delete(booking);
            return ResponseEntity.ok().body(Common.OK);
        } catch (ConstraintViolationException exception) {
            return ResponseEntity.internalServerError().body(
                    Common.parseToErrorMessage(Common.extractError(exception)));
        }
    }
}
