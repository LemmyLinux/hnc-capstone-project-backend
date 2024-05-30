package es.carlos3.rocamora.hernandez.pfcbackend.controller;

import es.carlos3.rocamora.hernandez.pfcbackend.common.Common;
import es.carlos3.rocamora.hernandez.pfcbackend.model.Login;
import es.carlos3.rocamora.hernandez.pfcbackend.model.Student;
import es.carlos3.rocamora.hernandez.pfcbackend.repositories.LoginRepository;
import es.carlos3.rocamora.hernandez.pfcbackend.repositories.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class LoginController {
    private final LoginRepository loginRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public LoginController(LoginRepository loginRepository, StudentRepository studentRepository) {
        this.loginRepository = loginRepository;
        this.studentRepository = studentRepository;
    }
    private boolean validateRegistration(Student student){
        if(!Common.validateString(student.getName())) return false;
        if(!Common.validateString(student.getName())) return false;
        if(!Common.validateString(student.getLastName())) return false;
        if(!Common.validateString(student.getLogin().getMail())) return false;
        if(!Common.validateString(student.getLogin().getPassword())) return false;
        return true;
    }

    @PostMapping("/register")
    ResponseEntity<String> register(@RequestBody @Valid Student student) {
        try {
               if(!validateRegistration(student)){
                   return ResponseEntity.badRequest().body(Common.parseToMessageJson("Todos los campos deben tener entre 3 y 20 caracteres."));
               }
                student.getLogin().setAdmin(false);
               studentRepository.save(student);
               return ResponseEntity.ok(Common.parseToMessageJson(Common.OK));
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().body(
                    Common.parseToMessageJson(exception.getMessage()));
        }
    }

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody @Valid Login login){
        try {
            Login attempt = loginRepository.findByMailAndPassword(login.getMail(), login.getPassword());
            if(attempt == null){
                return ResponseEntity.badRequest().body(Common.parseToMessageJson("Datos de acceso incorrectos. Compruebe su usuario y su contraseña."));
            }
            return ResponseEntity.ok(Common.parseToMessageJson(attempt.getId() + ""));
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().body(
                    Common.parseToMessageJson(exception.getMessage()));
        }
    }

    @PostMapping("/admin")
    boolean isAdmin(@RequestBody long id){
        try {
            Login attempt = loginRepository.findById(id);
            if(attempt == null){
                return false;
            }
            return attempt.isAdmin();
        } catch (Exception exception) {
            return false;
        }
    }
}