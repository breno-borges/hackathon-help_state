package br.com.tripleahackathon.help_state.modules.state.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.tripleahackathon.help_state.exceptions.UserFoundException;
import br.com.tripleahackathon.help_state.modules.profile.entities.ProfileEntity;
import br.com.tripleahackathon.help_state.modules.state.repository.StateRepository;

@Service
public class CreateStateUseCase {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ProfileEntity execute(ProfileEntity profileEntity) {
        this.stateRepository.findByUsernameOrEmail(profileEntity.getUsername(), profileEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        var password = passwordEncoder.encode(profileEntity.getPassword());
        profileEntity.setPassword(password);
        profileEntity.setActive(1);

        return this.stateRepository.save(profileEntity);
    }
}
