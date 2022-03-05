package com.comp301.a09nonograms.model;

public class CluesImpl implements Clues {
  private final int height;
  private final int width;
  private final int[][] row;
  private final int[][] col;

  public CluesImpl(int[][] rowClues, int[][] colClues) {
    row = rowClues.clone();
    col = colClues.clone();
    height = row.length;
    width = col.length;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int[] getRowClues(int index) {
    if (index < 0 || index >= height) {
      throw new IndexOutOfBoundsException();
    }
    return row[index];
  }

  @Override
  public int[] getColClues(int index) {
    if (index < 0 || index >= width) {
      throw new IndexOutOfBoundsException();
    }
    return col[index];
  }

  @Override
  public int getRowCluesLength() {
    return row[0].length;
  }

  @Override
  public int getColCluesLength() {
    return col[0].length;
  }
}
