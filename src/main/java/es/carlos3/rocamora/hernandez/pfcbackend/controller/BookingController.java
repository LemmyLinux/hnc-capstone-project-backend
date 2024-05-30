package es.carlos3.rocamora.hernandez.pfcbackend.controller;

import es.carlos3.rocamora.hernandez.pfcbackend.common.Common;
import es.carlos3.rocamora.hernandez.pfcbackend.model.Booking;
import es.carlos3.rocamora.hernandez.pfcbackend.model.Login;
import es.carlos3.rocamora.hernandez.pfcbackend.model.Student;
import es.carlos3.rocamora.hernandez.pfcbackend.repositories.BookingRepository;
import es.carlos3.rocamora.hernandez.pfcbackend.repositories.StudentRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador de la clase Booking
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class BookingController {
    private final BookingRepository bookingRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public BookingController(BookingRepository bookingRepository, StudentRepository studentRepository) {
        this.bookingRepository = bookingRepository;
        this.studentRepository = studentRepository;
    }

    /**
     * Envía todas las Bookings.
     * La propiedad @GetMapping configura el endpoint que recibirá las peticiones del cliente, en este caso /bookings
     * @return Listado de Bookings encontrados
     */
    @GetMapping("/bookings")
    List<Booking> findBookings(){
        try {
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
            Student student = studentRepository.findById(booking.getStudentId());
            booking.setUserMail(student.getLogin().getMail());
            student.getBookings().add(booking);
            studentRepository.save(student);
            return ResponseEntity.ok().body(Common.parseToMessageJson(Common.OK));
        } catch (ConstraintViolationException exception) {
            return ResponseEntity.internalServerError().body(
                    Common.parseToMessageJson(Common.extractError(exception)));
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
            return ResponseEntity.ok().body(Common.parseToMessageJson(Common.OK));
        } catch (ConstraintViolationException exception) {
            return ResponseEntity.internalServerError().body(
                    Common.parseToMessageJson(Common.extractError(exception)));
        }
    }
}
