package br.com.stconsultoria.stexemplo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.stconsultoria.stexemplo.models.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByLogin(String login);

}
