package gui;

import exceptions.NotEnoughFundException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Timer;
import game.Game;
import game.Player;
import game.Position;
import game.Stock;

public class Frame extends JFrame {

    Game game;
    boolean gameAdded = false;
    public DecimalFormat df = new DecimalFormat("0.00");

    protected java.util.Timer timer;

    JPanel north = new JPanel();
    JPanel menuContainer = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
    JPanel header = new JPanel(new GridLayout());
    JPanel leftHeader = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel rightHeader = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    JPanel stocksPanel = new JPanel();
    JPanel activePositionsPanel = new JPanel(new BorderLayout());
    JPanel closedPositionsPanel = new JPanel(new BorderLayout());
    JTabbedPane pages = new JTabbedPane();


    JLabel name;
    JLabel capital;

    JMenuBar menuBar = new JMenuBar();
    JMenu  gameMenu = new JMenu("Game");

    JMenuItem  newMenuItem = new JMenuItem("New");
    JMenuItem  openMenuItem = new JMenuItem("Open");
    JMenuItem  saveMenuItem = new JMenuItem("Save");


    public Frame(){
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
        stocksPanel.setBackground(new Color(45,45,45));
        activePositionsPanel.setBackground(new Color(45,45,45));
        closedPositionsPanel.setBackground(new Color(45,45,45));


        gameMenu.add(newMenuItem);
        gameMenu.add(openMenuItem);
        gameMenu.add(saveMenuItem);
        menuBar.add(gameMenu);

        newMenuItem.addActionListener(new NewGameActionListener(this));

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
        activePositionsPanel.add(new JLabel("Active Positions"));
        closedPositionsPanel.add(new JLabel("Closed Positions"));

        stocksPanel.setLayout(new BoxLayout(stocksPanel, BoxLayout.Y_AXIS));
        JScrollPane stocksPanelScrollPane = new JScrollPane(stocksPanel);

        pages.add(stocksPanelScrollPane, "Stocks");
        pages.add(activePositionsPanel, "Active Positions");
        pages.add(closedPositionsPanel, "Closed Positions");
        JLabel stocksTabLabel = new JLabel("Stocks");
        stocksTabLabel.setFont(new Font("SF", Font.PLAIN, 20));
        JLabel activePositionstabLabel = new JLabel("Active Positions");
        activePositionstabLabel.setFont(new Font("SF", Font.PLAIN, 20));
        JLabel closedPositionsTabLabel = new JLabel("Closed Positions");
        closedPositionsTabLabel.setFont(new Font("SF", Font.PLAIN, 20));
        pages.setTabComponentAt(0, stocksTabLabel);
        pages.setTabComponentAt(1, activePositionstabLabel);
        pages.setTabComponentAt(2, closedPositionsTabLabel);

        activePositionsPanel.setLayout(new BoxLayout(activePositionsPanel, BoxLayout.Y_AXIS));
        PositionPanel positionPanel1 = new PositionPanel();
        PositionPanel positionPanel2= new PositionPanel();
        positionPanel1.setPreferredSize(new Dimension(100, 20));
        positionPanel1.setMaximumSize(new Dimension(100, 20));
        positionPanel1.setMinimumSize(new Dimension(100, 20));
        positionPanel2.setPreferredSize(new Dimension(100, 20));
        positionPanel2.setMaximumSize(new Dimension(100, 20));
        positionPanel2.setMinimumSize(new Dimension(100, 20));
        activePositionsPanel.add(new PositionPanel());
        activePositionsPanel.add(new PositionPanel());

        //this.add(pages, BorderLayout.CENTER);
        north.add(pages);
        this.add(north, BorderLayout.NORTH);
        this.setMinimumSize(new Dimension(1000, 500));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public Game getGame(){
        return this.game;
    }

    /**
     * Jatek hozzaadasa, lehet uj jatek vagy betoltott
     * @param game jatek objektum
     */
    public void addGame(Game game){
        this.game = game;
        name.setText("Player: " + game.getPlayer().name);
        capital.setText("Capital: " + df.format(game.getPlayer().getCapital()) + "$");
        gameAdded = true;
        /*
         * Rendering stocks
         */
        for (Stock stock:
        game.getStocks()){
            StockPanel stockPanel = new StockPanel(stock, this);
            stock.setStockPanel(stockPanel);
            stocksPanel.add(stockPanel);
        }
    }

}
