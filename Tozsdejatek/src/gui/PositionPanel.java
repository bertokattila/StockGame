package gui;

import game.Position;

import javax.swing.*;
import java.awt.*;

public class PositionPanel extends JPanel {
    private final Position position;
    JLabel id;
    JLabel stockName;
    JLabel type;
    JLabel startingValue;
    JLabel currentvalue;
    JLabel change;
    JButton sellButton = new JButton("Sell");
    JPanel parentPanel;

    public PositionPanel(Position position, JPanel parentPanel){
        this.position = position;
        this.parentPanel = parentPanel;
        this.setLayout(new GridLayout(0,7));
        this.setPreferredSize(new Dimension(1000, 50));
        this.setMaximumSize(new Dimension(1000, 50));
        this.setMinimumSize(new Dimension(1000, 50));

        sellButton.addActionListener(new SellPositionActionListener(position));

        id = new JLabel(String.valueOf(position.getId()));
        stockName = new JLabel(position.getStock().getName());
        type = new JLabel(position.getType().name());
        startingValue = new JLabel(Frame.df.format(position.getStartingStockValue() * position.getNumberOfStocks()) + "$");
        currentvalue = new JLabel(Frame.df.format(position.getCurrentValue()) + "$");
        change = new JLabel("0%");

        this.add(id);
        this.add(stockName);
        this.add(type);
        this.add(startingValue);
        this.add(currentvalue);
        this.add(change);
        this.add(sellButton);

    }

    public void refresh(){
        currentvalue.setText(Frame.df.format(position.getCurrentValue()) + "$");
        double change = 100 * (position.getCurrentValue() / (position.getStartingStockValue() * position.getNumberOfStocks())) - 100;
        if(change > 0){
            this.change.setText("+" + Frame.df.format(change) +"%");
            this.change.setForeground(Color.green);
        }else {
            this.change.setText(Frame.df.format(change) +"%");
            this.change.setForeground(Color.red);
        }
    }

    public void remove(){
        this.setVisible(false);
        parentPanel.remove(this);
    }
}
