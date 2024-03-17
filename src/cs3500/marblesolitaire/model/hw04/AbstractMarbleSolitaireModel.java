package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This abstract class represents an abstract version of the model of a game of Marble Solitaire
 * and the available operations for this game.
 */
public abstract class AbstractMarbleSolitaireModel implements MarbleSolitaireModel {
  protected SlotState[][] board;

  /**
   * Constructs an AbstractMarbleSolitaireModel object and initializes it with a board that has
   * the given arm thickness and the empty slot in the given row and column.
   *
   * @param armThickness the desired arm thickness
   * @param sRow the row of the desired empty slot
   * @param sCol the column of the desired empty slot
   * @throws IllegalArgumentException if the given position or arm thickness is invalid
   */
  public AbstractMarbleSolitaireModel(int armThickness,
                               int sRow, int sCol) throws IllegalArgumentException {
    if (armThickness % 2 == 0 || armThickness <= 0) {
      throw new IllegalArgumentException(
              "Invalid arm thickness; arm thickness must be a positive odd number.");
    }
    if (this.invalidSpace(armThickness, sRow, sCol)) {
      throw new IllegalArgumentException(
              "Invalid slot; empty slot cannot be placed there.");
    }

    this.board = createBoard(armThickness, sRow, sCol);
  }

  /**
   * This default constructor is used for classes that implement this class but do not have the
   * same restrictions as the public constructor with three parameters.
   */
  protected AbstractMarbleSolitaireModel() {
    // does nothing
  }

  /**
   * Returns a 2D array of SlotState (a board) constructed with the given arm
   * thickness and the empty slot in the given row and column.
   *
   * @param armThickness the desired arm thickness
   * @param sRow the row of the desired empty slot
   * @param sCol the column of the desired empty slot
   * @return the board created with the given parameters
   */
  protected SlotState[][] createBoard(int armThickness, int sRow, int sCol) {
    int boardSize = armThickness + 2 * (armThickness - 1);
    SlotState[][] board = new SlotState[boardSize][boardSize];

    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        if (i == sRow && j == sCol) {
          board[i][j] = SlotState.Empty;
        } else if (this.invalidSpace(armThickness, i, j)) {
          board[i][j] = SlotState.Invalid;
        } else {
          board[i][j] = SlotState.Marble;
        }
      }
    }
    return board;
  }

  /**
   * Determines if the given position is invalid for a board with the given arm thickness.
   *
   * @param armThickness the arm thickness
   * @param row the row of the position
   * @param col the column of the position
   * @return if the position is invalid for a board with the given arm thickness
   */
  protected abstract boolean invalidSpace(int armThickness, int row, int col);

  /**
   * Moves a marble from a position containing a marble to an empty position, removing the
   * marble in between. The move is invalid (and will cause this method to throw an exception)
   * if the from position doesn't have a marble, the to position isn't empty, the spacing between
   * the from and to position is invalid, or there isn't a marble in the position between the from
   * and to positions.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the move is invalid
   */
  @Override
  public void move(int fromRow, int fromCol,
                   int toRow, int toCol) throws IllegalArgumentException {
    // if the slot at the from position is a marble, continue
    if (this.getSlotAt(fromRow, fromCol) == SlotState.Marble) {
      // if the slot at the to position is a empty, continue
      if (this.getSlotAt(toRow, toCol) == SlotState.Empty) {
        // if the from and slot positions are 2 positions apart in only one direction, continue
        if (validSpacing(fromRow, fromCol, toRow, toCol)) {
          int slotInBetweenRow;
          int slotInBetweenCol;

          if ((Math.abs(fromRow - toRow) == 2 && Math.abs(fromCol - toCol) == 2)) {
            slotInBetweenRow = (fromRow + toRow) / 2;
            slotInBetweenCol = (fromCol + toCol) / 2;
          }
          // determines the position of the marble in between the from and to positions
          else if (Math.abs(fromRow - toRow) == 2) {
            slotInBetweenRow = (fromRow + toRow) / 2;
            slotInBetweenCol = fromCol;
          }
          else {
            slotInBetweenRow = fromRow;
            slotInBetweenCol = (fromCol + toCol) / 2;
          }

          SlotState slotInBetween = getSlotAt(slotInBetweenRow, slotInBetweenCol);

          if (slotInBetween == SlotState.Marble) {
            this.board[fromRow][fromCol] = SlotState.Empty;
            this.board[toRow][toCol] = SlotState.Marble;
            this.board[slotInBetweenRow][slotInBetweenCol] = SlotState.Empty;
          }
          else {
            throw new IllegalArgumentException("There is no marble in between these two slots!");
          }
        }
        else {
          throw new IllegalArgumentException(
                  "The distance between these two slots is invalid for a move.");
        }
      }
      else {
        throw new IllegalArgumentException("To slot is not empty!");
      }
    }
    else {
      throw new IllegalArgumentException("From slot does not have a marble!");
    }
  }

  /**
   * Determines if the spacing between the from position and the to position is valid for a move
   * for this board shape. The spacing is valid if they are exactly 2 spaces apart horizontally xor
   * vertically. (This spacing is valid for English and European Marble Solitaire).
   *
   * @param fromRow the row of the from position
   * @param fromCol the column of the from position
   * @param toRow the row of the to position
   * @param toCol the column of the to position
   * @return if the spacing is valid
   */
  protected boolean validSpacing(int fromRow, int fromCol, int toRow, int toCol) {
    return (Math.abs(fromRow - toRow) == 2 && fromCol == toCol)
            || (Math.abs(fromCol - toCol) == 2 && fromRow == toRow);
  }

  // Code change: the conditional statement in some of the loops were modified as after
  // more testing found that some rows were being skipped when checking for valid moves left
  @Override
  public boolean isGameOver() {
    // if there's only one marble left, there's no more valid moves left
    if (this.getBoardSize() == 1) {
      return true;
    }

    // checks if any marbles can make a valid move to the right
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize() - 2; j++) {
        if (this.getSlotAt(i, j) == SlotState.Marble
                && this.getSlotAt(i, j + 1) == SlotState.Marble
                && this.getSlotAt(i, j + 2) == SlotState.Empty) {
          return false;
        }
      }
    }

    // checks if any marbles can make a valid move down
    for (int i = 0; i < this.getBoardSize() - 2; i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.getSlotAt(i, j) == SlotState.Marble
                && this.getSlotAt(i + 1, j) == SlotState.Marble
                && this.getSlotAt(i + 2, j) == SlotState.Empty) {
          return false;
        }
      }
    }

    // checks if any marbles can make a valid move to the left
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = this.getBoardSize() - 1; j >= 2; j--) {
        if (this.getSlotAt(i, j) == SlotState.Marble
                && this.getSlotAt(i, j - 1) == SlotState.Marble
                && this.getSlotAt(i, j - 2) == SlotState.Empty) {
          return false;
        }
      }
    }

    // checks if any marbles can make a valid move up
    for (int i = this.getBoardSize() - 1; i >= 2; i--) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.getSlotAt(i, j) == SlotState.Marble
                && this.getSlotAt(i - 1, j) == SlotState.Marble
                && this.getSlotAt(i - 2, j) == SlotState.Empty) {
          return false;
        }
      }
    }

    return true;
  }

  @Override
  public int getBoardSize() {
    return this.board.length;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row > this.getBoardSize() - 1 || col > this.getBoardSize() - 1 || row < 0 || col < 0) {
      throw new IllegalArgumentException("Given position is beyond the dimensions of this board.");
    }
    return this.board[row][col];
  }

  @Override
  public int getScore() {
    int numMarble = 0;
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.board[i][j] == SlotState.Marble) {
          numMarble++;
        }
      }
    }
    return numMarble;
  }
}
