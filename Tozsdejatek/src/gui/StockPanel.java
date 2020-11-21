package gui;

import game.Stock;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * Jpanel ami az egyes reszvenyeket jeleniti meg
 */
public class StockPanel extends JPanel {
    private Frame frame;
    private JLabel name;
    private JLabel value;
    private JButton longButton;
    private JButton shortButton;


    public StockPanel(Stock stock){

        this.setLayout(new GridLayout(0, 4));
        this.setPreferredSize(new Dimension(1000, 50));
        this.setMaximumSize(new Dimension(1000, 50));
        this.setMinimumSize(new Dimension(1000, 50));
        this.name = new JLabel(stock.getName());
        this.add(name);
        this.value = new JLabel("Value: " + stock.currentValue() + "$");
        this.add(this.value);
        this.longButton = new JButton("Long");
        longButton.addActionListener(new MakePositionActionListener(stock, frame));
        longButton.setActionCommand("long");
        this.add(this.longButton);
        this.shortButton = new JButton("Short");
        shortButton.addActionListener(new MakePositionActionListener(stock, frame));
        shortButton.setActionCommand("short");
        this.add(shortButton);
    }

    /**
     * Frissiti a megjelenitett erteket
     */
    public void refreshValue(double value){
        this.value.setText("Value: " + frame.df.format(value) + "$");
    }
}