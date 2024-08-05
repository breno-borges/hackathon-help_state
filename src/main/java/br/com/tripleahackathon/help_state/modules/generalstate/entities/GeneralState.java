package br.com.tripleahackathon.help_state.modules.generalstate.entities;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import br.com.tripleahackathon.help_state.modules.helpplaces.entities.HelpPlaceEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "general_state")
public class GeneralState {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Length(max = 20)
    @Schema(example = "Ok ou Perigo")
    private String status;

    @Schema(example = "Observações do status geral do estado")
    private String observation;

    @ManyToOne
    @JoinColumn(name = "state", insertable = false, updatable = false)
    private HelpPlaceEntity helpPlaceEntity;

    @Length(max = 2, message = "Deve enviar a UF do estado")
    @Schema(example = "SP")
    @Column(name = "state", nullable = false)
    private String state;

}
