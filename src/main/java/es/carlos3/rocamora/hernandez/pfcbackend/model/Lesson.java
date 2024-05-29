package es.carlos3.rocamora.hernandez.pfcbackend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @JsonProperty("subject")
    @Column(name = "subject")
    @NotBlank(message = "Debe especificar un valor para la asignatura.")
    private String subject;

    @JsonProperty("comments")
    @ElementCollection
    private List<String> comments;

    public Lesson(){}

    @JsonCreator
    public Lesson(
            @JsonProperty("subject") String subject,
            @JsonProperty("comments") List<String> comments
    ) {
        this.subject = subject;
        this.comments = comments;
    }
}
