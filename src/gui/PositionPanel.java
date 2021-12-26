package gui;

import game.Position;

import javax.swing.*;
import java.awt.*;

public class PositionPanel extends JPanel {
    /**
     * A pozicio, amit reprezental grafikusaN
     */
    private final Position position;

    /**
     * A pozicio id-jat abrazolo label
     */
    protected final JLabel id;

    /**
     * A reszveny nevet abrazolo label, amibol a pozicio letrejott
     */
    protected final JLabel stockName;

    /**
     *A pozicio tipusat abrazolo label
     * SHORT vagy LONG
     */
    protected final JLabel type;

    /**
     * A pozicio aktualis erteket mutatja, dollarban
     */
    protected final JLabel currentvalue;

    /**
     * Szazalekban megjeleniti a pozicio ertekenek valtozasat
     */
    protected final JLabel change;

    /**
     * Gomb amivel el lehet adni a poziciot
     * csak akkor lathato, amikor aktiv allapotban van a pozicio,
     * hiszen csak azok eladhatoak
     */
    private final JButton sellButton = new JButton("Sell");
    protected final JLabel profitLabel;
    private final JPanel parentPanel;

    /**
     * Konstruktor
     * Osszerakja a pozicio adataibol az azt reprezentalo panelt
     * @param position a pozicio, amit reprezental
     * @param parentPanel a panel, ami tartalmazza ot
     */
    public PositionPanel(Position position, JPanel parentPanel){
        this.position = position;
        this.parentPanel = parentPanel;
        this.setLayout(new GridLayout(0,8));
        this.setPreferredSize(new Dimension(1000, 50));
        this.setMaximumSize(new Dimension(1000, 50));
        this.setMinimumSize(new Dimension(1000, 50));

        sellButton.addActionListener(new SellPositionActionListener(position));

        id = new JLabel(String.valueOf(position.getId()));
        stockName = new JLabel(position.getStock().getName());
        type = new JLabel(position.getType().name());
        JLabel leverage = new JLabel(Frame.df.format(position.getLeverage() * 100) + "%");
        JLabel startingValue = new JLabel(Frame.df.format(position.getStartingStockValue() * position.getNumberOfStocks()) + "$");
        currentvalue = new JLabel(Frame.df.format(position.getCurrentValue()) + "$");
        change = new JLabel("0%");

        profitLabel = new JLabel("0$");
        double changeValue = 100 * ((position.getCurrentValue() / (position.getStartingStockValue() * position.getNumberOfStocks())) - 1);
        if(changeValue > 0){
            this.change.setText("+" + Frame.df.format(changeValue) +"%");
            this.change.setForeground(Color.green);
        }else {
            this.change.setText(Frame.df.format(changeValue) +"%");
            this.change.setForeground(Color.red);
        }
        profitLabel.setVisible(false);

        this.add(id);
        this.add(stockName);
        this.add(type);
        this.add(leverage);
        this.add(startingValue);
        this.add(currentvalue);
        this.add(change);
        this.add(sellButton);

    }

    /**
     * Megjelenites frissitese az aktualis adatokkal,
     * gondoskodik arrol, hogy ha tobbet er a reszveny a kezdeti
     * ertekenel, akkor a szazalekos valtozas zoldben jelenik meg,
     * egyebkent pirosban
     */
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

    /**
     * A hozza tartozo pozicio lezarasra kerult, ehhez
     * igazitja a grafikus reprezentaciot:
     * pl.: eltunik a Sell button, stb.
     */
    public void close(boolean sold){
        this.setVisible(false);
        Frame.getClosedPositionsPanel().add(this);
        parentPanel.remove(this);
        this.remove(sellButton);
        if(sold) {
            profitLabel.setText(Frame.df.format(position.getCurrentValue() - (position.getStartingStockValue() * position.getNumberOfStocks())) + "$");
        }else {/// elertektelenedes miatt lett lezarva
            profitLabel.setText(Frame.df.format(-1 * position.getStartingStockValue() * position.getNumberOfStocks()) + "$");
            change.setText("-100%");
            currentvalue.setText("0$");
        }
        this.add(profitLabel);
        profitLabel.setVisible(true);
        this.setVisible(true);
    }
}
