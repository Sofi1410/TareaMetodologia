<<<<<<< Updated upstream
package main.java.com.github.cc3002.citricjuice.model.board;

public class EncounterPanel extends AbstractPanel {

    public EncounterPanel(final int c, final int d) {
        super(c,d);
    }
}

=======
package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

public class EncounterPanel extends AbstractPanel{
    /**
     * Creates a new panel.
     *
     * @param a
     * @param b the coordinates of the panel.
     */
    public EncounterPanel(int a, int b) {
        super(a, b);
    }

    @Override
    public void activateBy(@NotNull Player player) {

    }
}
>>>>>>> Stashed changes
