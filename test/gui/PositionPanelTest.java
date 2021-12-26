package gui;

import exceptions.NotEnoughFundException;
import game.Game;
import game.Player;
import game.Position;
import game.Stock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PositionPanelTest {
    Game game;
    Stock stock;
    Position position;
    Player player;

    @Before
    public void init() throws NotEnoughFundException {
        game = new Game(new Player("Sample Player", 3000));
        stock = new Stock("Microsoft", 100);
        player = game.getPlayer();
        player.makePosition(stock, 450, Position.PositionType.LONG, 1);
        position = game.getPlayer().getOpenPositions().get(0);
    }

    /**
     * teszteli, hogy a pozicio letrehozasa utan a helyes adatokat jeleniti-e meg
     */
    @Test
    public void testInitials(){
        PositionPanel positionPanel = new PositionPanel(position, null);
        Assert.assertEquals("0.00%", positionPanel.change.getText());
        Assert.assertEquals("1", positionPanel.id.getText());
        Assert.assertEquals("Microsoft", positionPanel.stockName.getText());
        Assert.assertEquals("LONG", positionPanel.type.getText());
        Assert.assertEquals("450.00$", positionPanel.currentvalue.getText());
    }

    /**
     * Teszteli, hogy ha valtozott a reszveny (es emiatt a pozicio) erteke, akkor
     * azt lekoveti-e a panel is
     */
    @Test
    public void testNewValue(){
        PositionPanel positionPanel = new PositionPanel(position, null);
        position.addPositionPanel(positionPanel);
        stock.newValue();
        position.refreshValue();
        Assert.assertEquals(Frame.df.format(position.getCurrentValue()) + "$", positionPanel.currentvalue.getText());
    }


}
