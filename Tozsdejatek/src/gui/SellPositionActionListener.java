package gui;

import game.Position;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellPositionActionListener implements ActionListener {

    /**
     * A hozza tartozo pozicio, amit eladhat
     */
    private final Position position;

    /**
     * Konstruktor
     * @param position A pozicioja
     */
    public SellPositionActionListener(Position position){
        this.position = position;
    }

    /**
     * Esemeny bekovetkezeset kezelo fuggveny,
     * Szol a Player-objektumnak, hogy adja el a poziciot,
     * majd a gui-nak, hogy frissitse a jatekos tokejet
     * @param e Esemeny
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Frame.getGame().getPlayer().sellPosition(position);
        Frame.refreshCapital();
    }
}
