package gui;

import game.Stock;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowStockChartActionListener implements ActionListener {
    Stock stock;

    public ShowStockChartActionListener(Stock stock){
        this.stock = stock;
    }

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
