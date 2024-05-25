package es.carlos3.rocamora.hernandez.pfcbackend.repositories;

import es.carlos3.rocamora.hernandez.pfcbackend.model.Lesson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repositorio de clases. Esta clase gestiona las Lessons con la base de datos.
 */
public interface LessonRepository extends CrudRepository<Lesson, Long> {

    @Query(value = "SELECT DISTINCT l.subject FROM Lesson l")
    public List<String> findDistinctSubject();


}
