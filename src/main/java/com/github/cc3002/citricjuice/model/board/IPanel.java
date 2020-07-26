package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Unit.*;

import java.util.List;
import java.util.Set;
/*
Interface implements by AbstractPanel
 */
public interface IPanel {
     Set<IPanel> getNextPanels();

    void addNextPanel(IPanel expectedIPanel1);

    void activateBy(Player player);

    List<Player> getPlayers();

    void addPlayer(Player player);

    void removePlayer(Player player);

    int getId();



    IPanel getLeft();

    IPanel getRight();

    IPanel getUp();

    IPanel getDown();
}
