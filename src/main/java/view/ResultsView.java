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
import java.awt.font.TextAttribute;
import java.util.Map;

public class ResultsView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "results";
    private final ResultsViewModel resultsViewModel;
    private final JPanel gamesPanel;
    private final JButton backButton;

    public ResultsView(ResultsViewModel resultsViewModel) {
        this.resultsViewModel = resultsViewModel;
        this.resultsViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        // Create a panel for the back button at the top
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        topPanel.add(backButton);
        add(topPanel, BorderLayout.NORTH);

        // Create a scrollable panel for games
        gamesPanel = new JPanel();
        gamesPanel.setLayout(new BoxLayout(gamesPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(gamesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            // Handle back button action
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            ResultsState state = (ResultsState) evt.getNewValue();
            updateGamesDisplay(state);
        }
    }

    private void updateGamesDisplay(ResultsState state) {
        gamesPanel.removeAll();

        for (Game game : state.getGames()) {
            JPanel gameCard = createGameCard(game);
            gamesPanel.add(gameCard);
            gamesPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacing between cards
        }

        gamesPanel.revalidate();
        gamesPanel.repaint();
    }

    private JPanel createGameCard(Game game) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            new EmptyBorder(10, 10, 10, 10)
        ));

        // Left panel for image
        JPanel leftPanel = new JPanel(new BorderLayout());
        // You would need to implement image loading ***********************************************************************
        JLabel imageLabel = new JLabel("Game Image");
        imageLabel.setPreferredSize(new Dimension(120, 120));
        leftPanel.add(imageLabel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel(game.getTitle());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        if ("1".equals(game.getIsOnSale())) {
            JLabel normalPriceLabel = new JLabel("$" + game.getNormalPrice());
            normalPriceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            @SuppressWarnings("unchecked")
            Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) normalPriceLabel.getFont().getAttributes();
            attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
            normalPriceLabel.setFont(normalPriceLabel.getFont().deriveFont(attributes));
            pricePanel.add(normalPriceLabel);
            
            JLabel salePriceLabel = new JLabel("$" + game.getSalePrice());
            salePriceLabel.setFont(new Font("Arial", Font.BOLD, 14));
            salePriceLabel.setForeground(Color.GREEN);
            pricePanel.add(salePriceLabel);

            DecimalFormat df = new DecimalFormat("#.##");
            String savingsStr = df.format(Double.parseDouble(game.getSavings()));
            JLabel savingsLabel = new JLabel(" (-" + savingsStr + "%)");
            pricePanel.add(savingsLabel);
        } else {
            JLabel priceLabel = new JLabel("$" + game.getNormalPrice());
            pricePanel.add(priceLabel);
        }

        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ratingPanel.add(new JLabel("Steam Rating: " + game.getSteamRatingText() + 
                                 " (" + game.getSteamRatingPercent() + "%) " +
                                 "from " + game.getSteamRatingCount() + " reviews"));

        if (!"0".equals(game.getMetacriticScore())) {
            JLabel metacriticLabel = new JLabel("Metacritic: " + game.getMetacriticScore());
            ratingPanel.add(metacriticLabel);
        }

        rightPanel.add(titleLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightPanel.add(pricePanel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightPanel.add(ratingPanel);

        card.add(leftPanel, BorderLayout.WEST);
        card.add(rightPanel, BorderLayout.CENTER);

        return card;
    }
}