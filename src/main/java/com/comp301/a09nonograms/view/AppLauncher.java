package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.PuzzleLibrary;
import com.comp301.a09nonograms.controller.Controller;
import com.comp301.a09nonograms.controller.ControllerImpl;
import com.comp301.a09nonograms.model.Model;
import com.comp301.a09nonograms.model.ModelImpl;
import com.comp301.a09nonograms.model.ModelObserver;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // Model and Controller init
    Model model = new ModelImpl(PuzzleLibrary.create());
    Controller control = new ControllerImpl(model);
    // Views init
    PuzzleNumberView puzzleNumberView = new PuzzleNumberView(control);
    PuzzleView puzzleView = new PuzzleView(control);
    ControlView controlView = new ControlView(control);
    // Parent init
    HBox superParent = new HBox();
    VBox parent = new VBox();
    parent.getChildren().add(puzzleNumberView.render());
    parent.getChildren().add(puzzleView.render());
    parent.getChildren().add(controlView.render());
    parent.setAlignment(Pos.CENTER);
    superParent.setAlignment(Pos.CENTER);
    superParent.getChildren().add(parent);
    // Observer init
    ModelObserver observer =
        whichModel -> {
          parent.getChildren().clear();
          parent.getChildren().add(puzzleNumberView.render());
          parent.getChildren().add(puzzleView.render());
          parent.getChildren().add(controlView.render());
        };
    model.addObserver(observer);
    // Final setup
    Scene scene = new Scene(superParent, 500, 500);
    scene.getStylesheets().add("style/styles.css");
    stage.setScene(scene);
    stage.show();
  }
}
