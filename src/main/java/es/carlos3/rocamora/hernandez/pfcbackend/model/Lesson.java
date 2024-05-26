package es.carlos3.rocamora.hernandez.pfcbackend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @JsonProperty("subject")
    @Column(name = "subject", unique=true)
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}
