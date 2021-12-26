package gui;

import game.Stock;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowStockChartActionListener implements ActionListener {

    /**
     * Reszveny, amihez letrehozza a chartot
     */
    private final Stock stock;

    /**
     * Konstruktor
     * @param stock Reszveny
     */
    public ShowStockChartActionListener(Stock stock){
        this.stock = stock;
    }

    /**
     * Esemeny bekovetkezeset kezelo fuggveny
     * Amennyiben nincsen chartja a reszvenynek, letrehoz neki egyet,
     * ha van, akkor pedig a chart ablakjara fokuszal
     * @param e Esemeny
     */
    public void actionPerformed(ActionEvent e) {
        StockChart chart = stock.getChart();
        if(chart == null){
            StockChart newChart = new StockChart(stock);
            newChart.pack();
            stock.addChart(newChart);
            newChart.setVisible( true );
        }else {
            chart.setVisible(true);
            chart.toFront();
            chart.requestFocus();
        }
    }
}
