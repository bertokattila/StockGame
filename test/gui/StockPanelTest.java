package gui;

import game.Game;
import game.Player;
import game.Stock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StockPanelTest {
    Game game;
    Stock stock;

    @Before
    public void init(){
        game = new Game(new Player("Sample Player", 3000));
        stock = new Stock("Gold", 100);
    }

    @Test
    public void testName(){
        StockPanel stockPanel = new StockPanel(stock);
        Assert.assertTrue(stockPanel.getName().equals("Gold"));
    }

    @Test
    public void testValue(){
        StockPanel stockPanel = new StockPanel(stock);
        Assert.assertTrue(stockPanel.getvalue().equals("Value: 100.00$"));
    }

    @Test
    public void testValueAfterChange(){
        StockPanel stockPanel = new StockPanel(stock);
        stock.newValue();
        stockPanel.refreshValue(stock.currentValue());
        Assert.assertTrue(stockPanel.getvalue().equals("Value: " + Frame.df.format(stock.currentValue()) + "$"));
    }
}
