package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;

import java.util.Set;
/*
Interface implements by AbstractPanel
 */
public interface Panel {
     Set<Panel> getNextPanels();

    void addNextPanel(Panel expectedPanel1);

    void activateBy(Player  player);
}
