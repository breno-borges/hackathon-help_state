package br.com.tripleahackathon.help_state.modules.helpplaces.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tripleahackathon.help_state.exceptions.HelpPlaceFoundException;
import br.com.tripleahackathon.help_state.modules.helpplaces.entities.HelpPlaceEntity;
import br.com.tripleahackathon.help_state.modules.helpplaces.repositories.HelpPlaceRepository;

@Service
public class CreateHelpPlaceUseCase {

    @Autowired
    private HelpPlaceRepository helpPlaceRepository;

    public HelpPlaceEntity execute(HelpPlaceEntity helpPlaceEntity) {
        this.helpPlaceRepository.findByNameOrAdress(helpPlaceEntity.getName(), helpPlaceEntity.getAdress())
                .ifPresent((name) -> {
                    throw new HelpPlaceFoundException();
                });

        return this.helpPlaceRepository.save(helpPlaceEntity);
    }
}
