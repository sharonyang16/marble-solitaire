package cs3500.marblesolitaire.model.hw04;

import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the TriangleSolitaireModel class.
 */
public class TriangleSolitaireModelTest {
  private MarbleSolitaireModel tsm1;
  private MarbleSolitaireModel tsm2;
  private MarbleSolitaireModel tsm3;
  private MarbleSolitaireModel tsm4;

  // the initial conditions for the example TriangleSolitaireModel objects
  @Before
  public void init() {
    tsm1 = new TriangleSolitaireModel();
    tsm2 = new TriangleSolitaireModel(8);
    tsm3 = new TriangleSolitaireModel(2, 2);
    tsm4 = new TriangleSolitaireModel(6, 4, 2);
  }

  // to test creating valid TriangleSolitaireModel objects (doesn't throw an exception)
  @Test
  public void testCreateValidTriangleSolitaireModel() {
    tsm1 = new TriangleSolitaireModel();
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm1.getSlotAt(0, 0));
    assertEquals(5, tsm1.getBoardSize());
    assertEquals(14, tsm1.getScore());

    tsm2 = new TriangleSolitaireModel(8);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm2.getSlotAt(0, 0));
    assertEquals(8, tsm2.getBoardSize());
    assertEquals(35, tsm2.getScore());

    tsm3 = new TriangleSolitaireModel(2, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm3.getSlotAt(2, 2));
    assertEquals(5, tsm3.getBoardSize());
    assertEquals(14, tsm3.getScore());

    tsm4 = new TriangleSolitaireModel(6, 4, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm4.getSlotAt(4, 2));
    assertEquals(6, tsm4.getBoardSize());
    assertEquals(20, tsm4.getScore());
  }

  // to test creating an TriangleSolitaireModel object that throws an exception due to
  // the given dimension being zero
  @Test(expected = IllegalArgumentException.class)
  public void testCreateTriangleSolitaireModelZeroDimension() {
    new TriangleSolitaireModel(0);
  }

  // to test creating an TriangleSolitaireModel object that throws an exception due to
  // the given dimension being negative
  @Test(expected = IllegalArgumentException.class)
  public void testCreateTriangleSolitaireModelNegDimension() {
    new TriangleSolitaireModel(-5);
  }

  // to test creating an TriangleSolitaireModel object that throws an exception due to
  // the given empty position being an invalid slot
  @Test(expected = IllegalArgumentException.class)
  public void testCreateTriangleSolitaireModelInvalidPosition() {
    new TriangleSolitaireModel(2, 4);
  }

  // to test creating an TriangleSolitaireModel object using the constructor with 3 arguments
  // that throws an exception due to the given dimension being negative
  @Test(expected = IllegalArgumentException.class)
  public void testCreateTriangleSolitaireModelThreeArgNegDimension() {
    new TriangleSolitaireModel(-8, 6, 2);
  }

  // to test creating an TriangleSolitaireModel object using the constructor with 3 arguments
  // that throws an exception due to the given position being an invalid slot
  @Test(expected = IllegalArgumentException.class)
  public void testCreateTriangleSolitaireModelThreeArgInvalidPosition() {
    new TriangleSolitaireModel(7, 4, 5);
  }

  // to test the method move for valid inputs
  @Test
  public void testMove() {
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, tsm1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, tsm1.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm1.getSlotAt(0, 0));
    assertEquals(14, tsm1.getScore());
    // move diagonally up left 2
    tsm1.move(2, 2, 0, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm1.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, tsm1.getSlotAt(0, 0));
    assertEquals(13, tsm1.getScore());

    assertEquals(MarbleSolitaireModelState.SlotState.Marble, tsm1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, tsm1.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm1.getSlotAt(1, 1));
    assertEquals(13, tsm1.getScore());
    // move up/diagonally up right 2
    tsm1.move(3, 1, 1, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm1.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, tsm1.getSlotAt(1, 1));
    assertEquals(12, tsm1.getScore());

    assertEquals(MarbleSolitaireModelState.SlotState.Marble, tsm1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, tsm1.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm1.getSlotAt(3, 1));
    assertEquals(12, tsm1.getScore());
    // move left 2
    tsm1.move(3, 3, 3, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm1.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, tsm1.getSlotAt(3, 1));
    assertEquals(11, tsm1.getScore());

    assertEquals(MarbleSolitaireModelState.SlotState.Marble, tsm1.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, tsm1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm1.getSlotAt(3, 2));
    assertEquals(11, tsm1.getScore());
    // move right 2
    tsm1.move(3, 0, 3, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm1.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, tsm1.getSlotAt(3, 2));
    assertEquals(10, tsm1.getScore());

    assertEquals(MarbleSolitaireModelState.SlotState.Marble, tsm1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, tsm1.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm1.getSlotAt(2, 2));
    assertEquals(10, tsm1.getScore());
    // move diagonally down right 2
    tsm1.move(0, 0, 2, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm1.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, tsm1.getSlotAt(2, 2));
    assertEquals(9, tsm1.getScore());

    // using tsm4 now
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, tsm4.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, tsm4.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm4.getSlotAt(4, 2));
    assertEquals(20, tsm4.getScore());
    // move down/diagonally down left 2
    tsm4.move(2, 2, 4, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm4.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm4.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, tsm4.getSlotAt(4, 2));
    assertEquals(19, tsm4.getScore());
  }

  // to test the method move for when there's an exception thrown due to the from position
  // being an empty slot
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidFromEmptySlot() {
    // this move is also invalid because the to position isn't empty, but move catches
    // the from position not having a marble first
    tsm1.move(0, 0, 2, 2);
  }

  // to test the method move for when there's an exception thrown due to the from position
  // being an invalid slot
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidFromInvalidSlot() {
    tsm1.move(0, 2, 0, 0);
  }

  // to test the method move for when there's an exception thrown due to the to position
  // having a marble
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidToMarbleSlot() {
    tsm1.move(1, 1,3, 1);
  }

  // to test the method move for when there's an exception thrown due to the to position
  // being an invalid slot
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidToInvalidSlot() {
    tsm1.move(2, 1,2, 3);
  }

  // to test the method move for when an exception is thrown due to the spacing between the from
  // and to position not being exactly 2 spaces apart (in this test it's three)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidThreeSpacesAway() {
    tsm1.move(3, 0, 0, 0);
  }

  // to test the method move for when an exception is thrown due to attempting to moving diagonally
  // up right or diagonally down left (moves diagonally up left and down right are valid)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidDiagonally() {
    tsm3.move(4, 0, 2, 2);
  }

  // to test the method move for when there's an exception thrown due to the slot in between the
  // from and to position not having a marble
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidBetweenSlotEmpty() {
    tsm1.move(2, 0, 0, 0); // valid move
    tsm1.move(0, 0, 2, 0); // invalid move
  }

  // to test the method isGameOver
  @Test
  public void testIsGameOver() {
    assertEquals(false, tsm1.isGameOver());
    tsm1.move(2, 2, 0, 0);
    tsm1.move(2, 0, 2, 2);
    tsm1.move(0, 0, 2, 0);
    tsm1.move(4, 3, 2, 1);
    tsm1.move(4, 1, 4, 3);
    tsm1.move(4, 4, 4, 2);
    tsm1.move(3, 0, 3, 2);
    tsm1.move(3, 3, 3, 1);
    tsm1.move(2, 1, 4, 1);
    assertEquals(false, tsm1.isGameOver());
    tsm1.move(4, 1, 4, 3);
    assertEquals(true, tsm1.isGameOver());
    assertEquals(4, tsm1.getScore());
  }

  // to test the method getBoardSize
  @Test
  public void testGetBoardSize() {
    assertEquals(5, tsm1.getBoardSize());
    assertEquals(8, tsm2.getBoardSize());
    assertEquals(5, tsm3.getBoardSize());
    assertEquals(6, tsm4.getBoardSize());
  }

  // to test the method getSlotAt for valid inputs
  @Test
  public void testGetSlotAt() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, tsm1.getSlotAt(0, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, tsm1.getSlotAt(1, 0));
  }

  // to test the method getSlotAt for when an exception is thrown due to the given row being
  // greater than the board size
  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtRowGreaterThanBoardSize() {
    tsm1.getSlotAt(6, 3);
  }

  // to test the method getSlotAt for when an exception is thrown due to the given column being
  // greater than the board size
  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtColGreaterThanBoardSize() {
    tsm1.getSlotAt(3, 6);
  }

  // to test the method getSlotAt for when an exception is thrown due to the given row being
  // less than zero
  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtNegRow() {
    tsm1.getSlotAt(-3, 4);
  }

  // to test the method getSlotAt for when an exception is thrown due to the given column being
  // less than zero
  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtNegCol() {
    tsm3.getSlotAt(1, -6);
  }

  // to test the method getScore
  @Test
  public void testGetScore() {
    assertEquals(14, tsm1.getScore());
    assertEquals(35, tsm2.getScore());
    assertEquals(14, tsm3.getScore());
    assertEquals(20, tsm4.getScore());

    tsm1.move(2, 2, 0, 0);
    assertEquals(13, tsm1.getScore());
  }
}