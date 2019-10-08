package br.com.stconsultoria.stexemplo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.stconsultoria.stexemplo.exception.ObjectNotFoundException;
import br.com.stconsultoria.stexemplo.models.dto.CarroDTO;
import br.com.stconsultoria.stexemplo.models.entities.Carro;
import br.com.stconsultoria.stexemplo.repositories.CarroRepository;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository repositorio;
	
	public List<CarroDTO> getCarros() {
	
		return repositorio.findAll().stream().map(c ->  CarroDTO.create(c)).collect(Collectors.toList());
	}
	
	public CarroDTO getCarroById(Long id) {
		
		return repositorio.findById(id).map(c -> CarroDTO.create(c)).
				orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado."));
	}

	public List<CarroDTO> getCarrosByTipo(String tipo) {

		return repositorio.findByTipo(tipo).stream().map(c -> CarroDTO.create(c)).collect(Collectors.toList());
	}

	public CarroDTO insert(Carro carro) {
		Assert.isNull(carro.getId(), "Não foi possível inserir o registro.");
		
		return CarroDTO.create(repositorio.save(carro));
	}

	public CarroDTO update(Carro carro, Long id) {
		
		Optional<Carro> optinal = repositorio.findById(id);
		if(optinal.isPresent()) {
			Carro carroDb = optinal.get();
			carroDb.setNome(carro.getNome());
			carroDb.setTipo(carro.getTipo());
			repositorio.save(carroDb);
			return CarroDTO.create(carroDb);
		} else {
			return null;
			//throw new RuntimeException("Não foi possível atualizar o registro.");
		}
	}
	
	public void delete(Long id) {
			repositorio.deleteById(id);
	}
	
}
