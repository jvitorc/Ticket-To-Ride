package model;

import java.util.ArrayList;

public class Map {
		
	private ArrayList<Line> lines;
	
	private ArrayList<City> cities;

	public Map() {
		lines = new ArrayList<Line>();
		cities = new ArrayList<City>();
		createMap();
	}
	
	public ArrayList<Line> getLines() {
		return this.lines;
	}
	
	public ArrayList<City> getCities() {
		return cities;
	}
	
	public ArrayList<String> getLinesInfo() {
		ArrayList<String> lineId = new ArrayList<String>();
		for (Line it : this.lines) {
			lineId.add(it.toString());
		}
		return lineId;
	}
	
	private void createMap() {
		
		City vancouver = new City("Vancouver");
		City calgary = new City("Calgary");
		City seattle = new City("Seattle");
		City portland = new City("Portland");
		City helena	= new City("Helena");
		City winnipeg = new City("Winnipeg");
		City saltLakeCity = new City("Salt Lake City");
		City sanFrancisco = new City("San Francisco");
		City losAngeles = new City("Los Angeles");
		City lasVegas = new City("Las Vegas");
		City phoenix = new City("Phoenix");
		City elPaso = new City("El Paso");
		City santaFe = new City("Santa Fe");
		City denver = new City("Denver");
		City oklahomaCity = new City("Oklahoma City");
		City duluth = new City("Duluth");
		City omaha = new City("Omaha");
		City kansasCity = new City("Kansas City");
		City dallas = new City("Dallas");
		City houston = new City("Houston");
		City newOrleans = new City("New Orleans");
		City littleRock = new City("Little Rock");
		City saintLouis = new City("Saint Louis");
		City chicago = new City("Chicago");
		City nashville = new City("Nashville");
		City saultStMarie = new City("Sault St. Marie");
		City toronto = new City("Toronto");
		City montreal = new City("Montreal");
		City boston = new City("Boston");
		City newYork = new City("New York");
		City pittsburgh = new City("Pittsburgh");
		City washington = new City("Washington");
		City raleigh = new City("Raleigh");
		City atlanta = new City("Atlanta");
		City charleston = new City("Charleston");
		City miami = new City("Miami");

		this.cities.add(vancouver);
		this.cities.add(calgary);
		this.cities.add(seattle);
		this.cities.add(portland);
		this.cities.add(helena);
		this.cities.add(winnipeg);
		this.cities.add(saltLakeCity);
		this.cities.add(sanFrancisco);
		this.cities.add(losAngeles);
		this.cities.add(lasVegas);
		this.cities.add(phoenix);
		this.cities.add(elPaso);
		this.cities.add(santaFe);
		this.cities.add(denver);
		this.cities.add(oklahomaCity);
		this.cities.add(duluth);
		this.cities.add(omaha);
		this.cities.add(kansasCity);
		this.cities.add(dallas);
		this.cities.add(houston);
		this.cities.add(newOrleans);
		this.cities.add(littleRock);
		this.cities.add(saintLouis);
		this.cities.add(chicago);
		this.cities.add(nashville);
		this.cities.add(saultStMarie);
		this.cities.add(toronto);
		this.cities.add(montreal);
		this.cities.add(boston);
		this.cities.add(newYork);
		this.cities.add(pittsburgh);
		this.cities.add(washington);
		this.cities.add(raleigh);
		this.cities.add(atlanta);
		this.cities.add(charleston);
		this.cities.add(miami);
		
		
		lines.add(new Line(0, vancouver, calgary, 3, WagonCard.MULTICOLOR));
		lines.add(new Line(1, vancouver, seattle, 1, WagonCard.MULTICOLOR));
		lines.add(new Line(2, seattle, calgary ,4 , WagonCard.MULTICOLOR));
		lines.add(new Line(3, calgary, helena, 4, WagonCard.YELLOW));
		lines.add(new Line(4, seattle, portland, 1, WagonCard.MULTICOLOR));
		lines.add(new Line(5, portland, saltLakeCity, 6, WagonCard.BLUE));
		lines.add(new Line(6, portland, sanFrancisco , 5, WagonCard.PURPLE));
		lines.add(new Line(7, sanFrancisco, losAngeles, 3, WagonCard.YELLOW));
		lines.add(new Line(8, losAngeles, lasVegas, 2, WagonCard.MULTICOLOR));
		lines.add(new Line(9, losAngeles, phoenix, 3, WagonCard.MULTICOLOR));
		lines.add(new Line(10, losAngeles, elPaso, 6, WagonCard.BLACK));
		lines.add(new Line(11, elPaso, phoenix, 3, WagonCard.MULTICOLOR));
		lines.add(new Line(12, elPaso, santaFe, 2, WagonCard.MULTICOLOR));
		lines.add(new Line(13, phoenix, santaFe, 3, WagonCard.MULTICOLOR));
		lines.add(new Line(14, elPaso, oklahomaCity, 5, WagonCard.YELLOW));
		lines.add(new Line(15, elPaso, dallas, 3, WagonCard.RED));
		lines.add(new Line(16, elPaso, houston, 6, WagonCard.GREEN));
		lines.add(new Line(17, santaFe, oklahomaCity, 3, WagonCard.BLUE));
		lines.add(new Line(18, santaFe, denver, 2, WagonCard.MULTICOLOR));
		lines.add(new Line(19, phoenix, denver, 5, WagonCard.WHITE));
		lines.add(new Line(20, denver, oklahomaCity, 4, WagonCard.RED));
		lines.add(new Line(21, denver, kansasCity, 4, WagonCard.ORANGE));
		lines.add(new Line(22, oklahomaCity, kansasCity, 2, WagonCard.MULTICOLOR));
		lines.add(new Line(23, denver, omaha, 4, WagonCard.PURPLE));
		lines.add(new Line(24, denver, helena, 4, WagonCard.GREEN));
		lines.add(new Line(25, saltLakeCity, denver, 3, WagonCard.RED));
		lines.add(new Line(26, saltLakeCity, helena, 3, WagonCard.PURPLE));
		lines.add(new Line(27, helena, calgary, 4, WagonCard.MULTICOLOR));
		lines.add(new Line(28, helena, winnipeg, 4, WagonCard.BLUE));
		lines.add(new Line(29, helena, duluth, 6, WagonCard.ORANGE));
		lines.add(new Line(30, helena, omaha, 5, WagonCard.RED));
		lines.add(new Line(31, calgary, winnipeg, 6, WagonCard.WHITE));
		lines.add(new Line(32, winnipeg, saultStMarie, 6, WagonCard.MULTICOLOR));
		lines.add(new Line(33, winnipeg, duluth, 4, WagonCard.BLACK));
		lines.add(new Line(34, duluth, saultStMarie, 3, WagonCard.MULTICOLOR));
		lines.add(new Line(35, duluth, toronto, 6, WagonCard.PURPLE));
		lines.add(new Line(36, duluth, chicago, 3, WagonCard.RED));
		lines.add(new Line(37, duluth, omaha, 2, WagonCard.RED));
		lines.add(new Line(38, omaha, chicago, 4, WagonCard.BLUE));
		lines.add(new Line(39, omaha, kansasCity, 1, WagonCard.MULTICOLOR));
		lines.add(new Line(40, kansasCity, saintLouis, 2, WagonCard.BLUE));
		lines.add(new Line(41, oklahomaCity, littleRock, 2, WagonCard.MULTICOLOR));
		lines.add(new Line(42, oklahomaCity, dallas, 2, WagonCard.MULTICOLOR));
		lines.add(new Line(43, dallas, littleRock, 2, WagonCard.MULTICOLOR));
		lines.add(new Line(44, dallas, houston, 1, WagonCard.MULTICOLOR));
		lines.add(new Line(45, houston, newOrleans, 2, WagonCard.MULTICOLOR));
		lines.add(new Line(46, newOrleans, littleRock, 3, WagonCard.GREEN));
		lines.add(new Line(47, newOrleans, atlanta, 4, WagonCard.YELLOW));
		lines.add(new Line(48, newOrleans, miami, 6, WagonCard.RED));
		lines.add(new Line(49, miami, atlanta, 5, WagonCard.BLUE));
		lines.add(new Line(50, miami, charleston, 4, WagonCard.PURPLE));
		lines.add(new Line(51, charleston, atlanta, 2, WagonCard.MULTICOLOR));
		lines.add(new Line(52, charleston, raleigh, 2, WagonCard.MULTICOLOR));
		lines.add(new Line(53, atlanta, raleigh, 2, WagonCard.MULTICOLOR));
		lines.add(new Line(54, atlanta, nashville, 1, WagonCard.MULTICOLOR));
		lines.add(new Line(55, littleRock, nashville, 3, WagonCard.WHITE));
		lines.add(new Line(56, littleRock, saintLouis, 2, WagonCard.MULTICOLOR));
		lines.add(new Line(57, saintLouis, nashville, 2, WagonCard.MULTICOLOR));
		lines.add(new Line(58, saintLouis, pittsburgh, 5, WagonCard.GREEN));
		lines.add(new Line(59, saintLouis, chicago, 2, WagonCard.WHITE));
		lines.add(new Line(60, nashville, raleigh, 3, WagonCard.BLACK));
		lines.add(new Line(61, nashville, pittsburgh, 5, WagonCard.YELLOW));
		lines.add(new Line(62, raleigh, pittsburgh, 2, WagonCard.MULTICOLOR));
		lines.add(new Line(63, raleigh, washington, 2, WagonCard.MULTICOLOR));
		lines.add(new Line(64, washington, pittsburgh, 2, WagonCard.MULTICOLOR));
		lines.add(new Line(65, washington, newYork, 2, WagonCard.BLACK));
		lines.add(new Line(66, pittsburgh, newYork, 2, WagonCard.GREEN));
		lines.add(new Line(67, chicago, pittsburgh, 3, WagonCard.ORANGE));
		lines.add(new Line(68, chicago, toronto, 4, WagonCard.WHITE));
		lines.add(new Line(69, pittsburgh, toronto, 2, WagonCard.MULTICOLOR));
		lines.add(new Line(70, saultStMarie, montreal, 5, WagonCard.BLACK));
		lines.add(new Line(71, saultStMarie, toronto, 2, WagonCard.MULTICOLOR));
		lines.add(new Line(72, toronto, montreal, 3, WagonCard.MULTICOLOR));
		lines.add(new Line(73, newYork, montreal, 3, WagonCard.BLUE));
		lines.add(new Line(74, newYork, boston, 2, WagonCard.RED));
		lines.add(new Line(75, boston, montreal, 2, WagonCard.MULTICOLOR));

		
	}
	
	
}
