package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Subclass of AbstractPanel
 * The Unit in this panel has to battle against a BossUnit
 */
public class BossPanel extends AbstractPanel{
    /**
     * Creates a new panel.
     *
     * @param a
     * @param b the coordinates of the panel.
     */
    public BossPanel(int a, int b) {
        super(a, b);
    }

    @Override
    public void activateBy(@NotNull Player player) {

    }
}
