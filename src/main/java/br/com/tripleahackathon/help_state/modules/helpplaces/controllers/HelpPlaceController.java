package br.com.tripleahackathon.help_state.modules.helpplaces.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tripleahackathon.help_state.modules.helpplaces.dto.HelpPlaceRequestDTO;
import br.com.tripleahackathon.help_state.modules.helpplaces.entities.HelpPlaceEntity;
import br.com.tripleahackathon.help_state.modules.helpplaces.useCases.CreateHelpPlaceUseCase;
import br.com.tripleahackathon.help_state.modules.helpplaces.useCases.DeleteHelpPlaceUseCase;
import br.com.tripleahackathon.help_state.modules.helpplaces.useCases.ListHelpPlaceUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/help-place")
@Tag(name = "Local de ajuda", description = "Informacoes do local de ajuda")
public class HelpPlaceController {

    @Autowired
    private CreateHelpPlaceUseCase createHelpPlaceUseCase;

    @Autowired
    private ListHelpPlaceUseCase listHelpPlaceUseCase;

    @Autowired
    private DeleteHelpPlaceUseCase deleteHelpPlaceUseCase;

    @PostMapping("/")
    @Operation(summary = "Cadastro do local de ajuda", description = "Essa funcao e responsavel por cadastrar as informacoes do local de ajuda")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = HelpPlaceEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Local ja existe")
    })
    public ResponseEntity<Object> createCitizen(@Valid @RequestBody HelpPlaceRequestDTO helpPlaceRequestDTO) {
        HelpPlaceEntity newHelpPlace = new HelpPlaceEntity(helpPlaceRequestDTO);

        try {
            HelpPlaceEntity result = this.createHelpPlaceUseCase.execute(newHelpPlace);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @Operation(summary = "Lista do local de ajuda", description = "Essa funcao e responsavel por listar as informacoes do local de ajuda")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = HelpPlaceEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Local não encontrado")
    })
    public ResponseEntity<Object> get() {
        try {
            var listHelpPlaces = this.listHelpPlaceUseCase.listAllHelpPlaces();
            return ResponseEntity.ok().body(listHelpPlaces);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // A verificar
    @GetMapping("/state")
    @Operation(summary = "Lista do local de ajuda do estado", description = "Essa funcao e responsavel por listar as informacoes do local de ajuda do estado informado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = HelpPlaceEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Local não encontrado")
    })
    public ResponseEntity<Object> getByState(HttpServletRequest request) {
        var state = request.getAttribute("state");

        try {
            var listHelpPlaces = this.listHelpPlaceUseCase.listAllHelpPlacesState(state.toString());
            return ResponseEntity.ok().body(listHelpPlaces);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta local de ajuda do estado", description = "Essa funcao e responsavel por deletar as informacoes do local de ajuda do estado informado")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Local não encontrado")
    })
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        try {
            this.deleteHelpPlaceUseCase.execute(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
