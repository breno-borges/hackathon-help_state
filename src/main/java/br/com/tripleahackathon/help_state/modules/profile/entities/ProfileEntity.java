package br.com.tripleahackathon.help_state.modules.profile.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "profile")
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Length(max = 30, message = "O nome deve conter até (30) caracteres")
    @Schema(example = "Cidadao", requiredMode = RequiredMode.REQUIRED, description = "Nome do cidadao")
    private String name;

    @Schema(example = "33")
    private int age;

    @Email(message = "O campo [email] deve conter um e-mail válido!")
    @Schema(example = "cidadao@email.com", requiredMode = RequiredMode.REQUIRED, description = "E-mail do cidadao")
    private String email;

    @Length(max = 2, message = "Deve enviar a UF do estado")
    @Schema(example = "SP")
    private String state;

    private String userType;

    @NotBlank()
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaços!")
    @Schema(example = "usuario", requiredMode = RequiredMode.REQUIRED, description = "Username do cidadao")
    private String username;

    @Length(min = 6, max = 100, message = "A senha deve conter entre (6) e (100) caracteres")
    @Schema(example = "1234567890", minLength = 10, maxLength = 100, requiredMode = RequiredMode.REQUIRED, description = "Senha do candidato")
    private String password;

    private String profilePicture;

    @Schema(description = "Informa se o usuário está ativo ou não. 1 - ativo 0 - inativo")
    private int active;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;
}
