package es.carlos3.rocamora.hernandez.pfcbackend;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class PfcBackendApplication {

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Madrid"));
	}

	public static void main(String[] args) {
		SpringApplication.run(PfcBackendApplication.class, args);
	}

}
