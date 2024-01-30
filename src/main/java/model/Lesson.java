package model;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
    private long id;
    private String subject;
    private List<String> comments;

    public Lesson(int id, String subject) {
        this.id = id;
        this.subject = subject;
        this.comments = new ArrayList<String>();
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
