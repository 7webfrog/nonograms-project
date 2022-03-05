package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PuzzleNumberView implements FXComponent {
  private final Controller control;

  public PuzzleNumberView(Controller control) {
    this.control = control;
  }

  @Override
  public Parent render() {
    HBox hbox = new HBox();
    Label puzzleNumber = new Label();
    puzzleNumber.setText(
        "Puzzle #" + (control.getPuzzleIndex() + 1) + " of " + control.getPuzzleCount());
    hbox.getChildren().add(puzzleNumber);
    VBox vbox = new VBox();
    vbox.setStyle("-fx-padding: 0 0 0 20");
    Button clear = new Button("Clear");
    clear.setOnMouseClicked(event -> control.clearBoard());
    vbox.getChildren().add(clear);
    hbox.getChildren().add(vbox);
    hbox.getStyleClass().add("puzzleNumber");
    return hbox;
  }
}
