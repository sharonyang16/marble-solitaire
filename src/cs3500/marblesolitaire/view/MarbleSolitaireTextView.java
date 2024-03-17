package cs3500.marblesolitaire.view;

import java.io.IOException;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class represents the view of a game of Marble Solitaire in text.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  protected MarbleSolitaireModelState modelState;
  protected Appendable out;

  /**
   * Constructs a MarbleSolitaireTextView object with the given MarbleSolitaireModelState
   * and sets the Appendable object to System.out.
   *
   * @param modelState the desired model state
   * @throws IllegalArgumentException if the given model state is null
   */
  public MarbleSolitaireTextView(
          MarbleSolitaireModelState modelState) throws IllegalArgumentException {
    if (modelState == null) {
      throw new IllegalArgumentException("The given model is null.");
    }

    this.modelState = modelState;
    this.out = System.out;
  }

  /**
   * Constructs a MarbleSolitaireTextView object with the given MarbleSolitaireModelState
   * and Appendable object.
   *
   * @param modelState the desired model state
   * @param out the desired appendable object
   * @throws IllegalArgumentException if the given model state or appendable is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState modelState, Appendable out)
      throws IllegalArgumentException {
    if (modelState == null || out == null) {
      throw new IllegalArgumentException("The given model or Appendable object is null.");
    }
    this.modelState = modelState;
    this.out = out;
  }

  @Override
  public String toString() {
    String board = "";

    for (int i = 0; i < this.modelState.getBoardSize(); i++) {
      boolean startedArm = false;
      for (int j = 0; j < this.modelState.getBoardSize(); j++) {
        if (this.modelState.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Marble) {
          board += "O";
          startedArm = true;
        }
        else if (this.modelState.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty) {
          board += "_";
          startedArm = true;
        }
        else if ((this.modelState.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Invalid)
                && !startedArm) {
          board += " ";
        }
        else {
          // removes the last space
          board = board.substring(0, board.length() - 1);
          break;
        }
        // only add a space if the current j doesn't represent the last slot in this row
        if (j != this.modelState.getBoardSize() - 1) {
          board += " ";
        }

      }
      // only add a newline if the current i doesn't represent the last row of the board
      if (i != this.modelState.getBoardSize() - 1) {
        board += "\n";
      }
    }

    return board;
  }

  @Override
  public void renderBoard() throws IOException {
    this.out.append(toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.out.append(message);
  }
}
