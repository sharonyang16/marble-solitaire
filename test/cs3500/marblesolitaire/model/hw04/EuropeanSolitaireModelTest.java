package cs3500.marblesolitaire.model.hw04;

import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the EuropeanSolitaireModel class.
 */
public class EuropeanSolitaireModelTest {
  private MarbleSolitaireModel esm1;
  private MarbleSolitaireModel esm2;
  private MarbleSolitaireModel esm3;
  private MarbleSolitaireModel esm4;

  // the initial conditions for the example TriangleSolitaireModel objects
  @Before
  public void init() {
    esm1 = new EuropeanSolitaireModel();
    esm2 = new EuropeanSolitaireModel(7);
    esm3 = new EuropeanSolitaireModel(1, 1);
    esm4 = new EuropeanSolitaireModel(5, 6, 5);
  }

  // to test creating valid EuropeanSolitaireModel objects (doesn't throw an exception)
  @Test
  public void testCreateValidEuropeanSolitaireModel() {
    esm1 = new EuropeanSolitaireModel();
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm1.getSlotAt(3, 3));
    assertEquals(7, esm1.getBoardSize());
    assertEquals(36, esm1.getScore());

    esm2 = new EuropeanSolitaireModel(7);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm2.getSlotAt(9, 9));
    assertEquals(19, esm2.getBoardSize());
    assertEquals(276, esm2.getScore());

    esm3 = new EuropeanSolitaireModel(1, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm3.getSlotAt(1, 1));
    assertEquals(7, esm3.getBoardSize());
    assertEquals(36, esm3.getScore());

    esm4 = new EuropeanSolitaireModel(5, 6, 5);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm4.getSlotAt(6, 5));
    assertEquals(13, esm4.getBoardSize());
    assertEquals(128, esm4.getScore());
  }

  // to test creating an EuropeanSolitaireModel object that throws an exception due
  // to the given empty cell position being in an invalid slot
  @Test(expected = IllegalArgumentException.class)
  public void testCreateEuropeanSolitaireModelInvalidPosition() {
    new EuropeanSolitaireModel(0, 5);
  }

  // to test creating an EuropeanSolitaireModel object that throws an exception due
  // to the given given side length being negative
  @Test(expected = IllegalArgumentException.class)
  public void testCreateEuropeanSolitaireModelNegSideLength() {
    new EuropeanSolitaireModel(-5);
  }

  // to test creating an EuropeanSolitaireModel object that throws an exception due
  // to the given given side length being even
  @Test(expected = IllegalArgumentException.class)
  public void testCreateEuropeanSolitaireModelEvenSideLength() {
    new EuropeanSolitaireModel(6);
  }

  // to test creating an EuropeanSolitaireModel object using the constructor with 3 arguments
  // that throws an exception due to the given empty cell position being in an invalid
  @Test(expected = IllegalArgumentException.class)
  public void testCreateEuropeanSolitaireModelThreeArgInvalidPosition() {
    new EuropeanSolitaireModel(5, 12, 9);
  }

  // to test creating an EuropeanSolitaireModel object using the constructor with 3 arguments
  // that throws an exception due to the given side length being negative
  @Test(expected = IllegalArgumentException.class)
  public void testCreateEuropeanSolitaireModelThreeArgNegSideLength() {
    new EuropeanSolitaireModel(-7, 5, 6);
  }

  // to test creating an EuropeanSolitaireModel object using the constructor with 3 arguments
  // that throws an exception due to the given side length being even
  @Test(expected = IllegalArgumentException.class)
  public void testCreateEuropeanSolitaireModelThreeArgEvenSideLength() {
    new EuropeanSolitaireModel(6, 4, 4);
  }

  // to test the method move for valid inputs
  @Test
  public void testMove() {
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm3.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm3.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm3.getSlotAt(1, 1));
    assertEquals(36, esm3.getScore());
    // move up 2
    esm3.move(3, 1, 1, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm3.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm3.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm3.getSlotAt(1, 1));
    assertEquals(35, esm3.getScore());

    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm3.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm3.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm3.getSlotAt(2, 1));
    assertEquals(35, esm3.getScore());
    // move left 2
    esm3.move(2, 3, 2, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm3.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm3.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm3.getSlotAt(2, 1));
    assertEquals(34, esm3.getScore());

    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm3.getSlotAt(0, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm3.getSlotAt(1, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm3.getSlotAt(2, 2));
    assertEquals(34, esm3.getScore());
    // move down 2
    esm3.move(0, 2, 2, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm3.getSlotAt(0, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm3.getSlotAt(1, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm3.getSlotAt(2, 2));
    assertEquals(33, esm3.getScore());

    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm3.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm3.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm3.getSlotAt(2, 3));
    assertEquals(33, esm3.getScore());
    // move right 2
    esm3.move(2, 1, 2, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm3.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm3.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm3.getSlotAt(2, 3));
    assertEquals(32, esm3.getScore());
  }

  // to test the method move for when there's an exception thrown due to the from position
  // not having a marble/being an empty slot
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidFromEmptySlot() {
    esm3.move(1, 1, 1, 3);
  }

  // to test the method move for when there's an exception thrown due to the from position being
  // an invalid slot
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidFromInvalidSlot() {
    esm3.move(3, 1, 1, 1); // valid move
    esm3.move(0, 1, 2, 1); // invalid move
  }

  // to test the method move for when there's an exception thrown due to the to position
  // having a marble
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidToMarbleSlot() {
    esm3.move(0, 2, 0, 4);
  }

  // to test the method move for when there's an exception thrown due to the to position
  // being invalid
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidToInvalidSlot() {
    esm3.move(0, 3, 0, 5);
  }

  // to test the method move for when an exception is thrown due to the spacing between the from
  // and to position not being two positions away
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidThreeSpaces() {
    esm3.move(4, 1, 1, 1);
  }

  // to test the method move for when there's an exception thrown due to the two and from positions
  // being 2 spaces away from each other both horizontally and vertically (2 spaces away diagonally)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidDiagonally() {
    esm1.move(3, 3, 1, 1);
  }

  // to test the method move for when there's an exception thrown due to the slot in between the
  // from and to position not having a marble
  @Test(expected = IllegalArgumentException.class)
  public void testMoveNoMarbleBetween() {
    esm3.move(1, 3, 1, 1); // valid move
    esm3.move(1, 1, 1, 3); // invalid move
  }

  @Test
  public void testIsGameOver() {
    assertEquals(false, esm3.isGameOver());
    esm3.move(3, 1, 1, 1);
    esm3.move(2, 3, 2, 1);
    esm3.move(0, 2, 2, 2);
    esm3.move(1, 1, 3, 1);
    esm3.move(0, 4, 0, 2);
    esm3.move(1, 4, 1, 2);
    esm3.move(2, 5, 2, 3);
    esm3.move(2, 2, 2, 4);
    esm3.move(0, 2, 2, 2);
    esm3.move(4, 1, 2, 1);
    esm3.move(3, 3, 3, 1);
    esm3.move(4, 3, 4, 1);
    esm3.move(2, 1, 2, 3);
    esm3.move(4, 1, 2, 1);
    esm3.move(2, 0, 2, 2);
    esm3.move(4, 0, 2, 0);
    esm3.move(2, 3, 2, 1);
    esm3.move(2, 0, 2, 2);
    esm3.move(6, 2, 4, 2);
    esm3.move(6, 3, 4, 3);
    esm3.move(4, 3, 4, 1);
    esm3.move(5, 1, 3, 1);
    esm3.move(5, 5, 5, 3);
    esm3.move(4, 5, 4, 3);
    esm3.move(3, 5, 3, 3);
    esm3.move(4, 3, 6, 3);
    assertEquals(false, esm3.isGameOver());
    esm3.move(6, 4, 6, 2);
    assertEquals(true, esm3.isGameOver());
    assertEquals(9, esm3.getScore());
  }

  // to test the method getBoardSize
  @Test
  public void testGetBoardSize() {
    assertEquals(7, esm1.getBoardSize());
    assertEquals(19, esm2.getBoardSize());
    assertEquals(7, esm3.getBoardSize());
    assertEquals(13, esm4.getBoardSize());
  }

  // to test the method getSlotAt for valid inputs
  @Test
  public void testGetSlotAt() {
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, esm3.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm3.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm3.getSlotAt(1, 2));
  }

  // to test the method getSlotAt for when an exception is thrown due to the given row being
  // greater than the board size
  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtRowGreaterThanBoardSize() {
    esm2.getSlotAt(21, 4);
  }

  // to test the method getSlotAt for when an exception is thrown due to the given column being
  // greater than the board size
  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtColGreaterThanBoardSize() {
    esm4.getSlotAt(3, 13);
  }

  // to test the method getSlotAt for when an exception is thrown due to the given row being
  // less than zero
  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtNegRow() {
    esm2.getSlotAt(-6, 0);
  }

  // to test the method getSlotAt for when an exception is thrown due to the given column being
  // less than zero
  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtNegCol() {
    esm1.getSlotAt(4, -5);
  }

  // to test the method getScore
  @Test
  public void testGetScore() {
    assertEquals(36, esm1.getScore());
    assertEquals(276, esm2.getScore());
    assertEquals(36, esm3.getScore());
    assertEquals(128, esm4.getScore());

    esm3.move(3,1, 1, 1);
    assertEquals(35, esm3.getScore());
  }
}