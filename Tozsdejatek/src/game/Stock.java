package game;

import gui.Frame;
import gui.StockPanel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Stock implements Serializable {

    private final String name;

    private double lastChange = 0;

    /// utolso ertek az aktualis
    private final ArrayList<Double> valueHistory = new ArrayList<>();

    ///referencia a stockPanelre, ami megjeleniti ot
    private transient StockPanel stockPanel;

    public Stock(String name, double startingValue){
        this.name = name;
        this.valueHistory.add(startingValue);
    }

    /**
     * Reszveny nevet adja vissza
     * @return reszveny neve
     */
    public String getName(){ return name; }

    /**
     * Visszaadja a 1 db reszveny aktualis erteket
     * @return aktualis ertek
     */
    public double currentValue(){  return valueHistory.get(valueHistory.size() - 1); }

    /**
     * Visszaadja az utolso valtozast (aranyszam)
     * @return utolso valtozas
     */
    public double lastChange(){ return lastChange; }

    public void setStockPanel(StockPanel stockPanel) {
        this.stockPanel = stockPanel;
    }

    /**
     * Uj erteket general a reszvenynek
     */
    void newValue(){
        Random random = new Random();

        /// valtozas: egyenletes eloszlas -0.1 es 0.1 kozott
        lastChange = ((random.nextDouble() * 0.2) - 0.1);
        valueHistory.add(currentValue() + (currentValue() * lastChange));
        if(stockPanel != null){
            stockPanel.refreshValue(currentValue());
        }

        System.out.println(name + " " + currentValue());
    }
}
