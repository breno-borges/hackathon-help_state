package br.com.tripleahackathon.help_state.modules.helpplaces.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tripleahackathon.help_state.exceptions.IdNotFoundException;
import br.com.tripleahackathon.help_state.modules.helpplaces.repositories.HelpPlaceRepository;

@Service
public class DeleteHelpPlaceUseCase {

    @Autowired
    private HelpPlaceRepository helpPlaceRepository;

    public void execute(UUID id) {
        this.helpPlaceRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException());

        this.helpPlaceRepository.deleteById(id);
    }
}
