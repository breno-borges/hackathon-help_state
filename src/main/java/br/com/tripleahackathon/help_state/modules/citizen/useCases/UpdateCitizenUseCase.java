package br.com.tripleahackathon.help_state.modules.citizen.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.tripleahackathon.help_state.exceptions.UserNotFoundException;
import br.com.tripleahackathon.help_state.modules.citizen.dto.CitizenActiveRequestDTO;
import br.com.tripleahackathon.help_state.modules.citizen.dto.UpdateCitizenRequestDTO;
import br.com.tripleahackathon.help_state.modules.citizen.repository.CitizenRepository;
import br.com.tripleahackathon.help_state.modules.profile.entities.ProfileEntity;

@Service
public class UpdateCitizenUseCase {
    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ProfileEntity put(UUID id, UpdateCitizenRequestDTO updateCitizenRequestDTO) {

        ProfileEntity profileEntity = this.citizenRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        profileEntity.setName(updateCitizenRequestDTO.name());
        profileEntity.setEmail(updateCitizenRequestDTO.email());
        profileEntity.setAge(updateCitizenRequestDTO.age());
        profileEntity.setUsername(updateCitizenRequestDTO.username());
        profileEntity.setCreatedAt(profileEntity.getCreatedAt()); // Mantém o CreatedAt original.
        profileEntity.setProfilePicture(updateCitizenRequestDTO.profilePicture());

        var password = passwordEncoder.encode(profileEntity.getPassword());
        profileEntity.setPassword(password);

        return this.citizenRepository.save(profileEntity);
    }

    public ProfileEntity patch(UUID id, CitizenActiveRequestDTO citizenRequestActiveDTO) {
        ProfileEntity profileEntity = this.citizenRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        profileEntity.setActive(citizenRequestActiveDTO.active());

        return this.citizenRepository.save(profileEntity);
    }

}