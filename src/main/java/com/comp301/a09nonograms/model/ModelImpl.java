package com.comp301.a09nonograms.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  private final List<Puzzle> puzzles;
  private final List<ModelObserver> observers;
  private Puzzle currentPuzzle;

  public ModelImpl(List<Clues> clues) {
    if (clues == null) {
      throw new IllegalArgumentException();
    }
    observers = new ArrayList<>();
    puzzles = new ArrayList<>();
    for (Clues a : clues) {
      puzzles.add(new Puzzle(a, new BoardImpl(a)));
    }
    currentPuzzle = puzzles.get(0);
  }

  @Override
  public boolean isShaded(int row, int col) {
    return currentPuzzle.getBoard().isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return currentPuzzle.getBoard().isEliminated(row, col);
  }

  @Override
  public boolean isSpace(int row, int col) {
    return currentPuzzle.getBoard().isSpace(row, col);
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    currentPuzzle.getBoard().toggleCellShaded(row, col);
    notifyObservers();
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    currentPuzzle.getBoard().toggleCellEliminated(row, col);
    notifyObservers();
  }

  @Override
  public void clear() {
    currentPuzzle.getBoard().clear();
    notifyObservers();
  }

  @Override
  public int getWidth() {
    return currentPuzzle.getClues().getWidth();
  }

  @Override
  public int getHeight() {
    return currentPuzzle.getClues().getHeight();
  }

  @Override
  public int[] getRowClues(int index) {
    return currentPuzzle.getClues().getRowClues(index);
  }

  @Override
  public int[] getColClues(int index) {
    return currentPuzzle.getClues().getColClues(index);
  }

  @Override
  public int getRowCluesLength() {
    return currentPuzzle.getClues().getRowCluesLength();
  }

  @Override
  public int getColCluesLength() {
    return currentPuzzle.getClues().getColCluesLength();
  }

  @Override
  public int getPuzzleCount() {
    return puzzles.size();
  }

  @Override
  public int getPuzzleIndex() {
    return puzzles.indexOf(currentPuzzle);
  }

  @Override
  public void setPuzzleIndex(int index) {
    if (index < -1 || index > puzzles.size()) {
      throw new IndexOutOfBoundsException();
    }
    if (index == puzzles.size()) {
      currentPuzzle = puzzles.get(0);
    } else if (index == -1) {
      currentPuzzle = puzzles.get(puzzles.size() - 1);
    } else {
      currentPuzzle = puzzles.get(index);
    }
    notifyObservers();
  }

  @Override
  public void addObserver(ModelObserver observer) {
    if (observer == null) {
      throw new IllegalArgumentException();
    }
    observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    if (observer == null) {
      throw new IllegalArgumentException();
    }
    observers.remove(observer);
  }

  @Override
  public boolean isSolved() {
    for (int i = 0; i < currentPuzzle.getClues().getWidth(); i++) { // checking cols first
      int[] column = currentPuzzle.getClues().getColClues(i);
      int expected = 0;
      for (int j : column) { // how many are supposed to be shaded?
        expected += j;
      }
      int actual = 0;
      for (int j = 0;
          j < currentPuzzle.getClues().getHeight();
          j++) { // how many are actually shaded?
        if (isShaded(j, i)) {
          actual++;
        }
      }
      if (expected != actual) {
        return false;
      }
    }
    for (int i = 0; i < currentPuzzle.getClues().getHeight(); i++) { // now checking rows
      int[] row = currentPuzzle.getClues().getRowClues(i);
      int expected = 0;
      for (int j : row) { // how many are supposed to be shaded?
        expected += j;
      }
      int actual = 0;
      for (int j = 0;
          j < currentPuzzle.getClues().getWidth();
          j++) { // how many are actually shaded?
        if (isShaded(i, j)) {
          actual++;
        }
      }
      if (expected != actual) {
        return false;
      }
    }
    return true;
  }

  private void notifyObservers() {
    for (ModelObserver o : observers) {
      o.update(this);
    }
  }
}
