package br.com.stconsultoria.stexemplo.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.stconsultoria.stexemplo.models.dto.CarroDTO;
import br.com.stconsultoria.stexemplo.models.entities.Carro;
import br.com.stconsultoria.stexemplo.services.CarroService;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
	
	@Autowired
	private CarroService service;
	
	@GetMapping
	public ResponseEntity get() {
		return ResponseEntity.ok(service.getCarros());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") Long id) {
		CarroDTO carro = service.getCarroById(id);
		
		return ResponseEntity.ok(carro);	
		
		//lambda
		/*return carro.map(c -> ResponseEntity.ok(c))
				.orElse(ResponseEntity.notFound().build()); */
		
		//ternario
		/*return carro.isPresent() ?
				 ResponseEntity.ok(carro.get()) :
				 ResponseEntity.notFound().build(); */ 	

		/*
		 * if(carro.isPresent()) { 
		 * 		return ResponseEntity.ok(carro.get());
		 *  } else {
		 * 		return ResponseEntity.notFound().build(); 
		 * }
		 */
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity getCarrosByTipo(@PathVariable("tipo") String tipo) {
		List<CarroDTO> carros = service.getCarrosByTipo(tipo);
		
		//alterar lógica
		return carros.isEmpty() ? 
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(carros);
	}
	
	@PostMapping
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity post(@RequestBody Carro carro) {
			CarroDTO c = service.insert(carro);
			URI location = getUri(c.getId());
			return ResponseEntity.created(location).build();
	}
	
	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(id).toUri();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Carro carro) {

		CarroDTO c = service.update(carro, id);
		
		return c != null ? 
				ResponseEntity.ok(c) :
				ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
	 service.delete(id);
		
	 return ResponseEntity.ok().build();
	}
	
}
