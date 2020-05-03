package main.java.com.github.cc3002.citricjuice.model.board;

import main.java.com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Subclass of Panel
 */
public class HomePanel extends Panel{

    public HomePanel(final int c, final int d) {
        super(c,d);
    }
    /**
     * Restores a player's HP in 1.
     */
    private static void applyHealTo(final @NotNull Player player) {
        player.setCurrentHP(player.getCurrentHP() + 1);
    }
}
