package br.com.tripleahackathon.help_state.modules.citizen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthCitizenResponseDTO {
    private String access_token;
    private Long expires_in;
}
