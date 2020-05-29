package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Subclass of AbstractPanel
 * The Unit in this panel has to battle against a aleatory WildUnit
 */
public class EncounterPanel extends AbstractPanel{


    /**
     * Creates a new panel.
     *
     * @param a coordinate of the panel
     * @param b the coordinates of the panel.
     */
    public EncounterPanel(int a, int b) {
        super(a, b);
    }

    @Override
    public void activateBy(@NotNull Player player) {

    }
}
