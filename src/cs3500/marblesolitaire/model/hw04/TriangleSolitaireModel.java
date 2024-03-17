package cs3500.marblesolitaire.model.hw04;

/**
 * This class represents the model of a game of Triangle Solitaire and all the operations
 * that are available for this game.
 */
public class TriangleSolitaireModel extends AbstractMarbleSolitaireModel {

  /**
   * Constructs an TriangleSolitaireModel object and initializes it with a board that has
   * a dimension of 5 and the empty slot at the top of the board (0,0).
   */
  public TriangleSolitaireModel() {
    this(5, 0, 0);
  }

  /**
   * Constructs an TriangleSolitaireModel object and initializes it with a board that has
   * the given dimension and the empty slot at the top of the board (0,0).
   *
   * @param dimensions the dimension/number of rows
   * @throws IllegalArgumentException if the dimensions are non-positive
   */
  public TriangleSolitaireModel(int dimensions) throws IllegalArgumentException {
    this(dimensions, 0, 0);
  }

  /**
   * Constructs an TriangleSolitaireModel object and initializes it with a board that has
   * a dimension of 5 and the empty slot at the given position.
   * @param row the row of the empty slot
   * @param col the column of the empty slot
   * @throws IllegalArgumentException if the given position is invalid
   */
  public TriangleSolitaireModel(int row, int col) throws IllegalArgumentException {
    this(5, row, col);
  }

  /**
   * Constructs an TriangleSolitaireModel object and initializes it with a board that has
   * the given dimension and the empty slot at the given position.
   * @param dimensions the dimension/number of rows
   * @param row the row of the empty slot
   * @param col the column of the empty slot
   * @throws IllegalArgumentException if the given dimension is non-positive or position is negative
   */
  public TriangleSolitaireModel(int dimensions, int row, int col) throws IllegalArgumentException {
    super();
    if (dimensions <= 0) {
      throw new IllegalArgumentException("Invalid dimension; dimension must be positive.");
    }
    if (this.invalidSpace(dimensions, row, col)) {
      throw new IllegalArgumentException("Invalid slot; empty slot cannot be placed there.");
    }

    this.board = createBoard(dimensions, row, col);
  }

  @Override
  protected SlotState[][] createBoard(int dimensions, int sRow, int sCol) {
    SlotState[][] board = new SlotState[dimensions][dimensions];

    for (int i = 0; i < dimensions; i++) {
      for (int j = 0; j < dimensions; j++) {
        if (i == sRow && j == sCol) {
          board[i][j] = SlotState.Empty;
        } else if (this.invalidSpace(dimensions, i, j)) {
          board[i][j] = SlotState.Invalid;
        } else {
          board[i][j] = SlotState.Marble;
        }
      }
    }
    return board;
  }

  /**
   * Determines if the given space is invalid for a Triangle Solitaire Model. (Does not use the
   * armThickness parameter).
   *
   * @param armThickness the arm thickness
   * @param row the row position of the slot
   * @param col the column position of the slot
   * @return if the space is invalid
   */
  @Override
  protected boolean invalidSpace(int armThickness, int row, int col) {
    return col > row;
  }

  /**
   * Determines if the spacing between the from position and the to position is valid for a move
   * for a triangular shaped board. The spacing is valid if they are exactly 2 spaces apart
   * horizontally xor vertically or if the from position is diagonally 2 spaces above and to the
   * left of the to position or if the from position is diagonally 2 spaces below to the right of
   * the to position.
   *
   * @param fromRow the row of the from position
   * @param fromCol the column of the from position
   * @param toRow the row of the to position
   * @param toCol the column of the to position
   * @return if the spacing is valid
   */
  @Override
  protected boolean validSpacing(int fromRow, int fromCol, int toRow, int toCol) {
    return (Math.abs(fromRow - toRow) == 2 && fromCol == toCol)
            || (Math.abs(fromCol - toCol) == 2 && fromRow == toRow)
            || (fromRow - toRow == 2 && fromCol - toCol == 2)
            || (fromRow - toRow == -2 && fromCol - toCol == -2);
  }

  /**
   * Determines if there are any valid moves left in this game including moves
   * that go directly up, directly down, directly to the right, directly to the left, diagonally
   * down right, and diagonally up left.
   *
   * @return true if no valid moves are left
   */
  @Override
  public boolean isGameOver() {
    // checks if any marbles can make a valid move diagonally down right
    for (int i = 0; i < this.getBoardSize() - 2; i = i + 1) {
      for (int j = 0; j < this.getBoardSize() - 2; j = j + 1) {
        if (this.getSlotAt(i, j) == SlotState.Marble
                && this.getSlotAt(i + 1, j + 1) == SlotState.Marble
                && this.getSlotAt(i + 2, j + 2) == SlotState.Empty) {
          return false;
        }
      }
    }

    // checks if any marbles can make a valid move diagonally up left
    for (int i = this.getBoardSize() - 1; i >= 2; i = i - 1) {
      for (int j = 2; j < this.getBoardSize(); j = j + 1) {
        if (this.getSlotAt(i, j) == SlotState.Marble
                && this.getSlotAt(i - 1, j - 1) == SlotState.Marble
                && this.getSlotAt(i - 2, j - 2) == SlotState.Empty) {
          return false;
        }
      }
    }

    // if there aren't valid diagonal moves, then it depends on if there are valid moves
    // up, down, left, and right (which is what isGameOver in the superclass checks)
    return super.isGameOver();
  }

}
