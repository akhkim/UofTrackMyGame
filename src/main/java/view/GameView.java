package view;

import entity.Game;
import interface_adapter.game.GameController;
import interface_adapter.game.GameState;
import interface_adapter.game.GameViewModel;
import interface_adapter.recommendation.RecommendationController;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameView extends JPanel implements PropertyChangeListener {
    private String viewName = "ResultsView";
    private final GameViewModel gameViewModel;
    private final JPanel panel;
    private GameController gameController;
    private RecommendationController recommendationController;

    public GameView(GameViewModel gameViewModel) {
        Color backgroundColor = new Color(40, 40, 40);

        this.gameViewModel = gameViewModel;
        this.gameViewModel.addPropertyChangeListener(this);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(backgroundColor);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof GameState) {
            final GameState state = (GameState) evt.getNewValue();
            updateGameWindow(state);
        }
    }

    private void updateGameWindow(GameState state) {
        Color buttonColor = new Color(242, 243, 245);
        Color textColor = new Color(224, 224, 224);
        Font labelFont = new Font("Arial", Font.BOLD, 18);
        Font secondaryFont = new Font("Arial", Font.PLAIN, 14);

        panel.removeAll();
        Game game = state.getGame();
        JLabel titleLabel = new JLabel("Game Title: " + game.getTitle());
        titleLabel.setFont(labelFont);
        titleLabel.setForeground(textColor);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Thumbnail of the game
        try {
            URL thumbUrl = new URL(game.getThumb());
            ImageIcon thumbIcon = new ImageIcon(thumbUrl);
            Image image = thumbIcon.getImage();
            int width = thumbIcon.getIconWidth();
            int height = thumbIcon.getIconHeight();
            
            if (width > 750 || height > 750) {
                width *= 0.3;
                height *= 0.3;
            } else {
                width *= 2;
                height *= 2;
            }
            
            Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
            thumbIcon = new ImageIcon(newimg);
            JLabel thumbLabel = new JLabel(thumbIcon);
            thumbLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(thumbLabel);
        } catch (MalformedURLException e) {
            System.err.println("Invalid thumbnail URL: " + e.getMessage());
        }

        // Store Name
        String storeUrl = game.getStoreName();
        JLabel storeLabel = new JLabel("<html><a href='" + storeUrl + "' style='color: #FF0000;'>Store: " + game.getStoreName() + "</a></html>"); // Change #FF0000 to your desired color
        storeLabel.setFont(secondaryFont);
        storeLabel.setForeground(textColor);
        storeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        storeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        storeLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        storeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(storeUrl));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(storeLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Sale Price
        JLabel priceLabel = new JLabel("Price: $" + game.getSalePrice());
        priceLabel.setFont(secondaryFont);
        priceLabel.setForeground(textColor);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(priceLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Metacritic Score
        JLabel metacriticLabel = new JLabel("Metacritic Score: " + game.getMetacriticScore());
        metacriticLabel.setFont(secondaryFont);
        metacriticLabel.setForeground(textColor);
        metacriticLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(metacriticLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Deal Rating
        JLabel dealRatingLabel = new JLabel("Deal Rating: " + game.getDealRating());
        dealRatingLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        dealRatingLabel.setForeground(textColor);
        dealRatingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(dealRatingLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Text Field for Tracking Price
        JLabel trackingPriceLabel = new JLabel("Enter tracking price:");
        trackingPriceLabel.setFont(secondaryFont);
        trackingPriceLabel.setForeground(textColor);
        trackingPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(trackingPriceLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        JTextField trackingPriceField = new JTextField(15);
        trackingPriceField.setMaximumSize(new Dimension(200, 25));
        trackingPriceField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(trackingPriceField);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Text Field for Email
        JLabel emailLabel = new JLabel("Enter email:");
        emailLabel.setFont(secondaryFont);
        emailLabel.setForeground(textColor);
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(emailLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        JTextField emailField = new JTextField(15);
        emailField.setMaximumSize(new Dimension(200, 25));
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(emailField);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Notify Me Button
        JButton notifyButton = new JButton("Notify Me");
        notifyButton.setFont(secondaryFont);
        notifyButton.setBackground(buttonColor);
        notifyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        notifyButton.addActionListener(e -> {
            String trackingPrice = trackingPriceField.getText();
            String email = emailField.getText();
            gameController.setPriceAlert(email, game.getGameID(), trackingPrice);
            JOptionPane.showMessageDialog(panel,
                    "Notification set for price: $" + trackingPrice + " to email: " + email,
                    "Notification Set",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        panel.add(notifyButton);

        // Recommendation Button
        JButton recommendationButton = new JButton("Find Similar Games");
        recommendationButton.setFont(secondaryFont);
        recommendationButton.setBackground(buttonColor);
        recommendationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        recommendationButton.addActionListener(e -> {
            recommendationController.execute(game);
        });
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(recommendationButton);

        JFrame frame = new JFrame(game.getTitle());
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

        JButton addToWishlistButton = new JButton("Add To Wishlist");
        addToWishlistButton.setFont(secondaryFont);
        addToWishlistButton.setBackground(buttonColor);
        addToWishlistButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToWishlistButton.addActionListener(e -> {
            gameController.addToWishlist(game.getTitle(), game.getSalePrice(), game.getNormalPrice(),
                    game.getIsOnSale(), game.getSavings(), game.getMetacriticScore(), game.getSteamRatingText(),
                    game.getSteamRatingPercent(), game.getSteamRatingCount(), game.getDealRating(), game.getThumb(),
                    game.getGameID(), game.getStoreName());
        });
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(addToWishlistButton);
        SwingUtilities.getWindowAncestor(panel).pack();
    }

    public String getViewName(){
        return viewName;
    }

    public void setGameController(GameController controller) {
        gameController = controller;
    }

    public void setRecommendationController(RecommendationController controller) {
        recommendationController = controller;
    }
}
