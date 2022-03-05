package com.comp301.a09nonograms.model;

public class Puzzle {
  private final Clues clues;
  private final Board board;

  public Puzzle(Clues clues, Board board) {
    this.clues = clues;
    this.board = board;
  }

  public Board getBoard() {
    return board;
  }

  public Clues getClues() {
    return clues;
  }
}
