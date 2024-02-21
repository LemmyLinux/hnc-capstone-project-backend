package es.carlos3.rocamora.hernandez.pfcbackend.repositories;

import es.carlos3.rocamora.hernandez.pfcbackend.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {}
