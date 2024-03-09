package Procedures;

import Code.Bag;
import Code.Game;
import Items.Bread;

import java.awt.*;

public class BedroomProcedure implements Procedure{
    Game panel = null;
    public BedroomProcedure(Game game){
        panel = game;
    }
    @Override
    public String initialize(Object object) {
        if(((Bag)object).putItemOutOfBag(new Bread()) > 0){
            panel.setNewLocation("victory_location");
            System.out.println("endgame");
            panel.TestGame();
            panel.getPanel().repaint();
        }
        return null;
    }
}
