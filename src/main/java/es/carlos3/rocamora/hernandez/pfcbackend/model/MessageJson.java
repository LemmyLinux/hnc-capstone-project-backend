package es.carlos3.rocamora.hernandez.pfcbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageJson {

    @JsonProperty("message")
    private String message;

    public MessageJson(String message) {
        this.message = message;
    }
}
