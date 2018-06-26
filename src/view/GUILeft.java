package view;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class GUILeft {

    private BorderPane mainLayout;
    private VBox leftLayout;
    private GUIMain main;
    private ActorPlayer actorPlayer;
    private ArrayList<GUIPlayer> guiPlayers;

    GUILeft(GUIMain main, BorderPane mainLayout, ActorPlayer actorPlayer) {
        this.main = main;
        this.mainLayout = mainLayout;
        this.actorPlayer = actorPlayer;
        this.leftLayout = buildLeft();
        this.guiPlayers = actorPlayer.getDummyGUIPlayers();
    }

    public VBox getLeft() {
        return this.leftLayout;
    }

    private VBox buildLeft() {
        VBox leftLayout = new VBox();
        
        this.guiPlayers = actorPlayer.getDummyGUIPlayers();
        try {
        	if (actorPlayer.getGUIPlayers() != null) {
        		this.guiPlayers = actorPlayer.getGUIPlayers();
        	}
        } catch (Exception e ) { }

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
        card.setUserData("");
        Button button = new Button("Comprar " + place);
        button.setOnAction(e -> drawCardHandler(card));

        cardOption.getChildren().addAll(card, button);
        card.setPadding(labelPadding);
        cardOption.setAlignment(Pos.CENTER_RIGHT);
        cardOption.setPadding(boxPadding);

        return cardOption;
    }

    private void drawCardHandler(Label card) {
        // passar a carta para o jogador, atualizar carta nova carta no board
        switch (card.getText()) {
            case ("0"):
            	this.guiPlayers.get(0).incBlack();
                break;
            case ("1"):
            	this.guiPlayers.get(0).incBlue();
                break;
            case ("2"):
            	this.guiPlayers.get(0).incGreen();
                break;
            case ("3"):
            	this.guiPlayers.get(0).incOrange();
                break;
            case ("4"):
            	this.guiPlayers.get(0).incPurple();
                break;
            case ("5"):
            	this.guiPlayers.get(0).incMulticolor();
                break;
            case ("6"):
            	this.guiPlayers.get(0).incRed();
                break;
            case ("7"):
            	this.guiPlayers.get(0).incWhite();
                break;
            case ("8"):
            	this.guiPlayers.get(0).incYellow();
                break;
            default:
                // pega do monte, carta randomica
                break;
        }

        // update board
        main.buildLeft(mainLayout);
        main.buildBottom(mainLayout);
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
    	if (actorPlayer.getChooseObjectives()) {
    		actorPlayer.chooseObjectives();
    	} else {
    		actorPlayer.drawObjetives();
    	}
    	main.buildLeft(mainLayout);    		
    }

    private VBox buildObjectives() {
        VBox objectivesLayout = new VBox();

        Insets objectivePadding = new Insets(0, 0 ,10 ,0);

        Label header = new Label("Objetivos");

        objectivesLayout.getChildren().addAll(header, new Separator());
        
        try {
        	if (actorPlayer.getPlayerObjectives() != null) {
        		for (String m : actorPlayer.getPlayerObjectives()) {
                    Label objective = new Label(m);
                    objectivesLayout.getChildren().add(objective);
                }
        	}
        } catch (Exception e) {};
        

        objectivesLayout.setAlignment(Pos.CENTER_RIGHT);
        objectivesLayout.setSpacing(10);
        objectivesLayout.setPadding(objectivePadding);

        return objectivesLayout;
    }
}
