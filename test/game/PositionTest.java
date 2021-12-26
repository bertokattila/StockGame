package game;

import exceptions.NotEnoughFundException;
import gui.PositionPanel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PositionTest {
    /**
     * Jatek objektum, amire szukseg van, hiszen o tartalmazza a tobbi resztelni kivant objektumot
     */
    private Game game;



    /**
     * Masik reszveny objektum, aminek kerek 100 lesz a kezdoerteke, hogy konnyu legyen szamolni vele
     */
    private Stock otherStock;


    /**
     * Inicializaljuk minden teszteset elott a hasznalt objektumokat
     */
    @Before
    public void init(){
        game = new Game(new Player("Sample Player", 3000));

        otherStock = new Stock("Gold", 100);
    }


    /**
     * Teszteli, hogy helyesen reaglajak-e le a long tipusu poziciok a reszveny
     * arfolyamvaltozasat, kulonbozo tokeattetekkel
     * @throws NotEnoughFundException nem varjuk az eldobasat most
     */
    @Test
    public void testLongPositionFollowsStock() throws NotEnoughFundException {

        game.getPlayer().makePosition(otherStock, 100, Position.PositionType.LONG, 1);
        game.getPlayer().makePosition(otherStock, 100, Position.PositionType.LONG, 2);
        otherStock.newValue();
        Position position1 = game.getPlayer().getOpenPositions().get(0);
        Position position2 = game.getPlayer().getOpenPositions().get(1);

        /*
         *A teszt szempontjabol lenyegtelen, de hozza kell adni az ot reprezentalo jpanelt,
         *mert ertek frissiteskor azt is frissiteni akarja, emiatt nem lehet null
         */
        position1.addPositionPanel(new PositionPanel(position1,null));
        position2.addPositionPanel(new PositionPanel(position2,null));

        position1.refreshValue();
        position2.refreshValue();

        /*
         *Itt nem lehet konstansokkal teszteln, mert a stock uj erteke random generalodik
         */
        Assert.assertEquals(otherStock.currentValue(), position1.getCurrentValue(), 0.0001);
        Assert.assertEquals(100 + (otherStock.currentValue() - 100) * 2, position2.getCurrentValue(), 0.0001);
    }

    /**
     * Az elozo (testLongPositionFollowsStock) mintajara teszteli a short tipusu poziciokat
     * @throws NotEnoughFundException nem varjuk az eldobasat most
     */
    @Test
    public void testShortPositionFollowsStock() throws NotEnoughFundException {

        game.getPlayer().makePosition(otherStock, 100, Position.PositionType.SHORT, 1);
        game.getPlayer().makePosition(otherStock, 100, Position.PositionType.SHORT, 2);
        otherStock.newValue();
        Position position1 = game.getPlayer().getOpenPositions().get(0);
        Position position2 = game.getPlayer().getOpenPositions().get(1);

        /*
         *A teszt szempontjabol lenyegtelen, de hozza kell adni az ot reprezentalo jpanelt,
         *mert ertek frissiteskor azt is frissiteni akarja, emiatt nem lehet null
         */
        position1.addPositionPanel(new PositionPanel(position1,null));
        position2.addPositionPanel(new PositionPanel(position2,null));

        game.getPlayer().refreshPositions();

        /*
         *Itt nem lehet konstansokkal tesztelni, mert a stock uj erteke random generalodik
         */
        Assert.assertEquals(100 - (otherStock.currentValue() - 100), position1.getCurrentValue(), 0.0001);
        Assert.assertEquals(100 - (otherStock.currentValue() - 100) * 2, position2.getCurrentValue(), 0.0001);

    }


}
