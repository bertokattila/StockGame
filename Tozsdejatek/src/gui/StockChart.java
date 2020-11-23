package gui;

import game.Stock;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StockChart extends JFrame {
    XYSeriesCollection dataset = new XYSeriesCollection();
    XYSeries series = new XYSeries("sock");

    public StockChart(Stock stock) {
        super(stock.getName());
        JFreeChart lineChart = ChartFactory.createXYLineChart(
                stock.getName(),
                "Time","Price",
                createDataset(stock),
                PlotOrientation.VERTICAL,
                false,false,false);

        ChartPanel chartPanel = new ChartPanel( lineChart );

        chartPanel.setOpaque(true);

        chartPanel.setPreferredSize( new Dimension( 560 , 367 ) );
        setContentPane( chartPanel );

        this.addWindowListener(new CloseEventListener(stock));
    }

    private XYDataset createDataset(Stock stock) {

        for (int i = 0; i < stock.getValueHistory().size(); i++){
            series.add((double) i, (double) stock.getValueHistory().get(i) );
        }
        dataset.addSeries(series);
        return dataset;
    }

    public void addNewValue(double value, int time){
        series.add((double) time, value);
    }

    static class CloseEventListener extends WindowAdapter{
        Stock stock;

        public CloseEventListener(Stock stock){
            this.stock = stock;
        }
        @Override
        public void windowClosing(WindowEvent e)
        {
            stock.dropChart();
            e.getWindow().dispose();
        }
    }
}