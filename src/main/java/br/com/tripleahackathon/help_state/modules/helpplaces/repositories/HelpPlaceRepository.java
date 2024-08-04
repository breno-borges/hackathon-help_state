package br.com.tripleahackathon.help_state.modules.helpplaces.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tripleahackathon.help_state.modules.helpplaces.entities.HelpPlaceEntity;

public interface HelpPlaceRepository extends JpaRepository<HelpPlaceEntity, UUID> {

    Optional<HelpPlaceEntity> findByNameOrAdress(String name, String adress);

    List<HelpPlaceEntity> findByState(String state);
}
