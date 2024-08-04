package br.com.tripleahackathon.help_state.modules.citizen.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tripleahackathon.help_state.modules.citizen.entities.CitizenEntity;

public interface CitizenRepository extends JpaRepository<CitizenEntity, UUID> {

    Optional<CitizenEntity> findByUsernameOrEmail(String username, String email);

    Optional<CitizenEntity> findByUsername(String username);
}
