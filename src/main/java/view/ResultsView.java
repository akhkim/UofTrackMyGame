package view;

import entity.Game;
import interface_adapter.results.ResultsState;
import interface_adapter.results.ResultsViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

public class ResultsView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "ResultsView";
    private final ResultsViewModel resultsViewModel;

    private final JPanel gamesPanel;
    private final JButton backButton;
    private final JScrollPane scrollPane;

    public ResultsView(ResultsViewModel resultsViewModel) {
        this.resultsViewModel = resultsViewModel;
        this.resultsViewModel.addPropertyChangeListener(this);

        // Set layout to vertical box layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Title
        final JLabel title = new JLabel("Game Results");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 20)); // Make title more prominent
        this.add(title);

        // Back button panel
        final JPanel buttonPanel = new JPanel();
        backButton = new JButton("Show");
        buttonPanel.add(backButton);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(buttonPanel);

        // Games display panel with GridBagLayout
        gamesPanel = new JPanel();
        gamesPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Create scroll pane with increased preferred size
        scrollPane = new JScrollPane(gamesPanel);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Set preferred size to make the scroll pane taller
        scrollPane.setPreferredSize(new Dimension(1000, 600)); // Width: 1000, Height: 600

        // Ensure the scroll pane takes up more vertical space
        scrollPane.setMinimumSize(new Dimension(1000, 500));
        scrollPane.setMaximumSize(new Dimension(2000, 800));

        // Remove horizontal scroll bar
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(scrollPane);

        // Add action listener to back button
        backButton.addActionListener(this);
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
        if (evt.getSource().equals(backButton)) {
            resultsViewModel.firePropertyChanged();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ResultsState state = (ResultsState) evt.getNewValue();
        updateGamesDisplay(state);
    }

    private void updateGamesDisplay(ResultsState state) {
        System.out.println(state.getGames());
        // Clear existing games
        gamesPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10, 10, 10, 10);

        int row = 0;
        int col = 0;
        int cardWidth = 200;
        int cardHeight = 75;
        int columns = 3;

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

        // Calculate the preferred size of the gamesPanel
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
        gameCard.setBorder(new EmptyBorder(10, 10, 10, 10));

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

        return gameCard;
    }

    public String getViewName() {
        return viewName;
    }
}
