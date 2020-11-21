package gui;

import game.Game;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SaveActionListener implements ActionListener {
    private final JMenuItem saveButton;

    public SaveActionListener(JMenuItem saveButton){
        this.saveButton = saveButton;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(Frame.getGame() != null) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setMultiSelectionEnabled(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".ser files", "ser");
            fileChooser.setFileFilter(filter);
            fileChooser.setSelectedFile(new File(Frame.getGame().getPlayer().name + ".ser"));

            if (fileChooser.showSaveDialog(saveButton) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                if(file.getName().toLowerCase().endsWith(".ser")){
                    try {
                        FileOutputStream fileOut = new FileOutputStream(file);
                        ObjectOutputStream out = new ObjectOutputStream(fileOut);
                        out.writeObject(Frame.getGame());
                        out.close();
                        fileOut.close();
                        JOptionPane.showMessageDialog(saveButton,"Saving successful", "Saving", JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException exception) {
                        JOptionPane.showMessageDialog(saveButton,"Saving unsuccessful", "Saving", JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(saveButton,"Extension must be .ser, please try again", "Saving", JOptionPane.WARNING_MESSAGE);
                }
            }
        }else {
            JOptionPane.showMessageDialog(saveButton,"No game to be saved", "Saving", JOptionPane.WARNING_MESSAGE);
        }
    }
}
