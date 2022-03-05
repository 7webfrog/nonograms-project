package com.comp301.a09nonograms.controller;

import com.comp301.a09nonograms.PuzzleLibrary;
import com.comp301.a09nonograms.model.Clues;
import com.comp301.a09nonograms.model.Model;
import com.comp301.a09nonograms.model.ModelObserver;

import java.util.List;

public class ControllerImpl implements Controller {
  private static final List<Clues> clues = PuzzleLibrary.create();
  private final Model model;

  public ControllerImpl(Model model) {
    if (model == null) {
      throw new IllegalArgumentException();
    }
    this.model = model;
  }

  @Override
  public Clues getClues() {
    return clues.get(model.getPuzzleIndex());
  }

  @Override
  public boolean isSolved() {
    return model.isSolved();
  }

  @Override
  public boolean isShaded(int row, int col) {
    return model.isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return model.isEliminated(row, col);
  }

  @Override
  public void toggleShaded(int row, int col) {
    model.toggleCellShaded(row, col);
  }

  @Override
  public void toggleEliminated(int row, int col) {
    model.toggleCellEliminated(row, col);
  }

  @Override
  public void nextPuzzle() {
    model.setPuzzleIndex(model.getPuzzleIndex() + 1);
  }

  @Override
  public void prevPuzzle() {
    model.setPuzzleIndex(model.getPuzzleIndex() - 1);
  }

  @Override
  public void randPuzzle() {
    int newPuzzle = model.getPuzzleIndex();
    while (newPuzzle == model.getPuzzleIndex()) {
      newPuzzle = (int) (Math.random() * model.getPuzzleCount());
    }
    model.setPuzzleIndex(newPuzzle);
  }

  @Override
  public void clearBoard() {
    model.clear();
  }

  @Override
  public int getPuzzleIndex() {
    return model.getPuzzleIndex();
  }

  @Override
  public int getPuzzleCount() {
    return model.getPuzzleCount();
  }

  @Override
  public void addObserver(ModelObserver observer) {
    model.addObserver(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    model.removeObserver(observer);
  }
}
