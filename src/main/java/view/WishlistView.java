package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import interface_adapter.wishlist.*;

public class WishlistView extends JPanel {
    private final String viewName = "wishlist";
    private JPanel listPanel;
    private JFrame frame;
    private WishlistViewModel viewModel;
    private WishlistController controller;

    public WishlistView(WishlistViewModel viewModel, WishlistController controller) {
        this.viewModel = viewModel;
        this.controller = controller;
        setupUI();
        updateView();
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

        // Add Game Panel
        JPanel addPanel = new JPanel();
        JTextField gameInput = new JTextField(20);
        JButton addButton = new JButton("Add Game");

        addPanel.add(gameInput);
        addPanel.add(addButton);
        add(addPanel, BorderLayout.SOUTH);

        // Add Game Button Listener
        addButton.addActionListener(e -> {
            String gameTitle = gameInput.getText().trim();
            if (!gameTitle.isEmpty()) {
                controller.addGame(gameTitle);
                updateView();
                gameInput.setText(""); // Clear input field
            }
        });
    }

    public void updateView() {
        listPanel.removeAll();

        // Retrieve game data from ViewModel
        java.util.ArrayList<String> gameTitles = viewModel.getGameTitles();

        for (String title : gameTitles) {
            JPanel gamePanel = new JPanel();
            gamePanel.setLayout(new BorderLayout());
            gamePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            gamePanel.setPreferredSize(new Dimension(350, 50));

            JLabel titleLabel = new JLabel(title);

            JButton removeButton = new JButton("Remove");
            removeButton.addActionListener(e -> {
                controller.removeGame(title);
                updateView(); // Refresh UI
            });

            gamePanel.add(titleLabel, BorderLayout.WEST);
            gamePanel.add(removeButton, BorderLayout.EAST);

            gamePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JOptionPane.showMessageDialog(null, "Clicked on: " + title);
                }
            });

            listPanel.add(gamePanel);
        }

        // Use this to revalidate and repaint the current panel
        revalidate();
        repaint();
    }


    public String getViewName() {
        return viewName;
    }


}
