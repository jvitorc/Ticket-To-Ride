package view;

import java.util.ArrayList;
import java.util.List;

public class GUIData {

    private String name;
    private String server;
    private List<String> boardCards = new ArrayList<>();
    private List<GUIPlayer> players = new ArrayList<>();
    private List<String> routes = new ArrayList<>();
    private List<Integer> routesSize = new ArrayList<>();
    private List<String> objectives = new ArrayList<>();
    private boolean disconnect;
    private int acao;

    public boolean isDisconnect() {
        return disconnect;
    }

    public void setDisconnect(boolean disconnect) {
        this.disconnect = disconnect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public List<String> getBoardCards() {
        return boardCards;
    }

    public void setBoardCards(List<String> boardCards) {
        this.boardCards = boardCards;
    }

    public List<GUIPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<GUIPlayer> players) {
        this.players = players;
    }

    public int getAcao() {
        return acao;
    }

    public void setAcao(int acao) {
        this.acao = acao;
    }

    private void initList() {
        // pegar cartas do deck
        this.boardCards.add(0, "1");
        this.boardCards.add(1, "2");
        this.boardCards.add(2, "2");
        this.boardCards.add(3, "4");
        this.boardCards.add(4, "5");
    }

    public void initPlayers() {
        this.players.add(0, new GUIPlayer("abobora", "blue", 10, 10));
        this.players.add(1, new GUIPlayer("abacaxi", "red", 10, 10));
    }

    public List<String> getRoutes() {
        return routes;
    }

    public void initRoutes() {
        this.routes.add("Califa - São José");
        this.routes.add("Nova York - Curitiba");
        this.routes.add("Itu - Campinas");
        this.routes.add("Indaiatuba - Valinhos");
        this.routes.add("Nova Orleans - São Francisco");
        this.routes.add("Otawa - Campinas");
        this.routes.add("Rapaz - São Paulo");
        this.routes.add("Russia - Suiça");
        this.routes.add("Edinburgh - Glasgow");
        this.routes.add("Paris - Lion");
        this.routes.add("Dublin - Belfast");
        this.routes.add("Inverness - Sky");
        this.routes.add("Teste - Teste2");
        this.routes.add("Cansei - Também");
    }

    public List<Integer> getRoutesSize() {
        return routesSize;
    }

    public void initRoutesSizes() {
        this.routesSize.add(2);
        this.routesSize.add(4);
        this.routesSize.add(6);
        this.routesSize.add(5);
        this.routesSize.add(2);
        this.routesSize.add(7);
        this.routesSize.add(8);
        this.routesSize.add(4);
        this.routesSize.add(5);
        this.routesSize.add(2);
        this.routesSize.add(4);
        this.routesSize.add(3);
        this.routesSize.add(2);
        this.routesSize.add(5);
    }

    public List<String> getObjectives() {
        return objectives;
    }

    public void addObjective(String objective) {
        objectives.add(objective);
    }
}
