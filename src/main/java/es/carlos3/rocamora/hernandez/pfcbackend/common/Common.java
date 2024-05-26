package es.carlos3.rocamora.hernandez.pfcbackend.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.carlos3.rocamora.hernandez.pfcbackend.model.MessageJson;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

public class Common {
    public final static String OK = "OK";
    private final static ObjectMapper mapper = new ObjectMapper();
    public final static String GENERIC_ERROR = "Ocurrió un error inesperado, intenteló más tarde.";
    public static String extractError(ConstraintViolationException errorSource){
        try {
            return ((ConstraintViolation) errorSource.getConstraintViolations().toArray()[0]).getMessage();
        } catch(Exception exception) {
            return GENERIC_ERROR;
        }
    }

    public static String parseToMessageJson(String message) {
        try {
            return mapper.writeValueAsString(new MessageJson(message));
        } catch (Exception exception) {
            return message;
        }

    }
}
