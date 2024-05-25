package es.carlos3.rocamora.hernandez.pfcbackend.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.carlos3.rocamora.hernandez.pfcbackend.model.MessageError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.validation.BindingResult;

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

    public static String parseToErrorMessage(String message) {
        try {
            return mapper.writeValueAsString(new MessageError(message));
        } catch (Exception exception) {
            return message;
        }

    }
}
