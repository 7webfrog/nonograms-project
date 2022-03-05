package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControlView implements FXComponent {
  private final Controller control;

  public ControlView(Controller control) {
    this.control = control;
  }

  @Override
  public Parent render() {
    HBox paneInTheGlass = new HBox();
    Button prev = new Button("Previous Puzzle");
    prev.setOnMouseClicked(event -> control.prevPuzzle());
    VBox vbox = new VBox();
    Button rand = new Button("Random Puzzle");
    rand.setOnMouseClicked(event -> control.randPuzzle());
    vbox.getChildren().add(rand);
    Label message = new Label();
    if (control.isSolved()) {
      message.setText("Solved!");
    }
    message.setStyle("-fx-text-fill: black");
    vbox.getChildren().add(message);
    vbox.setAlignment(Pos.CENTER);
    Button next = new Button("Next Puzzle");
    next.setOnMouseClicked(event -> control.nextPuzzle());
    paneInTheGlass.getChildren().add(prev);
    paneInTheGlass.getChildren().add(vbox);
    paneInTheGlass.getChildren().add(next);
    paneInTheGlass.setAlignment(Pos.TOP_CENTER);
    return paneInTheGlass;
  }
}
