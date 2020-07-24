package com.github.cc3002.citricliquid.gui.Phases;

public class WaitPath extends Phase{
    public  WaitPath(){
        this.canIStart=true;
        this.canIMove=false;
        this.canFight=false;
        this.recover=false;
        this.stayAtHome=false;
        this.canIfinish=false;
        this.WaitAtHome=false;
        this.WaitTOFigth=false;
        this.WaitToPath=true;
        this.Battle=false;

    }

    @Override
    public String toString() {
        return "WaitPath_Phase";
    }
}
