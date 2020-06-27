package com.github.cc3002.citricjuice.model.board;
import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Subclass of IPanel
 * The Player in here reach a new level if
 * meets the requirements
 * also gets +1 HP
 */
public class HomePanel extends AbstractPanel  {


    /**
     * Creates a new panel.
     *
     * @param id the unique code for the panel
     */
    public HomePanel(int id) {
        super(id);
    }

    /**
     * Restores a player's HP in 1.
     */
    @Override
    public void activateBy(final @NotNull Player player) {
        if (player.getHomePanel().getId()==this.getId()) {
            player.setCurrentHP(player.getCurrentHP() + 1);
            player.normaCheck();
        }
    }
}
