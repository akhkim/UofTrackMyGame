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

public class GameView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Game Information";

    private final GameViewModel gameViewModel;
    private final JTextField thresholdPriceInputField = new JTextField(15);
    private final JTextField emailInputField = new JTextField(15);
    private final GameController gameController;

    private final JButton addToWishlist;

    // Labels to display game information
    private final JLabel gameTitle;
    private final JLabel gamePrice;
    private final JLabel metaScore;
    private final JLabel dealRating;

    public GameView(GameViewModel gameViewModel, GameController gameController) {
        this.gameController = gameController;
        this.gameViewModel = gameViewModel;
        gameViewModel.addPropertyChangeListener(this);

        // Set up the main panel layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create title label with center alignment
        JLabel title = new JLabel(GameViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initialize game information labels
        gameTitle = new JLabel("Game: " + gameViewModel.getState().getTitle());
        gamePrice = new JLabel("Price: $" + gameViewModel.getState().getSalePrice());
        metaScore = new JLabel("Metacritic Score: " + gameViewModel.getState().getMetacriticScore());
        dealRating = new JLabel("Deal Rating: " + gameViewModel.getState().getDealRating());

        // Create input panels with labels
        JPanel pricePanel = new JPanel();
        pricePanel.add(new JLabel("Enter Price: "));
        pricePanel.add(thresholdPriceInputField);

        JPanel emailPanel = new JPanel();
        emailPanel.add(new JLabel("Enter Email: "));
        emailPanel.add(emailInputField);

        // Create wishlist button
        addToWishlist = new JButton("Add to Wishlist");
        addToWishlist.addActionListener(this);

        // Add components to the main panel
        add(Box.createVerticalStrut(10));
        add(title);
        add(Box.createVerticalStrut(20));
        add(gameTitle);
        add(gamePrice);
        add(metaScore);
        add(dealRating);
        add(Box.createVerticalStrut(20));
        add(pricePanel);
        add(emailPanel);
        add(Box.createVerticalStrut(10));
        add(addToWishlist);
        add(Box.createVerticalStrut(10));

        // Set alignments
        for (Component component : getComponents()) {
            component.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addToWishlist)) {
            GameState currentState = gameViewModel.getState();

            try {
                String thresholdPrice = thresholdPriceInputField.getText();
                String email = emailInputField.getText();

                gameController.addToWishlist(thresholdPrice, email);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid price",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GameState state = (GameState) evt.getNewValue();
        if (state != null) {
            gameTitle.setText("Game: " + state.getTitle());
            gamePrice.setText("Price: $" + state.getSalePrice());
            metaScore.setText("Metacritic Score: " + state.getMetacriticScore());
            dealRating.setText("Deal Rating: " + state.getDealRating());
        }
    }

    public String getViewName() {
        return viewName;
    }
}