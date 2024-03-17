package cs3500.marblesolitaire.controller;

/**
 * This interface represents the controller of a game of Marble Solitaire.
 */
public interface MarbleSolitaireController {
  /**
   * Plays a new game of Marble Solitaire.
   *
   * @throws IllegalStateException if it is unable to read input or transmit output
   */
  void playGame() throws IllegalStateException;
}
