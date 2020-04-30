package com.github.cc3002.citricjuice.model.board;

/**
 * Subclass of Panel
 */
public class HomePanel extends Panel{
    private final PanelType type ;

    public HomePanel(PanelType type){
        this.type= HOME;
    }
    /**
     * Restores a player's HP in 1.
     */
    private static void applyHealTo(final @NotNull Player player) {
        player.setCurrentHP(player.getCurrentHP() + 1);
    }
}
