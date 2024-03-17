package cs3500.marblesolitaire.view;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A JUnit test class for the MarbleSolitaireTextView class.
 */
public class MarbleSolitaireTextViewTest {
  private MarbleSolitaireView mstv1;
  private MarbleSolitaireView mstv2;
  private MarbleSolitaireView mstv3;
  private MarbleSolitaireView mstv4;
  private MarbleSolitaireView mstv5;
  private MarbleSolitaireView mstv6;
  private MarbleSolitaireView mstv7;
  private MarbleSolitaireView mstv8;

  // initial condition for the example MarbleSolitaireTextView objects
  @Before
  public void init() {
    mstv1 = new MarbleSolitaireTextView(new EnglishSolitaireModel());
    mstv2 = new MarbleSolitaireTextView(new EnglishSolitaireModel(1, 2));
    mstv3 = new MarbleSolitaireTextView(new EnglishSolitaireModel(5, 8, 7));
    mstv4 = new MarbleSolitaireTextView(new EnglishSolitaireModel(), new StringBuilder());
    mstv5 = new MarbleSolitaireTextView(new EnglishSolitaireModel(5, 8, 7),
            new IOExceptionAppendable());
    mstv6 = new MarbleSolitaireTextView(new EuropeanSolitaireModel());
    mstv7 = new MarbleSolitaireTextView(new EuropeanSolitaireModel(5,3, 6),
            new IOExceptionAppendable());
  }

  // to test creating valid MarbleSolitaireTextView objects
  @Test
  public void testCreateValidMarbleSolitaireTextView() {
    mstv1 = new MarbleSolitaireTextView(new EnglishSolitaireModel());
    mstv4 = new MarbleSolitaireTextView(new EnglishSolitaireModel(), new StringBuilder());
    mstv3 = new MarbleSolitaireTextView(new EnglishSolitaireModel(5, 8, 7));
    mstv5 = new MarbleSolitaireTextView(new EnglishSolitaireModel(5, 8, 7),
            new StringBuffer());
    mstv6 = new MarbleSolitaireTextView(new EuropeanSolitaireModel());
    String mstv6ExpectedToString =
            "    O O O\n" + "  O O O O O\n"
                    + "O O O O O O O\n" + "O O O _ O O O\n"
                    + "O O O O O O O\n" + "  O O O O O\n" + "    O O O";
    assertEquals(mstv6ExpectedToString, mstv6.toString());
    mstv7 = new MarbleSolitaireTextView(new EuropeanSolitaireModel(5, 3, 6),
            new IOExceptionAppendable());
    String mstv7ExpectedToString =
            "        O O O O O\n" + "      O O O O O O O\n"
                    + "    O O O O O O O O O\n" + "  O O O O O _ O O O O O\n"
                    + "O O O O O O O O O O O O O\n" + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n" + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n" + "  O O O O O O O O O O O\n"
                    + "    O O O O O O O O O\n" + "      O O O O O O O\n" + "        O O O O O";
    assertEquals(mstv7ExpectedToString, mstv7.toString());


    String defaultMSTVExpected =
            "    O O O\n" + "    O O O\n"
                    + "O O O O O O O\n" + "O O O _ O O O\n"
                    + "O O O O O O O\n" + "    O O O\n" + "    O O O";

    // even though they aren't the same due to having different Appendable objects,
    // they have the same toString as their model states are the same
    assertEquals(defaultMSTVExpected, mstv1.toString());
    assertEquals(defaultMSTVExpected, mstv4.toString());

    String fiveArmEmptyEightSevenExpected =
            "        O O O O O\n" + "        O O O O O\n"
                    + "        O O O O O\n" + "        O O O O O\n"
                    + "O O O O O O O O O O O O O\n" + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n" + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O _ O O O O O\n" + "        O O O O O\n" + "        O O O O O\n"
                    + "        O O O O O\n" + "        O O O O O";

    // even though they aren't the same due to having different Appendable objects,
    // they have the same toString as their model states are the same
    assertEquals(fiveArmEmptyEightSevenExpected, mstv3.toString());
    assertEquals(fiveArmEmptyEightSevenExpected, mstv5.toString());

  }

  // to test creating a MarbleSolitaireTextView object with the constructor that takes in one
  // parameter that throws an exception due to the given MarbleSolitaireModelState being null
  @Test(expected = IllegalArgumentException.class)
  public void testCreateMarbleSolitaireTextViewOneParamNullModel() {
    new MarbleSolitaireTextView(null);
  }

  // to test creating a MarbleSolitaireTextView object with the constructor that takes in two
  // parameters that throws an exception due to the given MarbleSolitaireModelState being null
  @Test(expected = IllegalArgumentException.class)
  public void testCreateMarbleSolitaireTextViewTwoParamNullModel() {
    new MarbleSolitaireTextView(null, new StringBuilder());
  }

  // to test creating a MarbleSolitaireTextView object with the constructor that takes in two
  // parameters that throws an exception due to the given Appendable object being null
  @Test(expected = IllegalArgumentException.class)
  public void testCreateMarbleSolitaireTextViewTwoParamNullAppendable() {
    new MarbleSolitaireTextView(new EnglishSolitaireModel(), null);
  }

  // a second test to test that creating a MarbleSolitaireTextView object with the constructor
  // that takes in two parameters throws an exception due to the given Appendable object being null
  @Test(expected = IllegalArgumentException.class)
  public void testCreateMarbleSolitaireTextViewTwoParamNullAppendable2() {
    new MarbleSolitaireTextView(new EuropeanSolitaireModel(), null);
  }

  // to test creating a MarbleSolitaireTextView object with the constructor that takes in two
  // parameters that throws an exception due to both the given MarbleSolitaireModelState and
  // Appendable object being null
  @Test(expected = IllegalArgumentException.class)
  public void testCreateMarbleSolitaireTextViewTwoParamBothNull() {
    new MarbleSolitaireTextView(null, null);
  }

  // to test the method toString
  @Test
  public void testToString() {
    String mstv1Expected =
            "    O O O\n" + "    O O O\n"
                    + "O O O O O O O\n" + "O O O _ O O O\n"
                    + "O O O O O O O\n" + "    O O O\n" + "    O O O";
    assertEquals(mstv1Expected, mstv1.toString());

    String mstv2Expected =
            "    O O O\n" + "    _ O O\n"
                    + "O O O O O O O\n" + "O O O O O O O\n"
                    + "O O O O O O O\n" + "    O O O\n" + "    O O O";
    assertEquals(mstv2Expected, mstv2.toString());

    String mstv3Expected =
            "        O O O O O\n" + "        O O O O O\n"
                    + "        O O O O O\n" + "        O O O O O\n"
                    + "O O O O O O O O O O O O O\n" + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n" + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O _ O O O O O\n" + "        O O O O O\n" + "        O O O O O\n"
                    + "        O O O O O\n" + "        O O O O O";
    assertEquals(mstv3Expected, mstv3.toString());

    MarbleSolitaireModel modelMoveOne = new EnglishSolitaireModel();
    modelMoveOne.move(1, 3, 3, 3);

    mstv1 = new MarbleSolitaireTextView(modelMoveOne);

    mstv1Expected =
            "    O O O\n" + "    O _ O\n"
                    + "O O O _ O O O\n" + "O O O O O O O\n"
                    + "O O O O O O O\n" + "    O O O\n" + "    O O O";

    assertEquals(mstv1Expected, mstv1.toString());

    String mstv6Expected =
            "    O O O\n" + "  O O O O O\n"
                    + "O O O O O O O\n" + "O O O _ O O O\n"
                    + "O O O O O O O\n" + "  O O O O O\n" + "    O O O";
    assertEquals(mstv6Expected, mstv6.toString());

    String mstv7Expected =
            "        O O O O O\n" + "      O O O O O O O\n"
                    + "    O O O O O O O O O\n" + "  O O O O O _ O O O O O\n"
                    + "O O O O O O O O O O O O O\n" + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n" + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n" + "  O O O O O O O O O O O\n"
                    + "    O O O O O O O O O\n" + "      O O O O O O O\n" + "        O O O O O";
    assertEquals(mstv7Expected, mstv7.toString());

    EuropeanSolitaireModel model = new EuropeanSolitaireModel();
    model.move(3, 1, 3, 3);
    mstv6 = new MarbleSolitaireTextView(model);

    mstv6Expected =
            "    O O O\n" + "  O O O O O\n"
                    + "O O O O O O O\n" + "O _ _ O O O O\n"
                    + "O O O O O O O\n" + "  O O O O O\n" + "    O O O";
    assertEquals(mstv6Expected, mstv6.toString());
  }

  // to test the method renderBoard
  @Test
  public void testRenderBoard() {
    StringBuffer out = new StringBuffer();
    mstv4 = new MarbleSolitaireTextView(new EnglishSolitaireModel(), out);

    StringBuffer outEuropean = new StringBuffer();
    mstv6 = new MarbleSolitaireTextView(new EuropeanSolitaireModel(), outEuropean);
    try {
      String expectedRender =
              "    O O O\n" + "    O O O\n"
                      + "O O O O O O O\n" + "O O O _ O O O\n"
                      + "O O O O O O O\n" + "    O O O\n" + "    O O O";
      mstv4.renderBoard();
      assertEquals(expectedRender, out.toString());

      expectedRender =
              "    O O O\n" + "  O O O O O\n"
                      + "O O O O O O O\n" + "O O O _ O O O\n"
                      + "O O O O O O O\n" + "  O O O O O\n" + "    O O O";
      mstv6.renderBoard();
      assertEquals(expectedRender, outEuropean.toString());
    }
    catch (IOException e) {
      fail("IOException thrown");
    }
  }

  // to test the method renderBoard for when transmission fails
  @Test(expected = IOException.class)
  public void testRenderBoardMockAppendable() throws IOException {
    mstv5.renderBoard();
  }

  // a second test to test the method renderBoard for when transmission fails
  @Test(expected = IOException.class)
  public void testRenderBoardMockAppendable2() throws IOException {
    mstv7.renderBoard();
  }

  // to test the method renderMessage
  @Test
  public void testRenderMessage() {
    StringBuffer out = new StringBuffer();
    mstv4 = new MarbleSolitaireTextView(new EnglishSolitaireModel(), out);

    StringBuffer outEuropean = new StringBuffer();
    mstv7 = new MarbleSolitaireTextView(new EuropeanSolitaireModel(), outEuropean);
    try {
      mstv4.renderMessage("Hi");
      assertEquals("Hi", out.toString());
      mstv4.renderMessage("Hello");
      assertEquals("HiHello", out.toString());
      mstv4.renderMessage("Hola");
      assertEquals("HiHelloHola", out.toString());

      mstv7.renderMessage("European");
      assertEquals("European", outEuropean.toString());
      mstv7.renderMessage("Board");
      assertEquals("EuropeanBoard", outEuropean.toString());
    }
    catch (IOException e) {
      fail("IOException thrown");
    }
  }

  // to test the method renderMessage for when transmission fails
  @Test(expected = IOException.class)
  public void testRenderMessageMockAppendable() throws IOException {
    mstv5.renderMessage("owo");
  }

  // a second test to test the method renderMessage for when transmission fails
  @Test(expected = IOException.class)
  public void testRenderMessageMockAppendable2() throws IOException {
    mstv7.renderMessage("uwu");
  }
}