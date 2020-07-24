package com.github.cc3002.citricliquid.gui;
import com.github.cc3002.citricjuice.model.GameController;
import com.github.cc3002.citricjuice.model.Player;
import com.github.cc3002.citricjuice.model.board.HomePanel;
import com.github.cc3002.citricjuice.model.board.NeutralPanel;
import com.github.cc3002.citricliquid.gui.nodes.MovableNodeBuilder;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class QueenOfFailure extends Application {
    private static final String RESOURCE_PATH = "src/main/resources/";
    private final GameController controller = new GameController();
    private final Group root = new Group();
    private Button start;
    private Label pasos;
    private Label phaseLabel;
    private Label ownerLabel;
    private Label opponentLabel;
    private Label winnerLabel;
    private Label actualUnitLabel;
    private Label opHP;
    private Label myHP;
    private Label chapter;

    private String PLAYER_NAME = "Suguri";
    private String PLAYER_NAME_2 = "PANDA";
    private String PLAYER_NAME_3 = "POLAR";
    private String PLAYER_NAME_4 = "PARDO";

    private Player suguri;
    private Player panda;
    private Player pardo;
    private Player polar;

    private HomePanel HP1;
    private HomePanel HP2;
    private HomePanel HP3;
    private HomePanel HP4;
    private NeutralPanel NP1;
    private NeutralPanel NP2;
    private NeutralPanel NP3;
    private NeutralPanel NP4;
    private NeutralPanel NP5;
    private NeutralPanel NP6;








    @Override
    public void start(final @NotNull Stage primaryStage) throws FileNotFoundException {
        HP1 = controller.addHomePanel(11);
        HP2 = controller.addHomePanel(12);
        HP3 = controller.addHomePanel(13);
        HP4 = controller.addHomePanel(14);
        NP1 =controller.addNeutralPanel(21);
        NP2 =controller.addNeutralPanel(22);
        NP3 =controller.addNeutralPanel(23);
        NP4 =controller.addNeutralPanel(24);
        NP5 =controller.addNeutralPanel(25);
        NP6 =controller.addNeutralPanel(26);

        suguri = controller.addPlayer(PLAYER_NAME, 4, 1, -1, 2,HP1);
        polar = controller.addPlayer(PLAYER_NAME_3, 5, 3, -1, 1,HP2);
        panda = controller.addPlayer(PLAYER_NAME_2, 6, 5, -2, 4,HP3);
        pardo = controller.addPlayer(PLAYER_NAME_4, 5, 6, 7, 8,HP4);
        controller.setNextPanel(HP1,NP1);
        controller.setNextPanel(NP1,NP2);
        controller.setNextPanel(NP2,NP3);
        controller.setNextPanel(NP3,NP4);
        controller.setNextPanel(NP4,NP5);
        controller.setNextPanel(NP5,NP6);

        controller.setPlayerPanel(pardo,NP1);

        primaryStage.setTitle("Queen of Failure");
        primaryStage.setResizable(false);
        Scene scene= new Scene(root,640,480);
        var background =
                new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "escenarioSofi.PNG")));
        root.getChildren().add(background);

        phaseLabel = createLabel(5, 5);
        ownerLabel=createLabel(5,25);
        opponentLabel=createLabel(5,45);
        winnerLabel=createLabel(5,65);
        actualUnitLabel=createLabel(5,85);
        pasos= createLabel(5,105);
        chapter=createLabel(5,125);



        start= new Button("Start");
        start.setLayoutX(500);
        start.setLayoutY(5);
        start.setOnAction(event -> controller.tryToStart());
        root.getChildren().add(start);



        Button stay_at_home= new Button("Stay At Home");
        stay_at_home.setLayoutX(500);
        stay_at_home.setLayoutY(50);
        stay_at_home.setOnAction(event -> controller.tryToStayAtHome());
        root.getChildren().add(stay_at_home);

        Button keep_Moving= new Button("keep Moving");
        keep_Moving.setLayoutX(500);
        keep_Moving.setLayoutY(95);
        keep_Moving.setOnAction(event -> controller.tryToKeepMoving());
        root.getChildren().add(keep_Moving);

        Button fight= new Button("Pelea");
        fight.setLayoutX(500);
        fight.setLayoutY(145);
        fight.setOnAction(event -> controller.tryToFight());
        root.getChildren().add(fight);

        Button evade= new Button("Evade");
        evade.setLayoutX(500);
        evade.setLayoutY(195);
        evade.setOnAction(event -> controller.tryToevade());
        root.getChildren().add(evade);

        Button defend= new Button("Defend");
        defend.setLayoutX(500);
        defend.setLayoutY(235);
        defend.setOnAction(event -> controller.tryToDefend());
        root.getChildren().add(defend);

        Button end= new Button("Finish my Turn");
        end.setLayoutX(500);
        end.setLayoutY(280);
        end.setOnAction(event -> controller.tryToEndTurn());
        root.getChildren().add(end);

        Button Up= new Button("Up");
        Up.setLayoutX(545);
        Up.setLayoutY(310);
        //Up.setOnAction(event -> controller.tryToEndTurn());
        root.getChildren().add(Up);

        Button Down= new Button("Down");
        Down.setLayoutX(540);
        Down.setLayoutY(340);
        //Down.setOnAction(event -> controller.tryToEndTurn());
        root.getChildren().add(Down);

        Button Left= new Button("Left");
        Left.setLayoutX(490);
        Left.setLayoutY(320);
        //Left.setOnAction(event -> controller.tryToEndTurn());
        root.getChildren().add(Left);

        Button Right= new Button("Right");
        Right.setLayoutX(590);
        Right.setLayoutY(320);
        //Right.setOnAction(event -> controller.tryToEndTurn());
        root.getChildren().add(Right);





        startAnimator();
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void startAnimator() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                int steps=controller.getSteps();
                pasos.setText("Steps: "+steps);
                phaseLabel.setText("Current Phase: "+controller.getCurrentPhase());
                ownerLabel.setText("Turn : "+controller.getUnitName(controller.getOwner())+",  HP: " + controller.getUnitHP(controller.getOwner()));
                opponentLabel.setText("Opponent: "+controller.getUnitName(controller.getOponnent()) +",  HP: "+controller.getUnitHP(controller.getOponnent()));
                winnerLabel.setText("Winner :"+controller.getUnitName(controller.getWinner()));
                actualUnitLabel.setText("Playing: "+controller.getUnitName(controller.getActualUnit()));
                chapter.setText("Chapter: "+controller.getChapter());


            }
        };
        timer.start();
    }

    private @NotNull Label createLabel(int xPos, int yPos) {
        Label label = new Label();
        label.setLayoutX(xPos);
        label.setLayoutY(yPos);
        root.getChildren().add(label);
        return label;
    }
}
