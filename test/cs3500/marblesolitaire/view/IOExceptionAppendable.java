package cs3500.marblesolitaire.view;

import java.io.IOException;

/**
 * This class represents a mock Appendable object that always throws an
 * IOException for all implementation of the methods outlined in Appendable.
 */
public class IOExceptionAppendable implements Appendable {

  /**
   * Throws an IOException when trying to append a CharSequence.
   *
   * @param csq the CharSequence attempting to be appended to this appendable
   * @return nothing
   * @throws IOException no matter what
   */
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException();
  }

  /**
   * Throws an IOException when trying to append a subsequence of a CharSequence.
   *
   * @param csq the full sequence that's attempting to be appended to this appendable
   * @param start the index of the first character in the subsequence
   * @param end the index of the character after the last character of the sequence
   * @return nothing
   * @throws IOException no matter what
   */
  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException();
  }

  /**
   * Throws an IOException when trying to append char.
   * @param c the char attempting to be appended to this appendable
   * @return nothing
   * @throws IOException no matter what
   */
  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException();
  }
}
