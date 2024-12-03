package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import interface_adapter.wishlist.*;

public class WishlistView extends JPanel {
    private final String viewName = "wishlist";
    private JPanel listPanel;
    private WishlistViewModel viewModel;
    private WishlistController controller;

    public WishlistView(WishlistViewModel viewModel, WishlistController controller) {
        this.viewModel = viewModel;
        this.controller = controller;
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
            java.util.ArrayList<String> gameTitles = viewModel.getGameTitles();

            // Create new UI components based on the updated list
            for (String title : gameTitles) {
                JPanel gamePanel = new JPanel();
                gamePanel.setLayout(new BorderLayout());
                gamePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                gamePanel.setPreferredSize(new Dimension(350, 50));

                JLabel titleLabel = new JLabel(title);

                JButton removeButton = new JButton("Remove");
                removeButton.addActionListener(e -> {
                    controller.removeGame(title);  // Remove the game from wishlist
                    updateView();  // Refresh UI after removing
                });

                gamePanel.add(titleLabel, BorderLayout.WEST);
                gamePanel.add(removeButton, BorderLayout.EAST);

                gamePanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JOptionPane.showMessageDialog(null, "Clicked on: " + title);
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
