package br.com.tripleahackathon.help_state.modules.state.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tripleahackathon.help_state.exceptions.UserNotFoundException;
import br.com.tripleahackathon.help_state.modules.state.dto.ProfileStateResponseDTO;
import br.com.tripleahackathon.help_state.modules.state.repository.StateRepository;

@Service
public class ProfileStateUseCase {

    @Autowired
    private StateRepository stateRepository;

    public ProfileStateResponseDTO execute(UUID idState) {
        var state = this.stateRepository.findById(idState)
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