package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PuzzleView implements FXComponent {
  private final Controller control;

  public PuzzleView(Controller control) {
    this.control = control;
  }

  @Override
  public Parent render() {
    // pane.add(element, column, row)
    GridPane pane = new GridPane();
    pane.getStyleClass().add("puzzleView");
    int rows = control.getClues().getHeight();
    int cols = control.getClues().getWidth();
    for (int i = 1; i <= rows; i++) {
      HBox hbox = new HBox();
      hbox.setAlignment(Pos.CENTER_RIGHT);
      int[] temp = control.getClues().getRowClues(i - 1);
      for (int j : temp) {
        if (j == 0) {
          continue;
        }
        Label tempLabel = new Label(String.valueOf(j));
        tempLabel.getStyleClass().add("rowLabel");
        hbox.getChildren().add(tempLabel);
      }
      pane.add(hbox, 0, i);
    }
    for (int i = 1; i <= cols; i++) {
      VBox vbox = new VBox();
      vbox.setAlignment(Pos.BOTTOM_CENTER);
      int[] temp = control.getClues().getColClues(i - 1);
      for (int j : temp) {
        if (j == 0) {
          continue;
        }
        Label tempLabel = new Label(String.valueOf(j));
        tempLabel.getStyleClass().add("columnLabel");
        vbox.getChildren().add(tempLabel);
      }
      pane.add(vbox, i, 0);
    }
    for (int i = 1; i <= rows; i++) {
      for (int j = 1; j <= cols; j++) {
        int finalI = i;
        int finalJ = j;
        Button btn = new Button();
        btn.setMinWidth(35);
        if (control.isShaded(i - 1, j - 1)) {
          ImageView shaded = new ImageView(new Image("shaded.png"));
          btn.setGraphic(shaded);
        } else if (control.isEliminated(i - 1, j - 1)) {
          ImageView elim = new ImageView(new Image("elim.png"));
          btn.setGraphic(elim);
        }
        btn.setOnMouseClicked(
            event -> {
              if (event.getButton() == MouseButton.PRIMARY) {
                control.toggleShaded(finalI - 1, finalJ - 1);
              } else if (event.getButton() == MouseButton.SECONDARY) {
                control.toggleEliminated(finalI - 1, finalJ - 1);
              }
            });
        pane.add(btn, j, i);
      }
    }
    return pane;
  }
}
