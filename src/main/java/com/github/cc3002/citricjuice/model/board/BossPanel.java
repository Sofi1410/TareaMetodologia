package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Subclass of AbstractPanel
 * The IUnit in this panel has to battle against a BossUnit
 */
public class BossPanel extends AbstractPanel{

    /**
     * Creates a new panel.
     *
     * @param id the unique code for the panel
     */
    public BossPanel(int id) {
        super(id);
    }

    @Override
    public void activateBy(@NotNull Player player) {

    }
}
