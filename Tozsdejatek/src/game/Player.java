package game;

import exceptions.NotEnoughFundException;
import gui.Frame;
import gui.PositionPanel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class Player implements Serializable {
    public String name;
    private double capital;
    ArrayList<Position> openPositions = new ArrayList<>();
    ArrayList<Position> closedPositions = new ArrayList<>();


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

    public void sellPosition(Position position){
        capital += position.getCurrentValue();
        position.sell();
        closedPositions.add(position);
        openPositions.remove(position);
    }

    public double getCapital() {
        return capital;
    }

    public ArrayList<Position> getOpenPositionsPositions(){ return openPositions; }
    public ArrayList<Position> getClosedPositions(){ return closedPositions; }
}
