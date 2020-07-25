package com.github.cc3002.citricliquid.gui;
import com.github.cc3002.citricjuice.model.GameController;

import java.beans.PropertyChangeEvent;
public class MoreThanOnePathHandler implements IHandler{
    private final GameController controller;

    public MoreThanOnePathHandler(final GameController controler){
        this.controller=controler;
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.onMoreThanOnePath((boolean) evt.getNewValue());
    }
}
