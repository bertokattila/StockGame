package gui;

import game.Position;

import javax.swing.*;
import java.awt.*;

public class PositionPanel extends JPanel {
    public PositionPanel(){
        this.setLayout(new GridLayout(0,6));
        this.setPreferredSize(new Dimension(100, 15));
        JLabel id = new JLabel("Id: 12");
        JLabel stockName = new JLabel("Apple");
        JLabel startingValue = new JLabel("Starting value: 1300$");
        JLabel currentvalue = new JLabel("Current value: 500$");
        JLabel change = new JLabel("Change: -55%");
        JButton sellButton = new JButton("Sell");
        this.add(id);
        this.add(stockName);
        this.add(startingValue);
        this.add(currentvalue);
        this.add(change);
        this.add(sellButton);

    }
}
