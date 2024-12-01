package view;

import entity.Game;
import interface_adapter.home.HomeController;
import interface_adapter.results.ResultsController;
import interface_adapter.results.ResultsState;
import interface_adapter.results.ResultsViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

public class ResultsView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "ResultsView";
    private final ResultsViewModel resultsViewModel;
    private final Game selected = null;
    private ResultsController resultsController;
    private HomeController homeController;

    private final JPanel gamesPanel;
    private final JButton backButton;
    private final JScrollPane scrollPane;

    public ResultsView(ResultsViewModel resultsViewModel) {
        this.resultsViewModel = resultsViewModel;
        this.resultsViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        gamesPanel = new JPanel();
        gamesPanel.setLayout(new GridLayout(0, 3, 10, 10)); // 0 rows, 3 columns, with 10px gaps

        scrollPane = new JScrollPane(gamesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        backButton = new JButton("Back");
        backButton.addActionListener(this);

        add(scrollPane, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);

        // Set preferred size to fit three games perfectly
        setPreferredSize(new Dimension(1000, 700)); // Adjust dimensions as needed

        // Set minimum size to ensure the window is not resized below a certain size
        setMinimumSize(new Dimension(900, 600)); // Adjust dimensions as needed
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
        if (evt.getSource() == backButton) {
            // Switch to the search view
            homeController.execute();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getNewValue() instanceof ResultsState) {
        final ResultsState state = (ResultsState) evt.getNewValue();
        updateGamesDisplay(state);
        }
    }

    private void updateGamesDisplay(ResultsState state) {
        gamesPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10, 10, 10, 10);

        int row = 0;
        int col = 0;
        int cardWidth = 200;
        int cardHeight = 100;
        int columns = 3;

        if (state.getGames().isEmpty()) {
            JLabel errorLabel = new JLabel("No games found");
            gamesPanel.add(errorLabel);
        } else {
            for (Game game : state.getGames()) {
                JPanel gameCard = createGameCard(game);
                gbc.gridx = col;
                gbc.gridy = row;
                gamesPanel.add(gameCard, gbc);
                gameCard.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
                col++;
                if (col >= columns) {
                    col = 0;
                    row++;
                }
            }
        }

        int panelWidth = columns * (cardWidth + gbc.insets.left + gbc.insets.right);
        int panelHeight = (row + 1) * (cardHeight + gbc.insets.top + gbc.insets.bottom);
        gamesPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));

        // Adjust the parent container size if needed
        gamesPanel.getParent().revalidate();
        gamesPanel.getParent().repaint();
    }

    private JPanel createGameCard(Game game) {
        JPanel gameCard = new JPanel();
        gameCard.setLayout(new BoxLayout(gameCard, BoxLayout.Y_AXIS));
        gameCard.setBorder(new EmptyBorder(15, 15, 15, 15)); // Increase padding for larger cards

        // Game title
        JLabel titleLabel = new JLabel(game.getTitle());
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Make game titles more prominent
        gameCard.add(titleLabel);

        // Price information
        JPanel pricePanel = new JPanel();
        pricePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        if ("1".equals(game.getIsOnSale())) {
            JLabel normalPriceLabel = new JLabel("Original: $" + game.getNormalPrice());
            JLabel salePriceLabel = new JLabel("Sale: $" + game.getSalePrice());

            DecimalFormat df = new DecimalFormat("#.##");
            String savingsStr = df.format(Double.parseDouble(game.getSavings()));
            JLabel savingsLabel = new JLabel("Savings: " + savingsStr + "%");

            pricePanel.add(normalPriceLabel);
            pricePanel.add(salePriceLabel);
            pricePanel.add(savingsLabel);
        } else {
            pricePanel.add(new JLabel("Price: $" + game.getNormalPrice()));
        }
        gameCard.add(pricePanel);

        // Ratings
        JPanel ratingPanel = new JPanel();
        ratingPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ratingPanel.add(new JLabel("Steam Rating: " + game.getSteamRatingText() + 
                                    " (" + game.getSteamRatingPercent() + "%)"));

        if (!"0".equals(game.getMetacriticScore())) {
            ratingPanel.add(new JLabel("Metacritic: " + game.getMetacriticScore()));
        }
        gameCard.add(ratingPanel);

        gameCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                // Handle mouse release event
                System.out.println("Mouse released on game card: " + game.getTitle());
                resultsController.execute(game);
            }
        });

        return gameCard;
    }

    public String getViewName() {
        return viewName;
    }

    public void setResultsController(ResultsController resultsController){
        this.resultsController = resultsController;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
}
