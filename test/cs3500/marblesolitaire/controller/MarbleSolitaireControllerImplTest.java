package cs3500.marblesolitaire.controller;

import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.IOExceptionAppendable;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * A JUnit test class for the MarbleSolitaireControllerImpl class.
 */
public class MarbleSolitaireControllerImplTest {
  // controller, model, controllerIn, and controllerOut changed to have "english" prefix it
  // to be more specific on which board shape each controller is using
  private MarbleSolitaireController englishController;
  private MarbleSolitaireModel englishModel;
  private Readable englishControllerIn;
  private Appendable englishControllerOut;

  private MarbleSolitaireController europeanController;
  private MarbleSolitaireModel europeanModel;
  private Readable europeanControllerIn;
  private Appendable europeanControllerOut;

  private MarbleSolitaireController triangleController;
  private MarbleSolitaireModel triangleModel;
  private Readable triangleControllerIn;
  private Appendable triangleControllerOut;

  // used to test inputs for the view
  private MarbleSolitaireController controllerUsingMockView;
  private StringBuilder log;
  private MarbleSolitaireView mockView;

  private MarbleSolitaireController controllerUsingMockModel;
  private MarbleSolitaireModel mockModel;

  // initial condition for the examples used in this test class
  @Before
  public void init() {
    this.englishModel = new EnglishSolitaireModel();
    this.englishControllerIn = new StringReader("q");
    this.englishControllerOut = new StringBuffer();
    this.englishController = new MarbleSolitaireControllerImpl(englishModel,
            new MarbleSolitaireTextView(englishModel, englishControllerOut), englishControllerIn);

    this.europeanModel = new EuropeanSolitaireModel(1, 1);
    this.europeanControllerIn = new StringReader("q");
    this.europeanControllerOut = new StringBuffer();
    this.europeanController = new MarbleSolitaireControllerImpl(europeanModel,
            new MarbleSolitaireTextView(europeanModel, europeanControllerOut),
            europeanControllerIn);

    this.triangleModel = new TriangleSolitaireModel();
    this.triangleControllerIn = new StringReader("q");
    this.triangleControllerOut = new StringBuffer();
    this.triangleController = new MarbleSolitaireControllerImpl(triangleModel,
            new TriangleSolitaireTextView(triangleModel, triangleControllerOut),
            triangleControllerIn);

    this.log = new StringBuilder();
    this.mockView = new ConfirmInputMarbleSolitaireView(log);
    this.controllerUsingMockView
            = new MarbleSolitaireControllerImpl(this.englishModel,
            this.mockView, this.englishControllerIn);

    this.mockModel = new ConfirmInputMarbleSolitaireModel(log);
    this.controllerUsingMockModel
            = new MarbleSolitaireControllerImpl(mockModel,
            new MarbleSolitaireTextView(mockModel,
                    this.englishControllerOut), this.englishControllerIn);
  }

  // to test creating a MarbleSolitaireControllerImpl object that throws an exception
  // due to the given MarbleSolitaireModel object being null
  @Test(expected = IllegalArgumentException.class)
  public void testCreateControllerNullModel() {
    new MarbleSolitaireControllerImpl(
            null, new MarbleSolitaireTextView(englishModel), new StringReader(""));
  }

  // to test creating a MarbleSolitaireControllerImpl object that throws an exception
  // due to the given MarbleSolitaireView object being null
  @Test(expected = IllegalArgumentException.class)
  public void testCreateControllerNullView() {
    new MarbleSolitaireControllerImpl(
            europeanModel, null, new StringReader(""));
  }

  // to test creating a MarbleSolitaireControllerImpl object that throws an exception
  // due to the given Readable object being null
  @Test(expected = IllegalArgumentException.class)
  public void testCreateControllerNullReadable() {
    new MarbleSolitaireControllerImpl(
            englishModel,  new MarbleSolitaireTextView(englishModel), null);
  }

  // to test creating a MarbleSolitaireControllerImpl object that throws an exception
  // due to the given MarbleSolitaireModel and MarbleSolitaireView objects being null
  @Test(expected = IllegalArgumentException.class)
  public void testCreateControllerNullModelAndView() {
    new MarbleSolitaireControllerImpl(
            null,  null,  new StringReader(""));
  }

  // to test creating a MarbleSolitaireControllerImpl object that throws an exception
  // due to the given MarbleSolitaireModel and Readable objects being null
  @Test(expected = IllegalArgumentException.class)
  public void testCreateControllerNullModelAndReadable() {
    new MarbleSolitaireControllerImpl(
            null,    new MarbleSolitaireTextView(englishModel),  null);
  }

  // to test creating a MarbleSolitaireControllerImpl object that throws an exception
  // due to the given MarbleSolitaireView and Readable objects being null
  @Test(expected = IllegalArgumentException.class)
  public void testCreateControllerNullViewAndReadable() {
    new MarbleSolitaireControllerImpl(
            triangleModel,    null,  null);
  }

  // to test creating a MarbleSolitaireControllerImpl object that throws an exception
  // due to all of the given objects being null
  @Test(expected = IllegalArgumentException.class)
  public void testCreateControllerAllNull() {
    new MarbleSolitaireControllerImpl(
            null,    null,  null);
  }

  // to test the method playGame where no moves are made and the game is quit immediately
  @Test
  public void testEnglishPlayGameQuitImmediately() {
    setEnglishInputs("q");

    String[] actualOut = this.englishControllerOut.toString().split("\n");

    assertEquals("Score: 32", actualOut[7]);
    assertEquals("Score: 32", actualOut[17]);
    assertEquals(actualOut[17], actualOut[7]); // they're the same as no moves were made

    // quitting should transmit these messages
    assertEquals("Game quit!", actualOut[8]);
    assertEquals("State of game when quit:", actualOut[9]);
  }

  // to test the method playGame where one good input is made and the game is quit
  @Test
  public void testEnglishPlayGameOneInput() {
    setEnglishInputs("1 q");

    String[] actualOut = this.englishControllerOut.toString().split("\n");

    assertEquals("Score: 32", actualOut[7]);
    assertEquals("Score: 32", actualOut[17]);
    assertEquals(actualOut[17], actualOut[7]); // they're the same as no full move was never made

    // quitting should transmit these messages
    assertEquals("Game quit!", actualOut[8]);
    assertEquals("State of game when quit:", actualOut[9]);
  }

  // to test the method playGame where some good inputs are made but the game is quit before
  // a full move is made
  @Test
  public void testEnglishPlayGameQuitBeforeMoveFinish() {
    setEnglishInputs("6 4 q");

    String[] actualOut = this.englishControllerOut.toString().split("\n");

    // equal because a full move couldn't be made before quit
    assertEquals("Score: 32", actualOut[7]);
    assertEquals("Score: 32", actualOut[17]);
    assertEquals(actualOut[17], actualOut[7]);
  }

  // to test the method playGame where three good inputs are made and the game is quit
  @Test
  public void testEnglishPlayGameThreeInput() {
    setEnglishInputs("1 1 4 q");

    String[] actualOut = this.englishControllerOut.toString().split("\n");

    assertEquals("Score: 32", actualOut[7]);
    assertEquals("Score: 32", actualOut[17]);
    assertEquals(actualOut[17], actualOut[7]); // they're the same as no full move was never made

    // quitting should transmit these messages
    assertEquals("Game quit!", actualOut[8]);
    assertEquals("State of game when quit:", actualOut[9]);
  }

  // to test the method playGame where 2 valid moves are made and then the game is quit
  @Test
  public void testEnglishPlayGameMove2ValidThenGameQuit() {
    setEnglishInputs("4 6 4 4 2 5 4 5 q");

    String[] actualOut = this.englishControllerOut.toString().split("\n");

    // middle row of the board
    assertEquals("O O O _ O O O", actualOut[3]); // row before any moves
    assertEquals("O O O O _ _ O", actualOut[11]); // row after first move
    assertEquals("O O O O O _ O", actualOut[19]); // row after second move
    assertEquals("O O O O O _ O", actualOut[29]); // final state of this row
    assertEquals(actualOut[29], actualOut[19]); // same as the second move was last valid move

    assertEquals("Score: 32", actualOut[7]);
    assertEquals("Score: 31", actualOut[15]);
    assertEquals("Score: 30", actualOut[23]);
  }

  // to test the method playGame where a bad input is made and a valid move is made
  // and then the game is quit
  @Test
  public void testEnglishPlayGameBadInputMove1ValidThenGameQuit() {
    setEnglishInputs("t 2 4 4 4 q");

    String[] actualOut = this.englishControllerOut.toString().split("\n");

    // a bad input should cause this message to be transmitted
    assertEquals("Bad input, please re-enter value:", actualOut[8]);

    // even though there was a bad input, 4 good inputs were made before quitting
    // and they lead to a valid move
    assertEquals("Score: 32", actualOut[7]);
    assertEquals("Score: 31", actualOut[16]);
  }

  // to test the method playGame where an invalid move is attempted to be made
  // the move is invalid because the from position isn't a slot with a marble
  @Test
  public void testEnglishPlayGameInvalidFromMove() {
    setEnglishInputs("2 4 4 4 2 6 2 4 5 4 3 4 4 4 2 4 q");
    String[] actualOut = this.englishControllerOut.toString().split("\n");

    // Note: 2 4 4 4 and 5 4 3 4 inputs lead to valid moves

    // 2 6 2 4 causes this invalid move as the from position is an invalid slot
    assertEquals("Invalid move. Play again. From slot does not have a marble!",
            actualOut[16]);
    // 4 4 2 4 causes this invalid move as after making some valid moves,
    // this from position is an empty slot now
    assertEquals("Invalid move. Play again. From slot does not have a marble!",
            actualOut[33]);
  }

  // to test the method playGame where an invalid move is attempted to be made
  // the move is invalid because the to position isn't an empty slot
  @Test
  public void testEnglishPlayGameInvalidToMove() {
    setEnglishInputs("3 6 3 4 4 6 2 6 q");
    String[] actualOut = this.englishControllerOut.toString().split("\n");

    // 3 6 3 4 causes this invalid move as the to position is a marble
    assertEquals("Invalid move. Play again. To slot is not empty!",
            actualOut[8]);
    // 4 6 2 6 causes this invalid move as the to position is an invalid slot
    assertEquals("Invalid move. Play again. To slot is not empty!",
            actualOut[17]);
  }

  // to test the method playGame where an invalid move is attempted to be made
  // the move is invalid because the spacing of the from and two positions aren't
  // 2 apart either horizontally or vertically
  @Test
  public void testEnglishPlayGameInvalidSpacingMove() {
    setEnglishInputs("1 4 4 4 2 4 4 4 5 6 3 4 q");
    String[] actualOut = this.englishControllerOut.toString().split("\n");

    // Note: 2 4 4 4 input lead to a valid move

    // 1 4 4 4 causes this invalid move as it tries to move three spaces down
    assertEquals(
            "Invalid move. Play again. "
                    + "The distance between these two slots is invalid for a move.",
            actualOut[8]);

    // 5 6 3 4 causes this invalid move as it tries to move diagonally
    assertEquals(
            "Invalid move. Play again. "
                    + "The distance between these two slots is invalid for a move.",
            actualOut[25]);
  }

  // to test the method playGame where an invalid move is attempted to be made
  // the move is invalid because the slot between the to and from positions doesn't have
  // a marble
  @Test
  public void testEnglishPlayGameInvalidBetweenMove() {
    setEnglishInputs("4 2 4 4 4 5 4 3 4 3 4 5 q");
    String[] actualOut = this.englishControllerOut.toString().split("\n");

    // Note: 4 2 4 4 and 4 5 4 3  inputs lead to valid moves

    // 4 3 4 5 causes this invalid move as the slot in between 4 3 and 4 5 is empty
    assertEquals(
            "Invalid move. Play again. "
                    + "There is no marble in between these two slots!", actualOut[24]);
  }

  // to test the method playGame where the game is played without any bad inputs or invalid moves
  // until it is game over
  @Test
  public void testEnglishPlayGameThrough() {
    setEnglishInputs("2 4 4 4 5 4 3 4 5 6 5 4 3 6 5 6 5 7 5 5 3 7 5 7 4 2 4 4 6 3 4 3"
            + " 6 5 6 3 7 3 5 3 7 5 7 3 4 4 2 4 3 2 3 4 1 3 3 3 1 5 1 3 2 5 2 3"
            + " 3 4 3 2 1 3 3 3 5 4 5 6 5 7 5 5 5 2 5 4 5 5 5 3 3 2 3 4 3 5 3 3"
            + " 4 3 6 3 7 3 5 3");
    String[] actualOut = this.englishControllerOut.toString().split("\n");

    // playing the game until game over should cause this message to be transmitted
    assertEquals("Game over!", actualOut[208]);
  }

  // to test the method playGame where the game is played through until it is game over but
  // additional q input is made after the game is already over
  @Test
  public void testEnglishPlayGameTryQuitAfterGameOver() {
    setEnglishInputs("2 4 4 4 5 4 3 4 5 6 5 4 3 6 5 6 5 7 5 5 3 7 5 7 4 2 4 4 6 3 4 3"
            + " 6 5 6 3 7 3 5 3 7 5 7 3 4 4 2 4 3 2 3 4 1 3 3 3 1 5 1 3 2 5 2 3"
            + " 3 4 3 2 1 3 3 3 5 4 5 6 5 7 5 5 5 2 5 4 5 5 5 3 3 2 3 4 3 5 3 3"
            + " 4 3 6 3 7 3 5 3 q");
    String[] actualOut = this.englishControllerOut.toString().split("\n");

    // playing the game until game over should cause this message to be transmitted
    assertEquals("Game over!", actualOut[208]);

    // these two messages should only appear if a game quit happens, but since the game is
    // already over, a game quit isn't possible and these two messages should never appear
    for (String message : actualOut) {
      assertNotEquals("Game quit!", message);
      assertNotEquals("State of game when quit:", message);
    }
  }

  // to test the method playGame where the game is played through until it is game over but
  // additional bad inputs are made after the game is already over
  @Test
  public void testEnglishPlayGameBadInputsAfterGameOver() {
    setEnglishInputs("2 4 4 4 5 4 3 4 5 6 5 4 3 6 5 6 5 7 5 5 3 7 5 7 4 2 4 4 6 3 4 3"
            + " 6 5 6 3 7 3 5 3 7 5 7 3 4 4 2 4 3 2 3 4 1 3 3 3 1 5 1 3 2 5 2 3"
            + " 3 4 3 2 1 3 3 3 5 4 5 6 5 7 5 5 5 2 5 4 5 5 5 3 3 2 3 4 3 5 3 3"
            + " 4 3 6 3 7 3 5 3 n -4 3");
    String[] actualOut = this.englishControllerOut.toString().split("\n");

    // playing the game until game over should cause this message to be transmitted
    assertEquals("Game over!", actualOut[208]);

    // this message should appear when a bad input is read, but since the game is
    // already over, the left over inputs don't matter and this message shouldn't appear
    for (String message : actualOut) {
      assertNotEquals("Bad input, please re-enter value:", message);
    }
  }

  // to test the method playGame where the game is played until it is game over with some bad inputs
  // along the way
  @Test
  public void testEnglishPlayGameWithBadInputsThrough() {
    setEnglishInputs("2 4 4 4 5 4 t 3 4 5 6 5 4 3 6 -9 5 6 5 7 5 5 3 7 5 7 4 2 4 no 4 6 3 4 3 k"
            + " 6 5 0 6 3 7 3 5 3 7 -33 5 7 3 4 4 2 4 3 2 3 4 1 3 3 3 y 1 5 1 3 2 5 2 m 3"
            + " 3 4 3 hellooooo 2 1 3 3 3 5 4 5 6 5 7 5 5 5 2 5 4 5 5 5 3 3 2 3 4 3 5 3 3"
            + " 4 3 6 3 7 jk 3 5 3");
    String[] actualOut = this.englishControllerOut.toString().split("\n");

    // playing the game until game over should cause this message to be transmitted
    assertEquals("Game over!", actualOut[218]);

  }

  // to test the method playGame where the reader throws an IllegalStateException due to
  // running out of inputs as the game hasn't ended because there wasn't enough moves for a game
  // over and there's no input telling the game to quit
  @Test(expected = IllegalStateException.class)
  public void testEnglishPlayGameNoQuitOrGameOver() {
    this.englishControllerIn = new StringReader("4 2 4 4");

    this.englishController = new MarbleSolitaireControllerImpl(
            this.englishModel,
            new MarbleSolitaireTextView(this.englishModel, this.englishControllerOut),
            this.englishControllerIn);

    this.englishController.playGame();
  }

  // to test the method playGame where the reader throws an IllegalStateException due to
  // being unable to transmit to the view (by initializing the view with a corrupted
  // Appendable object); the fail transmission throws an IOException, and playGame catches it
  // and throws it as an IllegalStateException
  @Test(expected = IllegalStateException.class)
  public void testEnglishPlayGameTransmissionFail() {
    this.englishControllerIn = new StringReader("4 6 4 4 q");

    this.englishController = new MarbleSolitaireControllerImpl(
            this.englishModel,
            new MarbleSolitaireTextView(this.englishModel, new IOExceptionAppendable()),
            this.englishControllerIn);

    this.englishController.playGame();
  }

  // helper for tests to initialize a controller that has the given string as the
  // string reader's inputs and starts the game
  private void setEnglishInputs(String inputs) {
    this.englishControllerIn = new StringReader(inputs);

    this.englishController = new MarbleSolitaireControllerImpl(
            this.englishModel,
            new MarbleSolitaireTextView(this.englishModel, this.englishControllerOut),
            this.englishControllerIn);

    this.englishController.playGame();
  }

  // to test the method playGame where no moves are made and the game is quit immediately
  @Test
  public void testEuropeanPlayGameQuitImmediately() {
    setEuropeanInputs("q");

    String[] actualOut = this.europeanControllerOut.toString().split("\n");

    assertEquals("Score: 36", actualOut[7]);
    assertEquals("Score: 36", actualOut[17]);
    assertEquals(actualOut[17], actualOut[7]); // they're the same as no moves were made

    // quitting should transmit these messages
    assertEquals("Game quit!", actualOut[8]);
    assertEquals("State of game when quit:", actualOut[9]);
  }

  // to test the method playGame where one good input is made and then a quit
  @Test
  public void testEuropeanPlayGameQuitOneInput() {
    setEuropeanInputs("1 q");

    String[] actualOut = this.europeanControllerOut.toString().split("\n");

    assertEquals("Score: 36", actualOut[7]);
    assertEquals("Score: 36", actualOut[17]);
    assertEquals(actualOut[17], actualOut[7]); // they're the same as a full move wasn't made

    // quitting should transmit these messages
    assertEquals("Game quit!", actualOut[8]);
    assertEquals("State of game when quit:", actualOut[9]);
  }

  // to test the method playGame where two good inputs are made and then a quit
  @Test
  public void testEuropeanPlayGameQuitTwoInput() {
    setEuropeanInputs("2 2 q");

    String[] actualOut = this.europeanControllerOut.toString().split("\n");

    assertEquals("Score: 36", actualOut[7]);
    assertEquals("Score: 36", actualOut[17]);
    assertEquals(actualOut[17], actualOut[7]); // they're the same as a full move wasn't made

    // quitting should transmit these messages
    assertEquals("Game quit!", actualOut[8]);
    assertEquals("State of game when quit:", actualOut[9]);
  }

  // to test the method playGame where three good inputs are made and then a quit
  @Test
  public void testEuropeanPlayGameQuitThreeInput() {
    setEuropeanInputs("4 5 2 q");

    String[] actualOut = this.europeanControllerOut.toString().split("\n");

    assertEquals("Score: 36", actualOut[7]);
    assertEquals("Score: 36", actualOut[17]);
    assertEquals(actualOut[17], actualOut[7]); // they're the same as a full move wasn't made

    // quitting should transmit these messages
    assertEquals("Game quit!", actualOut[8]);
    assertEquals("State of game when quit:", actualOut[9]);
  }

  // to test the method playGame where one valid move is made and then a quit
  @Test
  public void testEuropeanPlayGameOneMove() {
    setEuropeanInputs("2 4 2 2 q");

    String[] actualOut = this.europeanControllerOut.toString().split("\n");

    assertEquals("Score: 36", actualOut[7]);
    assertEquals("Score: 35", actualOut[15]);
    assertEquals("Score: 35", actualOut[25]);
    // these are the same as line 16 is the score after the valid move and the game is quit
    // after that one move
    assertEquals(actualOut[15], actualOut[25]);

    // quitting should transmit these messages
    assertEquals("Game quit!", actualOut[16]);
    assertEquals("State of game when quit:", actualOut[17]);
  }

  // to test the method playGame where the game is played without any bad inputs or invalid moves
  // until it is game over
  @Test
  public void testEuropeanPlayGameThrough() {
    setEuropeanInputs("4 2 2 2 3 4 3 2 1 3 3 3 2 2 4 2 1 5 1 3 2 5 2 3 3 6 3 4 "
            + "3 3 3 5 1 3 3 3 5 2 3 2 4 4 4 2 5 4 5 2 3 2 3 4 5 2 3 2 "
            + "3 1 3 3 5 1 3 1 3 4 3 2 3 1 3 3 7 3 5 3 7 4 5 4 5 4 5 2 "
                    + "6 2 4 2 6 6 6 4 5 6 5 4 4 6 4 4 5 4 7 4 7 5 7 3");
    String[] actualOut = this.europeanControllerOut.toString().split("\n");

    // playing the game until game over should cause this message to be transmitted
    assertEquals("Game over!", actualOut[216]);
  }

  // to test the method playGame where the game is played with some bad inputs
  // until it is game over
  // same moves are made in testEuropeanPlayGameThrough() to finish the game
  @Test
  public void testEuropeanPlayGameThroughWithBadInputs() {
    setEuropeanInputs("h 4 2 2 2 3 4 3 2 t 1 3 3 3 2 2 4 2 1 5 1 3 2 5 2 3 3 6 3 4 "
            + "3 3 -100 3 5 1 -5 3 3 3 5 2 3 2 4 4 t 4 2 5 4 5 2 b n 3 2 3 4 5 2 3 2 "
            + "3 1 3 3 5 1 3 1 3 4 3 2 3 1 3 3 7 3 5 3 7 4 0 -23 5 + 4 st 5 4 5 2 "
            + "6 2 4 2 6 bm 6 tsgb 6 0 4 5 6 -3 5 4 r 4 6 4 4 5 4 7 4 7 5 y 7 3");
    String[] actualOut = this.europeanControllerOut.toString().split("\n");

    // a bad input is made during the first move
    assertEquals("Bad input, please re-enter value:", actualOut[8]);

    // playing the game until game over should cause this message to be transmitted
    assertEquals("Game over!", actualOut[233]);
  }

  // to test the method playGame where the reader throws an IllegalStateException due to
  // running out of inputs as the game hasn't ended because there wasn't enough moves for a game
  // over and there's no input telling the game to quit
  @Test(expected = IllegalStateException.class)
  public void testEuropeanPlayGameNoQuitOrGameOver() {
    this.europeanControllerIn = new StringReader("1 3 1 1");

    this.europeanController = new MarbleSolitaireControllerImpl(
            this.europeanModel,
            new MarbleSolitaireTextView(this.europeanModel, this.europeanControllerOut),
            this.europeanControllerIn);

    this.europeanController.playGame();
  }

  // to test the method playGame where the reader throws an IllegalStateException due to
  // being unable to transmit to the view (by initializing the view with a corrupted
  // Appendable object); the fail transmission throws an IOException, and playGame catches it
  // and throws it as an IllegalStateException
  @Test(expected = IllegalStateException.class)
  public void testEuropeanPlayGameTransmissionFail() {
    this.europeanControllerIn = new StringReader("q");

    this.europeanController = new MarbleSolitaireControllerImpl(
            this.europeanModel,
            new MarbleSolitaireTextView(this.europeanModel, new IOExceptionAppendable()),
            this.europeanControllerIn);

    this.europeanController.playGame();
  }

  // helper for tests to initialize a controller that has the given string as the
  // string reader's inputs and starts the game
  private void setEuropeanInputs(String inputs) {
    this.europeanControllerIn = new StringReader(inputs);

    this.europeanController = new MarbleSolitaireControllerImpl(
            this.europeanModel,
            new MarbleSolitaireTextView(this.europeanModel, this.europeanControllerOut),
            this.europeanControllerIn);

    this.europeanController.playGame();
  }

  // to test the method playGame where no moves are made and the game is quit immediately
  @Test
  public void testTrianglePlayGameQuitImmediately() {
    setTriangleInputs("q");

    String[] actualOut = this.triangleControllerOut.toString().split("\n");

    assertEquals("Score: 14", actualOut[5]);
    assertEquals("Score: 14", actualOut[13]);
    assertEquals(actualOut[5], actualOut[13]); // they're the same as a full move wasn't made

    // quitting should transmit these messages
    assertEquals("Game quit!", actualOut[6]);
    assertEquals("State of game when quit:", actualOut[7]);
  }

  // to test the method playGame where one good input is made and then a quit
  @Test
  public void testTrianglePlayGameQuitOneInput() {
    setTriangleInputs("2 q");

    String[] actualOut = this.triangleControllerOut.toString().split("\n");

    assertEquals("Score: 14", actualOut[5]);
    assertEquals("Score: 14", actualOut[13]);
    assertEquals(actualOut[5], actualOut[13]); // they're the same as a full move wasn't made

    // quitting should transmit these messages
    assertEquals("Game quit!", actualOut[6]);
    assertEquals("State of game when quit:", actualOut[7]);
  }

  // to test the method playGame where two good inputs are made and then a quit
  @Test
  public void testTrianglePlayGameQuitTwoInput() {
    setTriangleInputs("3 3 q");

    String[] actualOut = this.triangleControllerOut.toString().split("\n");

    assertEquals("Score: 14", actualOut[5]);
    assertEquals("Score: 14", actualOut[13]);
    assertEquals(actualOut[5], actualOut[13]); // they're the same as a full move wasn't made

    // quitting should transmit these messages
    assertEquals("Game quit!", actualOut[6]);
    assertEquals("State of game when quit:", actualOut[7]);
  }

  // to test the method playGame where three good inputs are made and then a quit
  @Test
  public void testTrianglePlayGameQuitThreeInput() {
    setTriangleInputs("4 5 2 q");

    String[] actualOut = this.triangleControllerOut.toString().split("\n");

    assertEquals("Score: 14", actualOut[5]);
    assertEquals("Score: 14", actualOut[13]);
    assertEquals(actualOut[5], actualOut[13]); // they're the same as a full move wasn't made

    // quitting should transmit these messages
    assertEquals("Game quit!", actualOut[6]);
    assertEquals("State of game when quit:", actualOut[7]);
  }

  // to test the method playGame where one valid move is made and then a quit
  @Test
  public void testTrianglePlayGameQuitOneMove() {
    setTriangleInputs("3 1 1 1 q");

    String[] actualOut = this.triangleControllerOut.toString().split("\n");

    assertEquals("Score: 14", actualOut[5]);
    assertEquals("Score: 13", actualOut[11]);
    assertEquals("Score: 13", actualOut[19]);
    // these are the same as line 12 is the score after the valid move and the game is quit
    // after that one move
    assertEquals(actualOut[11], actualOut[19]);

    // quitting should transmit these messages
    assertEquals("Game quit!", actualOut[12]);
    assertEquals("State of game when quit:", actualOut[13]);
  }

  // to test the method playGame where the game is played without any bad inputs or invalid moves
  // until it is game over
  @Test
  public void testTrianglePlayGameThrough() {
    setTriangleInputs("3 3 1 1 3 1 3 3 1 1 3 1 5 4 3 2 5 2 5 4 "
            + "5 5 5 3 4 1 4 3 4 4 4 2 3 2 5 2 5 2 5 4 ");
    String[] actualOut = this.triangleControllerOut.toString().split("\n");

    // playing the game until game over should cause this message to be transmitted
    assertEquals("Game over!", actualOut[60]);
  }

  // to test the method playGame where the game is played with some bad inputs
  // until it is game over
  // same valid moves are made in testTrianglePlayGameThrough() to finish the game
  @Test
  public void testTrianglePlayGameThroughWithBadInputs() {
    setTriangleInputs("3 3 1 p 1 3 1 3 3 gg 1 1 3 1 5 4 3 -9 -100 2 5 2 5 4 "
            + "5 5 5 0 0 0 t 3 4 1 4 3 4 4 b 4 s 2 3 -8 2 5 2 t 5 b 2 5 4 ");
    String[] actualOut = this.triangleControllerOut.toString().split("\n");


    // this message transmit as a bad input is made during the first move
    assertEquals("Bad input, please re-enter value:", actualOut[6]);

    int numBadInputs = 0;
    for (String s : actualOut) {
      if (s.equals("Bad input, please re-enter value:")) {
        numBadInputs++;
      }
    }
    // message appears 13 times because 13 bad inputs are made
    assertEquals(13, numBadInputs);

    // playing the game until game over should cause this message to be transmitted
    assertEquals("Game over!", actualOut[73]);
  }

  // to test the method playGame where the reader throws an IllegalStateException due to
  // running out of inputs as the game hasn't ended because there wasn't enough moves for a game
  // over and there's no input telling the game to quit
  @Test(expected = IllegalStateException.class)
  public void testTrianglePlayGameNoQuitOrGameOver() {
    this.triangleControllerIn = new StringReader("2 2 0 0");

    this.triangleController = new MarbleSolitaireControllerImpl(
            this.triangleModel,
            new TriangleSolitaireTextView(this.triangleModel, this.triangleControllerOut),
            this.triangleControllerIn);

    this.triangleController.playGame();
  }

  // to test the method playGame where the reader throws an IllegalStateException due to
  // being unable to transmit to the view (by initializing the view with a corrupted
  // Appendable object); the fail transmission throws an IOException, and playGame catches it
  // and throws it as an IllegalStateException
  @Test(expected = IllegalStateException.class)
  public void testTrianglePlayGameTransmissionFail() {
    this.triangleControllerIn = new StringReader("q");

    this.triangleController = new MarbleSolitaireControllerImpl(
            this.triangleModel,
            new TriangleSolitaireTextView(this.triangleModel, new IOExceptionAppendable()),
            this.triangleControllerIn);

    this.triangleController.playGame();
  }

  // helper for tests to initialize a controller that has the given string as the
  // string reader's inputs and starts the game
  private void setTriangleInputs(String inputs) {
    this.triangleControllerIn = new StringReader(inputs);

    this.triangleController = new MarbleSolitaireControllerImpl(
            this.triangleModel,
            new TriangleSolitaireTextView(this.triangleModel, this.triangleControllerOut),
            this.triangleControllerIn);

    this.triangleController.playGame();
  }

  // to test that renderMessage receives the correct inputs when quiting immediately after calling
  // playGame
  @Test
  public void testPlayGameQuitImmediatelyRenderMessage() {
    setInputsMockView("q");

    // throughout playGame, it uses renderMessage to print out the score at the start of the loop,
    // the game quit message, a header to indicate the state of the game when quit (then uses
    // renderBoard to show the actual state), and then the final score at the end when quitting
    // immediately after running playGame; since this scenario uses renderMessage
    // 4 times, that's why there's 4 "message =".
    String renderMessageStr =
            "message = \nScore: 32\nmessage = Game quit!"
                    + "\nmessage = State of game when quit:\nmessage = \nScore: 32";
    assertEquals(renderMessageStr, this.log.toString());

    // renderMessage should not receive a game over message if there is a game quit
    assertFalse(this.log.toString()
            .contains("message = Game over!\n"));
  }

  // to test that renderMessage receives the correct inputs when making a valid move, then
  // an invalid move, specifically due to the from position being invalid for a move,
  // then quiting in playGame
  @Test
  public void testPlayGameInvalidFromMoveThenQuitRenderMessage() {
    // 4 2 4 4 is the valid move, 2 2 4 2 is the invalid move
    setInputsMockView("4 2 4 4 2 2 4 2 q");

    // invalid moves causes renderMessage to be called and the message specifies why the move was
    // invalid; in this test, the from slot was invalid
    assertTrue(this.log.toString()
            .contains("message = Invalid move. Play again. From slot does not have a marble!\n"));
  }

  // to test that renderMessage receives the correct inputs when making an invalid move,
  // specifically due to the to position being invalid for a move, then quiting in playGame
  @Test
  public void testPlayGameInvalidToMoveThenQuitRenderMessage() {
    setInputsMockView("4 1 4 3 q");

    // invalid moves causes renderMessage to be called and the message specifies why the move was
    // invalid; in this test, the to slot was invalid
    assertTrue(this.log.toString()
            .contains("message = Invalid move. Play again. To slot is not empty!\n"));
  }

  // to test that renderMessage receives the correct inputs when making an invalid move,
  // specifically due to the spacing between the from and to positions being invalid for a move,
  // then quiting in playGame
  @Test
  public void testPlayGameInvalidSpacingMoveThenQuitRenderMessage() {
    setInputsMockView("1 4 4 4 q");

    // invalid moves causes renderMessage to be called and the message specifies why the move was
    // invalid; in this test, the spacing between the from and to positions was invalid
    assertTrue(this.log.toString()
            .contains("message = Invalid move. Play again. "
                    + "The distance between these two slots is invalid for a move.\n"));
  }

  // to test that renderMessage receives the correct inputs when making a valid move, then
  // an invalid move, specifically due to the slot between the from and to positions being empty,
  // then quiting in playGame
  @Test
  public void testPlayGameInvalidBetweenMoveThenQuitRenderMessage() {
    // 6 4 4 4 is the valid move, 4 4 6 4 is the invalid move
    setInputsMockView("6 4 4 4 4 4 6 4 q");

    // invalid moves causes renderMessage to be called and the message specifies why the move was
    // invalid; in this test, the from slot was invalid
    assertTrue(this.log.toString()
            .contains("message = Invalid move. Play again. "
                    + "There is no marble in between these two slots!\n"));
  }

  // to test that renderMessage receives the correct inputs when a bad input, due to the input
  // being a negative number, is made followed by a quit when running playGame
  @Test
  public void testPlayGameBadInputNegNumThenQuitRenderMessage() {
    setInputsMockView("-2 q");

    // a bad input causes renderMessage to be called and the message indicates that the input was
    // bad and to re-enter
    assertTrue(this.log.toString().contains("message = Bad input, please re-enter value:\n"));
  }

  // to test that renderMessage receives the correct inputs when a bad input, due to the input
  // being a string/char other than 'q' or 'Q', is made followed by a quit when running playGame
  @Test
  public void testPlayGameBadInputInvalidStringThenQuitRenderMessage() {
    setInputsMockView("b q");

    // a bad input causes renderMessage to be called and the message indicates that the input was
    // bad and to re-enter
    assertTrue(this.log.toString().contains("message = Bad input, please re-enter value:\n"));
  }

  // to test that renderMessage receives the correct inputs when a game over occurs in playGame
  @Test
  public void testPlayGameGameOverRenderMessage() {
    // finishes out a game with all valid moves and no bad inputs
    setInputsMockView("2 4 4 4 5 4 3 4 5 6 5 4 3 6 5 6 5 7 5 5 3 7 5 7 4 2 4 4 6 3 4 3"
            + " 6 5 6 3 7 3 5 3 7 5 7 3 4 4 2 4 3 2 3 4 1 3 3 3 1 5 1 3 2 5 2 3"
            + " 3 4 3 2 1 3 3 3 5 4 5 6 5 7 5 5 5 2 5 4 5 5 5 3 3 2 3 4 3 5 3 3"
            + " 4 3 6 3 7 3 5 3");

    // ending the game by playing it through (not quitting) results in a game over message
    assertTrue(this.log.toString().contains("message = Game over!\n"));

    // if the game ends through a game over, there should never be a game quit message recieved
    assertFalse(this.log.toString()
            .contains("message = Game quit!\n"));

    // if all the moves made were valid and there were no bad inputs, a bad input message
    // and an invalid move message should not appear
    assertFalse(this.log.toString()
            .contains("message = Bad input, please re-enter value:\n"));
    assertFalse(this.log.toString()
            .contains("message = Invalid move. Play again. "));
  }

  // helper for tests to initialize a controller for testing the view inputs that has the given
  // string as the string reader's inputs and starts the game
  private void setInputsMockView(String inputs) {
    this.englishControllerIn = new StringReader(inputs);

    this.controllerUsingMockView
            = new MarbleSolitaireControllerImpl(this.englishModel,
            this.mockView, this.englishControllerIn);

    this.controllerUsingMockView.playGame();
  }

  // to test that move receives the correct inputs when making a valid move and quitting in playGame
  @Test
  public void testPlayGameMoveInputValidMove() {
    setInputsMockModel("4 6 4 4 q");

    // the inputs that the method move receives are the actual inputs - 1 as we let the board
    // indices to the user start at 1 to be more user friendly
    assertEquals("fromRow = 3, fromCol = 5, toRow = 3, toCol = 3", this.log.toString());
  }

  // to test that move receives the correct inputs when making an invalid move (without bad inputs)
  // and quitting in playGame
  @Test
  public void testPlayGameMoveInputInvalidMove() {
    setInputsMockModel("2 3 5 1 Q");

    // the inputs that the method move receives are the actual inputs - 1 as we let the board
    // indices to the user start at 1 to be more user friendly
    // additionally, move still receives these inputs as they are valid inputs, but just invalid to
    // cause a move
    assertEquals("fromRow = 1, fromCol = 2, toRow = 4, toCol = 0", this.log.toString());
  }

  // to test that move receives the correct inputs when inputting both good and bad inputs that
  // lead to a valid move and quitting in playGame
  @Test
  public void testPlayGameMoveInputValidMoveBadInputs() {
    setInputsMockModel("6 4 b -7 4 r 4 Q");

    // the inputs that the method move receives are the actual inputs - 1 as we let the board
    // indices to the user start at 1 to be more user friendly
    // additionally, the invalid inputs get thrown away/the user is asked to put in another input,
    // so move never receives them as inputs
    assertEquals("fromRow = 5, fromCol = 3, toRow = 3, toCol = 3", this.log.toString());
  }

  // to test the input move receives when quitting in the middle of a move in playGame
  @Test
  public void testPlayGameMoveInputQuitMidMove() {
    setInputsMockModel("4 2 q");

    // move never gets called/never receives anything because quit was called after only
    // 2 good inputs were made
    assertEquals("", this.log.toString());
  }

  // to test the input move recieves when making 4 bad inputs and quitting in playGame
  @Test
  public void testPlayGameMoveInputBadInputsThenQuitMove() {
    setInputsMockModel("-4 t b 0 q");

    // move never gets called/never receives anything because no good inputs were made and then the
    // game quit
    assertEquals("", this.log.toString());
  }

  // helper for tests to initialize a controller for testing the model inputs that has the given
  // string as the string reader's inputs and starts the game
  private void setInputsMockModel(String inputs) {
    this.englishControllerIn = new StringReader(inputs);

    this.controllerUsingMockModel
            = new MarbleSolitaireControllerImpl(mockModel,
            new MarbleSolitaireTextView(mockModel,
                    this.englishControllerOut), this.englishControllerIn);

    this.controllerUsingMockModel.playGame();
  }
}