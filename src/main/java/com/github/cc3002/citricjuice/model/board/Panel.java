package main.java.com.github.cc3002.citricjuice.model.board;

<<<<<<< Updated upstream
public interface Panel {
=======
import com.github.cc3002.citricjuice.model.Player;

import java.util.Set;

public interface Panel {
     Set<Panel> getNextPanels();

    void addNextPanel(Panel expectedPanel1);

    void activateBy(Player player);
>>>>>>> Stashed changes
}
