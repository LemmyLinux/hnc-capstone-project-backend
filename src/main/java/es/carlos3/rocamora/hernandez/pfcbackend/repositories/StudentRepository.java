package es.carlos3.rocamora.hernandez.pfcbackend.repositories;

import es.carlos3.rocamora.hernandez.pfcbackend.model.Booking;
import es.carlos3.rocamora.hernandez.pfcbackend.model.Login;
import es.carlos3.rocamora.hernandez.pfcbackend.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositorio de estudiantes. Esta clase gestiona los Students con la base de datos.
 */
public interface StudentRepository extends CrudRepository<Student, Long> {
    public Student findById(long id);

}
