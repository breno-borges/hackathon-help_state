package br.com.tripleahackathon.help_state.modules.citizen.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.tripleahackathon.help_state.exceptions.UserFoundException;
import br.com.tripleahackathon.help_state.modules.citizen.entities.CitizenEntity;
import br.com.tripleahackathon.help_state.modules.citizen.repository.CitizenRepository;

@Service
public class CreateCitizenUseCase {

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CitizenEntity execute(CitizenEntity citizenEntity) {
        this.citizenRepository.findByUsernameOrEmail(citizenEntity.getUsername(), citizenEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        var password = passwordEncoder.encode(citizenEntity.getPassword());
        citizenEntity.setPassword(password);

        return this.citizenRepository.save(citizenEntity);
    }
}
