package cs3500.marblesolitaire;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * This class represents a playable game of Marble Solitaire. Supported versions of Marble Solitaire
 * include: English, European, and Triangle. The size and starting hole of each version of the game
 * is adjustable with some restrictions; the side length for English and European Solitaire must
 * be an odd and positive integer, the dimensions for Triangle Solitaire must be a positive integer,
 * and the starting hole for every version must be in a valid position on the board.
 */
public final class MarbleSolitaire {
  /**
   * Plays a game of Marble Solitaire where the gameplay is based on the given arguments. Currently,
   * English Solitaire, European Solitaire, and Triangle Solitaire are supported. Additionally, the
   * size of the board and the starting empty hole may also be selected. To select a game mode, pass
   * "english", "european", or "triangular" as the first argument. Optionally, to adjust the size of
   * the board, pass "-size" along with the desired size after. Additionally, to adjust the starting
   * empty hole, pass "-hole" along with the desired row and column numbers after.
   *
   * @param args the arguments received
   */
  public static void main(String[] args) {
    MarbleSolitaireController controller = createController(args);
    controller.playGame();

  }

  /**
   * Helps main create the desired controller with the given arguments.
   *
   * @param args the arguments received
   * @return the desired controller
   */
  private static MarbleSolitaireControllerImpl createController(String[] args) {
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    MarbleSolitaireView view;
    Readable in = new InputStreamReader(System.in);
    switch (args.length) {
      // only the board shape was specified
      case 1:
        switch (args[0]) {
          case "english" :
            model = new EnglishSolitaireModel();
            break;
          case "european":
            model = new EuropeanSolitaireModel();
            break;
          case "triangular":
            model = new TriangleSolitaireModel();
            break;
          default:
            System.err.println(args[0] + " is not a valid game mode.");
            break;
        }
        break;
      // the board shape and size was specified
      case 3:
        try {
          int size = Integer.parseInt(args[2]);
          switch (args[0]) {
            case "english":
              model = new EnglishSolitaireModel(size);
              break;
            case "european":
              model = new EuropeanSolitaireModel(size);
              break;
            case "triangular":
              model = new TriangleSolitaireModel(size);
              break;
            default:
              System.err.println(args[0] + " is not a valid game mode.");
              break;
          }
        }
        catch (NumberFormatException e) {
          System.err.println("Invalid inputs.");
        }
        break;
      // the board shape and hole was specified
      case 4:
        try {
          int row = Integer.parseInt(args[2]);
          int col = Integer.parseInt(args[3]);
          switch (args[0]) {
            case "english":
              model = new EnglishSolitaireModel(row, col);
              break;
            case "european":
              model = new EuropeanSolitaireModel(row, col);
              break;
            case "triangular":
              model = new TriangleSolitaireModel(row, col);
              break;
            default:
              System.err.println(args[0] + " is not a valid game mode.");
              break;
          }
        }
        catch (NumberFormatException e) {
          System.err.println("Invalid inputs.");
        }
        break;
      // the board shape, hole, and size was specified
      case 6:
        try {
          List<String> argsAsList = Arrays.asList(args);
          int size = Integer.parseInt(args[argsAsList.indexOf("-size") + 1]);
          int row = Integer.parseInt(args[argsAsList.indexOf("-hole") + 1]);
          int col = Integer.parseInt(args[argsAsList.indexOf("-hole") + 2]);
          switch (args[0]) {
            case "english":
              model = new EnglishSolitaireModel(size, row, col);
              break;
            case "european":
              model = new EuropeanSolitaireModel(size, row, col);
              break;
            case "triangular":
              model = new TriangleSolitaireModel(size, row, col);
              break;
            default:
              System.err.println(args[0] + " is not a valid game mode.");
              break;
          }
        }
        catch (NumberFormatException e) {
          System.err.println("Invalid inputs.");
        }
        break;
      // an invalid amount of arguments was given
      default:
        System.err.println(args.length + " is an invalid number of elements");
        break;
    }

    // only use the TriangleSolitaireTextView if the model is the TriangleSolitaireModel
    if (args[0].equals("triangular")) {
      view = new TriangleSolitaireTextView(model);
    }
    else {
      view = new MarbleSolitaireTextView(model);
    }

    return new MarbleSolitaireControllerImpl(model, view, in);
  }

}
