package br.com.tripleahackathon.help_state.modules.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthProfileResponseDTO {
    private String access_token;
    private Long expires_in;
}
