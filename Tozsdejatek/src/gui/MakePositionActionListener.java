package gui;

import exceptions.NotEnoughFundException;
import game.Position;
import game.Stock;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Pozicio letrehozasa a adott reszvenybol
 */
class MakePositionActionListener implements ActionListener {
    private Stock stock;
    private Frame frame;

    public MakePositionActionListener(Stock stock, Frame parentFrame){
        this.stock = stock;
        this.frame = parentFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        double value = 0;
        boolean acceptableValue = false;
        while (!acceptableValue) {
            try {
                double tmpvalue = Double.parseDouble((String) JOptionPane.showInputDialog(frame,
                        "Please enter the value in dollars ",
                        "New position",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "100"));
                if (tmpvalue >= 0) {
                    acceptableValue = true;
                    value = tmpvalue;
                }
            } catch (NumberFormatException numberFormatException) {
                /**
                 * Nem megfelelo a formatum, ekkor nem jon letre pozicio,
                 * ilyenkor a ciklusnak folytatodnia kell
                 */
            }catch (NullPointerException nullPointerException){
                return;
            }
        }


        if(e.getActionCommand().equals("long")) {
            try {
                Position position = frame.getGame().getPlayer().makePosition(this.stock, value, Position.PositionType.LONG);
                frame.capital.setText("Capital:" + Frame.df.format(frame.getGame().getPlayer().getCapital()) + "$");
                PositionPanel positionPanel = new PositionPanel(position, frame.activePositionsPanel);
                position.addPositionPanel(positionPanel);
                frame.activePositionsPanel.add(positionPanel);
            } catch (NotEnoughFundException notEnoughFundException) {
                notEnoughFundException.printStackTrace();
            }
        }
        else {
            try {
                Position position = frame.getGame().getPlayer().makePosition(this.stock, value, Position.PositionType.SHORT);
                frame.capital.setText("Capital:" + Frame.df.format(frame.getGame().getPlayer().getCapital()) + "$");
                PositionPanel positionPanel = new PositionPanel(position, frame.activePositionsPanel);
                position.addPositionPanel(positionPanel);
                frame.activePositionsPanel.add(positionPanel);
            } catch (NotEnoughFundException notEnoughFundException) {
                notEnoughFundException.printStackTrace();
            }
        }
    }
}