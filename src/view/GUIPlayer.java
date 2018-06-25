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
    private int pink;
    private int rainbow;
    private int red;
    private int white;
    private int yellow;

    public GUIPlayer(String name, String color, int points, int qttWagons) {
        this.name = name;
        this.color = color;
        this.points = points;
        this.qttWagons = qttWagons;
        this.qttCards = 0;
        this.black = 0;
        this.blue = 0;
        this.green = 0;
        this.orange = 0;
        this.pink = 0;
        this.rainbow = 0;
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

    public void incBlack() {
        this.black++;
        this.qttCards++;
    }

    public int getBlue() {
        return blue;
    }

    public void incBlue() {
        this.blue++;
        this.qttCards++;
    }

    public int getGreen() {
        return green;
    }

    public void incGreen() {
        this.green++;
        this.qttCards++;
    }

    public int getOrange() {
        return orange;
    }

    public void incOrange() {
        this.orange++;
        this.qttCards++;
    }

    public int getPink() {
        return pink;
    }

    public void incPink() {
        this.pink++;
        this.qttCards++;
    }

    public int getRainbow() {
        return rainbow;
    }

    public void incRainbow() {
        this.rainbow++;
        this.qttCards++;
    }

    public int getRed() {
        return red;
    }

    public void incRed() {
        this.red++;
        this.qttCards++;
    }

    public int getWhite() {
        return white;
    }

    public void incWhite() {
        this.white++;
        this.qttCards++;
    }

    public int getYellow() {
        return yellow;
    }

    public void incYellow() {
        this.yellow++;
        this.qttCards++;
    }
}
