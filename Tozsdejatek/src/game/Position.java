package game;

import gui.PositionPanel;

import javax.swing.*;
import java.io.Serializable;

public class Position implements Serializable {
    private final int id;
    private final Stock stock;
    private final PositionType type;
    private final double startingStockValue;
    private double currentValue;
    private final double numberOfStocks;
    private final double leverage;
    boolean active = true;
    private transient PositionPanel positionPanel;

    public enum PositionType{
        SHORT,
        LONG
    }

    /**
     * Uj pozicio letrehozasa egy reszvenybol
     * @param id
     * @param stock Reszveny, amibol letrejon a pozicio
     * @param numberOfStocks Reszveny darabszam
     * @param type Pozicio tipusa
     * @param leverage Tokeattet
     */
    public Position(int id, Stock stock, double numberOfStocks, PositionType type, double leverage){
        this.id = id;
        this.stock = stock;
        this.startingStockValue = stock.currentValue();
        currentValue = numberOfStocks * stock.currentValue();
        this.type = type;
        this.leverage = leverage;
        this.numberOfStocks = numberOfStocks;
    }

    void refreshValue(){
        if(type == PositionType.LONG){
            /// sima reszveny vasarlas
            currentValue = numberOfStocks * stock.currentValue();
        }else {
            /// sima shortolas
            currentValue =  startingStockValue * numberOfStocks + numberOfStocks * (startingStockValue - stock.currentValue());
        }
        if(currentValue <= 0){
            active = false;
            positionPanel.close();
        }

        positionPanel.refresh();

        System.out.println(currentValue);
    }

    public void addPositionPanel(PositionPanel positionPanel){
        this.positionPanel = positionPanel;
    }

    public int getId(){ return id; }
    public Stock getStock(){ return stock; }
    public PositionType getType(){ return type; }
    public double getCurrentValue(){ return currentValue; }
    public double getStartingStockValue(){ return startingStockValue; }
    public double getNumberOfStocks(){ return numberOfStocks; }
    public void sell(){
        active = false;
        positionPanel.close();
    }
}
