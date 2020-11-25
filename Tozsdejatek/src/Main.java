import com.bulenkov.darcula.DarculaLaf;
import gui.Frame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.StandardChartTheme;
import com.apple.eawt.Application;


import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException {

       ///Drakula lnf alkalmazasa
        BasicLookAndFeel darcula = new DarculaLaf();
        UIManager.setLookAndFeel(darcula);

        ///JFreeChart look igazitasa a Drakula lnf-hez
        StandardChartTheme theme = new StandardChartTheme("Custom", false);
        Color color = new Color(60, 63, 65);
        theme.setPlotBackgroundPaint(color.darker());
        theme.setTickLabelPaint(new Color(187, 187, 187));
        theme.setTitlePaint(new Color(187, 187, 187));
        theme.setAxisLabelPaint(new Color(187, 187, 187));
        theme.setChartBackgroundPaint(color);
        ChartFactory.setChartTheme(theme);

        ///Main window letrehozasa
        Frame frame = new Frame();

        ///Mac platform specifikus
        Application.getApplication().setDockIconImage(new ImageIcon("img/icon.png").getImage());

        frame.setVisible(true);
    }
}
