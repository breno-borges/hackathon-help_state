package br.com.tripleahackathon.help_state.modules.profile.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.tripleahackathon.help_state.modules.profile.dto.ProfileCitizenResponseDTO;
import br.com.tripleahackathon.help_state.modules.profile.dto.UpdateCitizenRequestDTO;
import br.com.tripleahackathon.help_state.modules.profile.entities.ProfileEntity;
import br.com.tripleahackathon.help_state.modules.profile.useCases.CreateProfileUseCase;
import br.com.tripleahackathon.help_state.modules.profile.useCases.ProfileUseCase;
import br.com.tripleahackathon.help_state.modules.profile.useCases.UpdateProfileUseCase;
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
@RequestMapping("/profile")
@Tag(name = "Profile", description = "Informacoes do perfil")
public class ProfileController {

    @Autowired
    private CreateProfileUseCase createProfileUseCase;

    @Autowired
    private ProfileUseCase profileUseCase;

    @Autowired
    private UpdateProfileUseCase updateProfileUseCase;

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
            ProfileEntity result = this.createProfileUseCase.execute(profileEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CITIZEN') or hasRole('STATE')")
    @Operation(summary = "Perfil do cidadao", description = "Essa funcao e responsavel por buscar as informacoes do perfil do cidadao")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProfileCitizenResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "User not found")
    })

    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> get(HttpServletRequest request) {

        var id = request.getAttribute("id");

        try {
            var profile = this.profileUseCase.execute(UUID.fromString(id.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}") // Voltar aqui
    @Operation(summary = "Atualizacao do cidadao", description = "Essa funcao e responsavel por atualizar as informacoes do perfil do cidadao")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProfileCitizenResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "User not found")
    })
    public ResponseEntity<Object> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateCitizenRequestDTO updateCitizenRequestDTO) {
        try {
            var result = this.updateProfileUseCase.putCitizen(id, updateCitizenRequestDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}