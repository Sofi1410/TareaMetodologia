package main.java.com.github.cc3002.citricjuice.model.board;

<<<<<<< Updated upstream
/**
 * Enum for the types of panels of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater M.</a>.
 * @version 1.0.6-rc.1
 * @since 1.0
 */
public class DrawPanel extends AbstractPanel{
  public DrawPanel(final int c, final int d) {
    super(c,d);
  }
  }
=======
import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

public class DrawPanel extends AbstractPanel{

    /**
     * Creates a new panel.
     *
     * @param a
     * @param b the coordinates of the panel.
     */
    public DrawPanel(int a, int b) {
        super(a, b);
    }

    @Override
    public void activateBy(@NotNull Player player) {

    }
}
>>>>>>> Stashed changes
