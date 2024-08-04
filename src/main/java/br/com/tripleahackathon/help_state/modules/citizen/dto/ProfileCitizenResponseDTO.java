package br.com.tripleahackathon.help_state.modules.citizen.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCitizenResponseDTO {

    private String name;
    private int age;
    private String email;
    private String state;
    private String userType;
    private String username;

    private String profilePicture;

    private UUID id;
}
