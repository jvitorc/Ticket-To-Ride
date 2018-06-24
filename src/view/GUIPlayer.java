package view;

public class GUIPlayer {

    private String name;
    private String color;
    private int points;
    private int qttWagons;
    private int qttCards;

    public GUIPlayer() {
        this.name = "";
        this.color = "";
        this.points = 0;
        this.qttWagons = 0;
        this.qttCards = 0;
    }

    public GUIPlayer(String name, String color, int points, int qttWagons, int qttCards) {
        this.name = name;
        this.color = color;
        this.points = points;
        this.qttWagons = qttWagons;
        this.qttCards = qttCards;
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
}
