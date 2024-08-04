package br.com.tripleahackathon.help_state.modules.state.useCases;

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

import br.com.tripleahackathon.help_state.modules.state.dto.AuthStateRequestDTO;
import br.com.tripleahackathon.help_state.modules.state.dto.AuthStateResponseDTO;
import br.com.tripleahackathon.help_state.modules.state.repository.StateRepository;

@Service
public class AuthStateUseCase {
    @Value("${security.token.secret.state}")
    private String secretKey;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthStateResponseDTO execute(AuthStateRequestDTO authStateRequestDTO)
            throws AuthenticationException {
        var citizen = this.stateRepository.findByUsername(authStateRequestDTO.username())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Username/password incorrect");
                });

        var passwordMatches = this.passwordEncoder.matches(authStateRequestDTO.password(), citizen.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofMinutes(10));
        var token = JWT.create().withIssuer("help_state")
                .withSubject(citizen.getId().toString())
                .withClaim("roles", Arrays.asList("STATE"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        var authStateResponse = AuthStateResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return authStateResponse;
    }
}
