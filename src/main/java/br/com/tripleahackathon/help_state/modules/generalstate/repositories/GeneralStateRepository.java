package br.com.tripleahackathon.help_state.modules.generalstate.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tripleahackathon.help_state.modules.generalstate.entities.GeneralState;

public interface GeneralStateRepository extends JpaRepository<GeneralState, UUID> {

}
