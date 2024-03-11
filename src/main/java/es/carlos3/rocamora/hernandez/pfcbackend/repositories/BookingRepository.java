package es.carlos3.rocamora.hernandez.pfcbackend.repositories;

import es.carlos3.rocamora.hernandez.pfcbackend.model.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {

    @Query(
            value = "SELECT * FROM booking b WHERE b.start >= :start AND b.start <= :end ORDER BY b.start",
            nativeQuery = true
    )
    public List<Booking> findByStartDateBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

}
