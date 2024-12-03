package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import interface_adapter.wishlist.*;
import interface_adapter.results.*;
import entity.Game;

public class WishlistView extends JPanel {
    private final String viewName = "wishlist";
    private JPanel listPanel;
    private WishlistViewModel viewModel;
    private WishlistController controller;
    private ResultsController resultsController;

    public WishlistView(WishlistViewModel viewModel, WishlistController controller, ResultsController resultsController) {
        this.viewModel = viewModel;
        this.controller = controller;
        this.resultsController = resultsController;
        setupUI();
        updateView();  // Initial view setup
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("My Wishlist", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        // Panel for Game List
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS)); // Vertical layout

        JScrollPane scrollPane = new JScrollPane(listPanel);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateView() {
        SwingUtilities.invokeLater(() -> {
            listPanel.removeAll();  // Clear the existing game list

            // Retrieve game data from ViewModel
            ArrayList<Game> games = viewModel.getGames();

            // Create new UI components based on the updated list
            for (Game game : games) {
                JPanel gamePanel = new JPanel();
                gamePanel.setLayout(new BorderLayout());
                gamePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                gamePanel.setPreferredSize(new Dimension(350, 50));

                JLabel titleLabel = new JLabel(game.getTitle());

                JButton removeButton = new JButton("Remove");
                removeButton.addActionListener(e -> {
                    controller.removeGame(game.getGameID());  // Remove the game from wishlist
                    updateView();  // Refresh UI after removing
                });

                gamePanel.add(titleLabel, BorderLayout.WEST);
                gamePanel.add(removeButton, BorderLayout.EAST);

                gamePanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // Navigate to the game page using ResultsController
                        resultsController.execute(game);
                    }
                });

                listPanel.add(gamePanel);  // Add new game panel to the list
            }

            // After modifying the list, revalidate and repaint to ensure UI refresh
            revalidate();
            repaint();
        });
    }


    public String getViewName() {
        return viewName;
    }
}
