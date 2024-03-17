package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

/**
 * This class represents the view of a game of Triangle Solitaire in text.
 */
public class TriangleSolitaireTextView extends MarbleSolitaireTextView {

  /**
   * Constructs a TriangleSolitaireTextView object with the given MarbleSolitaireModelState
   * and sets the Appendable object to System.out. The model has to be a triangle solitaire model
   * and not null to be valid.
   *
   * @param model the desired model state
   * @throws IllegalArgumentException if the given model is invalid
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model)
          throws IllegalArgumentException {
    super(model);
    if (!(model instanceof TriangleSolitaireModel)) {
      throw new IllegalArgumentException("Invalid model!");
    }
  }

  /**
   * Constructs a TriangleSolitaireTextView object with the given MarbleSolitaireModelState
   * and Appendable object. The model has to be a triangle solitaire model and not null to be valid.
   *
   * @param model the desired model state
   * @param out the desired appendable object
   * @throws IllegalArgumentException if the given model is invalid or appendable is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable out)
          throws IllegalArgumentException {
    super(model, out);
    if (!(model instanceof TriangleSolitaireModel)) {
      throw new IllegalArgumentException("Invalid model!");
    }
  }

  @Override
  public String toString() {
    String board = "";

    for (int i = 0; i < this.modelState.getBoardSize(); i = i + 1) {
      // adds the spaces on the left of the triangle
      for (int j = 0; j < this.modelState.getBoardSize() - i - 1; j = j + 1) {
        board += " ";
      }

      for (int j = 0; j < this.modelState.getBoardSize(); j++) {
        if (this.modelState.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Marble) {
          board += "O";
        }
        else if (this.modelState.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty) {
          board += "_";
        }
        else if ((this.modelState.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Invalid)) {
          // removes the last space
          board = board.substring(0, board.length() - 1);
          // only add a newline if the current i doesn't represent the last row of the board
          if (i != this.modelState.getBoardSize() - 1) {
            board += "\n";
          }
          break;
        }

        // only add a space if the current j doesn't represent the last slot in this row
        if (j != this.modelState.getBoardSize() - 1) {
          board += " ";
        }
      }
    }

    return board;
  }
}
