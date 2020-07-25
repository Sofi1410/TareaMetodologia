package com.github.cc3002.citricliquid.gui;
import com.github.cc3002.citricjuice.model.GameController;
import com.github.cc3002.citricjuice.model.Player;
import com.github.cc3002.citricjuice.model.board.HomePanel;
import com.github.cc3002.citricjuice.model.board.NeutralPanel;
import com.github.cc3002.citricliquid.gui.Phases.InvalidMovementException;
import com.github.cc3002.citricliquid.gui.nodes.MovableNode;
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
    private Label NormaGoal;
    private Label stars;
    private Label chapter;
    private Label victories;



    @Override
    public void start(final @NotNull Stage primaryStage) throws FileNotFoundException {
        controller.escenario();
        primaryStage.setTitle("Queen of Failure");
        primaryStage.setResizable(false);
        Scene scene= new Scene(root,1280,700);
        var background =
                new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "escenarioSofi.PNG")));
        root.getChildren().add(background);

        var pardo = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "pardo.png")
                .setPosition(590, 190)
                .setSize(50, 50)
                .build();
        root.getChildren().add(pardo.getNode());

        var polar = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "polar.png")
                .setPosition(319, 450)
                .setSize(50, 50)
                .build();
        root.getChildren().add(polar.getNode());

        var panda = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "panpan.png")
                .setPosition(452, 123)
                .setSize(50, 50)
                .build();
        root.getChildren().add(panda.getNode());

        var nomnom = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "nomnom.png")
                .setPosition(107, 452)
                .setSize(50, 50)
                .build();
        root.getChildren().add(nomnom.getNode());


        int xLabel=60;
        int ylabel=90;
        phaseLabel = createLabel(620,680);
        ownerLabel=createLabel(929,50);
        opponentLabel=createLabel( 929,ylabel);
        winnerLabel=createLabel(13,41);
        actualUnitLabel=createLabel(xLabel,ylabel);
        pasos= createLabel(xLabel,ylabel+20);
        chapter=createLabel(xLabel,ylabel+40);
        victories=createLabel(xLabel,ylabel+60);
        stars=createLabel(xLabel,ylabel+80);
        NormaGoal=createLabel(xLabel,ylabel+100);



        int xB=1000;
        int yB=120;
        start= new Button("Start");
        start.setLayoutX(xB);
        start.setLayoutY(yB);
        start.setOnAction(event -> tryTostart());
        root.getChildren().add(start);



        Button stay_at_home= new Button("Stay At Home");
        stay_at_home.setLayoutX(xB-50);
        stay_at_home.setLayoutY(yB+30);
        stay_at_home.setOnAction(event -> controller.tryToStayAtHome());
        root.getChildren().add(stay_at_home);

        Button keep_Moving= new Button("keep Moving");
        keep_Moving.setLayoutX(xB+50);
        keep_Moving.setLayoutY(yB+30);
        keep_Moving.setOnAction(event -> controller.tryToKeepMoving());
        root.getChildren().add(keep_Moving);

        Button fight= new Button("Fight");
        fight.setLayoutX(xB+150);
        fight.setLayoutY(yB+30);
        fight.setOnAction(event -> controller.tryToFight());
        root.getChildren().add(fight);

        Button evade= new Button("Evade");
        evade.setLayoutX(xB+50);
        evade.setLayoutY(yB+60);
        evade.setOnAction(event -> controller.tryToevade());
        root.getChildren().add(evade);

        Button defend= new Button("Defend");
        defend.setLayoutX(xB-50);
        defend.setLayoutY(yB+60);
        defend.setOnAction(event -> controller.tryToDefend());
        root.getChildren().add(defend);

        Button end= new Button("Finish my Turn");
        end.setLayoutX(xB);
        end.setLayoutY(yB+90);
        end.setOnAction(event -> controller.tryToEndTurn());
        root.getChildren().add(end);

        //ParticularButton
        int xU=545;
        int yU=310;
        int xD=540;
        int yD=340;
        int xL=490;
        int yL=320;
        int xR=590;
        int yR=320;
        //NomNomButtons
        int N=404;
        Button Up= new Button("Up");
        Up.setLayoutX(xU+N);
        Up.setLayoutY(yU);
        Up.setOnAction(event -> upNomNom(nomnom,65));
        root.getChildren().add(Up);

        Button Down= new Button("Down");
        Down.setLayoutX(xD+N);
        Down.setLayoutY(yD);
        Down.setOnAction(event -> downNomNom(nomnom,65));
        root.getChildren().add(Down);

        Button Left= new Button("Left");
        Left.setLayoutX(xL+N);
        Left.setLayoutY(yL);
        Left.setOnAction(event -> leftNomNom(nomnom,70));
        root.getChildren().add(Left);

        Button Right= new Button("Right");
        Right.setLayoutX(xR+N);
        Right.setLayoutY(yR);
        Right.setOnAction(event -> rightNomNom(nomnom,70));
        root.getChildren().add(Right);





        startAnimator();
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void rightNomNom(MovableNode u,int a) {
        controller.tryToGoRight();
        u.moveRight(a);
    }
    private void leftNomNom(MovableNode u,int a){
        controller.tryToGoLeft();
        u.moveLeft(a);
    }

    private void upNomNom(MovableNode u,int a) {
        controller.tryToGoRUp();
        u.moveUp(a);
    }
    private void downNomNom(MovableNode u,int a) {
        controller.tryToGoDown();
        u.moveDown(a);
    }

    private void tryTostart()  {
        controller.tryToStart();
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
                victories.setText("Victories: "+controller.getOwnerVictories());
                stars.setText("Stars: "+controller.getOwnerStars());
                chapter.setText("Chapter: "+controller.getChapter());
                NormaGoal.setText("Norma Goal: "+controller.getOwnerNormaGoal());


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
