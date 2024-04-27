package es.carlos3.rocamora.hernandez.pfcbackend.repositories;

import es.carlos3.rocamora.hernandez.pfcbackend.model.Lesson;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio de clases. Esta clase gestiona las Lessons con la base de datos.
 */
public interface LessonRepository extends CrudRepository<Lesson, Long> {}
