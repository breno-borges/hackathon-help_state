package br.com.tripleahackathon.help_state.modules.state.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileStateResponseDTO {

    @Schema(example = "Estado")
    private String name;
    @Schema(example = "gov@admin.com")
    private String email;
    @Schema(example = "SP")
    private String state;
    @Schema(example = "Cidadao")
    private String userType;
    @Schema(example = "username")
    private String username;

    private UUID id;
}
