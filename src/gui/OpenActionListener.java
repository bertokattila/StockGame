package gui;

import game.Game;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Timer;

public class OpenActionListener implements ActionListener {
    /**
     * Jmenuben levo Open button,
     * amire majd a showOpenDialog hivatkozik
     */
    private final JMenuItem openButton;

    /**
     * Konstruktor
     * @param openButton Open button
     */
    public OpenActionListener(JMenuItem openButton){
        this.openButton = openButton;
    }

    /**
     * Esemeny bekezeleset kezelo fuggveny
     * megjeleniti a fajlrendszert egy ablakban, aminek segitsegevel
     * a felhaasznalo kivalaszthatja a mentett allapotot, amit folytatni szeretne
     * gondoskodik arrol, hogy csak megfelelo tartalommal rendelkezo .ser fajlok toltodhetnek be
     * @param e Esemeny
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".ser files", "ser");
        fileChooser.setFileFilter(filter);
        if (fileChooser.showOpenDialog(openButton) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try{
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                Game game = (Game) objectInputStream.readObject();

                objectInputStream.close();
                fileInputStream.close();


                if(Frame.gameAdded) {
                    Frame.timer.cancel();
                    Frame.timer.purge();
                }
                Frame.addGame(game);

                Frame.timer = new Timer();
                Frame.timer.scheduleAtFixedRate(game, 3000, 3000);

            } catch (IOException | ClassNotFoundException exception) {
                JOptionPane.showMessageDialog(openButton,"Error during loading the file", "Opening saved game", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
