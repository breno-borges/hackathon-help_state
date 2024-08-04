package br.com.tripleahackathon.help_state.modules.citizen.useCases;

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

import br.com.tripleahackathon.help_state.modules.citizen.dto.AuthCitizenRequestDTO;
import br.com.tripleahackathon.help_state.modules.citizen.dto.AuthCitizenResponseDTO;
import br.com.tripleahackathon.help_state.modules.citizen.repository.CitizenRepository;

@Service
public class AuthCitizenUseCase {

    @Value("${security.token.secret.citizen}")
    private String secretKey;

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCitizenResponseDTO execute(AuthCitizenRequestDTO authCitizenRequestDTO)
            throws AuthenticationException {
        var citizen = this.citizenRepository.findByUsername(authCitizenRequestDTO.username())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Username/password incorrect");
                });

        var passwordMatches = this.passwordEncoder.matches(authCitizenRequestDTO.password(), citizen.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofDays(1));
        var token = JWT.create().withIssuer("help_state")
                .withSubject(citizen.getId().toString())
                .withClaim("roles", Arrays.asList("CITIZEN"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        var authCitizenResponse = AuthCitizenResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return authCitizenResponse;
    }
}
