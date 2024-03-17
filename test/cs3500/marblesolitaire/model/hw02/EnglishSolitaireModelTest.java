package cs3500.marblesolitaire.model.hw02;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the EnglishSolitaireModel class.
 */
public class EnglishSolitaireModelTest {
  private MarbleSolitaireModel esm1;
  private MarbleSolitaireModel esm2;
  private MarbleSolitaireModel esm3;
  private MarbleSolitaireModel esm4;

  // initial condition for the example EnglishSolitaireModels
  @Before
  public void init() {
    esm1 = new EnglishSolitaireModel();
    esm2 = new EnglishSolitaireModel(1, 2);
    esm3 = new EnglishSolitaireModel(7);
    esm4 = new EnglishSolitaireModel(5, 8, 7);
  }

  // to test creating an EnglishSolitaireModel object with no errors
  @Test
  public void testCreateValidEnglishSolitaireModel() {
    esm1 = new EnglishSolitaireModel();
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm1.getSlotAt(3, 3));
    assertEquals(7, esm1.getBoardSize());
    assertEquals(32, esm1.getScore());

    esm2 = new EnglishSolitaireModel(1, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm2.getSlotAt(1, 2));
    assertEquals(7, esm2.getBoardSize());
    assertEquals(32, esm2.getScore());

    esm3 = new EnglishSolitaireModel(7);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm3.getSlotAt(9, 9));
    assertEquals(19, esm3.getBoardSize());
    assertEquals(216, esm3.getScore());

    esm4 = new EnglishSolitaireModel(5, 8, 7);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm4.getSlotAt(8, 7));
    assertEquals(13, esm4.getBoardSize());
    assertEquals(104, esm4.getScore());
  }

  // to test creating an EnglishSolitaireModel object that throws an exception due
  // to the given empty cell position being in an invalid slot
  @Test(expected = IllegalArgumentException.class)
  public void testCreateEnglishSolitaireModelInvalidPosition() {
    new EnglishSolitaireModel(1, 5);
  }

  // to test creating an EnglishSolitaireModel object that throws an exception due
  // to the given arm thickness being negative
  @Test(expected = IllegalArgumentException.class)
  public void testCreateEnglishSolitaireModelNegArmThickness() {
    new EnglishSolitaireModel(-3);
  }

  // to test creating an EnglishSolitaireModel object that throws an exception due
  // to the given arm thickness being an even integer
  @Test(expected = IllegalArgumentException.class)
  public void testCreateEnglishSolitaireModelEvenArmThickness() {
    new EnglishSolitaireModel(6);
  }

  // to test creating an EnglishSolitaireModel object using the constructor with 3 arguments
  // that throws an exception due to the given empty cell position being in an invalid slot
  @Test(expected = IllegalArgumentException.class)
  public void testCreateEnglishSolitaireModelThreeArgInvalidPosition() {
    new EnglishSolitaireModel(9, 0, 3);
  }

  // to test creating an EnglishSolitaireModel object using the constructor with 3 arguments
  // that throws an exception due to the given arm thickness being a negative even integer
  @Test(expected = IllegalArgumentException.class)
  public void testCreateEnglishSolitaireModelThreeArgInvalidArmThickness() {
    new EnglishSolitaireModel(-4, 4, 5);
  }

  // to test the method move for valid inputs
  @Test
  public void testMove() {
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm1.getSlotAt(1, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm1.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm1.getSlotAt(3, 3));
    assertEquals(32, esm1.getScore());

    // move down 2
    esm1.move(1, 3, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm1.getSlotAt(1, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm1.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm1.getSlotAt(3, 3));
    assertEquals(31, esm1.getScore());


    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm1.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm1.getSlotAt(2, 3));
    assertEquals(31, esm1.getScore());

    // move right 2
    esm1.move(2, 1, 2, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm1.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm1.getSlotAt(2, 3));
    assertEquals(30, esm1.getScore());

    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm1.getSlotAt(2, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm1.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm1.getSlotAt(2, 2));
    assertEquals(30, esm1.getScore());

    // move left 2
    esm1.move(2, 4, 2, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm1.getSlotAt(2, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm1.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm1.getSlotAt(2, 2));
    assertEquals(29, esm1.getScore());

    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm1.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm1.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm1.getSlotAt(2, 4));
    assertEquals(29, esm1.getScore());

    // move up 2
    esm1.move(4, 4, 2, 4);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm1.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm1.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm1.getSlotAt(2, 4));
    assertEquals(28, esm1.getScore());
  }

  // to test the method move for when there's an exception thrown due to the from position
  // not having a marble
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidFromNoMarble() {
    esm2.move(3, 2, 1, 2); // valid move
    esm2.move(3, 2, 1, 2); // invalid move
  }

  // to test the method move for when there's an exception thrown due to the from position being
  // an invalid slot
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidFromInvalidSlot() {
    esm2.move(1, 3, 3, 3); // valid move
    esm2.move(1, 5, 1, 3); // invalid move
  }

  // to test the method move for when there's an exception thrown due to the to position
  // having a marble
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidToNotEmpty() {
    esm2.move(3, 0, 3, 2);
  }

  // to test the method move for when there's an exception thrown due to the to position
  // being invalid
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidToInvalidSlot() {
    esm1.move(6, 2, 4, 2);
  }

  // to test the method move for when an exception is thrown due to
  // the spacing between the from and to position being invalid/not two positions away
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidSpacing() {
    esm2.move(4, 2, 3, 2);
  }

  // to test the method move for when there's an exception thrown due to the slot in between the
  // from and to position not having a marble
  @Test(expected = IllegalArgumentException.class)
  public void testMoveNoMarbleBetween() {
    esm2.move(3, 2, 1, 2); // valid move
    esm2.move(1, 2, 3, 2); // invalid move
  }

  // to test the method move for when there's an exception thrown due to the two and from positions
  // being 2 spaces away from each other both horizontally and vertically (2 spaces away diagonally)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveTwoSpacesDiagonally() {
    esm1.move(3, 1, 3, 3); // valid move
    esm1.move(1, 3, 3, 1); // invalid move
  }

  // to test the method isGameOver
  @Test
  public void testIsGameOver() {
    assertEquals(false, esm1.isGameOver());
    esm1.move(1,3, 3, 3);
    esm1.move(4,3, 2, 3);
    esm1.move(4,5, 4, 3);
    esm1.move(2,5, 4, 5);
    esm1.move(4,6, 4, 4);
    assertEquals(false, esm1.isGameOver());
    esm1.move(2,6, 4, 6);
    esm1.move(3,1, 3, 3);
    esm1.move(5,2, 3, 2);
    esm1.move(5,4, 5, 2);
    esm1.move(6,2, 4, 2);
    esm1.move(6,4, 6, 2);
    esm1.move(3,3, 1, 3);
    esm1.move(2,1, 2, 3);
    esm1.move(0,2, 2, 2);
    esm1.move(0,4, 0, 2);
    assertEquals(false, esm1.isGameOver());
    esm1.move(1,4, 1, 2);
    esm1.move(2,3, 2, 1);
    esm1.move(0,2, 2, 2);
    esm1.move(4,3, 4, 5);
    esm1.move(4,6, 4, 4);
    esm1.move(4,1, 4, 3);
    esm1.move(4,4, 4, 2);
    esm1.move(2,1, 2, 3);
    esm1.move(2,4, 2, 2);
    esm1.move(3,2, 5, 2);
    assertEquals(false, esm1.isGameOver());
    esm1.move(6,2, 4, 2);
    assertEquals(true, esm1.isGameOver());
    assertEquals(6, esm1.getScore());

    esm1 = new EnglishSolitaireModel();
    assertEquals(false, esm1.isGameOver());

  }

  // to test the method getBoardSize
  @Test
  public void testGetBoardSize() {
    assertEquals(7, esm1.getBoardSize());
    assertEquals(7, esm2.getBoardSize());
    assertEquals(19, esm3.getBoardSize());
    assertEquals(13, esm4.getBoardSize());
  }

  // to test the method getSlotAt for valid inputs
  @Test
  public void testGetSlotAt() {
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, esm1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm1.getSlotAt(0, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm1.getSlotAt(3, 3));
  }

  // to test the method getSlotAt for when an exception is thrown due to the given row being
  // greater than the board size
  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtRowGreaterThanBoardSize() {
    esm2.getSlotAt(7, 2);
  }

  // to test the method getSlotAt for when an exception is thrown due to the given column being
  // greater than the board size
  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtColGreaterThanBoardSize() {
    esm4.getSlotAt(5, 13);
  }

  // to test the method getSlotAt for when an exception is thrown due to the given row being
  // less than zero
  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtNegRow() {
    esm1.getSlotAt(-4, 5);
  }

  // to test the method getSlotAt for when an exception is thrown due to the given column being
  // less than zero
  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtNegCol() {
    esm3.getSlotAt(2, -3);
  }

  // to test the method getScore
  @Test
  public void testGetScore() {
    assertEquals(32, esm1.getScore());
    assertEquals(32, esm2.getScore());
    assertEquals(216, esm3.getScore());
    assertEquals(104, esm4.getScore());

    esm1.move(5,3, 3, 3);
    assertEquals(31, esm1.getScore());

    esm1.move(4,1, 4, 3);
    assertEquals(30, esm1.getScore());
  }
}
