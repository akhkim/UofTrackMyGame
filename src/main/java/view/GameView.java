package view;

import javax.swing.*;
import java.awt.*;

//TEMPORARY FILE FOR WISHLISTVIEW change it if needed
public class GameView {
    private JFrame frame;

    public GameView(String gameTitle) {
        setupUI(gameTitle);
    }

    private void setupUI(String gameTitle) {
        frame = new JFrame(gameTitle);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Game Details for: " + gameTitle);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel);

        // Add more details as needed for the game
        JLabel ratingLabel = new JLabel("Rating: [Add Rating]");
        JLabel priceLabel = new JLabel("Price Threshold: [Add Threshold]");

        panel.add(ratingLabel);
        panel.add(priceLabel);

        frame.add(panel);
        frame.setVisible(true);
    }
}
