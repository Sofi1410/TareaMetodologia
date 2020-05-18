package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Subclass of Panel
 * The Player in here reach a new level if
 * meets the requirements
 * also gets +1 HP
 */
public class HomePanel extends AbstractPanel  {
    /**
     * Creates a new panel.
     *
     * @param a
     * @param b the coordinates of the panel.
     */
    public HomePanel(int a, int b) {
        super(a, b);
    }

    /**
     * Restores a player's HP in 1.
     */
    @Override
    public void activateBy(final @NotNull Player player) {
        player.setCurrentHP(player.getCurrentHP() + 1);
    }
}
