package cs3500.marblesolitaire.controller;

import java.util.Objects;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This class represents a mock MarbleSolitaireModel that is used to confirm
 * that the MarbleSolitaireController receives the correct inputs when calling
 * the method move.
 */
public class ConfirmInputMarbleSolitaireModel implements MarbleSolitaireModel {
  private final StringBuilder log;

  public ConfirmInputMarbleSolitaireModel(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    this.log.append(String.format("fromRow = %d, fromCol = %d, toRow = %d, toCol = %d",
            fromRow, fromCol, toRow, toCol));
  }

  @Override
  public boolean isGameOver() {
    // doesn't receive any inputs, don't care
    return false;
  }

  @Override
  public int getBoardSize() {
    // doesn't receive any inputs, don't care
    return 0;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    // doesn't get called directly in the controller, don't care
    return null;
  }

  @Override
  public int getScore() {
    // doesn't receive any inputs, don't care
    return 0;
  }
}
