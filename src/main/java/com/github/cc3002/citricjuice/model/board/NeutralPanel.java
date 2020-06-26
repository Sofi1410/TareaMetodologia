package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

/**
Subclass of Abstract IPanel
 Nothing happens in here
 */
public class NeutralPanel extends AbstractPanel {

  /**
   * Creates a new panel.
   *
   * @param id the unique code for the panel
   */
  public NeutralPanel(int id) {
    super(id);
  }

  @Override
  public void activateBy(@NotNull Player player) {

  }
}
