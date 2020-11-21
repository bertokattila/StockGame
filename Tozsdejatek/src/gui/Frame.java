package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.DecimalFormat;

import game.Game;
import game.Stock;

public class Frame extends JFrame {

    private static Game game;
    static boolean gameAdded = false;
    public static DecimalFormat df = new DecimalFormat("0.00");

    protected java.util.Timer timer;

    JPanel north = new JPanel();
    JPanel menuContainer = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
    JPanel header = new JPanel(new GridLayout());
    JPanel leftHeader = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel rightHeader = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    private static JPanel stocksPanel = new JPanel();
    public static JPanel activePositionsPanel = new JPanel();
    private static JPanel closedPositionsHeader = new JPanel(new GridLayout(0,7));
    private static JPanel activePositionsHeader = new JPanel(new GridLayout(0,7));
    private static final JPanel closedPositionsPanel = new JPanel();
    JTabbedPane pages = new JTabbedPane();


    private static JLabel name;
    static JLabel capital;

    JMenuBar menuBar = new JMenuBar();
    JMenu  gameMenu = new JMenu("Game");

    JMenuItem  newMenuItem = new JMenuItem("New");
    JMenuItem  openMenuItem = new JMenuItem("Open");
    JMenuItem  saveMenuItem = new JMenuItem("Save");


    public Frame(){

        this.setMinimumSize(new Dimension(1050, 510));
        name = new JLabel("Player: ");
        capital = new JLabel("Capital: ");
        name.setFont(new Font("SF", Font.BOLD, 25));
        capital.setFont(new Font("SF", Font.BOLD, 25));
        capital.setForeground(new Color(39,99,40));
      /*  leftHeader.setBackground(new Color(45,45,45));
        rightHeader.setBackground(new Color(45,45,45));
        menuContainer.setBackground(new Color(45,45,45));
        menuBar.setBackground(new Color(45,45,45));
        gameMenu.setBackground(new Color(45,45,45));*/
        //pages.setBackground(new Color(45,45,45));
   /*     stocksPanel.setBackground(new Color(45,45,45));
        activePositionsPanel.setBackground(new Color(45,45,45));
        closedPositionsPanel.setBackground(new Color(45,45,45));*/


        gameMenu.add(newMenuItem);
        gameMenu.add(openMenuItem);
        gameMenu.add(saveMenuItem);
        menuBar.add(gameMenu);

        newMenuItem.addActionListener(new NewGameActionListener(this));
        saveMenuItem.addActionListener(new SaveActionListener(saveMenuItem));
        openMenuItem.addActionListener(new OpenActionListener(openMenuItem));

        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));

        menuContainer.add(menuBar);
        //leftHeader.setBackground(Color.red);
        //rightHeader.setBackground(Color.BLUE);

        leftHeader.add(name, FlowLayout.LEFT);
        rightHeader.add(capital);

        header.add(leftHeader);
        header.add(rightHeader);
        north.add(menuContainer);
        north.add(header);


        //stocksPanel.add(new JLabel("Stocks"));
        //activePositionsPanel.add(new JLabel("Active Positions"));
        //closedPositionsPanel.add(new JLabel("Closed Positions"));

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

/*        pages.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                System.out.println("Tab: " + pages.getSelectedIndex());
                if(pages.getSelectedIndex() == 0){
                    stocksPanel.setVisible(true);
                    activePositionsPanel.setVisible(false);
                    closedPositionsPanel.setVisible(false);

                }else if(pages.getSelectedIndex() == 1){
                    stocksPanel.setVisible(false);
                    activePositionsPanel.setVisible(false);
                    closedPositionsPanel.setVisible(false);
                }else {
                    stocksPanel.setVisible(false);
                    activePositionsPanel.setVisible(false);
                    closedPositionsPanel.setVisible(true);
                }
            }
        });*/



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

        //this.add(pages, BorderLayout.CENTER);
        north.add(pages);
        this.add(north, BorderLayout.NORTH);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static Game getGame(){
        return game;
    }

    /**
     * Jatek hozzaadasa, lehet uj jatek vagy betoltott
     * @param game jatek objektum
     */
    public static void addGame(Game game){
        stocksPanel.removeAll();
        activePositionsPanel.removeAll();
        closedPositionsPanel.removeAll();
        activePositionsPanel.add(activePositionsHeader);
        closedPositionsPanel.add(closedPositionsHeader);
        stocksPanel.updateUI();
        activePositionsPanel.updateUI();
        closedPositionsPanel.updateUI();
        Frame.game = game;
        name.setText("Player: " + game.getPlayer().name);
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
    }

    public static void refreshCapital(){
        capital.setText("Capital:" + Frame.df.format(game.getPlayer().getCapital()) + "$");
    }

    public static JPanel getClosedPositionsPanel(){
        return closedPositionsPanel;
    }

}
