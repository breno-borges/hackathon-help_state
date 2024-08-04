package br.com.tripleahackathon.help_state.modules.helpplaces.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tripleahackathon.help_state.modules.helpplaces.entities.HelpPlaceEntity;

public interface HelpPlaceRepository extends JpaRepository<HelpPlaceEntity, UUID> {

    Optional<HelpPlaceRepository> findByNameOrAdress(String name, String adress);

    Optional<HelpPlaceRepository> findByState(String state);
}
