package com.github.cc3002.citricjuice.model.board;


import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

import static java.lang.Math.max;

/**
 * Subclass of AbstractPanel
 * The Player in here reduce his stars count
 */
 public class DropPanel extends AbstractPanel {
    /**
     * Creates a new panel.
     *
     * @param a
     * @param b the coordinates of the panel.
     */
    public DropPanel(int a, int b) {
        super(a, b);
    }

    @Override
    /**
     * Reduces the player's star count by the D6 roll multiplied by the player's norma level.
     */
    public void activateBy(@NotNull Player player) {
        player.reduceStarsBy(max(player.roll()*player.getNormaLevel(),0));

    }

}
