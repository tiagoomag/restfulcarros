package com.carros.domain;

import java.util.ArrayList;
import java.util.List;

public class CarroService {
	
	public List<Carro> getCarros() {
		List<Carro> carros = new ArrayList<>();

		carros.add(new Carro(1L, "Sandero"));
		carros.add(new Carro(2L, "Fiesta"));
		carros.add(new Carro(3L, "Uno"));

		return carros;
	}

}
