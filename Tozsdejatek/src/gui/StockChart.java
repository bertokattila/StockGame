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

    /**
     * Koordinatarendszzerben elhelyezett pontoknak a sorozata
     * A mi esetunkben az X tengely az idot, az Y pedig az erteket jelenti
     */
    private final XYSeries series = new XYSeries("sock");

    /**
     * Ponstorozatok gyujtemenye
     * Lehetoseg lenne egy grafikonon tobb pontsorozatot is abrazolni, de
     * ez a funkcio nem relevans most
     */
    private final XYSeriesCollection dataset = new XYSeriesCollection();


    /**
     *  Konstruktor, ami letrehozza a chartot a megadott reszvenybol
     * @param stock Reszveny
     */
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
        setContentPane(chartPanel);

        this.addWindowListener(new CloseEventListener(stock));
    }

    /**
     * Letrehozza az XY sorozatot a reszveny eddigi ertekeibol, majd berakja egy kollekcioba,
     * @param stock A reszveny, aminebol az eddigi ertekekeit felhasznaljuk
     * @return A kollekcio, ami tartalmazza a reszveny ertekeibol allo sorozatot
     */
    private XYDataset createDataset(Stock stock) {

        for (int i = 0; i < stock.getValueHistory().size(); i++){
            series.add((double) i, (double) stock.getValueHistory().get(i) );
        }
        dataset.addSeries(series);
        return dataset;
    }

    /**
     * Uj ertek hozzaadasa a pontsorozathoz
     * @param value Ertek
     * @param time Idopont
     */
    public void addNewValue(double value, int time){
        series.add((double) time, value);
    }

    /**
     * Belso osztaly, ami azert felelos, hogy amennyiben a felhasznalo bezarja a chart ablakjat,
     * az ne foglalja feleslegesen a memoriat (hiszen a chart sajat adattipusa miatt az adott reszveny ertekei
     * ekkor redundansak a memoriaban)
     * Amennyiben ujra megnyitja a felhasznalo a chartot, ujra letrejon
     */
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