package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView {

    public static void main(String[] args) {
        // Run the UI creation on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Create the main frame
            JFrame frame = new JFrame("Game Information");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 200);
            frame.setLocationRelativeTo(null); // Center the window on the screen

            // Create a panel with BoxLayout to hold the information vertically
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            // Game information (replace these with actual data)
            String gameTitle = "Game Title: The Legend of Code";
            String gamePrice = "Price: $29.99";
            String metaCriticScore = "MetaCritic Score: 85";
            String dealRating = "Deal Rating: Excellent";

            // Create labels for each piece of information
            JLabel titleLabel = new JLabel(gameTitle, JLabel.CENTER);
            JLabel priceLabel = new JLabel(gamePrice, JLabel.CENTER);
            JLabel metaCriticLabel = new JLabel(metaCriticScore, JLabel.CENTER);
            JLabel dealRatingLabel = new JLabel(dealRating, JLabel.CENTER);

            // Center align each label and add it to the panel
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            metaCriticLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            dealRatingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            panel.add(titleLabel);
            panel.add(Box.createVerticalStrut(5));  // Adds 2 pixels of vertical space
            panel.add(priceLabel);
            panel.add(Box.createVerticalStrut(5));
            panel.add(metaCriticLabel);
            panel.add(Box.createVerticalStrut(5));
            panel.add(dealRatingLabel);
            panel.add(Box.createVerticalStrut(10));

            // Create a sub-panel for the price input, email input, and button
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new FlowLayout());

            // Text field for entering a price
            JTextField priceInput = new JTextField(10);
            inputPanel.add(new JLabel("Enter Price: "));
            inputPanel.add(priceInput);

            // Text field for entering an email
            JTextField emailInput = new JTextField(15);
            inputPanel.add(new JLabel("Enter Email: "));
            inputPanel.add(emailInput);

            // "Notify Me" button
            JButton notifyButton = new JButton("Notify Me");
            inputPanel.add(notifyButton);

            // Action listener for the button
            notifyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String enteredPrice = priceInput.getText();
                    String enteredEmail = emailInput.getText();

                    if (!enteredPrice.isEmpty() && !enteredEmail.isEmpty()) {
                        JOptionPane.showMessageDialog(frame,
                                "You will be notified at " + enteredEmail + " when the price is " + enteredPrice,
                                "Notification Set",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame,
                                "Please enter both a valid price and email.",
                                "Invalid Input",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            // Center align the input panel and add it to the main panel
            inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(inputPanel);

            // Add the main panel to the frame
            frame.add(panel);

            // Make the frame visible
            frame.setVisible(true);
        });
    }
}
