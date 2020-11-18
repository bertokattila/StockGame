package game;

import exceptions.NotEnoughFundException;

import java.util.LinkedList;

public class Player {
    public String name;
    private double capital;
    LinkedList<Position> openPositions = new LinkedList<>();
    LinkedList<Position> closedPositions = new LinkedList<>();


    public Player(String name, double capital){
        this.name = name;
        this.capital = capital;
    }


    public void makePosition(Stock stock, double value, Position.PositionType type) throws NotEnoughFundException {
        double numberOfStocks = value/stock.currentValue();
        if(value > capital){
            throw new NotEnoughFundException();
        }
        else {
            openPositions.add(new Position(stock, numberOfStocks, type, 1.0));
            capital -= value;
        }
    }


    public void refreshPositions(){
        for (Position position
        :openPositions){
            position.refreshValue();
        }
    }

    public double getCapital() {
        return capital;
    }
}
