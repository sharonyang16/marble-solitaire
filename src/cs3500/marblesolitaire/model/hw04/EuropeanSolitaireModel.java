package cs3500.marblesolitaire.model.hw04;

/**
 * This class represents the model of a game of European Solitaire and all the operations
 * that are available for this game.
 */
public class EuropeanSolitaireModel extends AbstractMarbleSolitaireModel {
  /**
   * Constructs an EuropeanSolitaireModel object and initializes it with a board that has
   * a side length of 3 and the empty slot in the center of the board.
   */
  public EuropeanSolitaireModel() {
    super(3, 3, 3);
  }

  /**
   * Constructs an EuropeanSolitaireModel object and initializes it with a board that has
   * the given side length and the empty slot in the center of the board.
   *
   * @param sideLength the desired arm thickness
   * @throws IllegalArgumentException if the given side length is not odd and non-negative
   */
  public EuropeanSolitaireModel(int sideLength) {
    super(sideLength,
            (sideLength + 2 * (sideLength - 1)) / 2,
            (sideLength + 2 * (sideLength - 1)) / 2);
  }

  /**
   * Constructs an EuropeanSolitaireModel object and initializes it with a board that has
   * a side length of 3 and the empty slot in given row and column.
   *
   * @param row the row of the desired empty slot
   * @param col the column of the desired empty slot
   * @throws IllegalArgumentException if the given position is invalid
   */
  public EuropeanSolitaireModel(int row, int col) {
    super(3, row, col);
  }

  /**
   * Constructs an EuropeanSolitaireModel object and initializes it with a board that has
   * the given side length and the empty slot in the given row and column.
   *
   * @param sideLength the desired arm thickness
   * @param row the row of the desired empty slot
   * @param col the column of the desired empty slot
   * @throws IllegalArgumentException if the given position or side length is invalid
   */
  public EuropeanSolitaireModel(int sideLength, int row, int col) {
    super(sideLength, row, col);
  }

  @Override
  protected boolean invalidSpace(int armThickness, int row, int col) {
    // if the given position is in the upper left corner of the board
    if (row <= armThickness - 2 && col <= armThickness - 2) {
      return col <= armThickness - 2 - row;
    }
    // if the given position is in the upper right corner of the board
    else if (row <= armThickness - 2 && col >= 2 * armThickness - 1) {
      return col >= 2 * armThickness - 1 + row;
    }
    // if the given position is in the lower left corner of the board
    // *NOTE: (armThickness + 2 * (armThickness - 1)) is the board size*
    else if (row >= 2 * armThickness - 1 && col <= armThickness - 2) {
      return col <= (armThickness - 2) - ((armThickness + 2 * (armThickness - 1)) - 1 - row);
    }
    // if the given position is in the lower right corner of the board
    else if (row >= 2 * armThickness - 1 && col >= 2 * armThickness - 1) {
      return col >= (2 * armThickness - 1) + ((armThickness + 2 * (armThickness - 1)) - 1 - row);
    }
    // if it's not any of the four, it's always valid
    else {
      return false;
    }
  }
}
