package cs3500.marblesolitaire.view;

import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.io.StringWriter;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A JUnit test class for the TriangleSolitaireTextView class.
 */
public class TriangleSolitaireTextViewTest {
  private MarbleSolitaireView tstv1;
  private MarbleSolitaireView tstv2;
  private MarbleSolitaireView tstv3;

  // the initial conditions for the example TriangleSolitaireTextView objects
  @Before
  public void init() {
    tstv1 = new TriangleSolitaireTextView(new TriangleSolitaireModel());
    tstv2 = new TriangleSolitaireTextView(
            new TriangleSolitaireModel(7), new IOExceptionAppendable());
    tstv3 = new TriangleSolitaireTextView(
            new TriangleSolitaireModel(4, 1, 1), new StringBuilder());
  }

  // to test creating valid TriangleSolitaireTextView objects
  @Test
  public void testCreateValidTriangleSolitaireTextView() {
    tstv1 = new TriangleSolitaireTextView(new TriangleSolitaireModel());
    String defaultModelToString =
            "    _\n   O O\n  O O O\n O O O O\nO O O O O";
    assertEquals(defaultModelToString, tstv1.toString());

    tstv2 = new TriangleSolitaireTextView(
            new TriangleSolitaireModel(7), new IOExceptionAppendable());
    String sevenRowModel =
            "      _\n     O O\n    O O O\n   O O O O\n  O O O O O\n O O O O O O\nO O O O O O O";
    assertEquals(sevenRowModel, tstv2.toString());

    tstv3 = new TriangleSolitaireTextView(
            new TriangleSolitaireModel(4, 1, 1), new StringBuilder());
    String fourRowModel =
            "   O\n  O _\n O O O\nO O O O";
    assertEquals(fourRowModel, tstv3.toString());
  }

  // to test creating a TriangleSolitaireTextView object with the constructor that takes in one
  // parameter that throws an exception due to the given MarbleSolitaireModelState being null
  @Test(expected = IllegalArgumentException.class)
  public void testCreateMarbleSolitaireTextViewOneParamNullModel() {
    new TriangleSolitaireTextView(null);
  }

  // to test creating a TriangleSolitaireTextView object with the constructor that takes in one
  // parameter that throws an exception due to the given MarbleSolitaireModelState not being a
  // TriangleSolitaireModel
  @Test(expected = IllegalArgumentException.class)
  public void testCreateMarbleSolitaireTextViewOneParamNotTriangleModel() {
    new TriangleSolitaireTextView(new EnglishSolitaireModel());
  }

  // to test creating a TriangleSolitaireTextView object with the constructor that takes in two
  // parameters that throws an exception due to the given MarbleSolitaireModelState being null
  @Test(expected = IllegalArgumentException.class)
  public void testCreateMarbleSolitaireTextViewTwoParamNullModel() {
    new TriangleSolitaireTextView(null, new StringWriter());
  }

  // to test creating a TriangleSolitaireTextView object with the constructor that takes in two
  // parameters that throws an exception due to the given MarbleSolitaireModelState not being a
  // TriangleSolitaireModel
  @Test(expected = IllegalArgumentException.class)
  public void testCreateMarbleSolitaireTextViewTwoParamNotTriangleModel() {
    new TriangleSolitaireTextView(new EuropeanSolitaireModel(), new StringBuilder());
  }

  // to test creating a TriangleSolitaireTextView object with the constructor that takes in two
  // parameters that throws an exception due to the given Appendable object being null
  @Test(expected = IllegalArgumentException.class)
  public void testCreateMarbleSolitaireTextViewTwoParamNullAppendable() {
    new TriangleSolitaireTextView(new TriangleSolitaireModel(), null);
  }

  // to test the method toString
  @Test
  public void testToString() {
    String tstv1Expected =
            "    _\n   O O\n  O O O\n O O O O\nO O O O O";
    assertEquals(tstv1Expected, tstv1.toString());

    String tstv2Expected =
            "      _\n     O O\n    O O O\n   O O O O\n  O O O O O\n O O O O O O\nO O O O O O O";
    assertEquals(tstv2Expected, tstv2.toString());

    String tstv3Expected =
            "   O\n  O _\n O O O\nO O O O";
    assertEquals(tstv3Expected, tstv3.toString());

    TriangleSolitaireModel model = new TriangleSolitaireModel();
    model.move(2, 0, 0, 0);

    tstv1 = new TriangleSolitaireTextView(model);

    tstv1Expected =
            "    O\n   _ O\n  _ O O\n O O O O\nO O O O O";
    assertEquals(tstv1Expected, tstv1.toString());
  }

  // to test the method renderBoard
  @Test
  public void testRenderBoard() {
    StringBuffer out = new StringBuffer();
    tstv1 = new TriangleSolitaireTextView(new TriangleSolitaireModel(), out);
    String expected =
            "    _\n   O O\n  O O O\n O O O O\nO O O O O";

    try {
      tstv1.renderBoard();
      assertEquals(expected, out.toString());
    }
    catch (IOException e) {
      fail("IOException thrown");
    }
  }

  // to test the method renderBoard for when transmission fails
  @Test(expected = IOException.class)
  public void testRenderBoardMockAppendable() throws IOException {
    tstv2.renderBoard();
  }

  // to test the method renderBoard
  @Test
  public void testRenderMessage() {
    StringBuffer out = new StringBuffer();
    tstv1 = new TriangleSolitaireTextView(new TriangleSolitaireModel(), out);

    try {
      tstv1.renderMessage("Triangle");
      assertEquals("Triangle", out.toString());
      tstv1.renderMessage("Solitaire");
      assertEquals("TriangleSolitaire", out.toString());
      tstv1.renderMessage("Model");
      assertEquals("TriangleSolitaireModel", out.toString());
    }
    catch (IOException e) {
      fail("IOException thrown");
    }
  }

  // to test the method renderMessage for when transmission fails
  @Test(expected = IOException.class)
  public void testRenderMessageMockAppendable() throws IOException {
    tstv2.renderMessage("fail");
  }
}