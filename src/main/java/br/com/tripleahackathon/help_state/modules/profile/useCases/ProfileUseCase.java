package br.com.tripleahackathon.help_state.modules.profile.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tripleahackathon.help_state.exceptions.UserNotFoundException;
import br.com.tripleahackathon.help_state.modules.profile.dto.ProfileCitizenResponseDTO;
import br.com.tripleahackathon.help_state.modules.profile.dto.ProfileResponseDTO;
import br.com.tripleahackathon.help_state.modules.profile.dto.ProfileStateResponseDTO;
import br.com.tripleahackathon.help_state.modules.profile.entities.ProfileEntity;
import br.com.tripleahackathon.help_state.modules.profile.repositories.ProfileRepository;

@Service
public class ProfileUseCase {

    @Autowired
    private ProfileRepository profileRepository;

    public ProfileResponseDTO execute(UUID idProfile) {
        var profile = this.profileRepository.findById(idProfile)
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
                });

        if (profile.getUserType().equals("CITIZEN")) {
            return executeCitizen(profile);
        } else if (profile.getUserType().equals("STATE")) {
            return executeState(profile);
        } else {
            throw new IllegalArgumentException("Invalid user type");
        }
    }

    private ProfileCitizenResponseDTO executeCitizen(ProfileEntity profile) {
        var citizenDTO = ProfileCitizenResponseDTO.builder()
                .name(profile.getName())
                .age(profile.getAge())
                .email(profile.getEmail())
                .state(profile.getState())
                .userType(profile.getUserType())
                .username(profile.getUsername())
                .profilePicture(profile.getProfilePicture())
                .id(profile.getId())
                .build();
        return citizenDTO;
    }

    private ProfileStateResponseDTO executeState(ProfileEntity profile) {
        var stateDTO = ProfileStateResponseDTO.builder()
                .name(profile.getName())
                .email(profile.getEmail())
                .state(profile.getState())
                .userType(profile.getUserType())
                .username(profile.getUsername())
                .id(profile.getId())
                .build();
        return stateDTO;
    }
}
