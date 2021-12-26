package game;

import gui.PositionPanel;

import java.io.Serializable;

public class Position implements Serializable {
    /**
     * Pozicio azonositoja, ami pozitiv egesz szam
     * a jatekos a segitsegevel konnyebben meg tudja kulonboztetni a
     * pozicioit, mas (funkcionalis) szerepe nincsen
     */
    private final int id;

    /**
     * Referencia a reszvenyre, amibol letre lett hozva
     * ennek az arfolyamvaltozasat kell kovetnie a parametereinek megfeleloen
     */
    private final Stock stock;

    /**
     * Pozicio tipusa
     */
    private final PositionType type;

    /**
     * A reszvenynek az erteke abban az idopontban, amikor letre lett hozva
     * a pozicio belole
     */
    private final double startingStockValue;

    /**
     * Az aktualis erteke a pozicionak
     */
    private double currentValue;

    /**
     * Megadja, hogy hany darab reszvenybol all a pozicio
     */
    private final double numberOfStocks;

    /**
     * A tokeattet merteket tarolja, amennyiben nincsen, az erteke 1
     */
    private final double leverage;

    /**
     * Az eladott, illetve elertektelenedett poziciok nem aktivak, a tobbi igen
     * Csak az aktiv poziciok allapota valtozik
     */
    boolean active = true;

    /**
     * Referencia a poziciot reprezentalo panel-re, ami gui elem, igy
     * nem szukseges szerializalni menteskor, emiatt transient
     */
    private transient PositionPanel positionPanel;

    /**
     * A pozicio ket fele lehetseges tipusat reprezentalo enum
     */
    public enum PositionType{
        SHORT,
        LONG
    }

    /**
     * Uj pozicio letrehozasa egy reszvenybol
     * @param id A pozicio id-ja
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

    /**
     * Hozzaigazitja a reszveny aktualis ertekehez a pozicio erteket es allapotat a parameterei alapjan
     */
    public void refreshValue(){
        if(type == PositionType.LONG){
            /// reszveny vasarlas
            currentValue = startingStockValue * numberOfStocks + numberOfStocks * leverage * (stock.currentValue() - startingStockValue);
        }else {
            /// shortolas
            currentValue =  startingStockValue * numberOfStocks + numberOfStocks * leverage * (startingStockValue - stock.currentValue());
        }
        if(currentValue <= 0){
            active = false;
            positionPanel.close(false);
        }else {
            positionPanel.refresh();
        }

    }

    /**
     * Az ot reprezentalo hozzaadasa, amennyiben elertektelenedik a pozocio szolnia kell neki
     * @param positionPanel Referencia a PositionPanel-re
     */
    public void addPositionPanel(PositionPanel positionPanel){
        this.positionPanel = positionPanel;
    }

    /**
     * A jatekos hivja meg, ha ugy dont, hogy eladja
     */
    public void sell(){
        active = false;
        positionPanel.close(true);
    }

    ///innentol csak getter fuggvenyek vannak, ezeket nem kommentalom egyenkent

    public int getId(){ return id; }
    public Stock getStock(){ return stock; }
    public PositionType getType(){ return type; }
    public double getCurrentValue(){ return currentValue; }
    public double getStartingStockValue(){ return startingStockValue; }
    public double getNumberOfStocks(){ return numberOfStocks; }
    public double getLeverage(){ return leverage; }


}
