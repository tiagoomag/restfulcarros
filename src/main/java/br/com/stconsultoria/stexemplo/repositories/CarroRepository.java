package br.com.stconsultoria.stexemplo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.stconsultoria.stexemplo.models.entities.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {

	List<Carro> findByTipo(String tipo);
	
}
