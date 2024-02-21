package es.carlos3.rocamora.hernandez.pfcbackend.repositories;

import es.carlos3.rocamora.hernandez.pfcbackend.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {}
