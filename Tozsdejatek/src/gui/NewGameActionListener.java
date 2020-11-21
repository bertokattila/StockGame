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

    private Frame frame;
    public NewGameActionListener(Frame parentFrame){
        frame = parentFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String playerName;

        do {
            playerName = (String) JOptionPane.showInputDialog(frame,
                    "Please enter your name ",
                    "New game.Game",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "Warren Buffett");
            if (playerName == null) { /// cancelled
                return;
            }
        } while (!(playerName.length() > 0));

        if(frame.gameAdded) {
            frame.timer.cancel();
            frame.timer.purge();
        }

        Player player = new Player(playerName, 5000);
        Game game = new Game(player);
        frame.addGame(game);

        frame.timer = new Timer();
        frame.timer.scheduleAtFixedRate(game, 0, 3000);
    }

}