package game;

public class Position {
    private final Stock stock;
    private final PositionType type;
    private final double startingStockValue;
    private double currentValue;
    private final double numberOfStocks;
    private final double leverage;
    boolean active = true;

    public enum PositionType{
        SHORT,
        LONG
    }

    /**
     * Uj pozicio letrehozasa egy reszvenybol
     * @param stock Reszveny, amibol letrejon a pozicio
     * @param numberOfStocks Reszveny darabszam
     * @param type Pozicio tipusa
     * @param leverage Tokeattet
     */
    public Position(Stock stock, double numberOfStocks, PositionType type, double leverage){
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
        if(currentValue <= 0) active = false;

        System.out.println(currentValue);
    }

    public double getCurrentValue(){ return numberOfStocks * stock.currentValue(); }
    public void sell(){

    }
}
