package es.carlos3.rocamora.hernandez.pfcbackend.repositories;

import es.carlos3.rocamora.hernandez.pfcbackend.model.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio de reservas. Esta clase gestiona las Bookings con la base de datos.
 */
@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {

    /**
     * Obtiene todas las Bookings de la base de datos cuya propiedad start se sitúa entre los parámetros start y end ambos incluídos.
     * @param start Fecha inicio
     * @param end Fecha fin
     * @return Listado de Bookings presentes en la base de datos.
     */
    @Query(
            value = "SELECT * FROM booking b WHERE b.start >= :start AND b.start <= :end ORDER BY b.start",
            nativeQuery = true
    )
    public List<Booking> findByStartDateBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

}
