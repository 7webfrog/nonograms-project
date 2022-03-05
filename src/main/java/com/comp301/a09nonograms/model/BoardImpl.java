package com.comp301.a09nonograms.model;

public class BoardImpl implements Board {
  private static final int SHADED = 1;
  private static final int ELIMINATED = 2;
  private static final int SPACE = 0;
  private final int[][] board;

  public BoardImpl(Clues clues) {
    board = new int[clues.getHeight()][clues.getWidth()];
  }

  @Override
  public boolean isShaded(int row, int col) {
    if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
      throw new IllegalArgumentException();
    }
    return board[row][col] == SHADED;
  }

  @Override
  public boolean isEliminated(int row, int col) {
    if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
      throw new IllegalArgumentException();
    }
    return board[row][col] == ELIMINATED;
  }

  @Override
  public boolean isSpace(int row, int col) {
    if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
      throw new IllegalArgumentException();
    }
    return board[row][col] == SPACE;
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
      throw new IllegalArgumentException();
    }
    if (board[row][col] == SHADED) {
      board[row][col] = SPACE;
    } else {
      board[row][col] = SHADED;
    }
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
      throw new IllegalArgumentException();
    }
    if (board[row][col] == ELIMINATED) {
      board[row][col] = SPACE;
    } else {
      board[row][col] = ELIMINATED;
    }
  }

  @Override
  public void clear() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        board[i][j] = SPACE;
      }
    }
  }
}
