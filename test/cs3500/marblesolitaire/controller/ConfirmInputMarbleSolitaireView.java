package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.Objects;

import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * This class represents a mock MarbleSolitaireView that is used to confirm
 * that the MarbleSolitaireController receives the correct inputs when calling
 * the method renderMessage.
 */
public class ConfirmInputMarbleSolitaireView implements MarbleSolitaireView {
  private final StringBuilder log;

  public ConfirmInputMarbleSolitaireView(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public void renderBoard() throws IOException {
    // doesn't receive any inputs, don't care
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.log.append("message = " + message);
  }
}
