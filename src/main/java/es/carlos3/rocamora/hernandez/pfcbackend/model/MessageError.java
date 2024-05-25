package es.carlos3.rocamora.hernandez.pfcbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageError {

    @JsonProperty("message")
    private String message;

    public MessageError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
