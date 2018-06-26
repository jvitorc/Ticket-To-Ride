package view;

public class GUIPlayer {

    private String name;
    private String color;
    private int points;
    private int qttWagons;
    private int qttCards;
    private int black;
    private int blue;
    private int green;
    private int orange;
    private int purple;
    private int multicolor;
    private int red;
    private int white;
    private int yellow;
    
    GUIPlayer() {
    	this.name = "";
        this.color = "";
        this.points = 0;
        this.qttWagons = 0;
        this.qttCards = 0;
        this.black = 0;
        this.blue = 0;
        this.green = 0;
        this.orange = 0;
        this.purple = 0;
        this.multicolor = 0;
        this.red = 0;
        this.white = 0;
        this.yellow = 0;
    }

    GUIPlayer(String name, String color, int points, int qttWagons, int qttCards) {
        this.name = name;
        this.color = color;
        this.points = points;
        this.qttWagons = qttWagons;
        this.qttCards = qttCards;
        this.black = 0;
        this.blue = 0;
        this.green = 0;
        this.orange = 0;
        this.purple = 0;
        this.multicolor = 0;
        this.red = 0;
        this.white = 0;
        this.yellow = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getQttWagons() {
        return qttWagons;
    }

    public void setQttWagons(int qttWagons) {
        this.qttWagons = qttWagons;
    }

    public int getQttCards() {
        return qttCards;
    }

    public void setQttCards(int qttCards) {
        this.qttCards = qttCards;
    }

    public int getBlack() {
        return black;
    }
    
    public void setBlack(int black) {
    	this.black = black;
    }

    public void incBlack() {
        this.black++;
        this.qttCards++;
    }

    public int getBlue() {
        return blue;
    }
    
    public void setBlue(int blue) {
    	this.blue = blue;
    }

    public void incBlue() {
        this.blue++;
        this.qttCards++;
    }

    public int getGreen() {
        return green;
    }
    
    public void setGreen(int green) {
    	this.green = green;
    }

    public void incGreen() {
        this.green++;
        this.qttCards++;
    }

    public int getOrange() {
        return orange;
    }
    
    public void setOrange(int orange) {
    	this.orange = orange;
    }

    public void incOrange() {
        this.orange++;
        this.qttCards++;
    }

    public int getPurple() {
        return purple;
    }
    
    public void setPurple(int purple) {
    	this.purple = purple;
    }

    public void incPurple() {
        this.purple++;
        this.qttCards++;
    }

    public int getMulticolor() {
        return multicolor;
    }
    
    public void setMulticolor(int multicolor) {
    	this.multicolor = multicolor;
    }

    public void incMulticolor() {
        this.multicolor++;
        this.qttCards++;
    }

    public int getRed() {
        return red;
    }
    
    public void setRed(int red) {
    	this.red = red;
    }

    public void incRed() {
        this.red++;
        this.qttCards++;
    }

    public int getWhite() {
        return white;
    }
    
    public void setWhite(int white) {
    	this.white = white;
    }

    public void incWhite() {
        this.white++;
        this.qttCards++;
    }

    public int getYellow() {
        return yellow;
    }
    
    public void setYellow(int yellow) {
    	this.yellow = yellow;
    }

    public void incYellow() {
        this.yellow++;
        this.qttCards++;
    }
}
