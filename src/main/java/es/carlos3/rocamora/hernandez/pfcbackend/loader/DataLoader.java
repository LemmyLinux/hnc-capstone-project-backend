package es.carlos3.rocamora.hernandez.pfcbackend.loader;

import es.carlos3.rocamora.hernandez.pfcbackend.model.*;
import es.carlos3.rocamora.hernandez.pfcbackend.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase se emplea para añadir datos de prueba a la aplicación.
 */
@Component
public class DataLoader implements ApplicationRunner {

    private StudentRepository studentRepository;

    @Autowired
    public DataLoader(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private Timestamp createTimeStamp(java.time.Month month, int day, int hour) {
        int currentYear = LocalDateTime.now().getYear();
        LocalDateTime time = LocalDateTime.of(
                currentYear,
                month,
                day,
                hour,
                0,
                0,
                0);
        return Timestamp.valueOf(time);
    }

    private void generateBookings(Student student, String[] lessons, List<Timestamp> availableStartHours, List<Timestamp> availableEndHours) {
        // Fecha de creacion por defecto de todas las reservas
        Timestamp creationDateTime = Timestamp.valueOf(LocalDateTime.now());
        // Comentarios por defecto de todas las reservas
        List<String> emptyComments = new ArrayList<String>();
        // Se crean varias reservas de cada clase para cada estudiante
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < lessons.length; j++){
                // Se obtienen horas de inicio y fin aleatorias y se eliminan de las listas de horas disponibles
                int randomPosition = (int) (Math.random() * availableStartHours.size());
                Timestamp start = availableStartHours.remove(randomPosition);
                Timestamp end = availableEndHours.remove(randomPosition);

                // Se crea una nueva clase
                Lesson lesson = new Lesson(
                        lessons[j],
                        emptyComments
                );

                // Se crea una nueva reserva
                Booking booking = new Booking(
                        creationDateTime,
                        start,
                        end,
                        BookingStatus.BOOKED,
                        lesson,
                        student.getId(),
                        student.getLogin().getMail()
                );

                // Se añade a las reservas del estudiante
                student.getBookings().add(booking);
            }
        }
    }

    private void createInitialData() {
        // Horas de inicio
        int startAvailableHours[] = {15, 16, 17, 18, 19, 20};
        // Horas de fin
        int endAvailableHours[] = {16, 17, 18, 19, 20, 21};
        // mes
        Month month = Month.JUNE;
        // Primer día del mes disponible
        int minDay = 1;
        // Ultimo dia del mes disponible
        int maxDay = month.maxLength();

        // Timestamps para las horas de inicio y fin
        List<Timestamp> startHours = new ArrayList<Timestamp>();
        List<Timestamp> endHours = new ArrayList<Timestamp>();

        // Se generan listas con las horas de inicio y fin disponibles en total:
        for(int i = minDay; i <= maxDay; i++){
            for(int j = 0; j < startAvailableHours.length; j++){
                startHours.add(createTimeStamp(month, i, startAvailableHours[j]));
                endHours.add(createTimeStamp(month, i, endAvailableHours[j]));
            }
        }

        String michaelLessons[] = {
                "Improvisación",
                "Día de la diversidad",
                "Creación de empresas",
                "Gala premios Dundies",
                "Prevención de riesgos laborales",
                "Hockey sobre hielo",
                "Mnemotecnia"
        };

        String pamLessons[] = {
                "Dibujo",
                "Diseño gráfico",
                "Pintura",
                "Olimpiadas de oficina",
                "Administración de oficina",
                "Volley"
        };

        String dwightLessons[] = {
                "Karate",
                "Caballeros de la noche",
                "Simulacro de incendio",
                "Colada Michael",
                "Reciclop",
                "Paintball",
                "Laser tag"
        };

        String kellyLessons[] = {
                "Atención al cliente",
                "Administración"
        };

        String jimLessons[] = {
                "Postres caseros",
                "Representación",
                "Codirección",
                "Técnicas de venta"
        };

        // Se crea el admin
        Student michael = new Student(
                "Michael",
                "Scott",
                "123456",
                new Login("mscott@dundermifflin.com", "1234")
        );
        michael.getLogin().setAdmin(true);

        // Se crean los estudiantes
        Student pam = new Student(
                "Pamela",
                "Beesly",
                "345678",
                new Login("pbeesly@dundermifflin.com", "1234")
        );

        Student dwight = new Student(
                "Dwight",
                "Schrute",
                "234567",
                new Login("dschrute@dundermifflin.com", "1234")
        );

        Student kelly = new Student(
                "Kelly",
                "Kapoor",
                "456789",
                new Login("kkapoor@dundermifflin.com", "1234")
        );

        Student jim = new Student(
                "Jim",
                "Halpert",
                "567890",
                new Login("jhalpert@dundermifflin.com", "1234")
        );

        Student phillys = new Student(
                "Phyllis",
                "Vance",
                "678901",
                new Login("pvance@dundermifflin.com", "1234")
        );

        // Se generan las reservas de cada estudiante
        generateBookings(michael, michaelLessons, startHours, endHours);
        generateBookings(pam, pamLessons, startHours, endHours);
        generateBookings(dwight, dwightLessons, startHours, endHours);
        generateBookings(kelly, kellyLessons, startHours, endHours);
        generateBookings(jim, jimLessons, startHours, endHours);

        // Finalmente se guardan los datos generados en la base de datos
        studentRepository.save(michael);
        studentRepository.save(pam);
        studentRepository.save(dwight);
        studentRepository.save(kelly);
        studentRepository.save(jim);
        studentRepository.save(phillys);
    }

    /**
     * Este método se ejecuta cada vez que arranca la aplicación.
     * @param args incoming application arguments
     */
    public void run(ApplicationArguments args) {

        try {
            // Se trata de obtener el estudiante con id 1.
            Student student = studentRepository.findById(1);

            // Si no existe es porque no hay ningún dato en la base de datos aún
            if(student == null){
                createInitialData();
            }
        } catch(Exception exception){
            System.out.println(exception.getMessage());
        }


    }
}
