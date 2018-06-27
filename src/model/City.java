package model;

import br.ufsc.inf.leobr.cliente.Jogada;

public class City implements Jogada {
	private String name;
		
	public City(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
