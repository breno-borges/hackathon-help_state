package br.com.tripleahackathon.help_state.modules.helpplaces.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tripleahackathon.help_state.modules.helpplaces.entities.HelpPlaceEntity;
import br.com.tripleahackathon.help_state.modules.helpplaces.repositories.HelpPlaceRepository;

@Service
public class ListHelpPlaceUseCase {

    @Autowired
    private HelpPlaceRepository helpPlaceRepository;

    public List<HelpPlaceEntity> listAllHelpPlaces() {
        List<HelpPlaceEntity> helpPlaces = this.helpPlaceRepository.findAll();

        return helpPlaces;
    }

    public List<HelpPlaceEntity> listAllHelpPlacesState(String state) {
        List<HelpPlaceEntity> helpPlaces = this.helpPlaceRepository.findByState(state);

        return helpPlaces;
    }
}
