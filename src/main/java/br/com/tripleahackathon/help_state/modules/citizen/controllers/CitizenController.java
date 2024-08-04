package br.com.tripleahackathon.help_state.modules.citizen.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.tripleahackathon.help_state.modules.citizen.dto.ProfileCitizenResponseDTO;
import br.com.tripleahackathon.help_state.modules.profile.entities.ProfileEntity;
import br.com.tripleahackathon.help_state.modules.citizen.useCases.CreateCitizenUseCase;
import br.com.tripleahackathon.help_state.modules.citizen.useCases.ProfileCitizenUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/citizen")
@Tag(name = "Cidadao", description = "Informacoes do cidadao")
public class CitizenController {

    @Autowired
    private CreateCitizenUseCase createCitizenUseCase;

    @Autowired
    private ProfileCitizenUseCase profileCitizenUseCase;

    @PostMapping("/")
    @Operation(summary = "Cadastro do cidadao", description = "Essa funcao e responsavel por cadastrar as informacoes do cidadao")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProfileEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Usuario ja existe")
    })
    public ResponseEntity<Object> createCitizen(@Valid @RequestBody ProfileEntity profileEntity) {

        try {
            ProfileEntity result = this.createCitizenUseCase.execute(profileEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CITIZEN')")
    @Operation(summary = "Perfil do cidadao", description = "Essa funcao e responsavel por buscar as informacoes do perfil do cidadao")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProfileCitizenResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "User not found")
    })

    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> get(HttpServletRequest request) {

        var idCandidate = request.getAttribute("id");
        try {
            var profile = this.profileCitizenUseCase.execute(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
