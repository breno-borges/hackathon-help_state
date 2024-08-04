package br.com.tripleahackathon.help_state.modules.helpplaces.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import br.com.tripleahackathon.help_state.modules.helpplaces.dto.HelpPlaceRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "help_place")
public class HelpPlaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Length(max = 30, message = "O nome deve conter até (30) caracteres")
    @Schema(example = "Nome do local de ajuda", requiredMode = RequiredMode.REQUIRED, description = "Nome do local de ajuda")
    private String name;

    @Length(max = 2, message = "Deve enviar a UF do estado")
    @Schema(example = "SP")
    private String state;

    @Schema(description = "URL da foto")
    private String profilePicture;

    @Schema(example = "Rua do local de ajuda")
    private String adress;

    @Schema(example = "Sao Paulo")
    private String county;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    public HelpPlaceEntity(HelpPlaceRequestDTO helpPlaceRequestDTO) {
        this.name = helpPlaceRequestDTO.name();
        this.adress = helpPlaceRequestDTO.adress();
        this.state = helpPlaceRequestDTO.state();
        this.county = helpPlaceRequestDTO.county();
        this.profilePicture = helpPlaceRequestDTO.profilePicture();

    }
}
