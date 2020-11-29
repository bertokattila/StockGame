package gui;

import game.Stock;
import javax.swing.*;
import java.awt.*;

public class StockPanel extends JPanel {

    /**
     * Label, ami megjeleniti a reszveny erteket
     */
    private final JLabel value;

    /**
     * Label, ami megjeleniti a reszveny nevet
     */
    private final JLabel name;

    /**
     * Konstrukror
     * @param stock Reszveny, aminek a grafiku reprezentaciojat kivanjuk letrehozni
     */
    public StockPanel(Stock stock){

        this.setLayout(new GridLayout(0, 5));
        this.setPreferredSize(new Dimension(1000, 50));
        this.setMaximumSize(new Dimension(1000, 50));
        this.setMinimumSize(new Dimension(1000, 50));
        name = new JLabel(stock.getName());
        this.add(name);
        this.value = new JLabel("Value: " + Frame.df.format(stock.currentValue()) + "$");
        this.add(this.value);
        JButton longButton = new JButton("Long");
        longButton.addActionListener(new MakePositionActionListener(stock, null));
        longButton.setActionCommand("long");
        this.add(longButton);
        JButton shortButton = new JButton("Short");
        shortButton.addActionListener(new MakePositionActionListener(stock, null));
        shortButton.setActionCommand("short");
        this.add(shortButton);
        JButton chartButton = new JButton("Show chart");
        chartButton.addActionListener(new ShowStockChartActionListener(stock));
        this.add(chartButton);
    }

    /**
     * Frissiti a megjelenitett arfolyam-erteket
     * @param value Az uj ertek
     */
    public void refreshValue(double value){
        this.value.setText("Value: " + Frame.df.format(value) + "$");
    }

    public String getName(){ return name.getText(); }
    public String getvalue(){ return  value.getText(); }
}