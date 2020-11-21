package gui;

import game.Game;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class OpenActionListener implements ActionListener {
    private final JMenuItem openButton;

    public OpenActionListener(JMenuItem openButton){
        this.openButton = openButton;
    }
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
                Frame.addGame(game);
            } catch (IOException | ClassNotFoundException exception) {
                exception.printStackTrace();
            }
        }
    }
}
