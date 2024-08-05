package br.com.tripleahackathon.help_state.modules.profile.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tripleahackathon.help_state.modules.profile.dto.AuthProfileRequestDTO;
import br.com.tripleahackathon.help_state.modules.profile.useCases.AuthProfileUseCase;

@RestController
@RequestMapping("/profile")
public class AuthProfileController {
    @Autowired
    private AuthProfileUseCase authProfileUseCase;

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthProfileRequestDTO authProfileRequestDTO) {
        try {
            var token = this.authProfileUseCase.execute(authProfileRequestDTO);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
