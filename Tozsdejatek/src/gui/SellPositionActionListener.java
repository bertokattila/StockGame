package gui;

import game.Position;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellPositionActionListener implements ActionListener {
    Position position;

    public SellPositionActionListener(Position position){
        this.position = position;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        position.sell();
    }
}
