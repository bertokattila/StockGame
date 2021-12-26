package game;

import gui.StockChart;
import gui.StockPanel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Stock implements Serializable {

    /**
     * A reszveny neve
     */
    private final String name;

    /**
     * Az utolso sorsolt valtozas (aranyszam)
     */
    private double lastChange = 0;

    /**
     * A reszveny korabbi ertekeit tarolo lista,
     * a legutolso ertek mindig a jelenlegi
     */
    private final ArrayList<Double> valueHistory = new ArrayList<>();

    ///referencia a stockPanelre, ami megjeleniti ot
    private transient StockPanel stockPanel;

    private transient StockChart chart;

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
     * Getter az korabbi ertekek listajara
     * @return Lista a korabbi ertekekrol
     */
    public ArrayList<Double> getValueHistory() {
        return valueHistory;
    }

    /**
     * Visszaadja az utolso valtozast (aranyszam)
     * @return utolso valtozas
     */
    public double lastChange(){ return lastChange; }

    public void setStockPanel(StockPanel stockPanel) {
        this.stockPanel = stockPanel;
    }

    /**
     * Az erteke megadja, hogy hany kovetkezo erteknel kapjon boostot
     * ha nem lenne boostolas, akkor hosszabb jatekoknal egy ido utan
     * ekertektelenednenek a reszvenyek
     */
    private int valueBoost = 0;

    /**
     * Uj erteket general a reszvenynek
     */
    public void newValue(){
        Random random = new Random();

        /// valtozas: egyenletes eloszlas -0.1 es 0.1 kozott
        lastChange = ((random.nextDouble() * 0.2) - 0.1);

        if(currentValue() < 3 && valueBoost == 0){
            valueBoost = 30;

        }
        if(valueBoost > 0){
            valueBoost--;
            lastChange = lastChange + 0.1;
        }

        valueHistory.add(currentValue() + (currentValue() * lastChange));
        if(stockPanel != null){
            stockPanel.refreshValue(currentValue());
        }
        if(chart != null){
            chart.addNewValue(currentValue(), valueHistory.size());
        }
    }

    /**
     * Hozza tartozo chart hozzaadasa, hogy tudja updatelni az ertekekeit a stock
     * @param chart Referencia a chartra
     */
    public void addChart(StockChart chart){
        this.chart = chart;
    }

    /**
     * Referencia eldobasa a chartrol, hogy ne foglalja feleslegesen a memoriat,
     * ha nincsen ra szukseg eppen
     */
    public void dropChart(){
        if(this.chart != null){
            this.chart.dispose();
            this.chart = null;
        }
    }

    /**
     *Chart getter
     * @return Referencia a chartra
     */
    public StockChart getChart(){ return this.chart; }
}
