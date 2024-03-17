package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * This class represents an implementation of a controller for the game of Marble Solitaire. This
 * controller is able to start the game and gives the option to play the game all the way through
 * or to quit.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private Scanner input;

  /**
   * Constructs a controller with the given model, view, and readable object.
   *
   * @param model the desired model
   * @param view the desired view
   * @param in the desired readable object
   * @throws IllegalArgumentException if any of the given parameters are null
   */
  public MarbleSolitaireControllerImpl(
          MarbleSolitaireModel model, MarbleSolitaireView view, Readable in)
          throws IllegalArgumentException {
    if (model == null || view == null || in == null) {
      throw new IllegalArgumentException("Model, view, or Readable is null.");
    }
    this.model = model;
    this.view = view;
    this.input = new Scanner(in);
  }

  /**
   * Plays a game of Marble Solitaire with this controller's model and view. The user is able to
   * view the board and current score through the view and is prompted to make moves. To make a
   * move, the user inputs 4 positive integers, the first representing the row of the original
   * position, the second representing the column of the original position, the third representing
   * the row of the destination position, and the fourth representing the column of the destination
   * position. When a bad input (a non-positive number, a string, a char that's not 'q' or 'Q',
   * etc.) the user is asked to re-enter that particular input. This method ends once the game is
   * played through (there are no valid moves left) or if the player decides to quit by inputting
   * 'q' or 'Q'.
   *
   * @throws IllegalStateException if transmission to the view or reading fails
   */
  @Override
  public void playGame() throws IllegalStateException {
    // while the game isn't over, let the game play out
    while (!this.model.isGameOver()) {

      // tries to render the game and transmit the current score
      try {
        this.view.renderBoard();
        this.view.renderMessage("\nScore: " + this.model.getScore() + "\n");
      }
      // if transmission to the view fails, throw IllegalStateException
      catch (IOException e) {
        throw new IllegalStateException();
      }

      int fromRow;
      int fromCol;
      int toRow;
      int toCol;

      // tries to gather inputs for the model to make a move
      try {
        // subtracts one from inputs as the inputs are meant to be user friendly and therefore
        // start from 1
        fromRow = this.moveInput() - 1;
        fromCol = this.moveInput() - 1;
        toRow = this.moveInput() - 1;
        toCol = this.moveInput() - 1;
      }
      // if a FoundQuitException is thrown, end the game and transmit some information
      catch (FoundQuitException e) {
        // tries to transmit a quit message, the last render of the board, and the last score
        try {
          this.view.renderMessage("Game quit!\n");
          this.view.renderMessage("State of game when quit:\n");
          this.view.renderBoard();
          this.view.renderMessage("\nScore: " + this.model.getScore());
          return; // ends the method
        }
        // if transmission to the view fails, throw IllegalStateException
        catch (IOException ex) {
          throw new IllegalStateException();
        }
      }

      // attempt to make a move after fromRow, fromCol, toRow, and toCol
      // have been assigned values
      try {
        this.model.move(fromRow, fromCol, toRow, toCol);
      }
      // if an IllegalArgumentException is thrown, the move was invalid
      catch (IllegalArgumentException e) {
        // tries to transmit that move was invalid and why it was invalid,
        // continues the loop
        try {
          this.view.renderMessage("Invalid move. Play again. " + e.getMessage() + "\n");
          continue;
        }
        // if transmission to the view fails, throw IllegalStateException
        catch (IOException ex) {
          throw new IllegalStateException();
        }
      }
    }

    // when the game is over, transmit a game over message, render the final board,
    // and the final score and end the method
    try {
      this.view.renderMessage("Game over!\n");
      this.view.renderBoard();
      this.view.renderMessage("\nScore: " + this.model.getScore());
      return;
    }
    // if transmission to the view fails, throw IllegalStateException
    catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  /**
   * Helps the method playGame() take in inputs to move the model. Re-prompts for another
   * input if input isn't a positive integer, "q" or "Q".
   *
   * @return a positive integer
   * @throws FoundQuitException if "q" or "Q" is inputted
   * @throws IllegalStateException if Readable runs out of inputs
   */
  private int moveInput() throws FoundQuitException, IllegalStateException {
    // try reading the an input
    try {
      int nextInt;
      String userInput = input.next();

      // if the input is "q" or "Q", throw FoundQuitException
      if (userInput.equals("q") || userInput.equals("Q")) {
        throw new FoundQuitException();
      }
      // else, try parsing the input to an integer
      else {
        try {
          nextInt = Integer.parseInt(userInput);

          // if the input is non-positive, ask for a different input
          if (nextInt <= 0) {
            return this.badInput();
          }
          // if it can be parsed to an integer and it's positive, return it
          else {
            return nextInt;
          }
        }
        // if the input cannot be parsed to an integer, ask for a different input
        catch (NumberFormatException e) {
          return this.badInput();
        }
      }
    }
    // if it's not possible to read/ran out of inputs, throw IllegalStateException
    catch (NoSuchElementException e) {
      throw new IllegalStateException();
    }
  }

  /**
   * Transmits a message indicating that a bad input was received and calls
   * moveInput again to gather more inputs. Only used to help the method moveInput().
   *
   * @return a positive integer
   * @throws FoundQuitException if "q" or "Q" is inputted
   * @throws IllegalStateException if Readable runs out of inputs
   */
  private int badInput() throws FoundQuitException, IllegalStateException {
    // tries to transmit bad input message
    try {
      this.view.renderMessage("Bad input, please re-enter value:\n");
      return this.moveInput();
    }
    // if transmission to the view fails, throw IllegalStateException
    catch (IOException e) {
      throw new IllegalStateException();
    }
  }
}
