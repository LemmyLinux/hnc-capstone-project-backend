package es.carlos3.rocamora.hernandez.pfcbackend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @JsonProperty("subject")
    @Column(name = "subject")
    private String subject;

    @JsonProperty("comments")
    @ElementCollection
    private List<String> comments;

    public Lesson(){}

    @JsonCreator
    public Lesson(String subject, List<String> comments) {
        this.subject = subject;
        this.comments = comments;
    }

    public Lesson(String subject) {
        this(subject, new ArrayList<String>());
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
