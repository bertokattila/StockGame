package game;

import java.util.ArrayList;
import java.util.TimerTask;

public class Game extends TimerTask {
    Player player;
    long time;
    ArrayList<Stock> stocks;


    public Game(Player player){
        time = 0;
        this.player = player;

        ArrayList<Stock> stocks = new ArrayList<>();

        stocks.add(new Stock("Oil", 100));
        stocks.add(new Stock("Tesla", 100));
        stocks.add(new Stock("Oracle", 100));
        stocks.add(new Stock("Apple", 100));
        stocks.add(new Stock("Daimler", 100));
        stocks.add(new Stock("Microsoft", 100));
        stocks.add(new Stock("Chevron", 100));
        stocks.add(new Stock("Mol", 100));
        stocks.add(new Stock("Sony", 100));
        stocks.add(new Stock("Amazon", 100));
        stocks.add(new Stock("Netflix", 100));
        stocks.add(new Stock("Spotify", 100));
        stocks.add(new Stock("Facebook", 100));
        stocks.add(new Stock("Uber", 100));
        stocks.add(new Stock("Ford", 100));

        this.stocks = stocks;
    }

    @Override
    public void run() {
        for (Stock stock:
             stocks) {
            stock.newValue();
        }
        time++;
        player.refreshPositions();
        System.out.println(" ");
    }

    public Player getPlayer(){ return player; }
    public ArrayList<Stock> getStocks(){ return stocks; }
}
