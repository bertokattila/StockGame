package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import game.Game;
import game.Position;
import game.Stock;

public class Frame extends JFrame {

    /**
     * Referencia az aktualis jatekmenet objektumara
     */
    private static Game game;

    /**
     * Indikalja, hogy be van-e mar toltve jatekmenet
     */
    static boolean gameAdded = false;

    /**
     * Ket tizedes jegyre kerekito objektum
     */
    public static DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Timer, ami megfelelo idokozonkent lepteti a game-objektumot
     */
    protected static java.util.Timer timer;

    /**
     * Jpanel container, ami arra utal, hogy a tartalma
     * majd a frame Borderlayout-janak a north szegmensebe kerul
     */
    private static final JPanel north = new JPanel();

    /**
     * A Menut tarttalmazo container panel
     */
    private static final JPanel menuContainer = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));

    /**
     * Fejlecet tartalmazo panel
     */
    private static final JPanel header = new JPanel(new GridLayout());

    /**
     * A fejlec bal oldali tartalmat (Player name) tartalmazo panel
     */
    private static final JPanel leftHeader = new JPanel(new FlowLayout(FlowLayout.LEFT));

    /**
     * A fejlec jobb oldali tartalmat (Elerheto toke) tartalmazo panel
     */
    private static final JPanel rightHeader = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    /**
     * Panem ami a reszvenyek paneljeti tartalmazza
     */
    private static final JPanel stocksPanel = new JPanel();

    /**
     * Panel, ami az aktiv poziciokat tartalmazza
     */
    public static final JPanel activePositionsPanel = new JPanel();

    /**
     * Panel, ami a lezart poziciokat tartalmazza
     */
    private static final JPanel closedPositionsPanel = new JPanel();

    /**
     * Fejlec, ami segitsegevel egyertelmu, hogy a lezarult poziciok egyes attributumai mit jelentenek
     */
    private static final JPanel closedPositionsHeader = new JPanel(new GridLayout(0,8));

    /**
     * Fejlec, ami segitsegevel egyertelmu, hogy az aktiv poziciok egyes attributumai mit jelentenek
     */
    private static final JPanel activePositionsHeader = new JPanel(new GridLayout(0,8));

    /**
     * Lapozhato oldalakat tartalmazo Pane
     * Segitsegevel lehet valtani a reszvenyek, aktiv poziciok es lezarult poziciok kozott
     */
    private static final JTabbedPane pages = new JTabbedPane();

    /**
     * Az aktualis jatekos nevet tartalmazo label
     */
    private static JLabel name;

    /**
     * Az aktualis elerheto toket megado label
     */
    private static JLabel capital;

    /**
     * Menubar, ami tartalmazza a menut
     */
    private static final JMenuBar menuBar = new JMenuBar();

    /**
     * Jatek menu
     */
    private static final JMenu  gameMenu = new JMenu("Game");

    /**
     * Menupont, amivel uj jatekot lehet kezdeni
     */
    private static final JMenuItem  newMenuItem = new JMenuItem("New game");

    /**
     * Menupont, amivel meg lehet nyitni mentett jatekokat
     */
    private static final JMenuItem  openMenuItem = new JMenuItem("Open game");

    /**
     * Menupont, amivel el lehet menteni az aktualis jatekot
     */
    private static final JMenuItem  saveMenuItem = new JMenuItem("Save game");

    /**
     * Konstruktor, ami inicializalja az alapveto gui-elemeket, szerkezetileg osszerakja oket,
     * beallitja a megfelelo parametereket, majd vegul megjeleniti az ablakot
     */
    public Frame(){
        super("Stock game");
        this.setMinimumSize(new Dimension(1050, 510));
        name = new JLabel("Player: ");
        capital = new JLabel("Capital: ");
        name.setFont(new Font("SF", Font.BOLD, 25));
        capital.setFont(new Font("SF", Font.BOLD, 25));

        df.setRoundingMode(RoundingMode.HALF_UP);

        gameMenu.add(newMenuItem);
        gameMenu.add(openMenuItem);
        gameMenu.add(saveMenuItem);
        menuBar.add(gameMenu);

        newMenuItem.addActionListener(new NewGameActionListener(this));
        saveMenuItem.addActionListener(new SaveActionListener(saveMenuItem));
        openMenuItem.addActionListener(new OpenActionListener(openMenuItem));

        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));

        menuContainer.add(menuBar);

        leftHeader.add(name, FlowLayout.LEFT);
        rightHeader.add(capital);

        header.add(leftHeader);
        header.add(rightHeader);
        north.add(menuContainer);
        north.add(header);

        stocksPanel.setLayout(new BoxLayout(stocksPanel, BoxLayout.Y_AXIS));
        JScrollPane stocksPanelScrollPane = new JScrollPane(stocksPanel);

        activePositionsPanel.setLayout(new BoxLayout(activePositionsPanel, BoxLayout.Y_AXIS));
        JScrollPane activePositionsScrollPane = new JScrollPane(activePositionsPanel);

        closedPositionsPanel.setLayout(new BoxLayout(closedPositionsPanel, BoxLayout.Y_AXIS));
        JScrollPane closedPositionsScrollPane = new JScrollPane(closedPositionsPanel);


        stocksPanelScrollPane.setPreferredSize(new Dimension(300, this.getHeight() - 150));
        activePositionsScrollPane.setPreferredSize(new Dimension(300, this.getHeight() - 150));
        closedPositionsScrollPane.setPreferredSize(new Dimension(300, this.getHeight() - 150));
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                stocksPanelScrollPane.setPreferredSize(new Dimension(300, Frame.this.getHeight() - 150));
                activePositionsScrollPane.setPreferredSize(new Dimension(300, Frame.this.getHeight() - 150));
                closedPositionsScrollPane.setPreferredSize(new Dimension(300, Frame.this.getHeight() - 150));
            }
        });

        pages.add(stocksPanelScrollPane, "Stocks");
        pages.add(activePositionsScrollPane, "Active Positions");
        pages.add(closedPositionsScrollPane, "Closed Positions");
        JLabel stocksTabLabel = new JLabel("Stocks");
        stocksTabLabel.setFont(new Font("SF", Font.PLAIN, 20));
        JLabel activePositionstabLabel = new JLabel("Active Positions");
        activePositionstabLabel.setFont(new Font("SF", Font.PLAIN, 20));
        JLabel closedPositionsTabLabel = new JLabel("Closed Positions");
        closedPositionsTabLabel.setFont(new Font("SF", Font.PLAIN, 20));
        pages.setTabComponentAt(0, stocksTabLabel);
        pages.setTabComponentAt(1, activePositionstabLabel);
        pages.setTabComponentAt(2, closedPositionsTabLabel);

        activePositionsHeader.setPreferredSize(new Dimension(1000, 20));
        activePositionsHeader.setMaximumSize(new Dimension(1000, 20));
        activePositionsHeader.setMinimumSize(new Dimension(1000, 20));

        closedPositionsHeader.setPreferredSize(new Dimension(1000, 20));
        closedPositionsHeader.setMaximumSize(new Dimension(1000, 20));
        closedPositionsHeader.setMinimumSize(new Dimension(1000, 20));

        JLabel idLabel = new JLabel("Id");
        idLabel.setFont(new Font("SF", Font.BOLD, 14));
        activePositionsHeader.add(idLabel);

        JLabel stockNameLabel = new JLabel("Stock name");
        stockNameLabel.setFont(new Font("SF", Font.BOLD, 14));
        activePositionsHeader.add(stockNameLabel);

        JLabel typeLabel = new JLabel("Type");
        typeLabel.setFont(new Font("SF", Font.BOLD, 14));
        activePositionsHeader.add(typeLabel);

        JLabel leverageLabel = new JLabel("Leverage");
        leverageLabel.setFont(new Font("SF", Font.BOLD, 14));
        activePositionsHeader.add(leverageLabel);

        JLabel startingValueLabel = new JLabel("Starting value");
        startingValueLabel.setFont(new Font("SF", Font.BOLD, 14));
        activePositionsHeader.add(startingValueLabel);

        JLabel currentValueLabel = new JLabel("Current value");
        currentValueLabel.setFont(new Font("SF", Font.BOLD, 14));
        activePositionsHeader.add(currentValueLabel);

        JLabel changeLabel = new JLabel("Change");
        changeLabel.setFont(new Font("SF", Font.BOLD, 14));
        activePositionsHeader.add(changeLabel);

        JLabel idLabelClosedPositionsHeader = new JLabel("Id");
        idLabelClosedPositionsHeader.setFont(new Font("SF", Font.BOLD, 14));
        closedPositionsHeader.add(idLabelClosedPositionsHeader);

        JLabel stockNameLabelClosedPositionsHeader = new JLabel("Stock name");
        stockNameLabelClosedPositionsHeader.setFont(new Font("SF", Font.BOLD, 14));
        closedPositionsHeader.add(stockNameLabelClosedPositionsHeader);

        JLabel typeLabelClosedPositionsHeader = new JLabel("Type");
        typeLabelClosedPositionsHeader.setFont(new Font("SF", Font.BOLD, 14));
        closedPositionsHeader.add(typeLabelClosedPositionsHeader);

        JLabel leverageLabelClosedPositionsHeader = new JLabel("Leverage");
        leverageLabelClosedPositionsHeader.setFont(new Font("SF", Font.BOLD, 14));
        closedPositionsHeader.add(leverageLabelClosedPositionsHeader);

        JLabel startingValueLabelClosedPositionsHeader = new JLabel("Starting value");
        startingValueLabelClosedPositionsHeader.setFont(new Font("SF", Font.BOLD, 14));
        closedPositionsHeader.add(startingValueLabelClosedPositionsHeader);

        JLabel currentValueLabelClosedPositionsHeader = new JLabel("Selling value");
        currentValueLabelClosedPositionsHeader.setFont(new Font("SF", Font.BOLD, 14));
        closedPositionsHeader.add(currentValueLabelClosedPositionsHeader);

        JLabel changeLabelClosedPositionsHeader = new JLabel("Change");
        changeLabelClosedPositionsHeader.setFont(new Font("SF", Font.BOLD, 14));
        closedPositionsHeader.add(changeLabelClosedPositionsHeader);

        JLabel profitLabelClosedPositionsHeader = new JLabel("Profit");
        profitLabelClosedPositionsHeader.setFont(new Font("SF", Font.BOLD, 14));
        closedPositionsHeader.add(profitLabelClosedPositionsHeader);

        north.add(pages);
        this.add(north, BorderLayout.NORTH);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    /**
     * Visszaadja az aktualis jatekmenetet
     * @return Jatekmenet (game objektum)
     */
    public static Game getGame(){
        return game;
    }

    /**
     * Jatek hozzaadasa, lehet uj jatek vagy betoltott
     * @param game jatek objektum
     */
    public static void addGame(Game game) {
        stocksPanel.removeAll();
        activePositionsPanel.removeAll();
        closedPositionsPanel.removeAll();
        activePositionsPanel.add(activePositionsHeader);
        closedPositionsPanel.add(closedPositionsHeader);
        stocksPanel.updateUI();
        activePositionsPanel.updateUI();
        closedPositionsPanel.updateUI();
        if(gameAdded){
            for (Stock stock:
                 Frame.game.getStocks()) {
                stock.dropChart();
            }
        }
        Frame.game = game;
        name.setText("Player: " + game.getPlayer().getName());
        capital.setText("Capital: " + df.format(game.getPlayer().getCapital()) + "$");
        gameAdded = true;
        /*
         * Rendering stocks
         */
        for (Stock stock:
        game.getStocks()){
            StockPanel stockPanel = new StockPanel(stock);
            stock.setStockPanel(stockPanel);
            stocksPanel.add(stockPanel);
        }

        for (Position position:
        game.getPlayer().getOpenPositions()){
            PositionPanel positionPanel = new PositionPanel(position, activePositionsPanel);
            position.addPositionPanel(positionPanel);
            activePositionsPanel.add(positionPanel);
        }

        for (Position position:
                game.getPlayer().getClosedPositions()){
            PositionPanel positionPanel = new PositionPanel(position, activePositionsPanel);
            positionPanel.close(position.getCurrentValue() > 0);

            position.addPositionPanel(positionPanel);
            closedPositionsPanel.add(positionPanel);
        }


    }

    /**
     * Frissiti a megjelenitett toke erteket
     */
    public static void refreshCapital(){
        capital.setText("Capital:" + Frame.df.format(game.getPlayer().getCapital()) + "$");
    }

    /**
     * Visszaadja a lezart poziciokat tartalmazo panelt
     * @return lezart poziciokat tartalmazo panel
     */
    public static JPanel getClosedPositionsPanel(){
        return closedPositionsPanel;
    }

}
