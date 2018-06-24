package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GUILeft {

    private GUIData data;
    private VBox leftLayout;

    GUILeft(GUIData data) {
        this.data = data;
        this.leftLayout = buildLeft();
    }

    public VBox getLeft() {
        return this.leftLayout;
    }

    private VBox buildLeft() {
        VBox leftLayout = new VBox();

        VBox cardOption = buildCardOptions();
        cardOption.setAlignment(Pos.TOP_CENTER);

        VBox objectiveOption = buildObjectiveOption();
        objectiveOption.setAlignment(Pos.TOP_CENTER);

        VBox objectives = buildObjectives();
        objectives.setAlignment(Pos.TOP_CENTER);

        leftLayout.setAlignment(Pos.CENTER);
        leftLayout.getChildren().addAll(cardOption, objectiveOption, objectives);

        return leftLayout;
    }

    private VBox buildCardOptions() {
        VBox cardOptions = new VBox();

        Label header = new Label("Carta para Comprar");

        cardOptions.getChildren().addAll(header,
                makeOption("1","Carta"),
                makeOption("2", "Carta"),
                makeOption("3", "Carta"),
                makeOption("4", "Carta"),
                makeOption("5", "Carta"),
                makeOption(" ", "Monte"),
                new Separator());

        return cardOptions;
    }

    private HBox makeOption(String cardValue, String place) {
        HBox cardOption = new HBox();

        Insets labelPadding = new Insets(10);
        Insets boxPadding = new Insets(1, 10, 1,2);

        Label card = new Label(cardValue);
        Button button = new Button("Comprar " + place);
        button.setOnAction(e -> drawCardHandler(card.getText()));

        cardOption.getChildren().addAll(card, button);
        card.setPadding(labelPadding);
        cardOption.setAlignment(Pos.CENTER_RIGHT);
        cardOption.setPadding(boxPadding);

        return cardOption;
    }

    private void drawCardHandler(String card) {
        //aumentar contador de cartas compras?
    }

    private VBox buildObjectiveOption() {
        VBox objectiveOption = new VBox();

        Insets boxPadding = new Insets(10, 0 ,10 ,0);

        Button button = new Button("Comprar Objetivo");
        button.setOnAction(e -> drawObjectiveHandler());

        objectiveOption.getChildren().addAll(button, new Separator());
        objectiveOption.setAlignment(Pos.CENTER_RIGHT);
        objectiveOption.setSpacing(10);
        objectiveOption.setPadding(boxPadding);

        return objectiveOption;
    }

    private void drawObjectiveHandler() {

    }

    private VBox buildObjectives() {
        VBox objectivesLayout = new VBox();

        Insets objectivePadding = new Insets(0, 0 ,10 ,0);

        Label header = new Label("Objetivos");

        objectivesLayout.getChildren().addAll(header, new Separator());

        data.initObjectives();
        for (String m : data.getObjectives()) {
            Label objective = new Label(m);
            objectivesLayout.getChildren().add(objective);
        }

        objectivesLayout.setAlignment(Pos.CENTER_RIGHT);
        objectivesLayout.setSpacing(10);
        objectivesLayout.setPadding(objectivePadding);

        return objectivesLayout;
    }
}
