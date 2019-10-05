package com.carros.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carros.domain.dto.CarroDTO;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository repositorio;
	
	public List<CarroDTO> getCarros() {
		//List<Carro> carros = repositorio.findAll();
		
		//List<CarroDTO> list = carros.stream().map(c -> new CarroDTO(c)).collect(Collectors.toList());
		//return repositorio.findAll().stream().map(c -> new CarroDTO(c)).collect(Collectors.toList());
		return repositorio.findAll().stream().map(c ->  CarroDTO.create(c)).collect(Collectors.toList());
		
		
		/* List<CarroDTO> list = new ArrayList<>();
		for (Carro c : carros) {
			list.add(new CarroDTO(c));
		}
		return list; */
		
	}
	
	public Optional<CarroDTO> getCarroById(Long id) {
		//return repositorio.findById(id).map(c -> new CarroDTO(c));
		return repositorio.findById(id).map(c -> CarroDTO.create(c));
	}

	public List<CarroDTO> getCarrosByTipo(String tipo) {
		//return repositorio.findByTipo(tipo).stream().map(c -> new CarroDTO(c)).collect(Collectors.toList());
		return repositorio.findByTipo(tipo).stream().map(c -> CarroDTO.create(c)).collect(Collectors.toList());
	}

	public Carro insert(Carro carro) {
		return repositorio.save(carro);
	}

	public Carro update(Carro carro, Long id) {
		
		Optional<Carro> optinal = repositorio.findById(id);
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
	
	public void delete(Long id) {
		if(getCarroById(id).isPresent()) {
			repositorio.deleteById(id);
		}
	}
}
