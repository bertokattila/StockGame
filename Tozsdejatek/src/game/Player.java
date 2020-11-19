package game;

import exceptions.NotEnoughFundException;
import gui.Frame;
import gui.PositionPanel;

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


    public Position makePosition(Stock stock, double value, Position.PositionType type) throws NotEnoughFundException {
        Position position;
        double numberOfStocks = value/stock.currentValue();
        if(value > capital){
            throw new NotEnoughFundException();
        }
        else {
            position = new Position(openPositions.size() + closedPositions.size() + 1, stock, numberOfStocks, type, 1.0);
            openPositions.add(position);
            capital -= value;
        }
        return position;
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
