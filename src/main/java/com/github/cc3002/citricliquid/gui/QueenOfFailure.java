package com.github.cc3002.citricliquid.gui;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class QueenOfFailure extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Queen of Failure");
        primaryStage.setResizable(false);
        Group root = new Group();
        Scene scene= new Scene(root,640,480);

        primaryStage.setScene(scene);

        primaryStage.show();

    }
}
