package gui;

import game.Game;
import game.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

/**
 * Uj jatek letrehozasahoz ActionListener
 */
class NewGameActionListener implements ActionListener {

    private final Frame frame;

    /**
     * Konstruktor
     * @param parentFrame Szulo frame, a JOptionPane-nek van ra szuksege
     */
    public NewGameActionListener(Frame parentFrame){
        frame = parentFrame;
    }

    /**
     * Esemeny bekezeleset kezelo fuggveny
     * Megkerdezi a felhasznalot, hogy milyen neven hozzon letre jatekost
     * majd letrehozza azt es elinditja a jatekot
     * @param e Esemeny
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String playerName;

        do {
            playerName = (String) JOptionPane.showInputDialog(frame,
                    "Please enter your name ",
                    "New game",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "Warren Buffett");
            if (playerName == null) { /// cancelled
                return;
            }
        } while (!(playerName.length() > 0));

        if(Frame.gameAdded) {
            Frame.timer.cancel();
            Frame.timer.purge();
        }

        Player player = new Player(playerName, 5000);
        Game game = new Game(player);
        Frame.addGame(game);

        Frame.timer = new Timer();
        Frame.timer.scheduleAtFixedRate(game, 3000, 3000);
    }

}