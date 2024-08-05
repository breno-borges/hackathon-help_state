package br.com.tripleahackathon.help_state.modules.profile.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tripleahackathon.help_state.exceptions.UserNotFoundException;
import br.com.tripleahackathon.help_state.modules.profile.dto.ProfileCitizenResponseDTO;
import br.com.tripleahackathon.help_state.modules.profile.dto.ProfileStateResponseDTO;
import br.com.tripleahackathon.help_state.modules.profile.repositories.ProfileRepository;

@Service
public class ProfileUseCase {

    @Autowired
    private ProfileRepository profileRepository;

    public ProfileCitizenResponseDTO executeCitizen(UUID idProfile) {
        System.out.println(idProfile);
        var citizen = this.profileRepository.findById(idProfile)
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

    public ProfileStateResponseDTO executeState(UUID idState) {
        System.out.println(idState);
        var state = this.profileRepository.findById(idState)
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
                });

        var stateDTO = ProfileStateResponseDTO.builder()
                .name(state.getName())
                .email(state.getEmail())
                .state(state.getState())
                .userType(state.getUserType())
                .username(state.getUsername())
                .id(state.getId())
                .build();
        return stateDTO;
    }
}
