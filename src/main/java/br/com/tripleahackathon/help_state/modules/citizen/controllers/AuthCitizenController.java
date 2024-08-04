package br.com.tripleahackathon.help_state.modules.citizen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tripleahackathon.help_state.modules.citizen.dto.AuthCitizenRequestDTO;
import br.com.tripleahackathon.help_state.modules.citizen.useCases.AuthCitizenUseCase;

@RestController
@RequestMapping("/citizen")
public class AuthCitizenController {

    @Autowired
    private AuthCitizenUseCase authCitizenUseCase;

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthCitizenRequestDTO authCitizenRequestDTO) {
        try {
            var token = this.authCitizenUseCase.execute(authCitizenRequestDTO);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
