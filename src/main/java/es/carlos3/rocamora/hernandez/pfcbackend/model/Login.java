package es.carlos3.rocamora.hernandez.pfcbackend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "login")
public class Login {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @JsonProperty("mail")
    @Column(name = "mail", unique = true)
    @NotBlank(message = "Debe especificar un nombre de usuario válido.")
    private String mail;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @JsonProperty("admin")
    @Column(name = "admin")
    private boolean admin;

    @OneToOne(mappedBy = "login", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Student student;

    @JsonCreator
    public Login(
            @JsonProperty("mail") String mail,
            @JsonProperty("password") String password
    ) {
        this.mail = mail;
        this.password = password;
    }

    public Login() {}

}
