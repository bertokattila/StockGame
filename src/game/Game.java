package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TimerTask;

public class Game extends TimerTask implements Serializable {

    /**
     * Referencia a jatekosra, aki a jatekhoz tartozik
     */
    private final Player player;

    /**
     * Eltelt korok szama a jatek kezdete ota
     */
    private long time;

    /**
     * Reszvenyek listaja
     */
    private final ArrayList<Stock> stocks;

    /**
     * Konstruktor
     * @param player Jatekos, akit hozzarendelunk a jatekhoz
     */
    public Game(Player player){
        time = 0;
        this.player = player;

        ArrayList<Stock> stocks = new ArrayList<>();

        stocks.add(new Stock("Oil", 45.52));
        stocks.add(new Stock("Tesla", 585.76));
        stocks.add(new Stock("Oracle", 57.76));
        stocks.add(new Stock("Apple", 116.59));
        stocks.add(new Stock("Daimler", 56.59));
        stocks.add(new Stock("Microsoft", 215.23));
        stocks.add(new Stock("Chevron", 91.31));
        stocks.add(new Stock("Sony", 94.24));
        stocks.add(new Stock("Amazon", 3195.34));
        stocks.add(new Stock("Netflix", 491.36));
        stocks.add(new Stock("Spotify", 277.62));
        stocks.add(new Stock("Facebook", 271.66));
        stocks.add(new Stock("Uber", 50.72));
        stocks.add(new Stock("Ford", 9.09));

        this.stocks = stocks;
    }

    /**
     * TimerTask run fuggvenye felulirva, ez hivodik meg automatikusan a megadott idokozonkent
     * gyakorlatilak minden korben, uj erteket generaltat a reszvenyeknek es utana frissiti a poziciok erteket
     */
    @Override
    public void run() {
        for (Stock stock:
             stocks) {
            stock.newValue();
        }
        time++;
        player.refreshPositions();
    }

    /**
     * Visszaadja a jatekost
     * @return Jatekos referenciaja
     */
    public Player getPlayer(){ return player; }

    /**
     * Visszaadja a reszvenyek listajat
     * @return A reszvenyek listaja
     */
    public ArrayList<Stock> getStocks(){ return stocks; }

}
