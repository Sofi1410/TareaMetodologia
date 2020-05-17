package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.AbstractUnit;
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
