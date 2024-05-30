package es.carlos3.rocamora.hernandez.pfcbackend.repositories;

import es.carlos3.rocamora.hernandez.pfcbackend.model.Login;
import es.carlos3.rocamora.hernandez.pfcbackend.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface LoginRepository extends CrudRepository<Login, Long> {
    public Login findByMailAndPassword(String mail, String password);

    public Login findById(long id);
}
