package br.com.thallysprojetos.backenddesafio1.repositories;

import br.com.thallysprojetos.backenddesafio1.models.AuthCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthCredentialsRepository extends JpaRepository<AuthCredential, Long> {

    Optional<AuthCredential> findByEmail(String email);

    List<AuthCredential> findAllByEmail(String email);

}