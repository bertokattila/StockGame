package game;

import exceptions.NotEnoughFundException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Player implements Serializable {
    /**
     * A jatekos neve
     */
    private final String name;

    /**
     * A jatekos szabad tokeje (dollarban)
     * a kintlevosegeket nem szamitva, azaz ha vannak nyitott pozicioi,
     * azokat eladva novelheti
     */
    private double capital;

    /**
     * nyitott poziciok listaja
     */
    private final ArrayList<Position> openPositions = new ArrayList<>();

    /**
     * Lezarodoott poziciok listaja
     * Ketto fele keppen zarodhat le egy pozicio:
     * eladasra kerul, vagy elertektelenedik
     */
    private final ArrayList<Position> closedPositions = new ArrayList<>();

    /**
     *Konstruktor
     * @param name A jatekos neve
     * @param capital A jatekos kezdotokeje
     */
    public Player(String name, double capital){
        this.name = name;
        this.capital = capital;
    }

    /**
     * Letrehoz egy uj poziciot a jatekos
     * @param stock A reszveny, amibol letrehozza
     * @param value Az ertek, amennyiert letrejon a pozicio (dollarban)
     * @param type A letrejovo pozicio tipusa
     * @param leverage A tokeattet erteke, 1, ha nincsen attet
     * @return Referencia a letrejovo poziciora
     * @throws NotEnoughFundException Amennyiben nincsen eleg toke hozza
     */
    public Position makePosition(Stock stock, double value, Position.PositionType type, double leverage) throws NotEnoughFundException {
        Position position;
        double numberOfStocks = value/stock.currentValue();
        if(value > capital){
            throw new NotEnoughFundException();
        }
        else {
            position = new Position(openPositions.size() + closedPositions.size() + 1, stock, numberOfStocks, type, leverage);
            openPositions.add(position);
            capital -= value;
        }
        return position;
    }

    /**
     * Az aktiv poziciok ertekenek frissitese, valamint elertektelenedes eseten
     * annak kezelese
     */
    public void refreshPositions(){
        ///iteratort kell hasznalnom, mert iteralas kozben kell torolni elemet
        Iterator<Position> iter = openPositions.iterator();
        while (iter.hasNext()){
            Position position = iter.next();
            position.refreshValue();
            if(!position.active){ ///elertektelenedett
                closedPositions.add(position);
                iter.remove();
            }
        }
    }

    /**
     * Pozicio eladasa
     * @param position Referencia a poziciora, amit el kell adnia
     */
    public void sellPosition(Position position){
        capital += position.getCurrentValue();
        position.sell();
        closedPositions.add(position);
        openPositions.remove(position);
    }

    /// innentol getterek kovetkeznek, amiket nem kommentalok egyesevel

    public double getCapital() {
        return capital;
    }
    public ArrayList<Position> getOpenPositions(){ return openPositions; }
    public ArrayList<Position> getClosedPositions(){ return closedPositions; }
    public String getName(){return name;}
}
