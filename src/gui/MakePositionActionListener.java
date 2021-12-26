package gui;

import com.sun.codemodel.internal.JOp;
import exceptions.NotEnoughFundException;
import game.Position;
import game.Stock;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MakePositionActionListener implements ActionListener {
    private final Stock stock;
    private final Frame frame;

    /**
     * Konstruktor
     * @param stock Reszveny, amibol letrehozzuk a poziciot
     * @param parentFrame Szulo frame, a JOptionPane-nek van ra szuksege
     */
    public MakePositionActionListener(Stock stock, Frame parentFrame){
        this.stock = stock;
        this.frame = parentFrame;
    }

    /**
     * Az esemeny bekovetkezeset kezelo fuggveny
     * Kommunikal a felhasznaloval, elkeri tole a szukseges parametereket,
     * amiknemk az ervenyesseget is ellenorzi, majd letrehozza a poziciot, amennyiben
     * minden megadott parameter helyes
     * @param e Esemeny
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        double value = 0;
        double leverage = 1;
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

                    boolean acceptableLeverage = false;
                    while (!acceptableLeverage) {
                        try {
                            double tmpLeverage = Double.parseDouble((String) JOptionPane.showInputDialog(frame,
                                    "If you wish to use leverage," + System.getProperty("line.separator") +
                                            "please enter the desired leverage (100% - 1000%)." +
                                            System.getProperty("line.separator") +
                                            "The given value will be interpreted in percenteage.",
                                    "New position",
                                    JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    null,
                                    "100"));
                            if(tmpLeverage >= 100 && tmpLeverage <= 1000){
                                acceptableLeverage = true;
                                leverage = tmpLeverage / 100;
                            }
                        } catch (NumberFormatException numberFormatException) {
                            /*
                             * Nem megfelelo a formatum, ekkor nem jon letre pozicio,
                             * ilyenkor a ciklusnak folytatodnia kell
                             */
                        } catch (NullPointerException nullPointerException) {
                            return;
                        }

                    }
                    value = tmpvalue;
                }
            } catch (NumberFormatException numberFormatException) {
                /*
                 * Nem megfelelo a formatum, ekkor nem jon letre pozicio,
                 * ilyenkor a ciklusnak folytatodnia kell
                 */
            }catch (NullPointerException nullPointerException){
                return;
            }
        }


        if(e.getActionCommand().equals("long")) {
            try {
                Position position = Frame.getGame().getPlayer().makePosition(this.stock, value, Position.PositionType.LONG, leverage);
                Frame.refreshCapital();
                PositionPanel positionPanel = new PositionPanel(position, Frame.activePositionsPanel);
                position.addPositionPanel(positionPanel);
                Frame.activePositionsPanel.add(positionPanel);
            } catch (NotEnoughFundException notEnoughFundException) {
                JOptionPane.showMessageDialog(frame, "Not enough fund.", "Not enough fund", JOptionPane.WARNING_MESSAGE);
            }
        }
        else {
            try {
                Position position = Frame.getGame().getPlayer().makePosition(this.stock, value, Position.PositionType.SHORT, leverage);
                Frame.refreshCapital();
                PositionPanel positionPanel = new PositionPanel(position, Frame.activePositionsPanel);
                position.addPositionPanel(positionPanel);
                Frame.activePositionsPanel.add(positionPanel);
            } catch (NotEnoughFundException notEnoughFundException) {
                JOptionPane.showMessageDialog(frame, "Not enough fund.", "Not enough fund", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}