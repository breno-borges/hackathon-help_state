package br.com.tripleahackathon.help_state.modules.state.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tripleahackathon.help_state.modules.state.dto.AuthStateRequestDTO;
import br.com.tripleahackathon.help_state.modules.state.useCases.AuthStateUseCase;

@RestController
@RequestMapping("/state")
public class AuthStateController {

    @Autowired
    private AuthStateUseCase authStateUseCase;

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthStateRequestDTO authStateRequestDTO) {
        try {
            var token = this.authStateUseCase.execute(authStateRequestDTO);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
