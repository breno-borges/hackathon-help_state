package br.com.tripleahackathon.help_state.modules.profile.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.tripleahackathon.help_state.modules.profile.dto.AuthProfileRequestDTO;
import br.com.tripleahackathon.help_state.modules.profile.dto.AuthProfileResponseDTO;
import br.com.tripleahackathon.help_state.modules.profile.repositories.ProfileRepository;

@Service
public class AuthProfileUseCase {

    @Value("${security.token.secret.citizen}")
    private String secretKeyCitizen;

    @Value("${security.token.secret.state}")
    private String secretKeyState;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthProfileResponseDTO execute(AuthProfileRequestDTO authProfileRequestDTO)
            throws AuthenticationException {
        var profile = this.profileRepository.findByUsername(authProfileRequestDTO.username())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Username/password incorrect");
                });

        var passwordMatches = this.passwordEncoder.matches(authProfileRequestDTO.password(), profile.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        var expiresIn = Instant.now().plus(Duration.ofDays(1));

        String userType = profile.getUserType();
        String token;

        if (userType.equals("CITIZEN")) {
            Algorithm algorithm = Algorithm.HMAC256(secretKeyCitizen);
            token = JWT.create().withIssuer("help_state")
                    .withSubject(profile.getId().toString())
                    .withClaim("roles", Arrays.asList("CITIZEN"))
                    .withExpiresAt(expiresIn)
                    .sign(algorithm);
        } else if (userType.equals("STATE")) {
            Algorithm algorithm = Algorithm.HMAC256(secretKeyState);
            token = JWT.create().withIssuer("help_state")
                    .withSubject(profile.getId().toString())
                    .withClaim("roles", Arrays.asList("STATE"))
                    .withExpiresAt(expiresIn)
                    .sign(algorithm);
        } else {
            throw new AuthenticationException("Invalid user type");
        }

        var authProfileResponse = AuthProfileResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return authProfileResponse;
    }
}
