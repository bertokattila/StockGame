import com.bulenkov.darcula.DarculaLaf;
import gui.Frame;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;

public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException {

        BasicLookAndFeel darcula = new DarculaLaf();
        UIManager.setLookAndFeel(darcula);




    /*    try {
            player.makePosition(stocks.get(2), 2, game.Position.PositionType.LONG);
        }catch (exceptions.NotEnoughFundException e){
            System.out.println("Nincs eleg toke");
        }*/



        Frame frame = new Frame();
        frame.setVisible(true);
    }
}
