package br.com.tripleahackathon.help_state.modules.state.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tripleahackathon.help_state.modules.profile.entities.ProfileEntity;

public interface StateRepository extends JpaRepository<ProfileEntity, UUID> {

    Optional<ProfileEntity> findByUsernameOrEmail(String username, String email);

    Optional<ProfileEntity> findByUsername(String username);
}
