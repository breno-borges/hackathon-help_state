package br.com.tripleahackathon.help_state.modules.profile.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.tripleahackathon.help_state.exceptions.UserNotFoundException;
import br.com.tripleahackathon.help_state.modules.profile.dto.ProfileActiveRequestDTO;
import br.com.tripleahackathon.help_state.modules.profile.dto.UpdateCitizenRequestDTO;
import br.com.tripleahackathon.help_state.modules.profile.entities.ProfileEntity;
import br.com.tripleahackathon.help_state.modules.profile.repositories.ProfileRepository;

@Service
public class UpdateProfileUseCase {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ProfileEntity putCitizen(UUID id, UpdateCitizenRequestDTO updateCitizenRequestDTO) {

        ProfileEntity profileEntity = this.profileRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        profileEntity.setName(updateCitizenRequestDTO.name());
        profileEntity.setEmail(updateCitizenRequestDTO.email());
        profileEntity.setAge(updateCitizenRequestDTO.age());
        profileEntity.setUsername(updateCitizenRequestDTO.username());
        profileEntity.setCreatedAt(profileEntity.getCreatedAt()); // Mantém o CreatedAt original.
        profileEntity.setProfilePicture(updateCitizenRequestDTO.profilePicture());

        var password = passwordEncoder.encode(profileEntity.getPassword());
        profileEntity.setPassword(password);

        return this.profileRepository.save(profileEntity);
    }

    public ProfileEntity patch(UUID id, ProfileActiveRequestDTO profileRequestActiveDTO) {
        ProfileEntity profileEntity = this.profileRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        profileEntity.setActive(profileRequestActiveDTO.active());

        return this.profileRepository.save(profileEntity);
    }
}
