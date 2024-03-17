package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.AbstractMarbleSolitaireModel;

/**
 * This class represents the model of a game of English Solitaire and all the operations
 * that are available for this game.
 *
 * <p>Majority of code for this class was moved to the abstract class this class now extends
 * in order to create other versions of Marble Solitaire while avoiding code duplication.</p>
 */
public class EnglishSolitaireModel extends AbstractMarbleSolitaireModel {

  /**
   * Constructs an EnglishSolitaireModel object and initializes it with a board that has
   * an arm thickness of 3 and the empty slot in the center of the board.
   */
  public EnglishSolitaireModel() {
    super(3, 3, 3);
  }

  /**
   * Constructs an EnglishSolitaireModel object and initializes it with a board that has
   * an arm thickness of 3 and the empty slot in given row and column.
   *
   * @param sRow the row of the desired empty slot
   * @param sCol the column of the desired empty slot
   * @throws IllegalArgumentException if the given position is invalid
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(3, sRow, sCol);
  }

  /**
   * Constructs an EnglishSolitaireModel object and initializes it with a board that has
   * the given arm thickness and the empty slot in the center of the board.
   *
   * @param armThickness the desired arm thickness
   * @throws IllegalArgumentException if the given arm thickness is not odd and non-negative
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    super(armThickness,
            (armThickness + 2 * (armThickness - 1)) / 2,
            (armThickness + 2 * (armThickness - 1)) / 2);
  }

  /**
   * Constructs an EnglishSolitaireModel object and initializes it with a board that has
   * the given arm thickness and the empty slot in the given row and column.
   *
   * @param armThickness the desired arm thickness
   * @param sRow the row of the desired empty slot
   * @param sCol the column of the desired empty slot
   * @throws IllegalArgumentException if the given position or arm thickness is invalid
   */
  public EnglishSolitaireModel(int armThickness,
                               int sRow, int sCol) throws IllegalArgumentException {
    super(armThickness, sRow, sCol);
  }

  @Override
  protected boolean invalidSpace(int armThickness, int row, int col) {
    return (row <= armThickness - 2 && col <= armThickness - 2)
            || (row <= armThickness - 2 && col >= 2 * armThickness - 1)
            || (row >= 2 * armThickness - 1 && col <= armThickness - 2)
            || (row >= 2 * armThickness - 1 && col >= 2 * armThickness - 1);
  }
}

