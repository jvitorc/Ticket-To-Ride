package model;

import java.util.ArrayList;

public class Map {
	/*
	 *  Boad (DIAGRAMA DE CLASSE) == MAP 
	 * Mapa de Teste
	 * 
	 * A----(AB1)-----B
	 * |(AC1)		  |   
	 * |			  |(BD1)
	 * C----(CD1)-----D
	 */

	private static String A = "A";
	private static String B = "B";
	private static String C = "C";
	private static String D = "D";
	
	
	private ArrayList<Line> lines;
	
	private ArrayList<City> cities;
	
	
	public ArrayList<Line> getLines() {
		return lines;
	}

	public ArrayList<City> getCities() {
		return cities;
	}
	
	public Map() {
		lines = new ArrayList<Line>();
		cities = new ArrayList<City>();
		createMap();
	}
	
	private void createMap() {
		
		City a = new City("A");
		City b = new City("B");
		City c = new City("C");
		City d = new City("D");
		
		Line AB1 = new Line("AB1", a, b, 2, WagonCard.YELLOW);
		Line AC1 = new Line("AC1", a, c, 2, WagonCard.RED);
		Line CD1 = new Line("CD1", c, d, 2, WagonCard.BLUE);
		Line BD1 = new Line("BD1", b, d, 2, WagonCard.PURPLE);
		
		cities.add(a);
		cities.add(b);
		cities.add(c);
		cities.add(d);
		
		lines.add(AB1);
		lines.add(AC1);
		lines.add(CD1);
		lines.add(BD1);
	}
	
}
