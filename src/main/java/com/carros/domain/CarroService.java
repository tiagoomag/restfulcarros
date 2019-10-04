package com.carros.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository repositorio;
	
	public Iterable<Carro> getCarros() {
		return repositorio.findAll();
	}
	
	public Optional<Carro> getCarrosById(Long id) {
		return repositorio.findById(id);
	}

	public Iterable<Carro> getCarrosByTipo(String tipo) {
		return repositorio.findByTipo(tipo);
	}

	public Carro insert(Carro carro) {
		return repositorio.save(carro);
	}

	public Carro update(Carro carro, Long id) {
		
		Optional<Carro> optinal = getCarrosById(id);
		if(optinal.isPresent()) {
			Carro carroDb = optinal.get();
			carroDb.setNome(carro.getNome());
			carroDb.setTipo(carro.getTipo());
			System.out.println("carro id" + carroDb.getId());
			repositorio.save(carroDb);
			return carroDb;
		} else {
			throw new RuntimeException("Não foi possível atualizar o registro.");
		}
		
	}
}
