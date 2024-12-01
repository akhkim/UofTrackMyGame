package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.game.GameController;
import interface_adapter.game.GameState;
import interface_adapter.game.GameViewModel;

//TEMPORARY FILE FOR WISHLISTVIEW change it if needed
public class GameView {
    private JFrame frame;

    public GameView(String gameTitle, String salePrice, String metacriticScore, String dealRating) {
        setupUI(gameTitle, salePrice, metacriticScore, dealRating);
    }

    private void setupUI(String gameTitle, String salePrice, String metacriticScore, String dealRating) {
        JFrame frame = new JFrame("Game Details");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 300);

        // Panel with vertical layout for game details
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Game Title
        JLabel titleLabel = new JLabel("Game Title: " + gameTitle);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Sale Price
        JLabel priceLabel = new JLabel("Price: $" + salePrice);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(priceLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Metacritic Score
        JLabel metacriticLabel = new JLabel("Metacritic Score: " + metacriticScore);
        metacriticLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        metacriticLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(metacriticLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Deal Rating
        JLabel dealRatingLabel = new JLabel("Deal Rating: " + dealRating);
        dealRatingLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        dealRatingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(dealRatingLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Text Field for Tracking Price
        JLabel trackingPriceLabel = new JLabel("Enter tracking price:");
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
        notifyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        notifyButton.addActionListener(e -> {
            String trackingPrice = trackingPriceField.getText();
            String email = emailField.getText();
            JOptionPane.showMessageDialog(frame,
                    "Notification set for price: $" + trackingPrice + " to email: " + email,
                    "Notification Set",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        panel.add(notifyButton);

        // Add the panel to the frame
        frame.add(panel);
        frame.setVisible(true);
    }
}
