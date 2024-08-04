package br.com.tripleahackathon.help_state.modules.citizen.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tripleahackathon.help_state.exceptions.UserNotFoundException;
import br.com.tripleahackathon.help_state.modules.citizen.dto.ProfileCitizenResponseDTO;
import br.com.tripleahackathon.help_state.modules.citizen.repository.CitizenRepository;

@Service
public class ProfileCitizenUseCase {

    @Autowired
    private CitizenRepository citizenRepository;

    public ProfileCitizenResponseDTO execute(UUID idCitizen) {
        var citizen = this.citizenRepository.findById(idCitizen)
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
                });

        var citizenDTO = ProfileCitizenResponseDTO.builder()
                .name(citizen.getName())
                .age(citizen.getAge())
                .email(citizen.getEmail())
                .state(citizen.getState())
                .userType(citizen.getUserType())
                .username(citizen.getUsername())
                .profilePicture(citizen.getProfilePicture())
                .id(citizen.getId())
                .build();
        return citizenDTO;
    }
}
