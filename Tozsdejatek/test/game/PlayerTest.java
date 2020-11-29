package game;

import exceptions.NotEnoughFundException;
import gui.PositionPanel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

public class PlayerTest {
    /**
     * Reszveny objektum, ami majd 25-os kezdoerteket kap az init()-ben
     */
    private Stock stock;

    /**
     * Jatekos objektum
     */
    private Player player;

    @Before
    public void init(){
        player = new Player("Sample Player", 3000);
        stock = new Stock("Oil", 25);
    }

    /**
     * Ellenorzi, hogy megfelelo osszeg vonodik-e le a player tokejebol, amikor
     * letrehoz poziciot
     * @throws NotEnoughFundException Nem varjuk most az eldobasat
     */
    @Test
    public void testMakePosition() throws NotEnoughFundException {
        player.makePosition(stock, 100, Position.PositionType.LONG, 1);
        Assert.assertEquals(2900, player.getCapital(), 0.0001);
    }

    @Test
    public void testSellPosition() throws NotEnoughFundException {
        player.makePosition(stock, 500, Position.PositionType.LONG, 1);
        /// mivel nem frissitunk arfolyamot, emiatt nem valtozhat a pozicio erteke sem, azaz eladas utan
        /// ujra az eredeti toke mennyisegnek kell lennie a jatekosnal
        Position position = player.getOpenPositions().get(0);
        position.addPositionPanel(new PositionPanel(position, new JPanel()));
        player.sellPosition(position);
        Assert.assertEquals(3000, player.getCapital(), 0.0001);
    }

    /**
     * Teszteli, hogy dobodik e kivetel akkor, amikor tobbet szeretne kolteni a player, mint amennyi tokeje van
     * @throws NotEnoughFundException A kivetel, aminek az eldobasat varjuk
     */
    @Test(expected = NotEnoughFundException.class)
    public void testMakePositionNotEnoughFund() throws NotEnoughFundException {
        player.makePosition(stock, 5000, Position.PositionType.LONG, 1);
    }

}
