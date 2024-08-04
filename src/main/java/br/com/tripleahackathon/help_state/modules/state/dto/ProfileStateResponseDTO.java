package br.com.tripleahackathon.help_state.modules.state.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileStateResponseDTO {

    private String name;
    private String email;
    private String state;
    private String userType;
    private String username;

    private UUID id;
}
