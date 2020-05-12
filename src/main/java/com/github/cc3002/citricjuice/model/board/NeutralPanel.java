package main.java.com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

/**

 */
<<<<<<< Updated upstream
public class NeutralPanel extends Panel {

  public NeutralPanel(final int c, final int d) {
    super(c,d);
=======
public class NeutralPanel extends AbstractPanel {
  /**
   * Creates a new panel.
   *
   * @param a
   * @param b the coordinates of the panel.
   */
  public NeutralPanel(int a, int b) {
    super(a, b);
  }

  @Override
  public void activateBy(@NotNull Player player) {

>>>>>>> Stashed changes
  }
}
